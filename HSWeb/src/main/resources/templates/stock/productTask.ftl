<style>
    .productList {
        overflow: hidden;
    }

    .productTitle {
        font-size: 0;
        padding: 10px 10px 5px 0;
        border-bottom: 1px solid #ddd;
    }

    .productTitle > a {
        font-size: 14px;
        padding: 7px;
    }

    .productList {
        padding-top: 20px;
    }

    .productList > li {
        float: left;
        width: 16.66%;
        text-align: center;
        margin: 5px 0;
    }

    .productList > li > a {
        display: inline-block;
        width: 90%;
        padding: 10px 5%;
        border: 1px solid #ddd;
        border-radius: 5px;
    }

    .productActive {
        border: 1px solid #ddd;
        border-bottom: 0;
        background: #fff;
    }

    .productRemove {
        background: #ddd;
        border: 1px solid #ccc;
    }

    .clickBg {
        background: #ccc;
    }

    a:link, a:visited {
        text-decoration: none; /*超链接无下划线*/
    }
</style>

<div id="productDiv1">
    <div class="productTitle">
        <span class="pull-right"><em style="color:red;margin-right:5px;">*</em>仅限选择一个</span>
        <a id="product-1" class="productActive">全部</a>
    </div>
    <div id="productBox-1">
        <ul id="productList-1" class="productList">
        <#if parentOneList??>
            <#list parentOneList as p>
                <li>
                    <a id="${p.workFlowId}" href="javascript:void(0);"
                       onclick="methodA(${p.workFlowId},this)">${p.CName}
                    </a>
                </li>
            </#list>
        </#if>
        </ul>
    </div>
</div>

<script>
    function methodA(parentId, obj1) {
        Loading(true, "加载中", "#workProcessNameModal");
        var th = obj1;
        var txt1 = $(th).text();
        $(th).parents(".productList").find("a").removeClass("clickBg");
        $.post("${request.contextPath}/stock/getchildA.json", {parentId: parentId}, function (res) {
            if (null != res && "" != res) {
                $("#addWorkProcessName").attr("disabled", true);
                $("#productList-1").find("li").remove();
                $(".productTitle").append("<a id='product-2' class='productRemove' value=" + parentId + ">" + txt1 + "</a>");
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodB(' + n.workFlowId + ',this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                $(th).addClass("clickBg");
                $("#addWorkProcessName").removeAttr("disabled");
                tipDialog("没有下一级了", 3, 2);
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    }

    function methodB(parentId, obj2) {
        Loading(true, "加载中", "#workProcessNameModal");
        var th = obj2;
        var txt2 = $(th).text();
        $(th).parents(".productList").find("a").removeClass("clickBg");
        $.post("${request.contextPath}/stock/getchildB.json", {parentId: parentId}, function (res) {
            if (null != res && res != "") {
                $("#addWorkProcessName").attr("disabled", true);
                $("#productList-1").find("li").remove();
                $(".productTitle").append("<a id='product-3' class='productRemove' value=" + parentId + ">" + txt2 + "</a>");
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodC(' + n.workFlowId + ',this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                $(th).addClass("clickBg");
                tipDialog("没有下一级了", 3, 2);
                $("#addWorkProcessName").removeAttr("disabled");
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    }

    function methodC(parentId, obj3) {
        Loading(true, "加载中", "#workProcessNameModal");
        var th = obj3;
        var txt3 = $(th).text();
        $(th).parents(".productList").find("a").removeClass("clickBg");
        $.post("${request.contextPath}/stock/getchildC.json", {parentId: parentId}, function (res) {
            if (null != res && res != "") {
                $("#addWorkProcessName").attr("disabled", true);
                $("#productList-1").find("li").remove();
                $(".productTitle").append("<a id='product-4' class='productRemove' value=" + parentId + ">" + txt3 + "</a>");
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodD(this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                $(th).addClass("clickBg");
                tipDialog("没有下一级了", 3, 2);
                $("#addWorkProcessName").removeAttr("disabled");
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    }

    function methodD(obj) {
        Loading(true, "加载中", "#workProcessNameModal");
        $("#addWorkProcessName").removeAttr("disabled");
        var th = obj;
        $(th).parents(".productList").find("a").removeClass("clickBg");
        $(th).addClass("clickBg");
        Loading(false, "", "#workProcessNameModal");
    }

    //点击全部
    $("#product-1").click(function () {
        Loading(true, "加载中", "#workProcessNameModal");
        $("#product-2").remove();
        $("#product-3").remove();
        $("#product-4").remove();
        $("#productList-1").find("li").remove();
        $("#addWorkProcessName").attr("disabled", true);
        $.post("${request.contextPath}/stock/getParent.json", "", function (res) {
            if (null != res && res != "") {
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodA(' + n.workFlowId + ',this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                tipDialog("没有下一级了", 3, 2);
                $("#addWorkProcessName").removeAttr("disabled");
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    });
    //点击第二级
    $(".productTitle").on("click", "#product-2", function () {
        Loading(true, "加载中", "#workProcessNameModal");
        var parentId = $(this).attr("value");
        $("#product-3").remove();
        $("#product-4").remove();
        $("#productList-1").find("li").remove();
        $("#addWorkProcessName").attr("disabled", true);
        $.post("${request.contextPath}/stock/getchildA.json", {parentId: parentId}, function (res) {
            if (null != res && res != "") {
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodB(' + n.workFlowId + ',this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                tipDialog("没有下一级了", 3, 2);
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    });
    //点击第三级
    $(".productTitle").on("click", "#product-3", function () {
        Loading(true, "加载中", "#workProcessNameModal");
        $("#product-4").remove();
        var parentId = $(this).attr("value");
        $("#productList-1").find("li").remove();
        $("#addWorkProcessName").attr("disabled", true);
        $.post("${request.contextPath}/stock/getchildB.json", {parentId: parentId}, function (res) {
            if (null != res && res != "") {
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodC(' + n.workFlowId + ',this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                tipDialog("没有下一级了", 3, 2);
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    });
    //点击第四级
    $(".productTitle").on("click", "#product-4", function () {
        Loading(true, "加载中", "#workProcessNameModal");
        var parentId = $(this).attr("value");
        $("#productList-1").find("li").remove();
        $("#addWorkProcessName").attr("disabled", true);
        $.post("${request.contextPath}/stock/getchildC.json", {parentId: parentId}, function (res) {
            if (null != res && res != "") {
                $.each(res, function (i, n) {
                    $("#productList-1").append('<li><a id="' + n.workFlowId + '" onclick="methodD(this)">' + n.cname + '</a></li>');
                });
                Loading(false, "", "#workProcessNameModal");
            } else {
                tipDialog("没有下一级了", 3, 2);
                Loading(false, "", "#workProcessNameModal");
            }
        }, "JSON");
    });
</script>