﻿<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
    <title>农产品质量追溯管控平台</title>
    <link href="/images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <!--框架必需start-->
    <link href="/styles/learunui-accordionTree.css" rel="stylesheet" />
    <link href="/styles/learunui-framework.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/scripts/learunui-framework.js"></script>
    <link href="/styles/learunui-startmenu.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/less/less-1.3.3.min.js"></script>
    <!--框架必需end-->
    <!--树形组件start-->
    <link href="/scripts/tree/tree.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/tree/tree.js"></script>
    <!--树形组件end-->
    <!--引入弹窗组件start-->
    <script type="text/javascript" src="/scripts/lhgdialog/lhgdialog.min.js"></script>
    <!--引入弹窗组件end-->
    <!--自定义滚动条组件start-->
    <script type="text/javascript" src="/scripts/scrollbar/scrollbar.js"></script>
    <!--自定义滚动条组件end-->
    <!--日期组件start-->
    <!--日期组件start-->
    <script type="text/javascript" src="/scripts/Index.js"></script>
    <script type="text/javascript">
        /**初始化**/
        $(document).ready(function () {
            ServerCurrentTime()
            AddTabMenu('Imain', '@Url.Content("~/Home/TreePage")', '欢迎首页', "house.png", 'false');
            GetAccordionMenu();
            InitializeImpact();
            ShortcutsList();
            //var interval = setInterval("IconSong('icon_message')", 400);
            //$("#div_icon_message").click(function () {
            //    clearInterval(interval);
            //    window.open('http://www.baidu.com', '_blank');
            //})
            $(".popup li").click(function () {
                linkAddTabMenu()
            })
        });
        //点击菜单连接（隐藏导航菜单）
        function linkAddTabMenu() {
            $('.btn-nav-toggle').removeAttr('disabled');
            $('.btn-nav-toggle').removeClass('harvest');
            $('.btn-nav-toggle').trigger("click");
            //点击Tab事件
            $('#tabs_container li').click(function () {
                var id = $(this).attr('id');
                if (id == 'tabs_Imain') {
                    $('.btn-nav-toggle').attr('disabled', 'disabled');
                    //点击首页（显示导航菜单）
                    $(".navigation").css('position', '');
                    $(".navigation").css('width', '204');
                    $('.accordion').show();
                    $('.btn-nav-toggle').addClass('harvest');
                    $('.btn-nav-toggle').find('b').hide();
                    $('.btn-nav-toggle').find('i').show();
                    $('.btn-nav-toggle').attr('title', '');
                } else {
                    $('.btn-nav-toggle').removeAttr('disabled');
                    //（隐藏导航菜单）
                    $(".navigation").css('position', 'absolute');
                    $('.btn-nav-toggle').removeClass('harvest');
                    $('.btn-nav-toggle').trigger("click");
                }
            });
        }
        //初始化界面UI效果
        function InitializeImpact() {
            //设置自应高度
            resizeU();
            $(window).resize(resizeU);
            function resizeU() {
                var divkuangH = $(window).height();
                $(".mainPannel").height(divkuangH - 130);
                $(".navigation").height(divkuangH - 130);
                $("#ContentPannel").height(divkuangH - 130);
            }
            //手风琴效果
            var Accordion = function (el, multiple) {
                this.el = el || {};
                this.multiple = multiple || false;
                var links = this.el.find('.link');
                links.on('click', { el: this.el, multiple: this.multiple }, this.dropdown)
            }
            Accordion.prototype.dropdown = function (e) {
                //计算高度
                var accordionheight = ($("#accordion").children("ul li").length * 36);
                var navigationheight = $(".navigation").height()
                $('#accordion li').children('.submenu').height(navigationheight - accordionheight - 1);
                $(this).next().slideToggle();
                $(this).parent().toggleClass('open');
                if (!e.data.multiple) {
                    $(this).parent().parent().find('.submenu').not($(this).next()).slideUp().parent().removeClass('open');
                };
                GetTreeMenu($(this).next().attr('id'));
            }
            var accordion = new Accordion($('#accordion'), false);
            $(".submenu a").click(function () {
                $('.submenu a').removeClass('action');
                $(this).addClass('action');
            })
            $("#accordion li:first").find('div').trigger("click");//默认第一个展开
            $('.btn-nav-toggle').click(function () {
                if (!$('.btn-nav-toggle').attr('disabled') && !$(this).hasClass("harvest")) {
                    $(this).addClass('harvest');
                    $(".navigation").animate({ width: 0 }, 100);
                    $('.accordion').hide();
                    $(this).find('b').show();
                    $(this).find('i').hide();
                } else {
                    $(this).removeClass('harvest');
                    $(".navigation").animate({ width: 204 }, 100);
                    $('.accordion').show();
                    $(this).find('b').hide();
                    $(this).find('i').show();
                }
            }).hover(function () {
                if ($(this).hasClass("harvest")) {
                    $(this).attr('title', '隐藏导航');
                    $(this).removeClass('harvest');
                    $(".navigation").animate({ width: 204 }, 100);
                    $('.accordion').show();
                    $(this).find('b').hide();
                    $(this).find('i').show();
                    $(".navigation").css('position', 'absolute');
                }
            }, function () {
            });
        }
        /*导航菜单begin====================*/
        //手风琴
        function GetAccordionMenu() {
            var html = "";
            getAjax("/Home/LoadAccordionMenu", "", function (data) {
                if (!data) { return; }
                var accordionJson = eval("(" + data + ")");
                $.each(accordionJson, function (i) {
                    if (accordionJson[i].ParentID == 1) {
                        html += "<li title=" + accordionJson[i].CName + ">";
                        html += "<div class=\"link\"><img class='img' src='/images/Icon16/" + accordionJson[i].Icon + "'>";
                        html += "<span>" + accordionJson[i].CName + "</span><i class=\"chevron-down\"></i>";
                        html += "</div>";
                        html += "<div id=\"" + accordionJson[i].ID + "\" class=\"submenu bottomline\"></div>";
                        html += "</li>";
                    }
                });
            })
            $("#accordion").append(html);
        }
        //树形菜单
        function GetTreeMenu(ModuleId) {
            var itemtree = {
                onnodeclick: function (item) {
                    if (!!item.Location) {
                        var filename = item.img.replace(/.*(\/|\\)/, "");
                        AddTabMenu(item.id, RootPath() + item.Location, item.text, filename, 'true');
                        linkAddTabMenu();
                    }
                    //点击展开
                    $("#" + ModuleId + " .bbit-tree-selected").children('.bbit-tree-ec-icon').trigger("click");
                },
                url: "@Url.Content("~/Home/LoadTreeMenu?ModuleId=")" + ModuleId
            };
            $("#" + ModuleId).treeview(itemtree);
        }
        /*导航菜单end====================*/
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
        <div id="navigationtitle">
            <div id="CurrentTime" style="float: left; padding-left: 12px;"></div>
            <div disabled style="float: right;" class="btn-nav-toggle">
                <i></i>
                <b></b>
            </div>
        </div>
        <div style="float: left;width: calc(100% - 205px);">
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
    </div>
    <div class="mainPannel">
        <div class="navigation">
            <ul id="accordion" class="accordion"></ul>
        </div>
        <div id="overlay_navigation"></div>
        <div id="ContentPannel"></div>
    </div>
    <div id="footer" class="cs-south" style="height: 25px;">
        <div class="number" style="width: 30%; text-align: left; float: left; line-height: 25px;">
            &nbsp;技术支持：<a href="http://www.baidu.com" target="_blank" style="color: white;">信息技术有限公司</a>
            &nbsp; &nbsp;技术交流群：<a href="http://shang.qq.com/wpa/qunwpa?idkey=3ba0bad8da1b48fcd8a17cbdd4a67ac202f74b8864d6c94488a1aaec5add856d" title="点击加入QQ群" target="_blank" style="color: white;">239168429</a>
        </div>
        <div class="number" style="width: 40%; text-align: center; float: left; line-height: 25px;">
            CopyRight © 2010-2014 By Wind
        </div>
        <div style="width: 30%; text-align: right; float: right;">
            <div style="padding-right: 0px;">
                <div title="在线用户（@HttpContext.Current.Application["OnLineCount"].ToString()）人" class="bottom_icon" style="padding-top:2px;">
                    <img src="/images/bottom_icon_usergroup.png" />
                </div>
                <div title="邮件消息" class="bottom_icon" style="padding-top: 2px;">
                    <img id="icon_email" src="/images/youjian.png" style="padding-top: 5px;" />
                </div>
                <div id="div_icon_message" title="即时消息" class="bottom_icon" style="padding-top: 2px;">
                    <img id="icon_message" src="/images/bottom_icon_message.png" />
                </div>
                <div class="bottom_icon" style="padding-top: 1px;">
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