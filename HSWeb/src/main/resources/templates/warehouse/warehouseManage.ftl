


<div class="tools_bar leftline rightline" style="margin-bottom: 0px; margin: 1px;">
    <div class="PartialButton">
    <@shiro.hasPermission name="/warehouse/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/addWarehouse">
        <a id="lr-add" title="新增(Ctrl+Z)" onclick="addWarehouseModal()" class="tools_btn">
                <span>
                    <b class="btn-add">新增</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/updateWarehouse">
        <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateWarehouseModal()" class="tools_btn">
                <span>
                    <b class="btn-update">编辑</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/deleteWarehouse">
        <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteWarehouse()" class="tools_btn">
                <span>
                    <b class="btn-delete">删除</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/warehouseDetail">
        <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="warehouseDetailModal()" class="tools_btn">
                        <span>
                            <b class="btn-detail">详细</b>
                        </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/back">
        <div class="tools_separator"></div>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/uploadWarehouseImages">
        <a title="上传登记表" onclick="uploadWarehouseImages()" class="tools_btn">
                <span>
                    <b class="btn-uploadImages">上传登记表</b>
                </span>
        </a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/warehouse/warehouseImagesHistory">
        <a title="查看历来记录" onclick="warehouseImagesHistory()" class="tools_btn">
            <span>
                <b class="btn-detail">查看历来记录</b>
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
                    名称：<input id="txtCName" name="cName" type="text" class="input-txt" style="width: 100px">
                </td>
                <td>
                    负责人：
                    <select id="txtManagerId" name="managerId" class="txtselect" datacol="yes" err="负责人" checkexpession="NotNull">
                        <#if baUserList??>
                            <option value="">==请选择==</option>
                            <#list baUserList as users>
                                <option value="${users.userId}">${users.CName}</option>
                            </#list>
                        </#if>
                    </select>
                </td>
                <td>
                    <input id="btnSearch" type="button" class="btnSearch" value="查 询">
                    <input id="btnClear" type="button" class="btnSearch" value="重 置">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>


