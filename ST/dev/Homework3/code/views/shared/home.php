<?php 
    include("views/shared/session.php");
?>
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
    <?php include("views/shared/header.php"); ?>
    <div class="container p-5" style="height: 80vh">
        <div class="row h-100">
            <div class="col-7">
                <div class="card h-100">
                    <div class="card-header">
                        <h6 class="m-0 p-0">Statistika</h6>
                    </div>
                    <div class="card-body">
                        <canvas id="myChart"></canvas>
                    </div>
                </div>
            </div>
            <div class="col-5 d-flex flex-column justify-content-between">
                <div class="row h-50 mb-2">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="m-0 p-0">Organiziraj dokumente</h6>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Dodaj, uredi ali odstrani dokumente</h4>
                            <p class="card-text">
                                Organizator dokumentov je uporabna aplikacija, ki vam omogoča urejanje, 
                                dodajanje in brisanje dokumentov na enostaven način. 
                                Ta aplikacija vam pomaga pri učinkovitem upravljanju vaših digitalnih dokumentov 
                                in vam omogoča, da ohranite preglednost vseh vaših pomembnih informacij.
                            </p>
                        </div>
                        <div class="card-footer d-flex justify-content-end">
                            <a href="<?= BASE_URL . "documents" ?>" class="btn btn-secondary">Pojdi</a>
                        </div>
                    </div>
                </div>
                <div class="row h-50 mt-2">
                    <div class="card h-100">
                        <div class="card-header">
                            <h6 class="m-0 p-0">Podatki o uporabniku</h6>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Uredi svoje podatke</h4>
                            <p class="card-text">
                                Ta stran vam omogoča, da prilagajate svoje osebne podatke in nastavitve glede vašega računa.
                                Na tej strani lahko spremenite svoje uporabniško ime, geslo, e-poštni naslov in druge podatke, ki so povezani z vašim računom.
                            </p>
                        </div>
                        <div class="card-footer d-flex justify-content-end">
                            <a href="<?= BASE_URL . "dashboard"?>" class="btn btn-secondary">Uredi</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <?php include("views/shared/footer.php"); ?>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        var xValues = ["Italy", "France", "Spain", "USA", "Argentina"];
        var yValues = [55, 49, 44, 24, 15];
        var barColors = ["red", "green", "blue", "orange", "brown"];

        const ctx = document.getElementById("myChart");
        new Chart(ctx, {
            type: "pie",
            data: {
                labels: ["Italy", "France", "Spain", "USA", "Argentina"],
                datasets: [{
                    label: "Wine Production by Country",
                    backgroundColor: ["red", "green", "blue", "orange", "brown"],
                    data: [55, 49, 44, 24, 15]
                }]
            }
        });
    </script>
    <!-- <script>
        const ctx = document.getElementById('myChart');

        new Chart(ctx, {
            type: 'bar',
            data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            }]
            },
            options: {
            scales: {
                y: {
                beginAtZero: true
                }
            }
            }
        });
    </script> -->
</body>
</html>