<?php

// require_once("controller/BookController.php");

# Define a global constant pointing to the URL of the application
define("BASE_URL", $_SERVER["SCRIPT_NAME"] . "/");

# Request path after /index.php/ with leading and trailing slashes removed
$path = isset($_SERVER["PATH_INFO"]) ? trim($_SERVER["PATH_INFO"], "/") : "";

# The mapping of URLs. It is a simple array where:
# - keys represent URLs
# - values represent functions to be called when a client requests that URL
$urls = [
    "login" => function () {
        AuthController::showLoginForm();
    },
    "book/add" => function () {
        if ($_SERVER["REQUEST_METHOD"] == "GET") {
            // BookController::showAddForm();
        } else {
            // BookController::add();
        }
    },
    "" => function () {
        ViewHelper::redirect(BASE_URL . "login");
    }

    // TODO: Add router entries for 1) search, 2) book/edit and 3) book/delete
];

# The actual router.
# Tries to invoke the function that is mapped for the given path
try {
    if (isset($urls[$path])) {
        # Great, the path is defined in the router
        $urls[$path](); // invokes function that calls the controller
    } else {
        # Fail, the path is not defined. Show an error message.
        echo "No controller for '$path'";
    }
} catch (Exception $e) {
    # Provisional: whenever there is an exception, display some info about it
    # this should be disabled in production
    ViewHelper::error400($e);
} finally {
    exit();
}
