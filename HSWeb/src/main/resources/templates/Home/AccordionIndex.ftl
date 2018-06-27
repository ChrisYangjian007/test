<!DOCTYPE html>
<html>
<head>
<#include "../layout/head.ftl">
<#include "../layout/baseJs.ftl">
    <title>晓芹食品信息追溯管理系统</title>
    <script type="text/javascript">
        /**初始化**/
        $(document).ready(function () {
            String.prototype.endWith=function(endStr){
                var d=this.length-endStr.length;
                return (d>=0&&this.lastIndexOf(endStr)==d)
            };
            var href = window.top.location.href;
            if (!href.endWith("index.htm")){
                window.top.location.href="${request.contextPath}/login.htm" ;
            }else {
                //服务器当前日期 index.js
                ServerCurrentTime();
                //动态菜单tab标签 learunui-framework.js
                AddTabMenu('Imain', '${request.contextPath}/Home/AccordionPage.htm', '欢迎首页', "house.png", 'false', "${staticImg}");
                //导航一级菜单
                GetAccordionMenu();
                //初始化界面UI效果
                InitializeImpact();
                //快捷方式列表 index.js
                //ShortcutsList();

                $(".popup li").click(function () {
                    linkAddTabMenu()
                })
            }
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
                $('#accordion li').children('.b-children').height(navigationheight - accordionheight - 1);
                $(this).next().slideToggle();
                $(this).parent().toggleClass('open');
                if (!e.data.multiple) {
                    $(this).parent().parent().find('.submenu').not($(this).next()).slideUp().parent().removeClass('open');
                };
            }
            $(".submenu a").click(function () {
                $('.submenu a').removeClass('action');
                $(this).addClass('action');
            })
            var accordion = new Accordion($('#accordion'), false);
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
        //导航一级菜单
        var accordionJson = "";
        function GetAccordionMenu() {
            var html = "";
            $.ajax({
                type: 'post',
                url: "${request.contextPath}/Home/LoadAccordionMenu.json",
                cache: false,
                async: false,
                success: function (data) {
                    accordionJson = data.obj;
                    $.each(accordionJson, function (i) {
                        if (accordionJson[i].parentId == 1) {
                            html += "<li title=" + accordionJson[i].cname + ">";
                            html += "<div class=\"link\"><img src='${staticImg}/images/Icon16/" + accordionJson[i].icon + "'>";
                            html += "<span>" + accordionJson[i].cname + "</span><i class=\"chevron-down\"></i>";
                            html += "</div>";
                            html += GetSubmenu(accordionJson[i].resourceId, "b-children");
                            html += "</li>";
                        }
                    });
                },
                error: function (data) {
                    Loading(false);
                }
            });
            $("#accordion").append(html);
        }
        //导航子菜单
        function GetSubmenu(resourceId, _class) {
            var submenu = "<ul class=\"submenu " + _class + "\">";
            $.each(accordionJson, function (i) {
                if (accordionJson[i].parentId == resourceId) {
                    if (IsBelowMenu(accordionJson[i].resourceId) > 0) {
                        submenu += "<li title=" + accordionJson[i].cname + "><a class=\"link\"><img src='${staticImg}/images/Icon16/" + accordionJson[i].icon + "'><span>" + accordionJson[i].cname + "</span><i class=\"submenu-chevron-down\"></i></a>";
                        submenu += GetSubmenu(accordionJson[i].resourceId, "c-children");
                        submenu += "</li>";
                    } else {
                        submenu += "<li title=" + accordionJson[i].cname + " onclick=\"AddTabMenu('" + accordionJson[i].resourceId + "', '" + RootPath() + accordionJson[i].location + "', '" + accordionJson[i].cname + "', '" + accordionJson[i].icon + "','true','${staticImg}');linkAddTabMenu()\"><img src='${staticImg}/images/Icon16/" + accordionJson[i].icon + "'><a><span>" + accordionJson[i].cname + "</span></a></li>";
                    }
                }
            });
            submenu += "</ul>";
            return submenu;
        }
        //判断是否有子节点
        function IsBelowMenu(resourceId) {
            var count = 0;
            $.each(accordionJson, function (i) {
                if (accordionJson[i].parentId == resourceId) {
                    count++;
                    return false;
                }
            });
            return count;
        }
        /*导航菜单end====================*/


        /**安全退出**/
        function Logout() {
            var msg = "<div class='ui_alert'>确认要退出 晓芹食品信息追溯管理系统？</div>";
            top.$.dialog({
                id: "confirmDialog",
                lock: true,
                icon: "hits.png",
                content: msg,
                title: "提示",
                button: [
                    {
                        name: '退出',
                        callback: function () {
                            Loading(true, "正在退出系统...");
                            window.location.href = '${request.contextPath}/logout.htm';
                        }
                    },
                    /*{
                        name: '注销',
                        callback: function () {
                            Loading(true, "正在注销系统...");
                            getAjax("/Login/OutLogin", "", function (data) {
                                window.location.href = '../Login/Index';
                            })
                        }
                    },*/
                    {
                        name: '取消'
                    }
                ]
            });
        }

        function updateUserPassWord() {
            $("#updateUserPassWordModal").modal({
                remote: "${request.contextPath}/user/resetPasswordModal.htm?id=${shiroUser.id}"
            });
            $("#updateUserPassWord").unbind("click");
            $("#updateUserPassWord").click(function () {
                Loading(true,"正在提交...","#updateUserPassWordModal");
                var newPassword = $("#newPassword").val();
                if(""==newPassword){
                    $("#newPassword").focus();
                    tipDialog("请输入新的密码",4,"warning");
                    Loading(false,"","#updateUserPassWordModal");
                    return false;
                }else {
                    if(6>newPassword.length || 10<newPassword.length){
                        $("#newPassword").focus();
                        tipDialog("新的密码应该在6～10位之间",4,"warning");
                        Loading(false,"","#updateUserPassWordModal");
                        return false;
                    }
                }
                var confirmNewPassword = $("#confirmNewPassword").val();
                if(""==confirmNewPassword){
                    $("#confirmNewPassword").focus();
                    tipDialog("请确认您的密码",4,"warning");
                    Loading(false,"","#updateUserPassWordModal");
                    return false;
                }
                if(""!=newPassword&&""!=confirmNewPassword){
                    if(newPassword != confirmNewPassword){
                        $("#newPassword").focus();
                        $("#confirmNewPassword").focus();
                        tipDialog("新密码与确认密码不一致",4,"warning");
                        Loading(false,"","#updateUserPassWordModal");
                        return false;
                    }
                }
                $.post('${request.contextPath}/user/resetPassword.json', $("#resetPasswordForm").serialize(), function (result) {
                    if (result.success) {
                        tipDialog(result.msg, 4, '1');
                        $("#updateUserPassWordModal").modal('hide');
                        if(1===parseInt(result.obj)){
                            Loading(true, "密码修改，请重新登陆！");
                            window.location.href = '${request.contextPath}/logout.htm';
                        }
                    } else {
                        tipDialog(result.msg, 4, '0');
                    }
                    Loading(false,"","#updateUserPassWordModal");
                }, "JSON");
            });
        }

    </script>
</head>
<body onbeforeunload="PageClose()" onselectstart="return false;" style="-moz-user-select: none; overflow: hidden;">
<div id="ajax-loader" style="cursor:progress;position: fixed; top: -50%; left: -50%; width: 200%; height: 200%; background: #fff; z-index: 100; overflow: hidden;">
    <img src="${staticImg}/images/ajax-loader.gif" style="position: absolute; top: 0; left: 0; right: 0; bottom: 0; margin: auto;" />
</div>
<!-- header -->
<div class="header">
    <div class="logo fleft">
        <img src="${staticImg}/images/loginlogo3.png" />
    </div>
    <div id="Headermenu">
        <ul id="topnav">
            <#--<li id="metnav_1" class="list">
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
                                <img src="${staticImg}/images/Icon16/shortcuts.png" />快捷方式设置
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
                            <li>
                                <img src="${staticImg}/images/Icon16/help.png" />查看帮助
                            </li>
                            <li title="将反馈建议提交给开发商进行解决" onclick=" window.open('http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=06m_qaCruZOiov2wvL4', '_blank')">
                                <img src="${staticImg}/images/Icon16/email_open.png" />反馈建议
                            </li>
                            <li onclick="Support()">
                                <img src="${staticImg}/images/Icon16/premium_support.png" />技术支持
                            </li>
                            <li onclick="About()">
                                <img src="${staticImg}/images/Icon16/information.png" />关于我们
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
            </li>-->
<@shiro.hasPermission name="/user/resetPassword">
                <li id="metnav_5" class="list" onclick="updateUserPassWord()">
                    <a id="nav_5">
                        <span class="c8"></span>重置密码
                    </a>
                </li>
</@shiro.hasPermission>
            <li id="metnav_4" class="list" onclick="Logout();">
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
        <div id="CurrentTime" style="float: left;padding-left: 12px;"></div>
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
        &nbsp;技术支持：<a href="http://www.greenmarks.org" target="_blank" style="color: white;">上海绿度信息科技股份有限公司</a>
    </div>
    <div class="number" style="width: 40%; text-align: center; float: left; line-height: 25px;">
        大连晓芹食品有限公司&nbsp;&nbsp;&nbsp;辽ICP备12005866号-1
    </div>
    <div style="width: 30%; text-align: right; float: right;">
        <#--<div style="padding-right: 0px;">
            <div title="在线用户（@HttpContext.Current.Application["OnLineCount"].ToString()）人" class="bottom_icon" style="padding-top:2px;">
            <img src="${staticImg}/images/bottom_icon_usergroup.png" />
        </div>
        <div title="邮件消息" class="bottom_icon" style="padding-top: 2px;">
            <img id="icon_email" src="${staticImg}/images/youjian.png" style="padding-top: 5px;" />
        </div>
        <div id="div_icon_message" title="即时消息" class="bottom_icon" style="padding-top: 2px;">
            <img id="icon_message" src="${staticImg}/images/bottom_icon_message.png" />
        </div>
        <div class="bottom_icon" style="padding-top: 1px;">
            <img title="我的信息，账户：@ViewBag.Account" src="${staticImg}/images/bottom_icon_userinfo.png" />
        </div>-->
    </div>
</div>
<div class="clear"></div>
<#--重置密码-->
<@shiro.hasPermission name="/user/resetPassword">
<div id="updateUserPassWordModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">重置密码</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateUserPassWord" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--载进度条start-->
<#--<div id="loading_background" class="loading_background" style="display: none;"></div>
<div id="loading" onclick="Loading(false);">
    <img src="${staticImg}/images/loading.gif" style="vertical-align: middle;" />&nbsp;<span>正在拼了命为您加载…</span>&nbsp;
</div>
<div id="loadingGird">
    <img src="${staticImg}/images/loading.gif" style="vertical-align: middle;" />&nbsp;正在拼了命为您加载…&nbsp;
</div>-->
</body>
</html>