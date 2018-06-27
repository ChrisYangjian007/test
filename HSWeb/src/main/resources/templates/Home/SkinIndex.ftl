﻿<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>个性化皮肤设置</title>
    <!--框架必需start-->
    <link href="/styles/learunui-framework.css" rel="stylesheet" />
    <script src="/scripts/jquery/jquery-1.8.2.min.js"></script>
    <script src="/scripts/learunui-framework.js"></script>
    <script src="/scripts/cookie/jquery.cookie.js"></script>
    <!--框架必需end-->
    <script>
        $(function () {
            var Index =  localStorage.getItem("UItheme") || "accordionIndex";
            $("#theme").val(Index);
            $('.skinSelect').click(function () {
                var color = $(this).attr('background');
                top.$(".header").css('background', color);
                top.$("#footer").css('background', color);
                top.$(".ui_title").css('background', color);
                top.$(".ui_lt,.ui_rt,.ui_l,.ui_r,.ui_lb,.ui_b,.ui_t,.ui_rb").css('background', color);
                top.$(".popup ul").css('background', color);
                top.$(".popup i").css('border-color', "transparent transparent " + color);
                top.$(".submenu .action").css('background', color);
            })

        })

        //切换主题
        function SwitchTheme() {
            var theme = $("#theme").val();
            if (confirm('您确认要切换UI主题吗？系统将会自动刷新')) {
                Loading(true, "正在切换主题UI...");
                localStorage.setItem("UItheme", theme);
                top.location.href = "@Url.Content("~/Home/")" + theme;
                //window.setTimeout(function () {
                //    localStorage.setItem("UItheme",theme)
                //    //AjaxJson("/Home/SwitchTheme", { UItheme: theme }, function (data) {
                //    //    top.location.href = "@Url.Content("~/Home/")" + theme;
                //    //});
                //}, 200);
            }else{
                $("#theme").val(localStorage.getItem("UItheme"));
            }
        }
    </script>
</head>
<body style="margin: 1px;">
    <div class="bd" style="border-bottom: none; margin-top: 1px;">
        <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
            <div class="tab_list_top" style="position: absolute">
                <div class="tab_list bd actived">UI风格设置</div>
            </div>
        </div>
    </div>
    <div class="border" style="border-top: none; padding: 20px;">
        界面主题：
        <select id="theme" class="keyword" onchange="SwitchTheme()">
            <option value="AccordionIndex">抽屉式手风琴</option>
            <option value="TreeIndex">树形无限级手风琴</option>
            <option value="StartIndex">Windos开始菜单</option>
        </select>
    </div>
    <div class="bd" style="border-bottom: none; margin-top: 1px;">
        <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
            <div class="tab_list_top" style="position: absolute">
                <div class="tab_list bd actived">皮肤设置</div>
            </div>
        </div>
    </div>
    <div style="border-top: none;">
        <div title="间海蓝" class="skinSelect" background="#4A5B79" style="background: #4A5B79"></div>
        <div title="亚光黑" class="skinSelect" background="#2e3e4e" style="background: #2e3e4e"></div>
        <div title="亮天蓝" class="skinSelect" background="#1e71b1" style="background: #1e71b1"></div>
        <div title="玫瑰红" class="skinSelect" background="#B1181B" style="background: #B1181B"></div>
        <div title="暗灰蓝" class="skinSelect" background="#34495e" style="background: #34495e"></div>
        <div title="宝石绿" class="skinSelect" background="#28b779" style="background: #28b779"></div>
        <div title="柠檬黄" class="skinSelect" background="#ffb848" style="background: #ffb848"></div>
        <div title="玫瑰紫" class="skinSelect" background="#852b99" style="background: #852b99"></div>
        <div title="古董褐" class="skinSelect" background="#623f18" style="background: #623f18"></div>
        <div title="皇家黑" class="skinSelect" background="#333438" style="background: #333438"></div>
        <div title="苹果绿" class="skinSelect" background="#9BBD33" style="background: #9BBD33"></div>
        <div title="淡紫红" class="skinSelect" background="#C6487E" style="background: #C6487E"></div>
        <div title="间春绿" class="skinSelect" background="#62b600" style="background: #62b600"></div>
        <div title="暗桔黄" class="skinSelect" background="#DC6601" style="background: #DC6601"></div>
        <div title="间暗蓝" class="skinSelect" background="#5D83A3" style="background: #5D83A3"></div>
        <div title="咖啡黄" class="skinSelect" background="#B99A56" style="background: #B99A56"></div>
        <div title="海军蓝" class="skinSelect" background="#4C88C0" style="background: #4C88C0"></div>
        <div title="热粉红" class="skinSelect" background="#F2664A" style="background: #F2664A"></div>
    </div>
</body>
</html>
<style>
    .skinSelect {
        float: left;
        width: 55px;
        height: 46px;
        margin-left: 20px;
        margin-right: 20px;
        margin-top: 20px;
        display: block;
        position: relative;
        cursor: pointer;
        -moz-border-radius: 5px;
        -webkit-border-radius: 5px;
        border-radius: 5px;
        box-shadow: 0 0 15px #ccc;
    }
</style>