<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>MarketPlace</title>
</head>
<body>
<div class="container" style="margin-top: 20px;">
    <h1>MarketPlace</h1>
    <h4>Online store in Europe</h4>
    <h2>Hello ${username}, your link to complete registration:</h2>
    <a href="http://localhost:8080/registration/confirm?token=${token}">/registration/confirm?token=${token}</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" type="application/javascript" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="../js/card_script.js" type="text/javascript"></script>
</body>
</html>