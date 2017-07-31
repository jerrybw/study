<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function login() {
        alert($("#name").val());
    }
</script>
<body>
    <h1>${msg}</h1>
    用户名：<input type="text" id="name" name="name"><br>
    密码：<input type="password" id="pwd" name="password"><br>
    <input type="button"  onclick="javascript:login()" value="登陆"> &nbsp;&nbsp;
    <input type="button" onclick="window.location.href='/gotoRegister'" value="注册">
</body>
</html>