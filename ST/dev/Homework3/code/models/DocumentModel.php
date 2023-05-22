<?php

require_once "controllers/InitDB.php";
require_once "utils/AuthUtil.php";

class DocumentModel {
    public static function getAll() {
        $conn = InitDB::getInstance();

    }

    public static function get($id) {
        $conn = InitDB::getInstance();

    }

    public static function getByType($type) {

    }

    public static function create() {

    }

    public static function update($id) {

    }

    public static function delete($id) {
        $conn = InitDB::getInstance();
        
    }
}

?>