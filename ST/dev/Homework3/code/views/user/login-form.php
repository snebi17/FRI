<?php include("views/shared/session.php"); ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<?= CSS_URL . "bootstrap.min.css" ?>">
    <title>Login</title>
</head>
<body>
    <?php include("views/shared/header.php"); ?>
    <div class="container" style="height: 80vh">
        <div class="row h-100 justify-content-center align-items-center">
            <div class="col-6">
                <form action="<?= BASE_URL . "login" ?>" method="post" id="form">
                    <div class="form-group">
                        <label for="username">Uporabniško ime</label>
                        <input type="text" name="username" class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label for="password">Geslo</label>
                        <input type="password" name="password" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Prijava</button>
                        <small class="form-text text-muted">Še niste <a href="<?= BASE_URL . "register"?>">registrirani?</a></small>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <?php include("views/shared/footer.php"); ?>
    <script>
        $("button").on("click", function() {
            
        });

        $("#form").validate({
            rules: {
                name: "required",
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    password: true
                }
            },
            messages: {
                name: "Prosim vpišite vaše ime.",
                email: {
                    required: "Prosim vpišite vaš e-naslov.",
                    email: "E-naslov mora ustrezati obliki ime@domena.com"
                }
            },
            success: function(label) {

            }
        })
    </script>
</body>
</html>
