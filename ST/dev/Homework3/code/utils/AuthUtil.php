<?php

class AuthUtil {
    private static $loggedIn;

    public static function isLoggedIn() { return self::$loggedIn; }

    public static function login($user) {
        session_start();
        $_SESSION["user"] = $user;
        self::$loggedIn = true;
    }

    public static function logout() {
        session_start();
        unset($_SESSION["user"]);
        self::$loggedIn = false;
    }

    public static function authorize() {
        return self::$loggedIn;
    }
}

?>