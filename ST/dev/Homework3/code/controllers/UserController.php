<?php

require_once "models/UserModel.php";
require_once("utils/ViewHelper.php");


class UserController {
    public static function showLoginForm() {
        ViewHelper::render("views/user/login-form.php");
    }

    public static function login() {
        $validData = isset($_POST["username"]) && !empty($_POST["username"]) &&
        isset($_POST["password"]) && !empty($_POST["password"]);

        if (!$validData) {
            $vars = ["errorMessage" => "Izpolniti morate vsa polja!"];
            ViewHelper::render("views/user/login-form.php", $vars);
            return;
        }

        if (!UserModel::login()) {
            $vars = ["errorMessage" => "Nepravilno uporabniško ime ali geslo!"];
            ViewHelper::render("views/user/login-form.php", $vars);
            return;
        }

        ViewHelper::redirect(BASE_URL . "home");
    }

    public static function showRegisterForm() {
        ViewHelper::render("views/user/register-form.php");
    }

    public static function register() {
        // Validate input
        $validData = isset($_POST["username"]) && !empty($_POST["username"]) &&
                     isset($_POST["password"]) && !empty($_POST["password"]) &&
                     isset($_POST["repeat"]) && !empty($_POST["repeat"]) &&
                     isset($_POST["fullname"]) && !empty($_POST["fullname"]) &&
                     isset($_POST["email"]) && !empty($_POST["email"]);

        if (!$validData) {
            $vars = ["errorMessage" => "Izpolniti morate vsa polja označena z *!"];
            ViewHelper::render("views/user/register-form.php", $vars);
            return;
        }

        // Input should match its pattern
        $matchesPattern = preg_match("/^[a-zA-Z][a-zA-Z0-9-_]*$/", strval($_POST["username"])) &&
                          preg_match("/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[_!?\-\/])[a-zA-Z\d_!?\-\/]{8,}$/", strval($_POST["password"])) &&
                          preg_match("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/", strval($_POST["email"]));


        if (!$matchesPattern) {
            $vars = ["errorMessage" => "Izpolnjena polja morajo ustrezati omejitvam!"];
            ViewHelper::render("views/user/register-form.php", $vars);
            return;
        }

        $password = strval($_POST["password"]);
        $repeat = strval($_POST["repeat"]);
        if (strcmp($password, $repeat)) {
            $vars = ["errorMessage" => "Gesli se morata ujemati!"];
            ViewHelper::render("views/user/register-form.php", $vars);
            return;
        }

        $userReturn = UserModel::register();

        var_dump($userReturn);

        if ($userReturn) {
            $vars = ["errorMessage" => "Uporabnik z uporabniškim imenom ali e-pošto že obstaja!"];
        } else {
            $vars = ["successMessage" => "Uspešno ste se registrirali!"];
        }
        // if (!UserModel::register()) {
        // } else {
        // }

        ViewHelper::render("views/user/register-form.php", $vars);
    }

    public static function showDashboard() {
        ViewHelper::render("views/shared/dashboard.php");
    }

    public static function update() {
        if (isset($_POST["id"]) && !empty($_POST["id"])) {
            UserModel::update($_POST["id"]);
        }
    }
}


?>