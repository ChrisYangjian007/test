﻿<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
    <title>农产品质量追溯管控平台</title>
    <link href="/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <!--框架必需start-->
    <link href="/styles/learunui-startmenu.css" rel="stylesheet" />
    <link href="/styles/learunui-framework.css" rel="stylesheet" />
    <script src="/scripts/jquery/jquery-1.8.2.min.js"></script>
    <script src="/scripts/learunui-framework.js"></script>
    <!--框架必需end-->
    <!--引入弹窗组件start-->
    <script src="/scripts/lhgdialog/lhgdialog.min.js"></script>
    <!--引入弹窗组件end-->
    <!--自定义滚动条组件start-->
    <script src="/scripts/scrollbar/scrollbar.js"></script>
    <!--自定义滚动条组件end-->
    <!--日期组件start-->
    <script src="/scripts/datepicker/WdatePicker.js"></script>
    <!--日期组件start-->
    <script src="/scripts/Index.js"></script>
    <script>
        /**初始化**/
        $(document).ready(function () {
            AddTabMenu('Imain', '/Home/StartPanel', '欢迎首页', "house.png", 'false');
            GetStartmenu();
            ShortcutsList();
            setIframeH();
            initStartMenu();
            //var interval = setInterval("IconSong('icon_message')", 400);
            //$("#div_icon_message").click(function () {
            //    clearInterval(interval);
            //    window.open('http://www.learun.cn/fdms/index.html', '_blank');
            //})
        });
        /*设置iframe高度*/
        function setIframeH() {
            resizeU();
            $(window).resize(resizeU);
            function resizeU() {
                var divkuangH = $(window).height();
                $("#ContentPannel").height(divkuangH - 137);
            }
        }
        function initStartMenu() {
            $('#overlay_startmenu').click(function () {
                if ($('#start_menu_panel:visible').length) {
                    $('#overlay_startmenu').hide();
                    $('#start_menu_panel').slideUp(1);
                    $('.nicescroll-rails').hide();
                }
            });
            $('#start_menu').click(function () {
                if ($('#start_menu_panel:visible').length) {
                    $('#overlay_startmenu').hide();
                    $('#start_menu_panel').slideUp(1);
                    $('.nicescroll-rails').hide();
                }
                //遮罩层位置和显示
                $('#overlay_startmenu').show();
                //菜单面板位置
                var top = $('#start_menu').offset().top - $('#start_menu_panel').outerHeight() - 1;
                $('#start_menu_panel').css({ top: top });
                $('#start_menu_panel').show();
                $('.nicescroll-rails').show();
            });
        }
        //导航一级菜单
        var StartmenuJson = "";
        function GetStartmenu() {
            var index = 0;
            var html = "";
            getAjax("/Home/LoadStartMenu", "", function (data) {
                StartmenuJson = eval("(" + data + ")");
                $.each(StartmenuJson, function (i) {
                    
                    if (StartmenuJson[i].ParentID == 1) {
                        html += "<li>";
                        if (index == 0) {
                            html += "<div class='main_menu leftselected' onclick=\"GetSubmenu('" + StartmenuJson[i].ID + "')\">";
                            GetSubmenu(StartmenuJson[i].ID);
                        } else {
                            html += "<div onclick=\"GetSubmenu('" + StartmenuJson[i].ID + "')\">";
                        }
                        html += "<img src='/images/Icon32/" + StartmenuJson[i].Icon + "' width='32' height='32'>" + StartmenuJson[i].CName + "";
                        html += "</div>";
                        html += "</li>";
                        index++;
                    }
                });
            })
            $("#htmlMenu").append(html);
            var menuheight = $('.main_menu div').height() * $('.main_menu li').length + $('.main_menu li').length + 1;
            if (menuheight <= 360) {
                menuheight = 360
            }
            $(".panel-menu").height(menuheight);
            $("#main_menu").height(menuheight);
            $("#Submenu").height(menuheight);
            divniceScroll("#Submenu");
            readyIndex();
        }
        //导航子菜单
        function GetSubmenu(ModuleId) {
            $("#Submenu").html("");
            var html = "";
            $.each(StartmenuJson, function (i) {
                if (StartmenuJson[i].ParentID == ModuleId) {
                    var Icon = "";
                    if (StartmenuJson[i].Icon != "") {
                        Icon = StartmenuJson[i].Icon;
                    }
                    if (IsBelowMenu(StartmenuJson[i].ID) > 0) {
                        html += "<div title='" + StartmenuJson[i].CName + "' class=\"shortcuticons\" onclick=\"GetSubmenu('" + StartmenuJson[i].ID + "')\"><img src='/images/Icon32/" + Icon + "'><br />" + StartmenuJson[i].CName + "</div>";
                    } else {
                        html += "<div title='" + StartmenuJson[i].CName + "' class=\"shortcuticons\" onclick=\"AddTabMenu('" + StartmenuJson[i].ID + "', '" + RootPath() + StartmenuJson[i].Location + "', '" + StartmenuJson[i].CName + "',  '" + StartmenuJson[i].Icon + "','true');\"><img src='/images/Icon32/" + Icon + "'><br />" + StartmenuJson[i].CName + "</div>";
                    }
                }
            });
            $("#Submenu").html(html);
        }
        //判断是否有子节点
        function IsBelowMenu(ModuleId) {
            var count = 0;
            $.each(StartmenuJson, function (i) {
                if (StartmenuJson[i].ParentID == ModuleId) {
                    count++;
                    return false;
                }
            });
            return count;
        }
    </script>
