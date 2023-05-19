<nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <span class="navbar-brand col-1 mb-0 h1">DocOrg</span>
        <ul class="navbar-nav col-9 me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
        </ul>
        <a class="btn btn-secondary" href="<?php BASE_URL . (isset($_SESSION["user"]) ? "logout" : "login") ?>">
            <?php /*echo(isset($_SESSION["user"]) ? "Logout" : "Login")*/ ?>
            <?php echo (BASE_URL . (isset($_SESSION["user"]) ? "logout" : "login")) ?>
        </a>
    </div>
</nav>