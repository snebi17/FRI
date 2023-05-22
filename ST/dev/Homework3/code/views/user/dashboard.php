<?php include("views/shared/session.php"); ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<?= CSS_URL . "bootstrap.min.css" ?>">
    <title>Dashboard</title>
</head>
<body>
    <?php include("views/shared/header.php"); ?>
    <div class="container" style="height: 80vh">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-6">
                <div class="card">
                    <div class="card-header">
                        <h6 class="m-0 fst-italic">Podatki osebe <b><?= $_SESSION["user"]["fullname"] ?></b></h6>
                    </div>
                    <form action="<?= BASE_URL . "dashboard" ?>" method="post" id="form">
                        <div class="card-body">
                            <div class="form-group">
                                <label for="username">Uporabniško ime</label>
                                <input type="text" name="username" class="form-control" value="<?= $_SESSION["user"]["username"] ?>" placeholder="<?= $_SESSION["user"]["username"] ?>"/>
                            </div>
                            <div class="form-group">
                                <label for="password">Geslo</label>
                                <input type="password" name="password" value="<?= $_SESSION["user"]["password"] ?>" class="form-control"/>
                                <small class="form-text text-muted">Geslo naj vsebuje vsaj 8 znakov.</small>
                            </div>                    
                            <div class="form-group">
                                <label for="repeat">Geslo ponovno</label>
                                <input type="password" name="repeat" value="<?= $_SESSION["user"]["password"] ?>" class="form-control"/>
                                <small class="form-text text-muted">Gesli se morata ujemati.</small>
                            </div>
                            <div class="form-group">
                                <label for="fullname">Ime in priimek</label>
                                <input type="text" name="fullname" class="form-control" value="<?= $_SESSION["user"]["fullname"] ?>" placeholder="<?= $_SESSION["user"]["fullname"] ?>"/>
                            </div>
                            <div class="form-group">
                                <label for="email">Elektronska pošta</label>
                                <input type="email" name="email" class="form-control" value="<?= $_SESSION["user"]["email"] ?>" placeholder="<?= $_SESSION["user"]["email"] ?>"/>
                            </div>
                            <input type="hidden" value="<?= $_SESSION["user"]["id"] ?>">
                        </div>
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-primary">Posodobi</button>
                        </div>
                    </form>
                    <?php if (isset($successMessage)): ?>
                            <p class="alert-success mt-2 py-2 text-center rounded" role="alert">
                                <?= $successMessage ?>
                            </p>
                            <?php unset($successMessage); ?>
                    <?php endif; ?>
                    <?php if (isset($errorMessage)): ?>
                            <p class="alert-danger mt-2 py-2 text-center rounded" role="alert">
                                <?= $errorMessage ?>
                            </p>
                            <?php unset($errorMessage); ?>
                    <?php endif; ?>
                </div>
            </div>
        </div>
    </div>
    <?php include("views/shared/footer.php"); ?>
</body>
</html>