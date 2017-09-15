<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>斗地主</title>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/doudizhu.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/doudizhu.css"/>
</head>
<body>

<#--游戏区域-->
<div class="body">
    <div class="gameScope">
        <#--机器人一区域-->
        <div class="robot1Scope">

        </div>
        <#--牌桌区域-->
        <div class="tableScope">

        </div>
        <#--机器人二区域-->
        <div class="robot2Scope">

        </div>
        <#--玩家区域-->
        <div class="playerScope">
            <div id="controlBtn" class="controlBtn">
                <input onclick="startGame()" id="startBtn" class="startBtn" type="button" value="开始游戏">
            </div>
            <div></div>
        </div>
    </div>
</div>
<div class="wallpapers"></div>
</body>
</html>