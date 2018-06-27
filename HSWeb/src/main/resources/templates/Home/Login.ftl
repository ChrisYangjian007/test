<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
    <link href="${staticImg}/images/login/xqhs.ico" rel="shortcut icon" type="image/x-icon" />
    <title>登录页面</title>
    <script type="text/javascript" src="${staticJs}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/learunui-framework.js" ></script>
    <script type="text/javascript" src="${staticJs}/js/jquery.blockUI.js"></script>

    <script type="text/javascript" src="${staticJs}/scripts/lhgdialog/lhgdialog.min.js"></script>
    <link rel="stylesheet" href="${staticCss}/styles/learunui-framework.css" />
    <link rel="stylesheet" href="${staticCss}/styles/learunui-startmenu.css" />

    <link rel="stylesheet" href="${staticCss}/js/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="${request.contextPath}/css/comment.css" />
    <link rel="stylesheet" href="${staticCss}/styles/login.css" />
</head>
<body >
    <header class="head">
        <img src="${staticImg}/images/login/login-logo.png" alt="">
        <span class="vertical-line"></span>
        <b class="title-text">汲取海参精华，尊享四季健康</b>
    </header>
    <div class="main">
        <div class="login-left">
            <div class="login-left-box">
                <p class="left-text">晓芹食品信息追溯管理系统</p>
                <ul class="left-List">
                    <li><img src="${staticImg}/images/login/left-1.png" alt=""></li>
                    <li><img src="${staticImg}/images/login/left-2.png" alt=""></li>
                    <li><img src="${staticImg}/images/login/left-3.png" alt=""></li>
                    <li><img src="${staticImg}/images/login/left-4.png" alt=""></li>
                </ul>
            </div>
        </div>
        <div class="login-right">
            <div class="login-box">
                <p class="welcome">欢迎登陆</p>
                <div class="form-message">
                </div>
                <div class="input-group red">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-user"></span>
                    </span>
                    <input id="txtaccount" type="text" class="form-control inputLogin" placeholder="请输入账号"
                           onkeyup="this.value=this.value.replace(/\s/g,'')"
                           onblur="this.value=this.value.replace(/\s/g,'')">
                </div>
                <div class="input-group red">
                    <span class="input-group-addon ">
                        <span class="glyphicon glyphicon-lock"></span>
                    </span>
                    <input id="txtpassword" type="password" class="form-control inputLogin"
                           placeholder="请输入密码" maxlength="10"
                           onkeyup="this.value=this.value.replace(/\s/g,'')"
                           onblur="this.value=this.value.replace(/\s/g,'')">
                </div>
                <button id="btlogin" class="btn loginBtn">
                    登录
                </button>
            </div>
        </div>
    </div>
    <footer class="foot">
        <p>大连晓芹食品有限公司&nbsp;&nbsp;&nbsp;辽ICP备12005866号-1 </p>
    </footer>
    <#--<div id="loading_background" class="loading_background" style="display: none;"></div>
    <div id="loading" onclick="Loading(false);">
        <img src="${staticImg}/images/loading.gif" style="vertical-align: middle;" />&nbsp;<span>正在拼了命为您加载…</span>&nbsp;
    </div>-->
</body>
<script type="text/javascript">
    /*
    检查浏览器是否支持
    */
    var isIE = !!window.ActiveXObject;
    var isIE6 = isIE && !window.XMLHttpRequest;
    if (isIE6) {
        window.location.href = "../Error/Browser";
    }
    //回车键
    document.onkeydown = function (e) {
        if (!e) e = window.event; //火狐中是 window.event
        if ((e.keyCode || e.which) == 13) {
            var btlogin = document.getElementById("btlogin");
            btlogin.focus();
            btlogin.click();
        }
    };
    $(function () {
        loadTopWindow();
        $("#btlogin").click(function () {
            Loading(true, "正在提交数据...");
            var txtaccount = $("#txtaccount").val();
            if (""==txtaccount) {
                $("#txtaccount").focus();
                tipDialog("登录账户不能为空", 4, 'warning');
                Loading(false);
                return false;
            }
            var txtpassword = $("#txtpassword").val();
            if (""==txtpassword) {
                $("#txtpassword").focus();
                tipDialog("登录密码不能为空", 4, 'warning');
                Loading(false);
                return false;
            }
            Loading(true, "正在登录...");
            $.post('${request.contextPath}/login.json', {username:txtaccount,password:txtpassword}, function (result) {
                if(result.success){
                    Loading(true, "登录验证成功,正在跳转首页");
                    window.location.href="${request.contextPath}/login.htm" ;
                }else {
                    $("#txtaccount").focus();
                    $("#txtpassword").val("");
                    tipDialog(result.msg, 4, '0');
                    Loading(false);
                }
            }, "JSON");
        });
    });

    $(".inputLogin").on("focus",function () {
        $(this).parent().css("border","2px solid #89efe5")
    });
    $(".inputLogin").on("blur",function () {
        $(this).parent().css("border","2px solid #facd89")
    });


    //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
    function loadTopWindow(){
        if (window.top!=null && window.top.document.URL!=document.URL){
            Loading(true,"正在跳转登陆页。。。");
            window.top.location= document.URL; //这样就可以让登陆窗口显示在整个窗口了
        }else {
            if (window.location.pathname.split(".htm")[0]!="${request.contextPath}/login"){
                Loading(true,"正在跳转登陆页。。。");
                window.top.location= document.URL;
            }
        }
    }
</script>
</html>