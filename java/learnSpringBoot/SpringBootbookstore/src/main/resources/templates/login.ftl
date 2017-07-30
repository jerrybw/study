<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<script type="text/javascript" src="/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    function login() {
        var param = {
            "userName": $("#username").val(),
            "password": $("#pwd").val()
        };
        $.ajax({
            url: "/login",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(param),
            success: function (data) {
                alert(data);
            }
        })
    }

</script>
<body>
    <h1>login</h1>
    用户名：<input type="text" id="username" name="username"><br>
    密码：<input type="password" id="pwd" name="password"><br>
    <input type="button" value="登陆" title="登陆" onclick="login()">&nbsp;&nbsp;
    <input type="button" value="注册" title="注册" onclick="javascript:window.location.href='/gotoRegister'">
</body>
</html>