</head>
<body onbeforeunload="PageClose()" onselectstart="return false;" style="-moz-user-select: none; overflow: hidden;">
    <div id="ajax-loader" style="position: fixed; top: -50%; left: -50%; width: 200%; height: 200%; background: #fff; z-index: 100; overflow: hidden;">
        <img src="/images/ajax-loader.gif" style="position: absolute; top: 0; left: 0; right: 0; bottom: 0; margin: auto;" />
    </div>
    <!-- header -->
    <div class="header">
        <div class="logo fleft">
            <img src="/images/loginlogo.png" />
        </div>
        <div id="Headermenu">
            <ul id="topnav">
                <li id="metnav_1" class="list">
                    <a id="nav_1" onclick="Replace();">
                        <span class="c1"></span>
                        系统首页
                    </a>
                </li>
                <li id="metnav_7" class="list droppopup">
                    <a id="nav_7">
                        <span class="c7"></span>快捷导航
                        <div class="popup">
                            <i></i>
                            <ul>
                                <li onclick="Shortcuts()">
                                    <img src="/images/Icon16/shortcuts.png" />快捷方式设置
                                </li>
                                <div id="Shortcuts"></div>
                            </ul>
                        </div>
                    </a>
                </li>
                <li id="metnav_3" class="list droppopup">
                    <a id="nav_3">
                        <span class="c3"></span>帮助中心
                        <div class="popup">
                            <i></i>
                            <ul>
                                <li onclick="alert('测试关闭')">
                                    <img src="/images/Icon16/help.png" />查看帮助
                                </li>
                                <li title="将反馈建议提交给开发商进行解决" onclick=" window.open('http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=06m_qaCruZOiov2wvL4', '_blank')">
                                    <img src="/images/Icon16/email_open.png" />反馈建议
                                </li>
                                <li onclick="Support()">
                                    <img src="/images/Icon16/premium_support.png" />技术支持
                                </li>
                                <li onclick="About()">
                                    <img src="/images/Icon16/information.png" />关于我们
                                </li>
                            </ul>
                        </div>
                    </a>
                </li>
                <li id="metnav_2" class="list" onclick="SkinIndex()">
                    <a id="nav_2">
                        <span class="c2"></span>切换皮肤
                    </a>
                </li>
                <li id="metnav_5" class="list" onclick="PersonCenter()">
                    <a id="nav_5">
                        <span class="c5"></span>个人中心
                    </a>
                </li>
                <li id="metnav_4" class="list" onclick="IndexOut();">
                    <a id="nav_4">
                        <span class="c4"></span>
                        安全退出
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <div class="taskbarTabs">
        <div id="dww-menu" class="mod-tab">
            <div class="mod-hd">
                <ul id="tabs_container" class="tab-nav"></ul>
            </div>
            <input id="ModuleId" type="hidden" />
        </div>
        <div class="rightMenu">
            <ul>
                <li onclick="top.frames[tabiframeId()].Replace()">刷新当前</li>
                <li onclick="ThisCloseTab()">关闭当前</li>
                <li onclick="AllcloseTab()">全部关闭</li>
                <li onclick="othercloseTab()">除此之外全部关闭</li>
                <div class='m-split'></div>
                <li>退出</li>
            </ul>
        </div>
    </div>
    <div id="ContentPannel">
    </div>
    <div id="footer" class="cs-south">
        <div style="width: 30%; text-align: left; float: left;">
            <div class="menu">
                <div id="start_menu" style="width: 89px; height: 31px; line-height: 31px;">
                    <img src="/images/start_menu.png" />
                </div>
            </div>
            <!-- 导航菜单 -->
            <div id="start_menu_panel">
                <div class="panel-head"></div>
                <!-- 登录用户信息 -->
                <div class="panel-user">
                    <div class="avatar" title="我的信息">
                        <img src="/images/man.png" />
                    </div>
                    <div class="name">
                        账户：@ViewBag.Account
                    </div>
                </div>
                <div class="panel-menu">
                    <div id="main_menu" class="main_menu" style="float: left;">
                        <ul id="htmlMenu"></ul>
                    </div>
                    <div id="Submenu" style="overflow: auto; background-color: #fff">
                    </div>
                </div>
            </div>
            <div id="overlay_startmenu"></div>
        </div>
        <div class="number" style="width: 40%; text-align: center; float: left;">
            技术支持：<a href="http://www.learun.cn/fdms/index.html" target="_blank" style="color: white;">信息技术有限公司</a>
            &nbsp; &nbsp;技术交流群：<a href="http://shang.qq.com/wpa/qunwpa?idkey=3ba0bad8da1b48fcd8a17cbdd4a67ac202f74b8864d6c94488a1aaec5add856d" title="点击加入QQ群" target="_blank" style="color: white;">2368429</a>
        </div>
        <div style="width: 30%; text-align: right; float: right;">
            <div style="padding-right: 2px;">
                <div title="在线用户（@HttpContext.Current.Application["OnLineCount"].ToString()）人" class="bottom_icon">
                    <img src="/images/bottom_icon_usergroup.png" />
                </div>
                <div title="邮件消息" class="bottom_icon">
                    <img id="icon_email" src="/images/youjian.png" style="padding-top: 5px;" />
                </div>
                <div id="div_icon_message" title="即时消息" class="bottom_icon">
                    <img id="icon_message" src="/images/bottom_icon_message.png" />
                </div>
                <div class="bottom_icon" style="padding-top: 4px;">
                    <img title="我的信息，账户：@ViewBag.Account" src="/images/bottom_icon_userinfo.png" />
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <!--载进度条start-->
    <div id="loading_background" class="loading_background" style="display: none;"></div>
    <div id="loading" onclick="Loading(false);">
        <img src="/images/loading.gif" style="vertical-align: middle;" />&nbsp;<span>正在拼了命为您加载…</span>&nbsp;
    </div>
    <div id="loadingGird">
        <img src="/images/loading.gif" style="vertical-align: middle;" />&nbsp;正在拼了命为您加载…&nbsp;
    </div>
</body>
</html>