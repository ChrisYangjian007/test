<style>
    .modal-title {
        text-align: center;
    }
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height: auto;
        vertical-align: text-top;
        padding-top: 2px;
        word-break: break-all;
    }
    #headingOne{
        background-color: #cccccc;
    }
    .select2-container .select2-selection__choice{
        line-height: 1.42857;
        margin: 3px 0;
    }
    .select2-selection--multiple{
        line-height:18px  ;
    }
</style>
<div>
    <div class="layoutPanel layout-center"
         style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
            <@shiro.hasPermission name="/receiveManagement/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/receiveManagement/back">
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                </a>
            </@shiro.hasPermission>
            </div>
        </div>
        <div class="line"></div>
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
                <a id="lr-detail" title="数据导出" onclick="reportCountExport()" class="tools_btn">
                        <span>
                            <b class="btn-export">数据导出</b>
                        </span>
                </a>  
            </div>
        </div>
        <div class="line"></div>
        <div class="tab-content">
            <!--进行中-->
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default noborder">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title tit">
                            <span style="font-size: 11pt;margin-left: 400pt">筛选条件</span>
                            <a  style=""  role="button" class="disBtn" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-menu-up" aria-hidden="true"    ></span>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
                            <form id="reportCountJson">
                            <table border="0" class="form-find" style="height: 45px;">
                                <tbody>
                                <tr>
                                    <td>
                                        货物类型：
                                        <select id="isMaterial" name="materialArray" style="width: 550px;height: 18px" class="selectOption" multiple>
                                            <option value="">===请选择===</option>
                                        </select>
                                    </td>
                                    <td>
                                        产品大类：
                                        <select id="productTypeId" name="productTypeArray" style="width: 550px" class="selectOption" multiple>
                                            <option value="">请先选择类型</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        产品小类：
                                        <select id="productId" name="productIdArray" style="width: 550px" class="selectOption" multiple>
                                            <option value="">请先选择类型</option>
                                        </select>
                                    </td>
                                    <td>
                                        规&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp格：
                                        <select id="reportProductSpecName" name="productSpecArray" style="width: 550px" class="selectOption" multiple>
                                            <option value="">请先选择产品</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        单&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp位：
                                        <select id="reportUnitId" name="unitId" style="width: 550px" class="selectOption" multiple>
                                            <option value="">请先选择产品</option>
                                    </td>
                                    <td>
                                        <div class="pull-right">
                                            <input id="reportBtnSearch" type="button" class="btnSearch" value="查 询">
                                            <input id="reportBtnClear" type="button" class="btnSearch" value="重 置">
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
                <table id="reportCountGridTable" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
                       aria-multiselectable="false" aria-labelledby="gbox_gridTable" class="ui-jqgrid-btable"
                       style="width: 1341px;">
                </table>
                <div id="reportCountGridPager" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
                     style="width: 1300px;height: 40px">
                </div>
        </div>
    </div>
</div>

