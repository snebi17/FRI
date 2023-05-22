<?php

class AuthUtil {
    private static $loggedIn;

    public static function isLoggedIn() { return self::$loggedIn; }

    public static function login($user, $isAdmin) {
        session_start();
        $_SESSION["user"] = $user;
        $_SESSION["user"]["isAdmin"] = $isAdmin;
        self::$loggedIn = true;
    }

    public static function logout() {
        session_start();
        unset($_SESSION["user"]);
        self::$loggedIn = false;
    }

    public static function authorize() {
        echo $loggedIn;
        return self::$loggedIn;
    }
}

?>