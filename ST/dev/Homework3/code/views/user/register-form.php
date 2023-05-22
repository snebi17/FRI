<?php include("views/shared/session.php"); ?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<?= CSS_URL . "bootstrap.min.css" ?>">
    <link rel="stylesheet" href="<?= CSS_URL . "style.css" ?>">
    <title>Register</title>
</head>
<body>
    <?php include("views/shared/header.php"); ?>
    <div class="container" style="height: 80vh">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-6">
                <form action="<?= BASE_URL . "register" ?>" method="post" id="form">
                    <div class="form-group">
                        <label for="username">Uporabniško ime*</label>
                        <input type="text" name="username" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label for="password">Geslo*</label>
                        <input type="password" name="password" class="form-control" required />
                        <small class="form-text text-muted">Geslo naj vsebuje vsaj 8 znakov.</small>
                    </div>                    
                    <div class="form-group">
                        <label for="repeat">Geslo ponovno*</label>
                        <input type="password" name="repeat" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="fullname">Ime in priimek*</label>
                        <input type="text" name="fullname" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Elektronska pošta*</label>
                        <input type="email" name="email" class="form-control" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Registracija</button>
                </form>
                <?php if (isset($successMessage)): ?>
                        <p class="alert-success mt-2 py-2 text-center rounded" role="alert">
                            <?= $successMessage ?> Kliknite za <a href="<?= BASE_URL . "login"?>">prijavo.</a>
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
    <?php include("views/shared/footer.php"); ?>
    <script>
        $(document).ready(function() {
            $("#form").validate({
                rules: {
                    username: "required",
                    password: {
                        required: true,
                        password: true
                    },
                    fullname: "required",
                    email: {
                        required: true,
                        email: true
                    }
                },
                messages: {
                    username: "Prosim vpišite vaše uporabniško ime.",
                    password: "Prosim vpišite vaše geslo.",
                    fullname: "Prosim vpišite vaše polno ime.",
                    email: {
                        required: "Prosim vpišite vaš e-naslov.",
                        email: "E-naslov mora ustrezati obliki ime@domena.com."
                    }
                },
                highlight: function(element) {
                    $(element).addClass('error');
                },
                unhighlight: function(element) {
                    $(element).removeClass('error');
                }
            });
        });
    </script>
</body>
</html>