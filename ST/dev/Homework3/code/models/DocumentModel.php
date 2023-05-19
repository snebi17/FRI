<?php

require_once "controllers/InitDB.php";
require_once "utils/AuthUtil.php";

class DocumentModel {
    public static function getAll() {
        $dbh = InitDB::getInstance();

    }

    public static function get($id) {
        $dbh = InitDB::getInstance();

    }

    public static function register($user) {
        $dbh = InitDB::getInstance();
        
    }

    public static function login($username, $password) {
        $dbh = InitDB::getInstance();

    }

    public static function logout() {
        $dbh = InitDB::getInstance();

    }

    public static function delete($id) {
        $dbh = InitDB::getInstance();
        
    }
}

?>