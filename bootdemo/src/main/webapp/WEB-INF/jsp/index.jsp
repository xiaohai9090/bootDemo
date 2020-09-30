<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>no title</title>
</head>
<body>

<input type="text" name="playerName" id="playerName">
<input type="text" name="playerIndex" id="playerIndex">
<input type="text" name="money" id="money">
<input type="text" name="teamId" id="teamId">
<button id="save" onclick="save()">保存</button>
<button id="search" onclick="searchPlayer()">查询</button>

<table id="playerList" >
    <tr>
        <th>名称</th>
        <th>ID</th>
        <th>注册时间</th>
        <th>地区ID</th>
        <th>钱</th>
        <th>团队</th>
    </tr>
</table>

<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script>
    function save() {
        const playerName = document.getElementById("playerName").value;
        const playerIndex = document.getElementById("playerIndex").value;
        const money = document.getElementById("money").value;
        const teamId = document.getElementById("teamId").value;
        $.ajax({
            url:'/player/insertPlayer',
            type:'post',
            data:{playerName:playerName,playerIndex:playerIndex,money:money,teamId:teamId},
            success:function (data) {
                location.reload();
            }
        });
    }

    $(function () {
        $.ajax({
           url:'/player/',
           type:'post',
           success:function (datas) {
               console.log(datas);
               for (i =0;i < datas.length; i++){
                   const playerName = datas[i].playerName;
                   const playerIndex = datas[i].playerIndex;
                   const money = datas[i].money;
                   const loginTime = datas[i].loginTime;
                   const areaId = datas[i].areaId;
                   const teamId = datas[i].teamId;
                   $('#playerList').append("<tr><td>"+playerName+"</td><td>"+playerIndex+"</td><td>"+loginTime+"</td><td>"+areaId+"</td><td>"+money+"</td><td>"+teamId+"</td></tr>");
               }
           }
        });
    });

    function searchPlayer() {
        const teamId = document.getElementById("teamId").value;
        $.ajax({
            url:'/player/getPlayersByTeamId',
            type:'post',
            data:{teamId:teamId},
            success:function (datas) {
                console.log(datas);
                for (i =0;i < datas.length; i++){
                    const playerName = datas[i].playerName;
                    const playerIndex = datas[i].playerIndex;
                    const money = datas[i].money;
                    const loginTime = datas[i].loginTime;
                    const areaId = datas[i].areaId;
                    const teamId = datas[i].teamId;
                    $('#playerList').append("<tr><td>"+playerName+"</td><td>"+playerIndex+"</td><td>"+loginTime+"</td><td>"+areaId+"</td><td>"+money+"</td><td>"+teamId+"</td></tr>");
                }
            }
        });
    }
</script>
</body>
</html>
