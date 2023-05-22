<?php

require_once "models/UserModel.php";
require_once "utils/ViewHelper.php";


class UserController {
    public static function showLoginForm() {
        ViewHelper::render("views/user/login-form.php");
    }
    public static function login() {
        $validData = isset($_POST["username"]) &&
                     !empty($_POST["username"]) &&
                     isset($_POST["password"]) &&
                     !empty($_POST["password"]);

        if (!$validData) {
            $vars = ["errorMessage" => "Izpolniti morate vsa polja!"];
            ViewHelper::render("views/user/login-form.php", $vars);
            return;
        }

        if (!UserModel::login(strval($_POST["username"]), strval($_POST["password"]))) {
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
        if ($vars = self::validateInput()) {
            ViewHelper::render("views/user/register-form.php", $vars);
            return;
        }

        if (!UserModel::register(strval($_POST["username"]), strval($_POST["password"]), strval($_POST["fullname"]), strval($_POST["email"]))) {
            $vars = ["errorMessage" => "Uporabnik z uporabniškim imenom ali e-pošto že obstaja!"];
        } else {
            $vars = ["successMessage" => "Uspešno ste se registrirali!"];
        }

        ViewHelper::render("views/user/register-form.php", $vars);
    }
    public static function showDashboard() {
        ViewHelper::render("views/user/dashboard.php");
    }
    public static function update() {
        if ($vars = self::validateInput("dashboard")) {
            ViewHelper::render("views/user/dashboard.php", $vars);
            return;
        }
        
        if (!UserModel::update(intval($_POST["id"]), strval($_POST["username"]), strval($_POST["password"]), strval($_POST["fullname"]), strval($_POST["email"]))) {
            $vars = ["errorMessage" => "Napaka pri posodabljanju profila!"];
        } else {
            $vars = ["successMessage" => "Profil je bil posodobljen!"];
        }

        ViewHelper::render("views/user/dashboard.php", $vars);
    }
    public static function search() {
        if (isset($_POST["query"]) && !empty($_POST["query"])) {
            $result = UserModel::search(strval($_POST["query"]));
            ViewHelper::render("views/admin/search.php", $result);
        }
    }
    private static function validateInput() {
        $validData = isset($_POST["username"]) && !empty($_POST["username"]) &&
                     isset($_POST["password"]) && !empty($_POST["password"]) &&
                     isset($_POST["repeat"]) && !empty($_POST["repeat"]) &&
                     isset($_POST["fullname"]) && !empty($_POST["fullname"]) &&
                     isset($_POST["email"]) && !empty($_POST["email"]);

        if (!$validData) {
            return ["errorMessage" => "Izpolniti morate vsa polja označena z *!"];
        }

        $matchesPattern = preg_match("/^[a-zA-Z][a-zA-Z0-9-_]*$/", strval($_POST["username"])) &&
                          preg_match("/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[_!?\-\/])[a-zA-Z\d_!?\-\/]{8,}$/", strval($_POST["password"])) &&
                          preg_match("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/", strval($_POST["email"]));

        if (!$matchesPattern) {
            return ["errorMessage" => "Izpolnjena polja morajo ustrezati omejitvam!"];
        }

        $password = strval($_POST["password"]);
        $repeat = strval($_POST["repeat"]);
        if (strcmp($password, $repeat)) {
            return ["errorMessage" => "Gesli se morata ujemati!"];
        }
    }

}


?>