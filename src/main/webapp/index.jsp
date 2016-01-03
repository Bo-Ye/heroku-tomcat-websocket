<html>
    <body>
        <h2>Tomcat-based WebSocket on Heroku Demo</h2>
        <button id="btnGet" onclick="getMessages()">Get Messages</button>
        <button id="btnStop" onclick="stopMessages()" disabled="true">Stop Messages</button>
        <p id="demo"></p>
    </body>
</html>

<script type="text/javascript">
    //set up websocket
    var url = (window.location.protocol === "https:" ? "wss:" : "ws:") + "//" + window.location.host + window.location.pathname + "message-endpoint";
    var webSocket = new WebSocket(url);
    webSocket.onopen = function () {
        console.log("WebSocket is connected.");
    };
    webSocket.onmessage = function (event) {
        console.log(event.data);
        var demo = document.getElementById("demo");
        demo.innerHTML = demo.innerHTML + "<br/>" + event.data;
    };
    //get & stop messages
    function getMessages() {
        webSocket.send("GET");
        document.getElementById("btnGet").disabled = true;
        document.getElementById("btnStop").disabled = false;
    }
    function stopMessages() {
        webSocket.send("STOP");
        document.getElementById("btnGet").disabled = false;
        document.getElementById("btnStop").disabled = true;
    }
</script>