<?php 
    $isLoggedIn = isset($_SESSION["user"]);
    $isAdmin = isset($_SESSION["user"]["isAdmin"]) ? boolval($_SESSION["user"]["isAdmin"]) : false;

?>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<div class="container-fluid bg-dark" style="height: 10vh">
    <div class="row align-items-center h-100">
        <div class="col-1">
            <h4 class="m-0 text-white">DocOrg</h4>
        </div>
        <div class="col-5">
            <ul class="list-inline mt-3 d-flex align-items-center">
                <li class="list-inline-item">
                    <a href="<?= BASE_URL . "home"?>" class="text-white">Domov</a>
                </li>
                <li class="list-inline-item mx-4">
                    <a href="<?= BASE_URL . "documents"?>" class="text-white">Dokumenti</a>
                </li>
                <li class="list-inline-item">
                    <?php ($isAdmin) ? include("views/admin/search.php") : ""?>
                </li>
            </ul>
        </div>
        <div class="col-6 d-flex justify-content-end">
            <a class="btn btn-light" href="<?= BASE_URL . ($isLoggedIn ? "logout" : "login") ?>">
                <?= $isLoggedIn ? "Odjava" : "Prijava" ?>
            </a>
        </div>
    </div>
</div>