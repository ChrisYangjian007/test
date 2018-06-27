<style>
    .modal-title{
        text-align: center;
    }
</style>
<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
    <@shiro.hasPermission name="/Laboratory/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/Laboratory/addReport">
        <a id="lr-add" title="上传报告(Ctrl+Z)" onclick="uploadingReportsModal()" class="tools_btn">
                <span>
                <b class="btn-add">上传报告</b>
        </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/Laboratory/dataOutput">
        <a id="lr-edit" title="数据导出(Ctrl+W)" onclick="updateEnterpriseModal()" class="tools_btn">
                <span>
                <b class="btn-update">数据导出</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/Laboratory/checkReport">
        <a id="lr-detail" title="查看检验报告" onclick="checkReport()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看检验报告</b>
                        </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/Laboratory/back">
        <div class="tools_separator"></div>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                <b class="btn-back">离开</b>
        </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>
<div class="rightline" style="margin: 1px; margin-top: -1px;">
    <div class="bottomline QueryArea" style="margin: 0 1px;">
        <table border="0" class="form-find" style="height: 45px;">
            <tr id="selectParameter">
                <td>
                    批次：<input id="batchNo" name="batchNo" type="text" class="input-txt" style="width: 100px">
                    货品类型：
                    <select id="goodsTypeId" name="goodsTypeId" class="txtselect" datacol="yes" err="分类"
                            checkexpession="NotNull">
                        <option value="">==请选择==</option>
                    </select>
                    产品名称： <select id="productName" class="txtselect" name="productName" checkexpession="NotNull">
                    <option value="">==请选择==</option>
                </select>
                    收货时间：<input id="StartTime" type="text" class="txt Wdate" style="width: 140px; "
                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    至
                    <input id="TerminalTime" type="text" class="txt Wdate" style="width: 140px; "
                           onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    <input id="btnSearch" type="button" class="btnSearch" value="查 询">
                    <input id="btnClear" type="button" class="btnSearch" value="重 置">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager" style="height: 40px;"></div>
