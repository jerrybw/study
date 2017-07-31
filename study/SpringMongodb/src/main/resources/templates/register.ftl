<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    function register() {
        alert(1);
        $.ajax({
            type: "POST",
            url: "register",
            data: {
                "name": $("#name").val(),
                "age": $("#age").val()
            },
            success: function (data) {
                alert(data);
            }
        })
    }
</script>
<body>
    <h1>${msg}</h1>
    用户名：<input type="text" id="name" name="name"><br>
    年龄：<input type="text" id="age" name="age"><br>
    密码：<input type="password" id="pwd" name="password"><br>
    <input type="button"  onclick="javascript:register()" value="注册">
</body>
</html>