<@shiro.hasPermission name="/warehouse/addWarehouse">
<!--添加仓库-->
<div id="addWarehouseModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" align="center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加仓库</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addWarehouse" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/warehouse/updateWarehouse">
<!--修改仓库信息-->
<div id="updateWarehouseModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" align="center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改仓库</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateWarehouse" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除-->
<@shiro.hasPermission name="/warehouse/deleteWarehouse">
<div id="deleteWarehouseModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteWarehouse" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/warehouse/updateWarehouse">
<!--查看仓库信息-->
<div id="warehouseDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" align="center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title" >仓库详细</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/warehouse/uploadWarehouseImages">
<!--上传登记表-->
<div id="uploadWarehouseImagesModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" align="center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" >上传登记表</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="uploadWarehouseImages" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/warehouse/warehouseImagesHistory">
<!--查看历来记录-->
<div id="warehouseImagesHistoryModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" align="center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" >查看历来记录</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">确定</button>
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
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/warehouse/GridJson.json",
            datatype: "json",
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'warehouseId', index: 'warehouseId', width: 80, hidden: true },
                {label: "仓库名称", name: "cname", index: "cName", width: 120 },
                {label: "仓库类型", name: "warehouseType", index: "warehouseType", width: 120 ,
                    formatter: function (cellvalue, options, rowObject) {
                        if (0==cellvalue){
                            return "无";
                        }else if (1==cellvalue){
                            return "活参库";
                        }
                    }
                },
                {label: "预警天数", name: "warningDay", index: "warningDay", width: 120 ,
                    formatter: function (cellvalue, options, rowObject) {
                        return cellvalue+"天";
                    }
                },
                {label: "描述", name: "remark", index: "remark", width: 250 },
                {label: "主要负责人", name: "warehouseUserName", index: "warehouseUserName", width: 250 },
                { label: "创建用户", name: "createUserName", index: "createUserName", width: 100 },
                {
                    label: "创建时间", name: "createDate", index: "createDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                { label: "修改用户", name: "updateUserName", index: "updateUserName", width: 100 },
                {
                    label: "修改时间", name: "updateDate", index: "updateDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        if("" == rowObject.updateUserName){
                            return "";
                        }else {
                            return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                        }
                    }
                },
                {
                    label: "本月登记表上传", name: "warehouseImageSize", index: "warehouseImageSize", width: 100,align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if("" ==cellvalue){
                            return "未上传";
                        }else {
                            return cellvalue;
                        }
                    }
                },
                {
                    label: "最新上传时间", name: "warehouseImageUpdateDate", index: "warehouseImageUpdateDate", width: 150,align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if("" ==cellvalue){
                            return rowObject.warehouseImageCreateDate;
                        }else {
                            return cellvalue;
                        }
                    }
                },
                {
                    label: "最新上传用户", name: "warehouseImageCreateName", index: "warehouseImageCreateName", width: 150,align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if("" ==cellvalue){
                            return rowObject.warehouseImageCreateName;
                        }else {
                            return cellvalue;
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


<@shiro.hasPermission name="/warehouse/uploadWarehouseImages">
    function uploadWarehouseImages() {
        let warehouseId = GetJqGridRowValue("#gridTable", "warehouseId");
        if (IsChecked(warehouseId)) {
            $("#uploadWarehouseImagesModal").modal({
                remote: "${request.contextPath}/warehouseImage/uploadWarehouseImagesModal.htm?warehouseId=" + warehouseId
            });
            $('#uploadWarehouseImagesModal').on('loaded.bs.modal', function (e) {
                $("#uploadWarehouseImages").attr('disabled',true);
            });
        }
    }
    $("#uploadWarehouseImages").unbind("click");
    $("#uploadWarehouseImages").click(function () {
        //Loading(true,"正在上传...","#uploadWarehouseImagesModal");
        let warehouseImageId = $("#warehouseImageId").val();
        if (""==warehouseImageId) {
            tipDialog("无任何变化，不能提交!", 4, 'a');
            Loading(false, "", "#uploadWarehouseImagesModal");
            return false;
        }
        let size=0;
        $('#uploadWarehouseImagesForm').find("input[name=warehouseImage]").each(function () {
            let imageUrl=$(this).val();
            if (""!=imageUrl) {
                let index = imageUrl.split(".");
                let imageType = index[(index.length - 1)];
                if (imageType != "bmp" && imageType != "png" && imageType != "gif"
                        && imageType != "jpg" && imageType != "jpeg") {
                    tipDialog("请上传图片格式的文件!", 4, 'a');
                    Loading(false, "", "#uploadWarehouseImagesModal");
                    return false;
                }
            }else {
                size++;
            }
        });
        console.log(size);
        if (2==size){
            tipDialog("图片有误，请重新选择！", 4, 'a');
            Loading(false, "", "#uploadWarehouseImagesModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/warehouseImage/uploadWarehouseImages.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable").trigger("reloadGrid");
                    $("#uploadWarehouseImagesModal").modal('hide');
                }
                else {
                    tipDialog(res.msg, 4, '0');

                }
                Loading(false, "", "#uploadWarehouseImagesModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false, "", "#uploadWarehouseImagesModal");
            }
        };
        $('#uploadWarehouseImagesForm').ajaxSubmit(options);
    });
</@shiro.hasPermission>

    <@shiro.hasPermission name="/warehouse/warehouseImagesHistory">
    function warehouseImagesHistory() {
        let warehouseId = GetJqGridRowValue("#gridTable", "warehouseId");
        if (IsChecked(warehouseId)) {
            $("#warehouseImagesHistoryModal").modal({
                remote: "${request.contextPath}/warehouseImage/warehouseImagesHistoryModal.htm?warehouseId=" + warehouseId
            });
            $('#warehouseImagesHistoryModal').on('loaded.bs.modal', function (e) {
                $("#warehouseImagesHistory-gridTable").jqGrid({
                    url: "${request.contextPath}/warehouseImage/GridJson.json?warehouseId="+warehouseId,
                    datatype: "json",
                    height: 400,
                    autowidth: true,
                    colModel: [
                        {label: '主键', name: 'warehouseImageId', index: 'warehouseImageId', width: 100, hidden: true },
                        {label: "时间", name: "uploadMonth", index: "uploadMonth", width: 100,align: "center",
                            formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m'}
                        },
                        {label: '上传', name: 'imagesSize', index: 'imagesSize',width: 80,align: "center"},
                        {label: '最新上传时间', name: 'updateDate', index: 'updateDate', width: 150,align: "center",
                            formatter: function (cellvalue, options, rowObject) {
                                if (""==cellvalue){
                                    return rowObject.createDate;
                                } else {
                                    return cellvalue;
                                }
                            }
                        },
                        {label: "最新上传用户", name: "updateUserName", index: "updateUserName", width: 100,align: "center",
                            formatter: function (cellvalue, options, rowObject) {
                                if (""==cellvalue){
                                    return rowObject.createUserName;
                                } else {
                                    return cellvalue;
                                }
                            }
                        }
                    ],
                    pagerpos : "right",
                    recordpos : "left",
                    viewrecords: true,
                    rownumbers: true,
                    rowNum: 10,
                    rowList: [10, 30, 50, 100, 500],
                    pager: "#warehouseImagesHistory-gridPager",
                    jsonReader:{
                        root:"root",page:"page",total:"total",records:"records"
                    },
                    shrinkToFit: false,
                    gridview: true
                });
            });
        }
    }
    </@shiro.hasPermission>

<@shiro.hasPermission name="/warehouse/addWarehouse">
//新增
    function addWarehouseModal() {
        $("#addWarehouseModal").modal({
            remote: "${request.contextPath}/warehouse/addWarehouseModal.htm?resourceId="+resourceId
        });
    }
    $("#addWarehouse").unbind("click");
    $("#addWarehouse").click(function () {
        Loading(true,"正在提交...","#addWarehouseModal");
        var warehouseUserId = $("#warehouseUserId").val();
        if(null==warehouseUserId){
            tipDialog("请选择仓库负责人",4,"warning");
            Loading(false,"","#addWarehouseModal");
            return false;
        }
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入仓库名称",4,"warning");
            Loading(false,"","#addWarehouseModal");
            return false;
        }
        var warningDay = $("#warningDay").val();
        if(""==warningDay){
            $("#warningDay").focus();
            tipDialog("请输入预警天数",4,"warning");
            Loading(false,"","#addWarehouseModal");
            return false;
        }
        $.post("${request.contextPath}/warehouse/addWarehouse.json", $("#addWarehouseForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addWarehouseModal").modal('hide');
            }else {
                tipDialog(result.msg,4,'0');
            }
            Loading(false,"","#addWarehouseModal");
        }, "JSON");
    });
