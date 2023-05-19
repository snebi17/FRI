<?php

require_once "InitDB.php";
require_once "../models/User.php";

class UserController {
    public static function getAll() {
        $db = InitDB::getInstance();

    }

    public static function get($id) {

    }

    public static function register($user) {

    }

    public static function login($username, $password) {

    }

    public static function logout() {

    }

    public static function delete($id) {
        
    }
}


?>