<#import 'macro/base.ftlh' as base>
<@base.base>
        <div class="row">
            <div class="col">
                <h1>Admin Panel</h1>
            </div>
            <div class="col">
                <h3 style="text-align: right">Admin: ${admin.username}</h3>
            </div>
        </div>
    <br>
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">All Products</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/users">Users</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Settings</a>
        </li>
    </ul>
</@base.base>