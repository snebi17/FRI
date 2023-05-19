<?php 
    $isLoggedIn = isset($_SESSION["user"]);
    $isAdmin = $isLoggedIn && array_key_exists("admin", $_SESSION["user"]);
?>
<div class="container-fluid" style="height: 7vh">
    <div class="row align-items-center h-100">
        <div class="col-1">
            <h4 class="m-0 p-0">DocOrg</h4>
        </div>
        <div class="col-5">
            <ul class="list-inline m-0">
                <li class="list-inline-item">Home</li>
                <li class="list-inline-item">Documents</li>
            </ul>
        </div>
        <div class="col-6 d-flex justify-content-end">
            <a class="btn btn-primary" href="<?= BASE_URL . ($isLoggedIn ? "logout" : "login") ?>">
                <?= $isLoggedIn ? "Logout" : "Login" ?>
            </a>
        </div>
    </div>
</div>