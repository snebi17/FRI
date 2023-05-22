<?php

require_once "controllers/InitDB.php";
require_once "utils/ViewHelper.php";
require_once "controllers/UserController.php";
require_once "controllers/DocumentController.php";

define("BASE_URL", $_SERVER["SCRIPT_NAME"] . "/");
define("CSS_URL", rtrim($_SERVER["SCRIPT_NAME"], "index.php") . "static/css/");
define("IMAGES_URL", rtrim($_SERVER["SCRIPT_NAME"], "index.php") . "static/images/");

$path = isset($_SERVER["PATH_INFO"]) ? trim($_SERVER["PATH_INFO"], "/") : "";

$urls = [
    "login" => function() {
       if ($_SERVER["REQUEST_METHOD"] == "POST") {
            UserController::login();
       } else {
           UserController::showLoginForm();
       }
    },
    "logout" => function() {
        AuthUtil::logout();
        ViewHelper::redirect(BASE_URL . "login");
    },
    "register" => function() {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            UserController::register();
       } else {
            UserController::showRegisterForm();
       }
    },
    "home" => function() {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            
        } else {
            ViewHelper::render("views/shared/home.php");
        }
    },
    "documents" => function() {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            DocumentController::create();
        } else {
            $vars = DocumentController::getAll();
            ViewHelper::render("views/shared/documents.php", $vars);
        }
    },
    "search" => function() {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            UserController::search();
        }
    },
    "dashboard" => function() {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            UserController::update();
        } else {
            UserController::showDashboard();
        }
        // if (AuthUtil::authorize()) {
            
        // } else {
        //     ViewHelper::redirect(BASE_URL . "login");
        // }

    },
    "" => function() {
        ViewHelper::redirect(BASE_URL . "login");
    },
];

try {
    if (isset($urls[$path])) {
       $urls[$path]();
    } else {
        echo "No controller for '$path'";
    }
} catch (Exception $e) {
    echo "An error occurred: <pre>$e</pre>";
    // ViewHelper::error404();
} 

?>
