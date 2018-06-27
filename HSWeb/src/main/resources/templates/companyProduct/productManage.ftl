

<div class="tools_bar leftline rightline" >
    <div class="PartialButton">
        <@shiro.hasPermission name="/companyProduct/reload">
            <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
            </a>
            <div class="tools_separator"></div>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/addProduct">
            <a id="lr-add" title="新增(Ctrl+Z)" onclick="addProduct()" class="tools_btn">
                <span>
                    <b class="btn-add">新增</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/updateProduct">
            <a id="lr-edit" title="编辑(Ctrl+W)" onclick="updateProduct()" class="tools_btn">
                <span>
                    <b class="btn-update">编辑</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/deleteProduct">
            <a id="lr-delete" title="删除(Ctrl+S)" onclick="deleteProduct()" class="tools_btn">
                <span>
                    <b class="btn-delete">删除</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/templateExport">
            <div class="tools_separator"></div>
            <a id="lr-detail" title="模板导出" onclick="templateExport()" class="tools_btn">
                <span>
                    <b class="btn-export">模板导出</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/productImport">
            <div class="tools_separator"></div>
            <a id="lr-detail" title="产品导入" onclick="productImport()" class="tools_btn">
                <span>
                    <b class="btn-import">产品导入</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/productExport">
            <div class="tools_separator"></div>
            <a id="lr-detail" title="产品导出" onclick="productExport()" class="tools_btn">
                <span>
                    <b class="btn-export">产品导出</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/productExport">
            <div class="tools_separator"></div>
            <a id="lr-detail" title="产品同步" onclick="productSynchronization()" class="tools_btn">
                <span>
                    <b class="btn-reload">产品同步</b>
                </span>
            </a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="/companyProduct/back">
            <div class="tools_separator"></div>
            <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
            </a>
        </@shiro.hasPermission>
    </div>
</div>
<div class="rightline" >
    <div class="bottomline QueryArea" >
        <table border="0" class="form-find" >
            <tr id="selectParameter">
                <td>
                    编码：<input id="txtProductNo" name="productNo" type="text" class="input-txt" style="width: 100px">
                </td>
                <td>
                    分类：
                    <select id="txtType" name="type" class="txtselect" >
                        <#if type??>
                            <option value="">==请选择==</option>
                            <#list type as companyProduct>
                                <option value="${companyProduct.type}">${companyProduct.typeName}</option>
                            </#list>
                        <#else >
                            <option value="">==无数据==</option>
                        </#if>
                    </select>
                </td>
                <td>
                    产品线：
                    <select id="txtProductLineId" name="productLineId" class="txtselect" >
                    <#if companyProducts??>
                        <option value="">==请选择==</option>
                        <#list companyProducts as companyProduct>
                            <option value="${companyProduct.productLineId}">${companyProduct.productLine}</option>
                        </#list>
                    <#else >
                        <option value="">==无数据==</option>
                    </#if>
                    </select>
                </td>
                <td>
                    产品大类：
                    <select id="txtProductCategoryId" name="productCategoryId" class="txtselect" >
                        <option value="">==请先选择产品线==</option>
                    </select>
                </td>
                <td>
                    产品小类：
                    <select id="txtProductTypeId" name="productTypeId" class="txtselect" >
                        <option value="">==请先选择产品大类==</option>
                    </select>
                </td>
                <td>
                    <input id="product-Search" type="button" class="btnSearch" value="查 询">
                    <input id="product-Clear" type="button" class="btnSearch" value="重 置">
                </td>
            </tr>
        </table>
    </div>
    <table id="gridTable"></table>
    <div id="gridPager"></div>
</div>


<@shiro.hasPermission name="/companyProduct/addProduct">
<!--添加产品-->
<div id="addProductModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">添加产品</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addProduct" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/companyProduct/updateProduct">
<!--修改产品-->
<div id="updateProductModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改产品</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateProduct" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<#--删除确认-->
<@shiro.hasPermission name="/companyProduct/deleteProduct">
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

