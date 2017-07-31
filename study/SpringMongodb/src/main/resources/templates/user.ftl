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
    <div>
        <table>
            <thead>
                <td>id</td>
                <td>姓名</td>
                <td>年龄</td>
            </thead>
            <#list users as user>
                <tr>
                    <td>user.id</td>
                    <td>user.name</td>
                    <td>user.age</td>
                </tr>
            </#list>
        </table>
    </div>
</body>
</html>