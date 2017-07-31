<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
    <#if user??>
        <h1>欢迎<span style="color: red">${user.userName}</span></h1>
    <#else >
        <a href="/gotoLogin" >登陆</a>&nbsp;&nbsp;<a href="/gotoRegister" >注册</a>&nbsp;&nbsp;<a href="/gotoBookManager">管理</a>
    </#if>
</body>
</html>