use std::{
    fs,
    io::{prelude::*, BufReader},
    net::{TcpListener, TcpStream},
};

fn main() {
    let listener = TcpListener::bind("127.0.0.1:7878").unwrap();

    for stream in listener.incoming() {
        let stream = stream.unwrap();
        handle_connection(stream);
    }
}

fn handle_connection(mut stream:TcpStream) {
    let buf_reader = BufReader::new(&mut stream);
    let req_line = buf_reader.lines().next().unwrap().unwrap();

    if req_line == "GET / HTTP/1.1" {
        let status_line = "HTTP/1.1 200 OK\r\n\r\n";
        let contents = fs::read_to_string("./static/hello.html").unwrap();
        let length = contents.len();

        let response = format!("{status_line}\r\nContent-Length: {length}\r\n\r\n{contents}");

        stream.write_all(response.as_bytes()).unwrap();
    } 
    else if req_line == "POST /post HTTP/1.1" {
        let status_line = "HTTP/1.1 200 OK\r\n\r\n";
        let contents = fs::read_to_string("./static/created.html").unwrap();
        let length = contents.len();

        let response = format!("{status_line}\r\nContent-Length: {length}\r\n\r\n{contents}");

        stream.write_all(response.as_bytes()).unwrap();
    }
    else if req_line == "PATCH /patch HTTP/1.1" {
        let status_line = "HTTP/1.1 200 OK\r\n\r\n";
        let contents = fs::read_to_string("./static/update.html").unwrap();
        let length = contents.len();

        let response = format!("{status_line}\r\nContent-Length: {length}\r\n\r\n{contents}");

        stream.write_all(response.as_bytes()).unwrap();
    }
    else if req_line == "DELETE /delete HTTP/1.1" {
        let status_line = "HTTP/1.1 200 OK\r\n\r\n";
        let contents = fs::read_to_string("./static/delete.html").unwrap();
        let length = contents.len();

        let response = format!("{status_line}\r\nContent-Length: {length}\r\n\r\n{contents}");

        stream.write_all(response.as_bytes()).unwrap();
    } else {
        let status_line = "HTTP/1.1 404 NOT FOUND";
        let contents = fs::read_to_string("./static/404.html").unwrap();
        let length = contents.len();

        let response = format!("{status_line}\r\nContent-Length: {length}\r\n\r\n{contents}");

        stream.write_all(response.as_bytes()).unwrap();
    }
}