<@shiro.hasPermission name="/companyProduct/productImport">
<!--产品导入-->
<div id="productImportModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">产品导入</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="productImportBtn" type="button" class="btn green">确认</button>
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
            url: "${request.contextPath}/companyProduct/GridJson.json",
            datatype: "json",
            height: $(window).height() - 150,
            autowidth: true,
            colModel: [
                {label: '主键', name: 'productId', index: 'productId', width: 80, hidden: true },
                {label: "产品编码", name: "productNo", index: "productNo", width: 80},
                {label: "产品名称", name: "cname", index: "cName", width: 120 },
                {label: "类别", name: "typeName", index: "typeName", width: 80},
                {label: "产品线", name: "productLine", index: "productLine", width: 120 },
                {label: "产品大类", name: "productCategory", index: "productCategory", width: 120 },
                {label: "产品小类", name: "productTypeName", index: "productTypeName", width: 120 },
                {label: "产品规格", name: "productSpecification", index: "productSpecification", width: 120 },
                {label: '单位', name: 'sysUnitName', index: 'sysUnitName', width: 150 },
                {label: '净重', name: 'netWeight', index: 'netWeight', width: 150 }
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
            /*sortname: 'updateDate',
            sortorder: 'desc',*/
            shrinkToFit: false,
            gridview: true
        });
    }
    //分页布局
    function gridPagerStyle() {
        let width = document.getElementById("gridPager_right").offsetWidth+5;
        $("#gridPager_right").attr("style","width:"+width);
    }

    $("#txtType").on("change",function () {
        Loading(true,"正在获取产品大类...");
        let html = "<option value=''>==请选择==</option>";
        let txtProductLineId =$("#txtProductLineId");
        $("#txtProductCategoryId").html("<option value=''>==请先选择产品线==</option>");
        $("#txtProductTypeId").html("<option value=''>==请先选择产品大类==</option>");
        let type =$("#txtType :selected").val();
        if (""!=type){
            $.post('${request.contextPath}/companyProduct/getProductLineByType.json', {type:type}, function (result) {
                if(result.success){
                    for (let i=0;i<result.obj.length;i++){
                        html+='<option value="'+result.obj[i].productLineId+'">'+result.obj[i].productLine+'</option>';
                    }
                }
                txtProductLineId.html(html);
                Loading(false);
            }, "JSON");
        }else {
            Loading(false);
        }
    });

    $("#txtProductLineId").on("change",function () {
        Loading(true,"正在获取产品大类...");
        let html = "<option value=''>==请先选择产品线==</option>";
        let txtProductTypeHtml = "<option value=''>==请先选择产品大类==</option>";
        let txtProductCategoryId =$("#txtProductCategoryId");
        let txtProductTypeId =$("#txtProductTypeId");
        let txtProductLineId =$("#txtProductLineId :selected").val();
        txtProductTypeId.html(txtProductTypeHtml);
        if (""!=txtProductLineId){
            $.post('${request.contextPath}/companyProduct/getZsCompanyProductByProductType.json', {productLineId:txtProductLineId}, function (result) {
                if(result.success){
                    for (let i=0;i<result.obj.length;i++){
                        html+='<option value="'+result.obj[i].productCategoryId+'">'+result.obj[i].productCategory+'</option>';
                    }
                }
                txtProductCategoryId.html(html);
                Loading(false);
            }, "JSON");
        }else {
            txtProductCategoryId.html(html);
            Loading(false);
        }
    });


    $("#txtProductCategoryId").on("change",function () {
        Loading(true,"正在获取产品小类...");
        var html = "<option value=''>==请先选择产品大类==</option>";
        var txtProductTypeId =$("#txtProductTypeId");
        var txtProductCategoryId =$("#txtProductCategoryId :selected").val();
        if (""!=txtProductCategoryId){
            $.post('${request.contextPath}/companyProduct/getZsCompanyProductByProductType.json', {productCategoryId:txtProductCategoryId}, function (result) {
                if(result.success){
                    for (var i=0;i<result.obj.length;i++){
                        html+='<option value="'+result.obj[i].productTypeId+'">'+result.obj[i].productTypeName+'</option>';
                    }
                }
                txtProductTypeId.html(html);
                Loading(false);
            }, "JSON");
        }else {
            txtProductTypeId.html(html);
            Loading(false);
        }
    });


