<?php

require_once("ViewHelper.php");

class Router {
    private $instance = null;

    public static function navigate($url) {
        header("Location: " . $url);
    }

    public static getInstance() {
        if (!self::$instance) {
            
        }
    }

}
?>