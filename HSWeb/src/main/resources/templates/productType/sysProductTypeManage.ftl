

<div id="layout" class="layout" >
    <!--左边-->
    <div class="layoutPanel layout-west" >
        <div class="btnbartitle">
            <div>
                导航目录
            </div>
        </div>
        <div class="ScrollBar" id="ItemsTree"></div>
    </div>
    <!--中间-->
    <div class="layoutPanel layout-center" >
        <div class="btnbartitle">
            <div>
                模块信息
            </div>
        </div>
        <div class="tools_bar">
            <div class="PartialButton">
                <@shiro.hasPermission name="/productType/reload">
                    <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                    </a>
                    <div class="tools_separator"></div>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/productType/addProductType">
                    <a id="lr-add" title="新增(Ctrl+Z)" onclick="addProductType()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/productType/updateProductType">
                    <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateProductTypeModal()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/productType/deleteProductType">
                    <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteProductType()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/productType/productTypeDetail">
                    <a id="lr-detail" title="详细信息(Ctrl+X)" onclick="productTypeDetail()" class="tools_btn">
                        <span>
                            <b class="btn-detail">详细</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="/productType/back">
                    <div class="tools_separator"></div>
                    <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                    </a>
                </@shiro.hasPermission>
            </div>
        </div>
        <div class="bottomline QueryArea" >
            <table border="0" class="form-find" >
                <tbody>
                <tr id="selectParameter">
                    <td>
                        名称：<input id="txtCName" name="cName" type="text" class="input-txt" style="width: 100px">
                    </td>
                    <td>
                        状态：
                        <select id="txtStatus" name="status" class="txtselect" datacol="yes" err="状态" checkexpession="NotNull">
                            <option value="">==请选择==</option>
                            <option value="1">正常</option>
                            <option value="2">禁用</option>
                        </select>
                    </td>
                    <td>
                        <input id="btnSearch" type="button" class="btnSearch" value="查 询">
                        <input id="btnClear" type="button" class="btnSearch" value="重 置">
                    </td>
                </tr>
                </tbody></table>
        </div>
        <table id="gridTable"></table>
        <div id="gridPager"></div>
    </div>
</div>


<@shiro.hasPermission name="/productType/addProductType">
<!--添加产品类型-->
<div id="addProductTypeModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
            <h4 class="modal-title">添加产品类型</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addProductType" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/productType/updateProductType">
<!--修改产品类型-->
<div id="updateProductTypeModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改产品类型</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateProductType" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>


<@shiro.hasPermission name="/productType/productTypeDetail">
<!--查看产品类型详情-->
<div id="productTypeDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">资源详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn green">离开</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除确认-->
<@shiro.hasPermission name="/productType/deleteProductType">
<div id="deleteProductTypeModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
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
        <button id="deleteProductType" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<script>
    var resourceId;
    $(document).ready(function () {
        GetTree();
        GetGrid();
        gridPagerStyle();
        Loadlayout();
        resourceId= top.$("#ModuleId").val();
    });
    var ID = "0", ALevel = "", Name = "";
    //加载左边树
    function GetTree() {
        var itemtree = {
            onnodeclick: function (item) {
                ID = item.id;            //ID
                ALevel = item.ALevel;            //aLevel
                Name = item.text;        //名称
                var Parameter = [{
                    Name: "ParentID",
                    Value: ID,
                    Comparison: 10
                }];
                $("#gridTable").jqGrid('setGridParam', {
                    url: "${request.contextPath}/productType/GridJson.json?parentId="+ID
                }).trigger('reloadGrid');
            },
            url: "${request.contextPath}/productType/TreeJson.json"
        };
        $("#ItemsTree").treeview(itemtree);
    }
    //加载表格
    function GetGrid() {
        $("#gridTable").jqGrid({
            url: "${request.contextPath}/productType/GridJson.json?parentId=0",
            datatype: "json",
            height: $(window).height() - 180,
            autowidth: true,
            colModel: [
                {label: "主键", name: "productTypeId", index: "productTypeId", hidden: true},
                {label: "名称", name: "cname", index: "cName", width: 100},
                {label: "层次", name: "alevel", index: "aLevel", width: 45, align: "center"},
                {
                    label: '有效', name: 'status', index: 'status', width: 45, align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == null || cellvalue == undefined || cellvalue == 6) return "<img src='${staticImg}/images/checknomark.gif' />";
                        if (cellvalue == 1) return "<img src='${staticImg}/images/checkokmark.gif' />"; else return "<img src='${staticImg}/images/checknomark.gif' />";
                    }
                },
                {label: "说明", name: "memo", index: "memo", width: 400}
            ],
            pagerpos : "right",
            viewrecords : true,
            recordpos : "left",
            pager: "#gridPager",
            rowNum : 10,
            rowList:[10,20,30,50,100],
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            rownumbers: true,
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        var width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
    }