</@shiro.hasPermission>


<@shiro.hasPermission name="/warehouse/updateWarehouse">
//修改
    function updateWarehouseModal() {
        var id = GetJqGridRowValue("#gridTable", "warehouseId");
        if(null != id){
            $("#updateWarehouseModal").modal({
                remote: "${request.contextPath}/warehouse/updateWarehouseModal.htm?id=" + id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateWarehouse").unbind("click");
    $("#updateWarehouse").click(function () {
        Loading(true,"正在提交...","#updateWarehouseModal");
        var warehouseUserId = $("#updWarehouseUserId").val();
        if(null==warehouseUserId){
            tipDialog("请选择仓库负责人",4,"warning");
            Loading(false,"","#updateWarehouseModal");
            return false;
        }
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("请输入仓库名称",4,"warning");
            Loading(false,"","#updateWarehouseModal");
            return false;
        }
        var warningDay = $("#updWarningDay").val();
        if(""==warningDay){
            $("#updWarningDay").focus();
            tipDialog("请输入预警天数",4,"warning");
            Loading(false,"","#updateWarehouseModal");
            return false;
        }else {
            $("#updWarningDay").val(warningDay.replace(/,/gi,''));
        }
        $.post("${request.contextPath}/warehouse/updateWarehouse.json", $("#updateWarehouseForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateWarehouseModal").modal('hide');
            }else {
                tipDialog(result.msg,4,'0');
            }
            Loading(false,"","#updateWarehouseModal");
        }, "JSON");
    });
</@shiro.hasPermission>


<@shiro.hasPermission name="/warehouse/warehouseDetail">
//详细
    function warehouseDetailModal() {
        var id = GetJqGridRowValue("#gridTable", "warehouseId");
        if(null != id){
            $("#warehouseDetailModal").modal({
                remote: "${request.contextPath}/warehouse/warehouseDetailModal.htm?id=" + id
            });
        }else {
            tipDialog("请选择您要查看的列",4,"warning");
        }
    }
</@shiro.hasPermission>

    //删除
<@shiro.hasPermission name="/warehouse/deleteWarehouse">
    function deleteWarehouse() {
        var id = GetJqGridRowValue("#gridTable", "warehouseId");
        if (IsChecked(id)) {
            $("#deleteWarehouseModal").modal("show");
        }
    }
    $("#deleteWarehouse").unbind("click");
    $("#deleteWarehouse").click(function () {
        Loading(true,"正在删除数据...","#deleteWarehouseModal");
        var id = GetJqGridRowValue("#gridTable", "warehouseId");
        $.post('${request.contextPath}/warehouse/deleteWarehouse.json',{id: id,resourceId:resourceId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteWarehouseModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteWarehouseModal");
        }, "json");
    });
    </@shiro.hasPermission>

    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var cName = $("#txtCName").val();
        var managerId = $("#txtManagerId").val();
        var postData ={warehouseName:cName,userWarehouseId:managerId};
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/warehouse/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });

    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        //重置表格
        $("#txtCName").val("");
        $("#txtManagerId").val("");
        $("#gridTable").jqGrid('setGridParam', {
            postData:{warehouseName:"",userWarehouseId:""},
            url: "${request.contextPath}/warehouse/GridJson.json",
            page:1
        }).trigger('reloadGrid');
    });

</script>