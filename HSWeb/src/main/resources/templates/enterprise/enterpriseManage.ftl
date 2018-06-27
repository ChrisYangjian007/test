<style>
    .modal-title{
        text-align: center;
    }
</style>
    <div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
            <div class="PartialButton">
        <@shiro.hasPermission name="/enterprise/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                <b class="btn-reload">刷新</b>
                </span>
                </a>
                <div class="tools_separator"></div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/enterprise/addEnterprise">
        <a id="lr-add" title="新增(Ctrl+Z)" onclick="addenterpriseModal()" class="tools_btn">
                <span>
                <b class="btn-add">新增</b>
        </span>
        </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/enterprise/updateEnterprise">
        <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateEnterpriseModal()" class="tools_btn">
                <span>
                <b class="btn-update">编辑</b>
        </span>
        </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/enterprise/deleteEnterprise">
        <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteEnterprise()" class="tools_btn">
                <span>
                <b class="btn-delete">删除</b>
        </span>
        </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/enterprise/enterpriseDetail">
        <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="enterpriseDetail()" class="tools_btn">
                <span>
                    <b class="btn-detail">详细</b>
                </span>
         </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/enterprise/enterpriseImage">
            <a id="lr-detail" title="查看图片" onclick="enterpriseImage()" class="tools_btn">
                <span>
                    <b class="btn-detail">查看图片</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/enterprise/back">
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
                供应商名称：<input id="txtProductNo" name="cName" type="text" class="input-txt" style="width: 100px">
                    <input id="btnSearch" type="button" class="btnSearch" value="查 询">
                    <input id="btnClear" type="button" class="btnSearch" value="重 置">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>

<!--添加供应商-->
<@shiro.hasPermission name="/enterprise/addEnterprise">
<div id="addenterpriseModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加供应商</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addZsEnterprise" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<!--修改供应商-->
<@shiro.hasPermission name="/enterprise/updateEnterprise">
<div id="updateEnterpriseModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改供应商</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateEnterprise" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/enterprise/enterpriseDetail">
<div id="enterpriseDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">供应商详细</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button  type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除-->
<@shiro.hasPermission name="/enterprise/deleteEnterprise">
<div id="deleteEnterpriseModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteEnterprise" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/enterprise/enterpriseImage">
<#--查看图片-->
<div id="enterpriseImageModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>

