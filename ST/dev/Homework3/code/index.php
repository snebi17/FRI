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
        ViewHelper::redirect(BASE_URL . "home");
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
            // DocumentController::getAll();
        } else {
            ViewHelper::render("views/shared/home.php");
        }
    },
    "dashboard" => function() {
        if (AuthUtil::authorize()) {
            if ($_SERVER["REQUEST_METHOD"] == "POST") {
                UserController::update();
            } else {
                UserController::showDashboard();
            }
        } else {
            ViewHelper::redirect(BASE_URL . "login");
        }

    },
    "terms-and-conditions" => function() {
        ViewHelper::render("views/shared/terms-and-conditions.php");
    },
>>>>>>> 89fb7a0ce770743759163b9b6ff15fc4c546884b
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
