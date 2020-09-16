<html>
    <head>
        <title>dubbo test</title>
        <script src="../jquery-1.7.2.js"></script>
    </head>
    <body>
        <input type="text" name="msg" id="msg"/>
        <button>发送消息</button>

        <script>
            $("button").click(function(){
                var msg = $("#msg").val();
                $.ajax({
                    url:"call",
                    type:"POST",
                    success:function(){
                        alert("call succcess!!!");
                    }
                });
            });
        </script>
    </body>
</html>