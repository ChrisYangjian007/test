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
<#include "../layout/head.ftl">
<#include "../layout/baseJs.ftl">
    <#--<link href="/styles/learunui-framework.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/scripts/learunui-framework.js"></script>
    <!--框架必需end&ndash;&gt;
    <!--jqgrid表格组件start&ndash;&gt;
    <script type="text/javascript" src="/scripts/jqgrid/jquery-ui-custom.min.js"></script>
    <script type="text/javascript" src="/scripts/jqgrid/grid.locale-cn.js"></script>
    <link href="/scripts/jqgrid/css/jqgrid.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/jqgrid/jqGrid.js"></script>
    <!--表格组件end&ndash;&gt;
    <!--树形组件start&ndash;&gt;
    <link href="/scripts/tree/tree.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/tree/tree.js"></script>
    <!--树形组件end&ndash;&gt;
    <!--布局组件start&ndash;&gt;
    <script type="text/javascript" src="/scripts/layout/splitter.js"></script>
    <!--布局组件end&ndash;&gt;
    <!--日期组件start&ndash;&gt;
    <script type="text/javascript" src="/scripts/datepicker/WdatePicker.js"></script>

    <!--日期组件start&ndash;&gt;
    <!--表单验证组件start&ndash;&gt;
    <script type="text/javascript" src="/scripts/validator/learunui-validator.js"></script>
    <script type="text/javascript" src="/scripts/Common.js"></script>-->
    <!--表单验证组件end-->
    <script type="text/javascript">
        $(document).ready(function () {
            /*Loadlayout();*/
        });
    </script>
    <style>
        #form1 {
            width: 100% !important;
        }
    </style>
</head>
<body>
<#include "${body_file_path}"><#--body页内容动态输出-->
</body>
</html>