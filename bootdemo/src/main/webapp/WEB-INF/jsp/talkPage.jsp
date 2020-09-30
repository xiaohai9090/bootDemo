<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
    <script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        let ws;
        var playerIndex = ${player.playerIndex};
        $(function () {
            if ("WebSocket" in window) {
                // 打开wocket连接
                var url = "ws://127.0.0.1:8500/echo/" + playerIndex;
                ws = new WebSocket(url);
                // ws = new WebSocket("ws://192.168.52.25:16828&pause=1");

                ws.onopen;

                ws.onmessage = function (msg){
                    console.log("接收消息");
                    var message = msg.data;
                    var msgDiv = document.getElementById('textBox');
                    msgDiv.innerText = message;
                }
            }
        });


        function sendMsg() {
            var s = new Object();
            s.playerIndex =  document.getElementById('playerIndex').value;
            s.message = document.getElementById('message').value;
            s.msgThreadId = 10010;

            var json = JSON.stringify(s);

            var appendMsg = "TOUSER"+message;
            ws.send(s);
        }


    </script>
</head>
<body>



<div>
    <input type="text" id="playerIndex">
    <input type="text" id="message">
    <button onclick="sendMsg()">发送消息</button>
</div>

<div id="textBox">

</div>
</body>
</html>