<@shiro.hasPermission name="/companyProduct/addProduct">
    //新增
    function addProduct() {
        $("#addProductModal").modal({
            remote: "${request.contextPath}/companyProduct/addProductModal.htm?resourceId="+resourceId
        });
    }

    $("#addProduct").unbind("click");
    $("#addProduct").click(function () {
        Loading(true, "正在提交数据...","#addProductModal");
        var cName = $("#addProductCName").val();
        if (""==cName){
            $("#addProductCName").focus();
            tipDialog("请输入产品名称", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }
        var type = $("#addProductType :selected").val();
        if (""==type){
            tipDialog("请选择类别", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }
        var productLineId = $("#addProductProductLineId :selected");
        if (""==productLineId.val()){
            tipDialog("请选择产品线", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }
        var productCategoryId = $("#addProductProductCategoryId :selected");
        if (""==productCategoryId.val()){
            tipDialog("请选择产品大类", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }
        var productTypeId = $("#addProductProductTypeName :selected");
        if (""==productTypeId.val()){
            tipDialog("请选择产品小类", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }else {
            var ProductTypeId = $("#addProductProductTypeId");
            ProductTypeId.val(productTypeId[0].id)
        }
        var productSpecification = $("#addProductProductSpecification").val();
        if (""==productSpecification){
            $("#addProductProductSpecification").focus();
            tipDialog("请输入产品规格", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }
        var ProductParentName = $("#addProductParentName").val();
        if (""==ProductParentName){
            tipDialog("请选择单位", 4, 'a');
            Loading(false,"","#addProductModal");
            return false;
        }
        $.post('${request.contextPath}/companyProduct/addZsCompanyProduct.json', $("#addProductForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#addProductModal").modal('hide')
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#addProductModal");
        }, "JSON");

    });

</@shiro.hasPermission>

<@shiro.hasPermission name="/companyProduct/updateProduct">
    //修改
    function updateProduct() {
        var id = GetJqGridRowValue("#gridTable", "productId");
        if (null!=id){
            $("#updateProductModal").modal({
                remote: "${request.contextPath}/companyProduct/updateProductModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列!", 4, 'warning');
        }
    }

    $("#updateProduct").unbind("click");
    $("#updateProduct").click(function () {
        Loading(true, "正在提交数据...","#updateProductModal");
        var cName = $("#updateProductCName").val();
        if (""==cName){
            $("#updateProductCName").focus();
            tipDialog("请输入产品名称", 4, 'a');
            Loading(false,"","#updateProductModal");
            return false;
        }
        var type = $("#updateType :selected").val();
        if (""==type){
            tipDialog("请选择类别", 4, 'a');
            Loading(false,"","#updateProductModal");
            return false;
        }
        var productLineId = $("#updateProductLineId :selected");
        if (""==productLineId.val()){
            tipDialog("请选择产品线", 4, 'a');
            Loading(false,"","#updateProductModal");
            return false;
        }
        var productCategoryId = $("#updateProductCategoryId :selected");
        if (""==productCategoryId.val()){
            tipDialog("请选择产品大类", 4, 'a');
            Loading(false,"","#updateProductModal");
            return false;
        }
        var productTypeName = $("#updateProductTypeName :selected");
        if (""==productTypeName.val()){
            tipDialog("请选择产品小类", 4, 'a');
            Loading(false,"","#updateProductModal");
            return false;
        }else {
            var productTypeId = $("#updateProductTypeId");
            productTypeId.val(productTypeName[0].id)
        }
        var productSpecification = $("#updateProductSpecification").val();
        if (""==productSpecification){
            $("#updateProductSpecification").focus();
            tipDialog("请输入产品规格", 4, 'a');
            Loading(false,"","#updateProductModal");
            return false;
        }
        $.post('${request.contextPath}/companyProduct/updateZsCompanyProductBy.json', $("#updateProductForm").serialize(), function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
                $("#updateProductModal").modal('hide')
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false,"","#updateProductModal");
        }, "JSON");

    });

</@shiro.hasPermission>
    //产品同步
    function productSynchronization() {
        Loading(true, "正在同步数据...");
        $.post('${request.contextPath}/companyProduct/synchronizationProduct.json',{resourceId: resourceId}, function (result) {
            if(result.success){
                $("#gridTable").trigger("reloadGrid");
                tipDialog(result.msg, 4, '1');
            }else {
                tipDialog(result.msg, 4, '0');
            }
            Loading(false);
        }, "JSON");
    }
    //删除
    <@shiro.hasPermission name="/companyProduct/deleteProduct">
    function deleteProduct() {
        var id = GetJqGridRowValue("#gridTable", "productId");
        if (IsChecked(id)) {
            $("#deleteProductModal").modal("show");
        }
    }
    $("#deleteProduct").unbind("click");
    $("#deleteProduct").click(function () {
        Loading(true, "正在删除数据...","#deleteProductModal");
        var id = GetJqGridRowValue("#gridTable", "productId");
        $.post('${request.contextPath}/companyProduct/deleteZsCompanyProduct.json', {productId: id,resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteProductModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteProductModal");
        }, "json");
    });
    </@shiro.hasPermission>

<@shiro.hasPermission name="/companyProduct/templateExport">
//模板导出
function templateExport() {
    window.open("${request.contextPath}/companyProduct/templateExport.json","_blank");
}
</@shiro.hasPermission>

<@shiro.hasPermission name="/companyProduct/productExport">
//产品导出
function productExport() {
    window.open("${request.contextPath}/companyProduct/productExport.json","_blank");
}
</@shiro.hasPermission>

<@shiro.hasPermission name="/companyProduct/productImport">
//产品导入
function productImport() {
    $("#productImportModal").modal({
        remote: "${request.contextPath}/companyProduct/productImportModal.htm?resourceId="+resourceId
    });
}

$("#productImportBtn").unbind("click");
$("#productImportBtn").click(function () {
    Loading(true, "正在对比、导入数据...","#productImportModal");
    String.prototype.endWith=function(endStr){
        var d=this.length-endStr.length;
        return (d>=0&&this.lastIndexOf(endStr)==d)
    };
    var productImportFile = $("#productImportFile").val();
    if (""==productImportFile){
        tipDialog("请选择要导入的产品文件", 4, 'a');
        Loading(false,"","#productImportModal");
        return false;
    }else {
        if (!(productImportFile.endWith(".xls"))){
            if (!(productImportFile.endWith(".xlsx"))){
                tipDialog("请上传excel格式的文件!", 4, 'a');
                Loading(false,"","#productImportModal");
                return false;
            }
        }
    }
    var options = {
        type: "post",
        cache: false,
        url: '${request.contextPath}/companyProduct/productImport.json',
        success: function (res) {
            if (res.success) {
                tipDialog(res.msg, 4, '1');
                Loading(true, "正在刷新数据...","#productImportModal");
                Replace();
            }else {
                $("#excelErrorTr").removeClass("hidden");
                let msg = res.msg;
                let error = msg.split("，");
                let html="";
                for (let i=0;i<error.length;i++){
                    html+=error[i]+"<br/>";
                }
                $("#excelErrorTd").html(html);
                tipDialog("数据有误，请修改再导入！", 4, '0');
                Loading(false,"","#productImportModal");
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            tipDialog("请求错误!", 4, '0');
            Loading(false,"","#productImportModal");
        }
    };
    $('#productImportForm').ajaxSubmit(options);

});

</@shiro.hasPermission>


    $("#product-Search").unbind("click");
    $("#product-Search").click(function () {
        Loading(true, "正在提交数据...");
        let txtProductNo = $("#txtProductNo").val();
        let txtType = $("#txtType :selected").val();
        let txtProductLineId = $("#txtProductLineId :selected").val();
        let txtProductCategoryId = $("#txtProductCategoryId :selected").val();
        let txtProductTypeId = $("#txtProductTypeId :selected").val();
        let postData ={productNo:txtProductNo,type:txtType,productLineId:txtProductLineId,productCategoryId:txtProductCategoryId,productTypeId:txtProductTypeId};
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/companyProduct/GridJson.json",
            page:1
        }).trigger("reloadGrid");
        Loading(false);
    });


    $("#product-Clear").unbind("click");
    $("#product-Clear").click(function () {
        Loading(true, "正在提交数据...");
        //重置表格
        let txtProductNo = $("#txtProductNo").val("");
        let txtType = $("#txtType").val("");
        let html="<option value=''>==请选择==</option>";
        <#if companyProducts??>
            <#list companyProducts as companyProduct>
                html+="<option value='${companyProduct.productLineId}'>${companyProduct.productLine}</option>";
            </#list>
        </#if>
        $("#txtProductLineId").html(html);
        $("#txtProductCategoryId").html("<option value=''>==请先选择产品线==</option>");
        $("#txtProductTypeId").html("<option value=''>==请先选择产品大类==</option>");
        let postData ={productNo:"",type:"",productLineId:"",productCategoryId:"",productTypeId:""};
        $("#gridTable").jqGrid("setGridParam",{
            postData:postData,
            url:"${request.contextPath}/companyProduct/GridJson.json",
            page:1
        }).trigger("reloadGrid");
        Loading(false);
    });
</script>






