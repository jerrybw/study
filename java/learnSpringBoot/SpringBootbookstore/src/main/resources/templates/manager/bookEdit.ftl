<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<script type="text/javascript" src="/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    function save() {
        alert("save");
    }
    $(function () {
       var height = document.body.clientHeight;
        var style = $("#myDiv").attr("style")
    })
</script>
<body style="margin: 0;padding: 0;">
 <div id="myDiv" style="width: 100%;height:;text-align: center;display: flex;justify-content: center;align-items: Center; background-color: beige; margin: auto">
        <div>
            <div>书名：<input type="text" id="name" name="name"></div>
            <div>作者：<input type="text" id="author" name="author"></div>
            <div>价格：<input type="text" id="price" name="price"></div>
            <div> 库存：<input type="text" id="stack" name="stack"></div>
            <div>销量：<input type="text" id="sale" name="sale"></div>
            <a href="javascript:;" onclick="save()">修改</a>
        </div>
 </div>
</body>
</html>