<script>
    var reportPostJson;
    var resourceId;
    $(document).ready(function () {
        resourceId = top.$("#ModuleId").val();
        GetGrid("${request.contextPath}/stock/getReportCountList.json", "#reportCountGridPager", "#reportCountGridTable");
        AppendIsMaterialOption();
    });
    //数据导出
    function reportCountExport() {
        window.open("${request.contextPath}/stock/reportCountExportData.json?"+reportPostJson);
    }

    function GetGrid(url, id, table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 174,
            autowidth: true,
            colModel: [
                {label: "", name: "enterStockDetailId", index: "enterStockDetailId", hidden: true},
                {label: "货物类型", name: "materialName", index: "isMaterial"},
                {label: "产品大类", name: "productTypeName", index: "productTypeId", hidden: true},
                {label: "产品小类", name: "productName", index: "productId", hidden: true},
                {label: "规格", name: "productSpecName", index: "productSpecName", hidden: true},
                {label: "总库存数量", name: "weightSum", index: "weightSum"},
                {label: "最近30天新增数量", name: "inWeightSum", index: "inWeightSum"},
                {label: "单位", name: "unitName", index: "unitId", hidden: true}
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "" + id,
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true,
            multiselect: false//开启全选
        });
        //表格宽度自适应
        $(function(){
            $(window).resize(function(){
                $("#reportCountGridTable").setGridWidth($(window).width());
            });
        });
    }
    $(".selectOption").select2({
        language:"zh-CN",
        placeholder:'请选择'
    });

    //添加入库详情已有的货物类型
    function AppendIsMaterialOption() {
        $.ajax({
            url: "${request.contextPath}/stock/getIsMaterialOption.json",
            type: "POST",
            success: function (res) {
                var resObj = res.obj;
                if (resObj != null) {
                    for (let i = 0; i < resObj.length; i++) {
                        $("#isMaterial").append('<option value="' + resObj[i].isMaterial + '">' + resObj[i].materialName + '</option>')
                    }
                } else {
                    tipDialog(res.msg, 4, 0);
                    Loading(false)
                }
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                Loading(false)
            }
        })
    }
    //筛选框联动：产品大类
    $("#isMaterial").on("select2:close",function () {
        $("#productTypeId").attr("title","");
        $("#productTypeId").empty();
        $("#productId").attr("title","");
        $("#productId").empty();
        $("#reportProductSpecName").attr("title","");
        $("#reportProductSpecName").empty();
        $("#reportUnitId").attr("title","");
        $("#reportUnitId").empty();
        var isMaterial =$.trim($(this).val());
        if(isMaterial !="") {
            $.ajax({
                url: "${request.contextPath}/stock/getProductTypeByStock.json?array=" + isMaterial,
                type: "POST",
                success: function (res) {
                    var resObj = res.obj;
                    if (resObj != null) {
                        for (let i = 0; i < resObj.length; i++) {
                            $("#productTypeId").append('<option value="' + resObj[i].productTypeId + '">' + resObj[i].productTypeName + '</option>')
                        }
                    } else {
                        tipDialog(res.msg, 4, 0);
                        Loading(false)
                    }
                },
                error: function () {
                    tipDialog("网络异常", 0, 2);
                    Loading(false)
                }
            })
        }
    });
    //筛选框联动：产品小类
    $("#productTypeId").on("select2:close",function () {
        $("#productId").attr("title","");
        $("#productId").empty();
        $("#reportProductSpecName").attr("title","");
        $("#reportProductSpecName").empty();
        $("#reportUnitId").attr("title","");
        $("#reportUnitId").empty();
        var materialArray =$.trim($("#isMaterial").val());
        var productTypeArray =$.trim($(this).val());
        if(materialArray !=""&&productTypeArray !=""){
            $.ajax({
                type: "POST",
                url: "${request.contextPath}/stock/getProductIdListFromStock.json?materialArray="+materialArray+"&&productTypeArray="+productTypeArray,
                success: function (res) {
                    var resObj = res.obj;
                    if (resObj != null) {
                        for (let i = 0; i < resObj.length; i++) {
                            $("#productId").append('<option value="' + resObj[i].productId + '">' + resObj[i].productName + '</option>')
                        }
                    } else {
                        tipDialog(res.msg, 4, 0);
                        Loading(false)
                    }
                },
                error: function () {
                    tipDialog("网络异常", 0, 2);
                    Loading(false)
                }
            })
        }
    });
    //筛选框联动：规格
    $("#productId").on("select2:close",function () {
        $("#reportProductSpecName").attr("title","");
        $("#reportProductSpecName").empty();
        $("#reportUnitId").attr("title","");
        $("#reportUnitId").empty();
        var materialArray =$.trim($("#isMaterial").val());
        var productTypeArray =$.trim($("#productTypeId").val());
        var productIdArray =$.trim($(this).val());
        if(materialArray !=""&&productTypeArray!=""&&productIdArray!=""){
            $.ajax({
                type: "POST",
                url: "${request.contextPath}/stock/getProductSpecNameFromStock.json?materialArray="+materialArray+"&&productTypeArray="+productTypeArray+"&&productIdArray="+productIdArray,
                success: function (res) {
                    var resObj = res.obj;
                    console.log(resObj);
                    if (resObj != null) {
                        for (let i = 0; i < resObj.length; i++) {
                            $("#reportProductSpecName").append('<option value="' + resObj[i].productSpecName + '">' + resObj[i].productSpecName + '</option>')
                        }
                    } else {
                        tipDialog(res.msg, 4, 0);
                        Loading(false)
                    }
                },
                error: function () {
                    tipDialog("网络异常", 0, 2);
                    Loading(false)
                }
            })
        }
    });
    //筛选框联动：单位
    $("#reportProductSpecName").on("select2:close",function () {
        $("#reportUnitId").attr("title","");
        $("#reportUnitId").empty();
        var materialArray =$.trim($("#isMaterial").val());
        var productTypeArray =$.trim($("#productTypeId").val());
        var productIdArray =$.trim($("#productId").val());
        var productSpecArray =$.trim($(this).val());
        var dataStr ="?materialArray="+materialArray+"&&productTypeArray="+productTypeArray+"&&productIdArray="+productIdArray+"&&productSpecArray="+productSpecArray;
        if(materialArray !=""&&productTypeArray!=""&&productIdArray!=""&&productSpecArray!=""){
            $.ajax({
                type: "POST",
                url:"${request.contextPath}/stock/getUnitIdFromStock.json"+dataStr ,
                success: function (res) {
                    var resObj = res.obj;
                    console.log(resObj);
                    if (resObj != null) {
                        for (let i = 0; i < resObj.length; i++) {
                            $("#reportUnitId").append('<option value="' + resObj[i].unitId + '">' + resObj[i].unitName + '</option>')
                        }
                    } else {
                        tipDialog(res.msg, 4, 0);
                        Loading(false)
                    }
                },
                error: function () {
                    tipDialog("网络异常", 0, 2);
                    Loading(false)
                }
            })
        }
    });

    $("#reportBtnSearch").unbind("click");
    $("#reportBtnSearch").click(function () {
        reportPostJson =$("#reportCountJson").serialize();
        var materialArray =$.trim($("#isMaterial").val());
        var productTypeArray =$.trim($("#productTypeId").val());
        var productIdArray =$.trim($("#productId").val());
        var productSpecArray =$.trim($("#reportProductSpecName").val());
        var unitArray =$.trim($("#reportUnitId").val());
        var postData = {
            materialArray:materialArray,
            productTypeArray:productTypeArray,
            productIdArray:productIdArray,
            productSpecArray:productSpecArray
        };
        //提交post并刷新表格
        if(productTypeArray =="") {
            $("#reportCountGridTable").hideCol("productTypeName");
        }else {
            $("#reportCountGridTable").showCol("productTypeName");
        }
        if(productIdArray =="") {
            $("#reportCountGridTable").hideCol("productName");
        }else {
            $("#reportCountGridTable").showCol("productName");
        }
        if(productSpecArray =="") {
            $("#reportCountGridTable").hideCol("productSpecName");
        }else {
            $("#reportCountGridTable").showCol("productSpecName");
        }
        if(unitArray ==""){
            $("#reportCountGridTable").hideCol("unitName");
        }else {
            $("#reportCountGridTable").showCol("unitName");
        }
        $("#reportCountGridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stock/getReportCountList.json",
            page: 1
        }).trigger("reloadGrid");
    });

    //重置表格
    $("#reportBtnClear").unbind("click");
    $("#reportBtnClear").click(function () {
        //重置表格
        $("#IsMaterial").attr("title","");
        $("#isMaterial").empty();
        $("#productTypeId").attr("title","");
        $("#productTypeId").empty();
        $("#productId").attr("title","");
        $("#productId").empty();
        $("#reportProductSpecName").attr("title","");
        $("#reportProductSpecName").empty();
        $("#reportUnitId").attr("title","");
        $("#reportUnitId").empty();
        var postData = {
            materialArray:"",
            productTypeArray:"",
            productIdArray:"",
            productSpecArray:""
        };
        //提交post并刷新表格
        $("#reportCountGridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/stock/getReportCountList.json",
            page: 1
        }).trigger("reloadGrid");
        AppendIsMaterialOption();
    });

    $(".disBtn").click(function() {
        //console.log($(this).hasClass("collapsed"));
        if($(this).hasClass("collapsed")){
            $(this).children("span").removeClass();
            $(this).children("span").addClass("glyphicon glyphicon-menu-down");
        }else{
            $(this).children("span").removeClass();
            $(this).children("span").addClass("glyphicon glyphicon-menu-up");
        }
    })
</script>