</div>
<!--查看检验报告-->
<@shiro.hasPermission name="/Laboratory/checkReport">
<div id="checkReportModal" class="modal fade " data-width="1200" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" >
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title" >检验报告</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--上传报告-->
<@shiro.hasPermission name="/Laboratory/addReport">
<div id="uploadingReportsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">检验报告</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addZsEnterprise" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<script type="text/javascript">

    $(document).ready(function () {
        initSearch();
        GetGrid();
        gridPagerStyle();
    });
    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/receivetest/GridJson.json",
            datatype: "json",
            height: $(window).height() - 172,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'receiveDetailId', index: 'receiveDetailId', width: 80, hidden: true },
                {label: "货物类型", name: "goodsType", index: "goodsType", width: 120 },
                {label: "批次号", name: "batchNo", index: "batchNo", width: 120 },
                {label: "产品名称", name: "productName", index: "productName", width: 120 },
                {label: "规格", name: "productSpecName", index: "productSpecName", width: 120 },
                {label: "供应商", name: "enterpriseName", index: "enterpriseName", width: 100 },
                {label: "数量", name: "weight", index: "weight", width: 100 },
                {label: "单位", name: "unitName", index: "unitName", width: 100 },
                {label: "收货人", name: "deliverName", index: "deliverName", width: 100 },
                {label: "图片", name: "iamges", index: "iamges", width: 80, hidden: true},
                {
                    label: "收货时间", name: "deliverDate", index: "deliverDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                { label: "检验人", name: "inspectors", index: "inspectors", width: 100 },
                { label: "检验方式", name: "testCName", index: "testCName", width: 100},
                {
                    label: "检验时间", name: "inspectionDate", index: "inspectionDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {

                            return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                { label: "检验结果", name: "testResult", index: "testResult", width: 100,
                    formatter: function (cellvalue) {
                        if(cellvalue==2){
                            return "合格";
                        }else if(cellvalue==3) {
                            return "不合格";
                        }else if(cellvalue==1){
                            return "待检验";
                        }
                    }
                },
                {label: "备注", name: "remarks", index: "remarks", width: 100 }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
    }

    //上传报告
    <@shiro.hasPermission name="/companyProduct/addProduct">
    function uploadingReportsModal() {
        var id = GetJqGridRowValue("#gridTable", "receiveDetailId");
        if(null != id){
            $("#uploadingReportsModal").modal({
                remote: "${request.contextPath}/receivetest/uploadingReportsModal.htm?id=" + id
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#addZsEnterprise").unbind("click");
    $("#addZsEnterprise").click(function () {
        Loading(true,"正在提交...");
        var inspectors = $("#inspectors").val();
        if(""==inspectors){
            $("#cName").focus();
            tipDialog("请输入检验人",4,'a');
            Loading(false);
            return false;
        }
        var inspectionMethod = $("#inspectionMethod").val();
        if(""==inspectionMethod){
            toastr["error"]("请选择检验方式");
            Loading(false);
            return false;
        }
        var testResult = $("#testResult").val();
        if(testResult==1 || ""==testResult){
            toastr["error"]("请选择检验结果");
            Loading(false);
            return false;
        }
        var inspectionDate = $("#inspectionDate").val();
        if(""==inspectionDate){
            toastr["error"]("请选择检验时间");
            Loading(false);
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/receivetest/uploadingReports.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    Loading(true, "正在刷新...");
                    $("#gridTable").trigger("reloadGrid");
                    $("#uploadingReportsModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                    Loading(false);
                    $("#uploadingReportsModal").modal("hide");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false);
            }
        };
        $('#uploadingReportsForm').ajaxSubmit(options);
    });

    </@shiro.hasPermission>

    //查看检验报告
    <@shiro.hasPermission name="/Laboratory/checkReport">
    function checkReport() {
        var iamges =  GetJqGridRowValue("#gridTable","iamges")
        var Id = GetJqGridRowValue("#gridTable", "receiveDetailId");
        var testResult = GetJqGridRowValue("#gridTable", "testResult");
        if(null==Id){
            tipDialog('请选择记录!', 4, 'warning');
        }
        var arrayr = Id.split(",");
        if(arrayr.length>1){
            tipDialog('只能查看单条检验报告!', 4, 'warning');
            return;
        }
        if(testResult=='未报送'||testResult=='待检验'){
            tipDialog('未上传检验报告!', 4, 'warning');
            return;
        }
        if(iamges==""){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }
        $("#checkReportModal").modal({
            remote: "${request.contextPath}/receivetest//checkReportModal.htm?id=" + Id
        });
    }
    </@shiro.hasPermission>
    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        //搜索
        var batchNo = $("#batchNo").val();
        var goodsTypeId = $("#goodsTypeId").val();
        var productName = $("#productName").val();
        var StartTime = $("#StartTime").val();
        var TerminalTime = $("#TerminalTime").val();
        var postData = {batchNo:batchNo,goodsTypeId:goodsTypeId,productName:productName,
            StartTime:StartTime,TerminalTime:TerminalTime}
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/receivetest/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });


    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        //重置表格
        $("#batchNo").val("");
        $("#goodsTypeId").val("");
        $("#productName").val("");
        $("#StartTime").val("");
        $("#TerminalTime").val("");
        var postData = {batchNo:"",goodsTypeId:"",productName:"",StartTime:"",TerminalTime:""}
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/receivetest/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });


    /**
     * 搜索栏初始化
     * */
    function initSearch() {
        $.post("${request.contextPath}/receiveManagement/getGoodsType.json", "", function (res) {
            $("#goodsTypeId").find("option").remove();
            $("#goodsTypeId").append("<option value=''>==请选择==</option>")
            for (i in res) {
                $("#goodsTypeId").append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
            }
        }, "JSON");
        loadProduct("#goodsTypeId", "#productName")
    }

    /**
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productName 产品dom元素
     * */
    function loadProduct(typeId,productName) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productName);
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
            } else {
                productEl.find("option").remove();
                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductByType.json", {type: type}, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
                        productEl.append("<option value=''>==请选择==</option>")
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productTypeName + "'>" + obj[i].productTypeName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                }, "JSON");
            }
        });
    }
    <@shiro.hasPermission name="/companyProduct/updateProduct">
    //产品导出
    function updateEnterpriseModal() {
        window.open("${request.contextPath}/receivetest/updateEnterpriseModal.json","_blank");
    }
    </@shiro.hasPermission>
</script>