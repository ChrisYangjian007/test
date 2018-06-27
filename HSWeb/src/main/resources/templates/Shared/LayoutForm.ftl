<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <#--<#attempt>-->
        <#--<title>${title_name}</title>-->
    <#--<#recover>-->
        <#--<title>标题</title>-->
    <#--</#attempt>-->
    <!--框架必需start-->
    <link href="/styles/learunui-framework.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/scripts/learunui-framework.js"></script>
    <!--框架必需end-->
    <!--树形组件start-->
    <link href="/scripts/tree/tree.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/tree/tree.js"></script>
    <!--树形组件end-->
    <!--日期组件start-->
    <script type="text/javascript" src="/scripts/datepicker/WdatePicker.js"></script>
    <!--日期组件start-->
    <!--表单验证组件start-->
    <script type="text/javascript" src="/scripts/validator/learunui-validator.js"></script>
    <!--表单验证组件end-->
    <!--布局组件start-->
    <script type="text/javascript" src="/scripts/layout/splitter.js"></script>
    <script type="text/javascript" src="/scripts/Common.js"></script>
    <!--布局组件end-->
    <script type="text/javascript">
        $(document).ready(function () {
            Loadlayout();
        });
    </script>
    <style>
        #form1 {
            width:100% !important;
        }
    </style>
</head>
<body>
    <div>
        <#include "${body_file_path}"><#--body页内容动态输出-->
    </div>
</body>
</html>