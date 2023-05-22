<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documents</title>
</head>
<body>
    <div class="container" style="height: 80vh">
        <?php foreach ($documents as $document): ?>
            <div class="row h-50">
                <?php for ($i = 0; $i < 3; $i++): ?>
                    <div class="col">
                        <div class="card h-100">
                            <div class="card-header">
                                <h5><?= $document["author"] ?></h5>
                            </div>
                            <div class="card-body">
                                <h4 class="card-title"><?= $document["title"] ?></h4>
                                <p class="card-text">
                                    <?= $document["content"] ?>
                                </p>
                            </div>
                            <div class="card-footer d-flex justify-content-end">
                                <button class="btn btn-secondary">Pojdi</button>
                            </div>
                        </div>
                    </div>
                <?php endfor; ?>
            </div>
        <?php endforeach; ?>
    </div>
</body>
</html>