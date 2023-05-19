<?php


class InitDB {
    private static $host = "localhost";
    private static $user = "root";
    private static $password = "";
    private static $schema = "organizer";
    private static $instance = null;

    private function __construct() {

    }

    private function __clone() {

    }

    public static function getInstance() {
        if (!self::$instance) {
            self::$instance = new mysqli(self::$host, self::$user, self::$password, self::$schema);
        }

        return self::$instance;
    }
}

?>