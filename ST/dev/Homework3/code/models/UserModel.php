<?php

require_once "controllers/InitDB.php";
require_once "utils/AuthUtil.php";

class UserModel {
    public static function getAll() {
        $conn = InitDB::getInstance();
        $result = $conn->query("SELECT * FROM user");
        return $result->fetch_assoc();
    }

    public static function get($id) {
        $conn = InitDB::getInstance();
        $result = $conn->query("SELECT * FROM user WHERE id = $id");
        return $result->fetch_assoc();
    }

    public static function getRoleById($id) {
        $conn = InitDB::getInstance();
        $result = $conn->query("SELECT role_id FROM user_roles WHERE user_id = $id");
        return $result->fetch_assoc();
    }

    private static function user_exists($conn, $username, $email) {
        $stmt = $conn->prepare("SELECT count(*) FROM user WHERE username = ? OR email = ?");
        $stmt->bind_param("ss", $username, $email);
        $stmt->execute();
        $count = 0;
        $stmt->bind_result($count);
        $stmt->fetch();

        return $count > 0;
    }

    public static function register($username, $password, $fullname, $email) {
        $conn = InitDB::getInstance();

        if (!self::user_exists($conn, $username, $email)) {
            $password = password_hash($password, PASSWORD_DEFAULT);

            $stmt = $conn->prepare("INSERT INTO user (username, password, fullname, email) VALUES (?, ?, ?, ?)");
            $stmt->bind_param("ssss", $username, $password, $fullname, $email);

            if (!$stmt->execute()) return false;
            
            $result = $conn->query("SELECT id FROM user ORDER BY id DESC LIMIT 1");
            $row = $result->fetch_assoc();
            $id = intval($row["id"]);

            $conn->query("INSERT INTO user_roles (user_id, role_id) VALUES ($id, 2)");

            return true;
        }

        return false;
    }

    public static function login($username, $password) {
        $conn = InitDB::getInstance();
        $stmt = $conn->prepare("SELECT * FROM user WHERE username = ?");
        $stmt->bind_param("s", $username);
        
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows == 1) {
            $user = $result->fetch_assoc();
            if (password_verify($password, $user["password"])) {
                $isAdmin = intval(self::getRoleById($user["id"])["role_id"]) == 1; 
                AuthUtil::login($user, $isAdmin);
                return true;
            }
        }

        return false;
    }

    public static function logout() {
        AuthUtil::logout();
    }

    public static function search($query) {
        $query = "%" . $query . "%";
        $conn = InitDB::getInstance();
        $stmt = $conn->prepare("SELECT * FROM user WHERE username LIKE ? OR fullname LIKE ?");
        $stmt->bind_param("ss", $query, $query);
        $result = $stmt->get_result();
        return $result->fetch_all(MYSQLI_ASSOC);
    }

    public static function update($id, $username, $password, $fullname, $email) {
        $conn = InitDB::getInstance();
        $stmt = $conn->prepare("UPDATE user SET username = ?, password = ?, fullname = ?, email = ? WHERE id = $id");
        $stmt->bind_param("ssss", $username, $password, $fullname, $email);
        return $stmt->execute();
    }

    public static function delete($id) {
        $conn = InitDB::getInstance();
        return $conn->query("DELETE FROM user WHERE id = $id");
    }

}

?>