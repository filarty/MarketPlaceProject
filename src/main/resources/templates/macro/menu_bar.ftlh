<#macro bar user>
    <h1>MarketPlace</h1>
    <h4>Online store in Europe</h4>
    <br>
    <#if user.username??>
        <form action="/logout" method="POST">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">logout</button>
        </form>
    <#else>
        <a href="/login">Login</a> | <a href="/registration">Registration</a>
    </#if>
    <#nested>
</#macro>