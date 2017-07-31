<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
</head>
<script src="/js/jquery-3.1.0.min.js"></script>
<script>
    function register() {
        var param = {
            "userName": $("#username").val(),
            "password": $("#pwd").val(),
            "gender": $(".gender").val(),
            "age": $("#age").val(),
        };
        $.ajax({
            url: "/register",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(param),
            success: function (data) {
                $("#msg").html(data);
            }
        })
    }

    function reset() {
        $("#username").val("");
        $("#pwd").val("");
        $("#age").val("");
        $(".gender").each(function () {
//            this.checked=false;
            $(this).attr("checked",false);
            alert($(this).val());
        })
    }


</script>
<body>
<h1 id="msg">欢迎注册</h1>
用户名：<input type="text" id="username" name="username" autocomplete="on"><br>
密码：<input type="password" id="pwd" name="password"><br>
年龄：<input type="text" id="age" name="age"><br>
性别：<input type="radio" class="gender" name="gender" value="m" checked> 男 &nbsp;&nbsp;<input type="radio" class="gender" name="gender" value="w">女<br>
<input type="button" value="注册" title="注册" onclick="register()"> &nbsp;&nbsp;<input type="reset" value="重置" title="重置" onclick="reset()">
</body>
</html>