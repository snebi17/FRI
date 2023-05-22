<?php

require_once "InitDB.php";
require_once "models/DocumentModel.php";

class DocumentController {
    public static function create() {
        DocumentModel::create();
    }

    public static function getAll() {

    }

    public static function get($id) {

    }
}

?>