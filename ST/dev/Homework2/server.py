"""An example of a simple HTTP server."""
import json
import mimetypes
import pickle
import socket
from os import listdir
from os.path import isdir, isfile, join
from urllib.parse import unquote_plus

# Pickle file for storing data
PICKLE_DB = "db.pkl"

# Directory containing www data
WWW_DATA = "www-data"

# Header template for a successful HTTP request
HEADER_RESPONSE_200 = """HTTP/1.1 200 OK\r
content-type: %s\r
content-length: %d\r
connection: Close\r
\r
"""

RESPONSE_301 = """HTTP/1.1 301 Moved permanently\r
location: %s
<!doctype html>
<h1>301 Moved Permanantly</h1>
"""

RESPONSE_400 = """HTTP/1.1 400 Bad request\r
<!doctype html>
<h1>400 Bad request</h1>
<p>Request doesn't match the server specification.</p>
"""

# Represents a table row that holds user data
TABLE_ROW = """
<tr>
    <td>%d</td>
    <td>%s</td>
    <td>%s</td>
</tr>
"""

# Template for a 404 (Not found) error
RESPONSE_404 = """HTTP/1.1 404 Not found\r
content-type: text/html\r
connection: Close\r
\r
<!doctype html>
<h1>404 Page not found</h1>
<p>Page cannot be found.</p>
"""

RESPONSE_405 = """HTTP/1.1 405 Method not allowed\r
<!doctype html>
<h1>405 Method not allowed</h1>
<p>Method is not allowed.</p>
"""

DIRECTORY_LISTING = """<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Directory listing: %s</title>

<h1>Contents of %s:</h1>

<ul>
%s
</ul>
"""

FILE_TEMPLATE = "  <li><a href='%s'>%s</li>"

def save_to_db(first, last):
    """Create a new user with given first and last name and store it into
    file-based database.

    For instance, save_to_db("Mick", "Jagger"), will create a new user
    "Mick Jagger" and also assign him a unique number.

    Do not modify this method."""

    existing = read_from_db()
    existing.append({
        "number": 1 if len(existing) == 0 else existing[-1]["number"] + 1,
        "first": first,
        "last": last
    })
    with open(PICKLE_DB, "wb") as handle:
        pickle.dump(existing, handle)


def read_from_db(criteria=None):
    """Read entries from the file-based DB subject to provided criteria

    Use this method to get users from the DB. The criteria parameters should
    either be omitted (returns all users) or be a dict that represents a query
    filter. For instance:
    - read_from_db({"number": 1}) will return a list of users with number 1
    - read_from_db({"first": "bob"}) will return a list of users whose first
    name is "bob".

    Do not modify this method."""
    if criteria is None:
        criteria = {}
    else:
        # remove empty criteria values
        for key in ("number", "first", "last"):
            if key in criteria and criteria[key] == "":
                del criteria[key]

        # cast number to int
        if "number" in criteria:
            criteria["number"] = int(criteria["number"])

    try:
        with open(PICKLE_DB, "rb") as handle:
            data = pickle.load(handle)

        filtered = []
        for entry in data:
            predicate = True

            for key, val in criteria.items():
                if val != entry[key]:
                    predicate = False

            if predicate:
                filtered.append(entry)

        return filtered
    except (IOError, EOFError):
        return []


def process_request(connection, address):
    """Process an incoming socket request.

    :param connection is a socket of the client
    :param address is a 2-tuple (address(str), port(int)) of the client
    """

    client = connection.makefile("wrb")
    line = client.readline().decode("utf-8")
    headers = parse_headers(client)

    try:
        method, uri, version = line.split()

        if version != "HTTP/1.1":
            client.write(bytes(RESPONSE_400, "utf-8"))

        if not headers["Host"]:
            client.write(bytes(RESPONSE_400, "utf-8"))

        if any(x in uri for x in ("/app-add", "/app-json", "/app-index")):
            serve_dynamic(method, uri, client)
        else:
            serve_static(method, uri, headers, client)

    except (ValueError, AssertionError) as e:
        print("Invalid request line %s -- %s" % (line, e))
    except FileNotFoundError:
        client.write(bytes(RESPONSE_404, "utf-8"))
    finally:
        client.close()


