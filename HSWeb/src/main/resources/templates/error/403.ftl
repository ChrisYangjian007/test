<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>403-权限不足</title>
        <link href="${staticCss}/styles/learunui-startmenu.css" rel="stylesheet" type="text/css" />
        <link href="${staticCss}/styles/learunui-framework.css" rel="stylesheet" type="text/css" />
        <link href="${staticImg}/images/login/xqhs.ico" rel="shortcut icon" type="image/x-icon" />
        <link href="${staticCss}/styles/error.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="${staticJs}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${staticJs}/js/jquery.blockUI.js"></script>
        <script type="text/javascript" src="${request.contextPath}/js/learunui-framework.js"></script>
        <script type="text/javascript" src="${staticJs}/scripts/lhgdialog/lhgdialog.min.js"></script>

    <body style="height: 600px">
    <div class="error-403">
        <a onclick="errorGoHome()"></a>
    </div>
    <#--<div class="row">
        <#-- class="col-md-12 page-500">
            <div class=" number font-red"> 403 </div>
            <div class=" details">
                <h3>您当前账号的权限不足，无法进入该页面！</h3>
                <p>
                    <a href="${request.contextPath}/login.htm" class="btn red btn-outline"> 返回首页 </a>
                    <br> </p>
            </div>
        </div>
    </div>-->
    </body>

</html>