<script type="text/javascript">
    var resourceId;
    $(document).ready(function () {
        GetGrid();
        gridPagerStyle();
        resourceId= top.$("#ModuleId").val();
    });
    //加载表格
    function GetGrid(){
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/enterprise/GridJson.json",
            datatype: "json",
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'enterpriseId', index: 'enterpriseId', width: 80, hidden: true },
                {label: "供应商名称", name: "cname", index: "cName", width: 80 },
                {label: "供应货品", name: "shortName", index: "shortName", width: 80 },
                {
                    label: "供应商类型", name: "enterpriseType", index: "enterpriseType", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if(1==cellvalue){
                            return "原料供应";
                        }else if(2==cellvalue){
                            return "其他供应";
                        }else {
                            return "";
                        }
                    }
                },
                {label: "所属海域", name: "corporateRep", index: "corporateRep", width: 80 },
                {label: "联系人", name: "contact", index: "contact", width: 80 },
                { label: "联系方式", name: "phone", index: "phone", width: 100 },
                { label: "详细地址", name: "address", index: "address", width: 100 },
                { label: "电子邮件", name: "email", index: "email", width: 80 },
                { label: "创建用户", name: "createUserName", index: "createUserName", width: 80 },
                {
                    label: "创建时间", name: "createDate", index: "createDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {
                    label: "营业执照", name: "businessLicenseImage", index: "businessLicenseImage", width: 80,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "未上传";
                        }else {
                            return "已上传";
                        }
                    }
                },
                {
                    label: "营业执照有效期", name: "businessLicenseDate", index: "businessLicenseDate", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "未选择";
                        }else {
                            return formatDate(cellvalue, 'yyyy-MM-dd');
                        }
                    }
                },
                {
                    label: "生产许可证", name: "productionLicenseImage", index: "productionLicenseImage", width: 80,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "未上传";
                        }else{
                            return "已上传";
                        }
                    }
                },
                {
                    label: "生产许可证有效期", name: "productionLicenseDate", index: "productionLicenseDate", width: 110,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "未选择";
                        }else {
                            return formatDate(cellvalue, 'yyyy-MM-dd');
                        }
                    }
                },
                {
                    label: "其他", name: "otherLicenseImageList", index: "otherLicenseImageList", width: 80,
                    formatter: function (cellvalue, options, rowObject) {
                        if(null!=cellvalue&&0!=cellvalue.length){
                            return "已上传";
                        }else {
                            return "未上传";
                        }
                    }
                },
                {
                    label: "其他有效期", name: "otherLicenseDate", index: "otherLicenseDate", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==cellvalue){
                            return "未选择";
                        }else {
                            return formatDate(cellvalue, 'yyyy-MM-dd');
                        }
                    }
                }
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

    //新增
    <@shiro.hasPermission name="/enterprise/addEnterprise">
    function addenterpriseModal() {
        $("#addenterpriseModal").modal({
            remote: "${request.contextPath}/enterprise/addenterpriseModal.htm?resourceId="+resourceId
        });
    }
    $("#addZsEnterprise").unbind("click");
    $("#addZsEnterprise").click(function () {
        Loading(true,"正在提交...","#addenterpriseModal");
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入供应商名称",4,'a');
            Loading(false,"","#addenterpriseModal");
            return false;
        }
        var contact = $("#contact").val();
        if(""==contact){
            tipDialog("请输入联系人",4,'a');
            Loading(false,"","#addenterpriseModal");
            return false;
        }
        var enterpriseType = $("#enterpriseType").val();
        var businessLicenseImage = $("#licenseImage1").val();
        var businessLicenseDate = $("#businessLicenseDate").val();
        var productionLicenseDate = $("#productionLicenseDate").val();
        var productionLicenseImage = $("#licenseImage2").val();
        console.log("enterpriseType:"+enterpriseType+"  businessLicenseImage:"+businessLicenseImage+"  productionLicenseImage:"+productionLicenseImage)
        if(""==enterpriseType){
            tipDialog("请选择供应商类型",4,'a');
            Loading(false,"","#addenterpriseModal");
            return false;
        } else {
            if(2==enterpriseType){
                if(""==businessLicenseDate){
                    tipDialog("请选择营业执照有效期",4,'a');
                    Loading(false,"","#addenterpriseModal");
                    return false;
                }
                if(""==productionLicenseDate){
                    tipDialog("请选择生产许可证有效期",4,'a');
                    Loading(false,"","#addenterpriseModal");
                    return false;
                }
                if(""==businessLicenseImage){
                    tipDialog("请上传营业执照照片",4,'a');
                    Loading(false,"","#addenterpriseModal");
                    return false;
                }
                if(""==productionLicenseImage){
                    tipDialog("请上传生产许可证照片",4,'a');
                    Loading(false,"","#addenterpriseModal");
                    return false;
                }
            }
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/enterprise/addZsEnterprise.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable").trigger("reloadGrid");
                    $("#addenterpriseModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addenterpriseModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
            }
        };
        $('#addenterpriseForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>

    //修改
    <@shiro.hasPermission name="/enterprise/updateEnterprise">
    function updateEnterpriseModal() {
        var id = GetJqGridRowValue("#gridTable", "enterpriseId");
        if(null != id){
            $("#updateEnterpriseModal").modal({
                remote: "${request.contextPath}/enterprise/updateEnterpriseModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateEnterprise").unbind("click");
    $("#updateEnterprise").click(function () {
        Loading(true,"正在提交...","#updateEnterpriseModal");
        var cName = $("#cNameUp").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入供应商名称",4,'a');
            Loading(false,"","#updateEnterpriseModal");
            return false;
        }
        var contact = $("#contactUp").val();
        if(""==contact){
            tipDialog("请输入联系人",4,'a');
            Loading(false,"","#updateEnterpriseModal");
            return false;
        }
        var enterpriseType = $("#enterpriseTypeUp").val();
        var businessLicenseImage = $("#updLicenseImage1").val();
        var businessLicenseDate = $("#businessLicenseDateUp").val();
        var productionLicenseDate = $("#productionLicenseDateUp").val();
        var productionLicenseImage = $("#updLicenseImage2").val();
        var updLicenseImageId1 = $("#updLicenseImageId1").val();
        var updLicenseImageId2 = $("#updLicenseImageId2").val();
        if(""==enterpriseType){
            tipDialog("请选择供应商类型",4,'a');
            Loading(false,"","#updateEnterpriseModal");
            return false;
        }else {
            if(2==enterpriseType){
                if(""==businessLicenseDate){
                    tipDialog("请选择营业执照有效期",4,'a');
                    Loading(false,"","#updateEnterpriseModal");
                    return false;
                }
                if(""==productionLicenseDate){
                    tipDialog("请选择生产许可证有效期",4,'a');
                    Loading(false,"","#updateEnterpriseModal");
                    return false;
                }
                if(1==updLicenseImageId1){
                        tipDialog("请上传营业执照照片",4,'a');
                        Loading(false,"","#updateEnterpriseModal");
                        return false;
                }
                if(2==updLicenseImageId1){
                    if(""==businessLicenseImage){
                        tipDialog("请上传营业执照照片",4,'a');
                        Loading(false,"","#updateEnterpriseModal");
                        return false;
                    }
                }
                if(1==updLicenseImageId2){
                        tipDialog("请上传生产许可证照片",4,'a');
                        Loading(false,"","#updateEnterpriseModal");
                        return false;
                }
                if(2==updLicenseImageId2){
                    if(""==productionLicenseImage){
                        tipDialog("请上传生产许可证照片",4,'a');
                        Loading(false,"","#updateEnterpriseModal");
                        return false;
                    }
                }
            }
        }

        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/enterprise/updateZsEnterprise.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable").trigger("reloadGrid");
                    $("#updateEnterpriseModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateEnterpriseModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
            }
        };
        $('#updateEnterpriseForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>

    //详细
    <@shiro.hasPermission name="/enterprise/enterpriseDetail">
    function enterpriseDetail() {
        var id = GetJqGridRowValue("#gridTable", "enterpriseId");
        console.log(id);
        if(null != id){
            $("#enterpriseDetailModal").modal({
                remote: "${request.contextPath}/enterprise/enterpriseDetailModal.htm?id=" + id
            });
        }else {
            tipDialog("请选择您要查看的列",4,"warning");
        }
    }
    </@shiro.hasPermission>

    //删除
    <@shiro.hasPermission name="/enterprise/deleteEnterprise">
    function deleteEnterprise() {
        var id = GetJqGridRowValue("#gridTable", "enterpriseId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteEnterpriseModal").modal("show");
        }
    }
    $("#deleteEnterprise").unbind("click");
    $("#deleteEnterprise").click(function () {
        Loading(true,"正在删除...","#deleteEnterpriseModal");
        var enterpriseId = GetJqGridRowValue("#gridTable", "enterpriseId");
        $.post("${request.contextPath}/enterprise/deleteEnterprise.json", {enterpriseId: enterpriseId,resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteEnterpriseModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
                $("#deleteEnterpriseModal").modal("hide");
            }
            Loading(false,"","#deleteEnterpriseModal");
        }, "json");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/enterprise/enterpriseImage">
        function enterpriseImage() {
            var id = GetJqGridRowValue("#gridTable", "enterpriseId");
            if(null != id){
                var businessLicenseImage = GetJqGridRowValue("#gridTable", "businessLicenseImage");
                var productionLicenseImage = GetJqGridRowValue("#gridTable", "productionLicenseImage");
                var otherLicenseImage = GetJqGridRowValue("#gridTable", "otherLicenseImageList");
                if(businessLicenseImage=="未上传"&&productionLicenseImage=="未上传"&&otherLicenseImage=="未上传"){
                    tipDialog("未上传图片，无法查看",4,"warning");
                }else {
                    $("#enterpriseImageModal").modal({
                        remote: "${request.contextPath}/enterprise/enterpriseImageModal.htm?id=" + id
                    });
                }
            }else {
                tipDialog("请选择您要查看图片的列",4,"warning");
            }
        }
    </@shiro.hasPermission>
    
    
    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var txtProductNo = $("#txtProductNo").val();
        var postData ={productNo:txtProductNo};
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/enterprise/select.json",
            page:1
        }).trigger("reloadGrid");
    });


    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        //重置表格
        var txtProductNo = $("#txtProductNo").val("");
        var postData ={productNo:""};
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/enterprise/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });
</script>