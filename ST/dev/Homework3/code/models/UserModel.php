<?php

require_once "controllers/InitDB.php";
require_once "utils/AuthUtil.php";

class UserModel {
    public static function getAll() {
        $dbh = InitDB::getInstance();

    }

    public static function get($id) {
        $conn = InitDB::getInstance();
        $stmt = $conn->prepare("SELECT * FROM user WHERE id = ?");
        $stmt->bind_param("d", $id);

        $stmt->execute();
        $result = $stmt->get_result();
        return $result->fetch_assoc();
    }

    public static function register() {
        $conn = InitDB::getInstance();
        $username = strval($_POST["username"]);
        $email = strval($_POST["email"]);

        $stmt = $conn->prepare("SELECT COUNT(id) FROM user WHERE username = ? OR email = ?");
        $stmt->bind_param("ss", $username, $email);

        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows == 1) {
            $password = password_hash(strval($_POST["password"]), PASSWORD_DEFAULT);
            $fullname = strval($_POST["fullname"]);

            $stmt = $conn->prepare("INSERT INTO user (username, password, fullname, email) VALUES (?, ?, ?, ?)");
            $stmt->bind_param("ssss", $username, $password, $fullname, $email);

            $stmt->execute();

            if (is_null($stmt->insert_id)) {
                return $stmt;
            }

            $stmt = $conn->prepare("INSERT INTO user_roles (user_id, role_id) VALUES (?, 1)");
            $stmt->bind_param("d", $conn->insert_id);
            $stmt->execute();

            return true;
        }

        return false;
    }

    public static function login() {
        $conn = InitDB::getInstance();
        $stmt = $conn->prepare("SELECT * FROM user WHERE username = ?");
        $username = strval($_POST["username"]);
        $stmt->bind_param("s", $username);
        
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows == 1) {
            $user = $result->fetch_assoc();
            $password = strval($_POST["password"]);
            if (password_verify($password, $user["password"])) {
                AuthUtil::login($user);
                return true;
            }
        }
        return false;
    }

    public static function logout() {
        AuthUtil::logout();
    }

    public static function delete($id) {
        $conn = InitDB::getInstance();
        $stmt = $conn->prepare("DELETE FROM user WHERE id = ?");
        $stmt->bind_param("d", $id);

        $stmt->execute();
        $result = $stmt->get_result();

        return $result->num_rows == 1;
    }

    public static function update($id) {
        
    }
}

?>