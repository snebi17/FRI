<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<?= CSS_URL . "bootstrap.min.css" ?>">
    <title>Home</title>
</head>
<body>
    <?php include("views/shared/navigation-menu.php"); ?>
    <div class="container" style="height: 90vh">
        <div class="row justify-content-center">
            <h1><?php var_dump($_SESSION["user"]); ?></h1>
        </div>
    </div>
</body>
</html>