<@shiro.hasPermission name="/productType/addProductType">
//新增
    function addProductType() {
        if (""!=ID&&"0"!=ID){
            if (3>ALevel){
                $("#addProductTypeModal").modal({
                    remote: "${request.contextPath}/productType/addProductTypeModal.htm?id=" + ID+"&resourceId="+resourceId
                });
            }else {
                tipDialog("选中级别不能添加下级，请重新选择!", 4, 'warning');
            }
        }else {
            tipDialog("请选择左侧目录!", 4, 'warning');
        }
    }

    $("#addProductType").unbind("click");
    $("#addProductType").click(function () {
        Loading(true, "正在提交数据...","#addProductTypeModal");
        var cName = $("#cName").val();
        if(""==cName){
            $("#cName").focus();
            tipDialog("请输入名称", 4, 'warning');
            Loading(false,"","#addProductTypeModal");
            return false;
        }
        $.post('${request.contextPath}/productType/addProductType.json', $("#addProductTypeForm").serialize(), function (result) {
            if (result.success) {
                GetTree();
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addProductTypeModal").modal('hide')
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addProductTypeModal");
        }, "JSON");
    });
</@shiro.hasPermission>


<@shiro.hasPermission name="/productType/updateProductType">
//修改
    function updateProductTypeModal() {
       var id = GetJqGridRowValue("#gridTable", "productTypeId");
        if (IsChecked(id)){
            $("#updateProductTypeModal").modal({
                remote: "${request.contextPath}/productType/updateProductTypeModal.htm?id=" + id+"&resourceId="+resourceId
            });
        }
    }

    $("#updateProductType").unbind("click");
    $("#updateProductType").click(function () {
        Loading(true, "正在提交数据...","#updateProductTypeModal");
        var cName = $("#updCName").val();
        if(""==cName){
            $("#updCName").focus();
            tipDialog("请输入名称", 4, 'warning');
            Loading(false,"","#updateProductTypeModal");
            return false;
        }
        $.post('${request.contextPath}/productType/updateProductType.json', $("#updateProductTypeForm").serialize(), function (result) {
            if (result.success) {
                GetTree();
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateProductTypeModal").modal('hide')
            } else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateProductTypeModal");
        }, "JSON");
    });
</@shiro.hasPermission>
    //删除
    <@shiro.hasPermission name="/productType/deleteProductType">
    function deleteProductType() {
        var id = GetJqGridRowValue("#gridTable", "productTypeId");
        if (IsChecked(id)) {
            $("#deleteProductTypeModal").modal("show");
        }
    }
    $("#deleteProductType").unbind("click");
    $("#deleteProductType").click(function () {
        Loading(true, "正在删除数据...","#deleteProductTypeModal");
        var id = GetJqGridRowValue("#gridTable", "productTypeId");
        $.post('${request.contextPath}/productType/deleteProductType.json', {id: id,resourceId:resourceId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteProductTypeModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteProductTypeModal");
        }, "json");
    });
    </@shiro.hasPermission>


<@shiro.hasPermission name="/productType/productTypeDetail">
//详情
    function  productTypeDetail() {
       var id = GetJqGridRowValue("#gridTable", "productTypeId");
        if (IsChecked(id)){
            $("#productTypeDetailModal").modal({
                remote: "${request.contextPath}/productType/productTypeDetailModal.htm?id=" + id
            });
        }
    }
</@shiro.hasPermission>

    $("#btnSearch").unbind("click");
    $("#btnSearch").click(function () {
        var cName = $("#txtCName").val();
        var status = $("#txtStatus").val();
        var postData ={cName:cName,status:status,parentId:ID};
        //提交post并刷新表格
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/productType/GridJson.json",
            page:1
        }).trigger("reloadGrid");
    });

    $("#btnClear").unbind("click");
    $("#btnClear").click(function () {
        //重置表格
        $("#txtCName").val("");
        $("#txtStatus").val("");
        $("#gridTable").jqGrid('setGridParam', {
            postData:{cName:"",status:"",parentId:ID},
            url: "${request.contextPath}/productType/GridJson.json",
            page:1
        }).trigger('reloadGrid');
    });

</script>