def serve_static(method, uri, headers, client):
    if method != "GET" and method != "POST":
        client.write(bytes(RESPONSE_405, "utf-8"))

    if method == "POST":
        if not headers["Content-Length"]:
            client.write(bytes(RESPONSE_400, "utf-8"))

    if uri[-1] != "/":
        if isfile(join(WWW_DATA, uri[1:])):
            with open(join(WWW_DATA, uri[1:]), "rb") as h:
                resource = h.read()
            mime, _ = mimetypes.guess_type(uri)
            response_headers = HEADER_RESPONSE_200 % (mime if mime is not None else "application/octet-stream", len(resource))
            client.write(bytes(response_headers, "utf-8"))
            client.write(resource)
        elif isdir(join(WWW_DATA, uri[1:])):
            client.write(bytes(RESPONSE_301 % (uri[1:] + "/"), "utf-8"))
        else:
            client.write(bytes(RESPONSE_404, "utf-8"))
    else:
        if "index.html" in listdir(join(WWW_DATA, uri[1:])):
            with open(join(WWW_DATA, join(uri[1:], "index.html")), "rb") as h:
                resource = h.read()
            response_headers = HEADER_RESPONSE_200 % ("text/html", len(resource))
            client.write(bytes(response_headers, "utf-8"))
            client.write(resource)
        else:
            contents = FILE_TEMPLATE % ("..", "..")
            for f in sorted(listdir(join(WWW_DATA, uri[1:]))):
                contents += "\n" + FILE_TEMPLATE % (f, f)
            dir_listing = DIRECTORY_LISTING % (uri, uri, contents)
            response_headers = HEADER_RESPONSE_200 % ("text/html", len(dir_listing))
            client.write(bytes(response_headers, "utf-8"))
            client.write(bytes(dir_listing, "utf-8"))

def serve_dynamic(method, uri, client):
    if "/app-add" in uri:
        if method != "POST":
            client.write(bytes(RESPONSE_405, "utf-8"))

        body = client.readline().decode("utf-8")
        first, last = unquote_plus(body).split("&")

        if not len(first) or not len(last):
            client.write(bytes(RESPONSE_400, "utf-8"))

        first = first.split("=")[1]
        last = last.split("=")[1]

        save_to_db(first, last)

        with open(join(WWW_DATA, "app_add.html"), "rb") as h:
            resource = h.read()

        response_headers = HEADER_RESPONSE_200 % ("text/html", len(resource))
        client.write(bytes(response_headers, "utf-8"))
        client.write(resource)

    if "/app-json" in uri:
        if method != "GET":
            client.write(bytes(RESPONSE_405, "utf-8"))

        split = uri.split("?")
        if len(split) == 1:
            students = read_from_db()

        else:
            params = split[1].split("&")
            criteria = {}
            for param in params:
                criteria[param.split("=")[0].strip()] = param.split("=")[1].strip()
            students = read_from_db(criteria)

        response_headers = HEADER_RESPONSE_200 % ("application/json", len(resource))
        client.write(bytes(response_headers, "utf-8"))
        client.write(json.dumps(students))

    if "/app-index" in uri:
        if method != "GET":
            client.write(bytes(RESPONSE_405, "utf-8"))

        split = uri.split("?")
        if len(split) == 1:
            students = read_from_db({})
        else:
            params = split[1].split("&")
            criteria = {}
            for param in params:
                criteria[param.split("=")[0].strip()] = param.split("=")[1].strip()
            students = read_from_db(criteria)

        response = ""
        for student in students:
            number = student["number"]
            first = student["first"]
            last = student["last"]
            response += TABLE_ROW % (
                int(number),
                first,
                last
            )
        response = response.strip()

        with open(join(WWW_DATA, "app_list.html"), "rb") as h:
            resource = h.read()

        resource = resource.replace(b"{{students}}", bytes(response, "utf-8"))
        response_headers = HEADER_RESPONSE_200 % ("text/html", len(resource))

        client.write(bytes(response_headers, "utf-8"))
        client.write(resource)

def parse_headers(client):
    headers = {}
    while True:
        line = client.readline().decode("utf-8").strip()
        if not line:
            return headers
        key, value = line.split(":", 1)
        headers[key.strip()] = value.strip()

def parse_body(client):
    body = {}
    client.readline()
    while True:
        line = client.readline().decode("utf-8").strip()
        if not line:
            return body
        key, value = line.split(":", 1)
        body[key.strip()] = value.strip()

def main(port):
    """Starts the server and waits for connections."""

    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind(("", port))
    server.listen(1)

    print("Listening on %d" % port)

    while True:
        connection, address = server.accept()
        print("[%s:%d] CONNECTED" % address)
        process_request(connection, address)
        connection.close()
        print("[%s:%d] DISCONNECTED" % address)


if __name__ == "__main__":
    main(8080)