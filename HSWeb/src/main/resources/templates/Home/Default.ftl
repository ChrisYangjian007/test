﻿<!DOCTYPE html>

<html>
<head>
    <title>登录信息超时</title>
    <script type="text/javascript">
        function fullwin() {
            alert("登录信息超时，请重新登录");
            top.location.href = "/Home/Login";
            return false;
        }
    </script>
</head>
<body onload="fullwin()">
</body>
</html>