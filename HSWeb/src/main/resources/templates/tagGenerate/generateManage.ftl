<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
    <@shiro.hasPermission name="/tagGenerate/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/tagGenerate/addGenerate">

        <a id="lr-add" title="新增(Ctrl+Z)" onclick="addGenerate()" class="tools_btn">
                <span>
                    <b class="btn-add">新增</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/tagGenerate/detailGenerate">

        <a id="lr-edit" title="明细" onclick="detailGenerate()" class="tools_btn">
                <span>
                    <b class="btn-update">明细</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/tagGenerate/downGenerate">

        <a id="lr-edit" title="下载" onclick="downGenerate()" class="tools_btn">
                <span>
                    <b class="btn-down">下载</b>x
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/tagGenerate/deleteGenerate">

        <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteGenerate()" class="tools_btn">
                <span>
                    <b class="btn-delete">删除</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/tagGenerate/importGenerate">

        <a id="lr-detail" title="标签导入" onclick="qrImport()" class="tools_btn">
                <span>
                    <b class="btn-import">二维码导入</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
        </a>
    </div>
</div>
<div class="rightline" style="margin: 1px; margin-top: -1px;">
    <#--<div class="bottomline QueryArea" style="margin: 0 1px;">-->
        <#--<table border="0" class="form-find" style="height: 45px;">-->
            <#--<tr id="selectParameter">-->
                <#--<td>-->
                    <#--编码：<input id="txtGenerateNo" name="productNo" type="text" class="input-txt" style="width: 100px">-->
                <#--</td>-->

                <#--<td>-->
                    <#--<input id="btnSearch" type="button" class="btnSearch" value="查 询">-->
                    <#--<input id="btnClear" type="button" class="btnSearch" value="重 置">-->
                <#--</td>-->
            <#--</tr>-->
        <#--</table>-->
    <#--</div>-->
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>


<@shiro.hasPermission name="/companyProduct/addProduct">
<!--新增-->
<div id="addGenerateModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增二维码</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addGenerate" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/companyProduct/updateProduct">
<!--详情-->
<div id="detailGenerateModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">明细</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<@shiro.hasPermission name="/companyProduct/deleteProduct">
<#--删除确认-->
<div id="deleteProductModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteProduct" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<div id="qrImportModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">导入</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="qrImportBtn" type="button" class="btn green">确认</button>
    </div>
</div>


<script type="text/javascript">
    var resourceId;
    $(document).ready(function () {
        GetGrid();
        gridPagerStyle();
        resourceId= top.$("#ModuleId").val();
    });

    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/tagGenerate/GridJson.json",
            datatype: "json",
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'generateId', index: 'generateId', width: 80, hidden: true},
                {label: "总码段", name: "allCode", index: "allCode", width: 80, align: "center"},
                {label: "生成量", name: "tagCount", index: "cName", width: 120, align: "center"},
                {label: "已使用码段", name: "allotedCode", index: "allotedCode", width: 120, align: "center"},
                {label: "已使用量", name: "alreadyCount", index: "typeName", width: 80, align: "center"},
                {label: "可用码段", name: "unAllotCode", index: "unAllotCode", width: 80, align: "center"},
                {label: "可用量", name: "remainCount", index: "remainCount", width: 120, align: "center"},
                {label: "创建时间", name: "createDate", index: "createDate", width: 120, align: "center"},
                {label: '导入状态', name: 'isPrint', index: 'isPrint', width: 120, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if("1"==cellvalue){
                            return "已导入";
                        }else {
                            return "未导入";
                        }
                    }

                },
                {label: "导入量", name: "importCount", index: "importCount", width: 120, align: "center"},
                {label: "导入时间", name: "importDate", index: "importDate", width: 120, align: "center"}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            /*sortname: 'updateDate',
            sortorder: 'desc',*/
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth + 5;
        $("#gridPager_right").attr("style", "width:" + width);
    }

    //导入
    function qrImport() {
        $("#qrImportModal").modal({
            remote: "${request.contextPath}/tagGenerate/qrImportGenerateModal.htm"
        });
    }

    $("#qrImportBtn").unbind("click");
    $("#qrImportBtn").click(function () {
        Loading(true, "正在对比、导入数据...", "#qrImportModal");
        String.prototype.endWith = function (endStr) {
            var d = this.length - endStr.length;
            return (d >= 0 && this.lastIndexOf(endStr) == d)
        };
        var qrImportFile = $("#qrImportFile").val();
        if ("" == qrImportFile) {
            tipDialog("请选择要导入的txt文件", 4, 'a');
            Loading(false, "", "#qrImportModal");
            return false;
        } else {
            if (!(qrImportFile.endWith(".txt"))) {
                tipDialog("请上传txt格式的文件!", 4, 'a');
                Loading(false, "", "#qrImportModal");
                return false;
            }
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/tagGenerate/importQrCode.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    Loading(false, "", "#qrImportModal");
                    $("#gridTable").trigger("reloadGrid");
                    $("#qrImportModal").modal("hide");
                }else {
                    tipDialog(res.msg, 4, '0');
                    Loading(false, "", "#qrImportModal");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求错误!", 4, '0');
                Loading(false, "", "#qrImportModal");
            }
        };
        $('#qrImportForm').ajaxSubmit(options);

    });


    <@shiro.hasPermission name="/companyProduct/addProduct">
    //新增
    function addGenerate() {
        $("#addGenerateModal").modal({
            remote: "${request.contextPath}/tagGenerate/addGenerateModal.htm?resourceId="+resourceId
        });
    }
    $("#addGenerate").unbind("click");
    $("#addGenerate").click(function () {
        var tagCount = $("#tagCount").val();
        if (tagCount == "") {
            tipDialog("请填写二维码数量！", 4, "a");
            return
        }
        Loading(true, "正在提交数据...");
        $.post('${request.contextPath}/tagGenerate/addGenerate.json', $("#addGenerateForm").serialize(), function (result) {
            if (result.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addGenerateModal").modal('hide')
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false);
        }, "JSON");

    });
    </@shiro.hasPermission>
    //加载表格
    function GetGrid1(ruleName, startCode, endCode) {
        console.log("jieshuma...................." + endCode)
        $("#gridTable1").jqGrid({
            url: "${request.contextPath}/tagSweep/GridJson.json",
            datatype: "json",
            postData: {
                ruleName: ruleName,
                startCode: startCode,
                endCode: endCode
            },
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'sweepId', index: 'sweepId', width: 80, hidden: true},
                {label: "二维码编码", name: "qrCode", index: "qrCode", width: 80, align: "center"},
                {label: "成品批次号", name: "batchNo", index: "batchNo", width: 80, align: "center"},
                {label: "成品产品名称", name: "productName", index: "productName", width: 120, align: "center"},
                {label: "绑定产品规格", name: "productSpecName", index: "productSpecName", width: 80, align: "center"},
                {label: "扫描总次数", name: "batchCount", index: "batchCount", width: 120, align: "center"},
                {label: "最近扫码IP", name: "updateIp", index: "updateIp", width: 120, align: "center"},
                {label: "最近扫码时间", name: "updateDate", index: "updateDate", width: 120, align: "center"},
                {label: "最近扫码区域", name: "updateArea", index: "updateArea", width: 120, align: "center"},
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager1",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle1() {
        var width = document.getElementById("gridPager1_right").offsetWidth + 5;
        $("#gridPager1_right").attr("style", "width:" + width);
    }





    <@shiro.hasPermission name="/companyProduct/updateProduct">
    //详情
    function detailGenerate() {
        var id = GetJqGridRowValue("#gridTable", "generateId");
        if (null != id) {
            $("#detailGenerateModal").modal({
                remote: "${request.contextPath}/tagGenerate/detailGenerateModal.htm?id=" + id
            });
        } else {
            tipDialog("请选择您要修改的列!", 4, 'warning');
        }
    }

    </@shiro.hasPermission>
    //下载
    function downGenerate() {
        var id = GetJqGridRowValue("#gridTable", "generateId");
        if (null != id) {
            window.open("${request.contextPath}/tagGenerate/downGenerateZip.htm?generateId=" + id, "_blank");
        <#--$("#dialog-down").dialog({-->
        <#--resizable: false,-->
        <#--height:140,-->
        <#--modal: true,-->
        <#--buttons: {-->
        <#--"确认": function() {-->
        <#--$( this ).dialog( "close" );-->
        <#--$.post('${request.contextPath}/tagGenerate/downGenerate.json',{generateId: id},function (result) {-->
        <#--if(result.success){-->
        <#--window.open("${request.contextPath}/tagGenerate/downGenerateZip.htm?generateId="+id,"_blank");-->
        <#--}else {-->
        <#--tipDialog(result.msg, 4, '0');-->
        <#--}-->
        <#--}, "JSON");-->
        <#--},-->
        <#--"取消": function() {-->
        <#--$( this ).dialog( "close" );-->
        <#--}-->
        <#--}-->
        <#--});-->

        } else {
            tipDialog("请选择您要删除的列", 4, 'warning');
        }

    }
    <@shiro.hasPermission name="/companyProduct/deleteProduct">
    //删除
    function deleteGenerate() {
        let id = GetJqGridRowValue("#gridTable", "generateId");
        if (IsChecked(id)) {
            $("#deleteProductModal").modal("show");
        }
    }
    $("#deleteProduct").unbind("click");
    $("#deleteProduct").click(function () {
        Loading(true, "正在删除数据...", "#deleteProductModal");
        let id = GetJqGridRowValue("#gridTable", "generateId");
        $.post('${request.contextPath}/tagGenerate/deleteGenerate.json', {generateId: id}, function (result) {
            if (result.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#deleteProductModal").modal("hide");
            } else {
                tipDialog(result.msg, 4, '0');
                $("#deleteProductModal").modal("hide");
            }
            Loading(false, "", "#deleteProductModal");
        }, "JSON");
    });
    </@shiro.hasPermission>

    //产品导出
    function productExport() {

        window.open("${request.contextPath}/companyProduct/productExport.json", "_blank");
    }





    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var txtGenerateNo = $("#txtGenerateNo").val();
        var postData = {productNo: txtGenerateNo};
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/companyProduct/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
    });


    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        //重置表格
        var txtProductNo = $("#txtProductNo").val("");
        var txtType = $("#txtType").val("");
        var txtProductLineId = $("#txtProductLineId").val("");
        var txtProductCategoryId = $("#txtProductCategoryId").val("");
        var txtProductTypeId = $("#txtProductTypeId").val("");
        var postData = {productNo: "", type: "", productLineId: "", productCategoryId: "", productTypeId: ""};
        $("#gridTable").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/companyProduct/GridJson.json",
            page: 1
        }).trigger("reloadGrid");
    });
</script>






