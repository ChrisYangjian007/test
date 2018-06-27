<style>
    .btn-search{
        display:inline-block;
        padding:3px 5px;
        border:1px solid #ccc;
    }
</style>
<form id="form1" autocomplete="off" style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableProperty">
                <table class="form">
                    <tr>
                        <th class="formTitle">
                            返库编号：
                        </th>
                        <td class="formValue">
                            <#if receiveNode??>
                                ${receiveNode}
                            </#if>
                            <input type="hidden" id="reBackNo" value="${receiveNode}"/>
                        </td>
                        <th class="formTitle">
                            返库申请人：
                        </th>
                        <td class="formValue">
                            <input id="reBackPerson" autocomplete="on" type="text" class="txt" style="width: 100%">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            经手人：
                        </th>
                        <td class="formValue">
                            <input id="reBackBrokerage" autocomplete="on" type="text" class="txt" style="width: 100%">
                        </td>
                        <th class="formTitle">
                            返库时间：
                        </th>
                        <td class="formValue">
                            <input id="reBackDate" type="text" class="txt Wdate" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            库存预警：
                        </th>
                        <td class="formValue">
                            <input id="reBackStockWarn" type="text" class="txt" style="width: 100%">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">备注：</th>
                        <td class="formValue" colspan="3">
                            <textarea id="reBackRemark" style="width: 100%;height: 50px;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="addStockDetail">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="TabTableField2" class="tab_list bd">入库详情</div>
                </div>
                <div style="float: right;">
                    <div class="tools_bar_icon">
                        <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                        <div class="icon-botton" title="插入行" onclick="rebackInsetTableRow()">
                            <img src="${staticImg}/images/Icon16/table_row_insert.png" />
                        </div>
                        <div class="icon-botton" title="清空行" onclick="EmptyTableRow()">
                            <img src="${staticImg}/images/Icon16/table_row_delete.png" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="ScrollBar" style="margin-top: 1px;">
                <div id="TableField2" class="tabPane2">
                    <table id="grid_Field3" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">编号</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">*成品批次号</div>
                            </td>
                            <td style="width: 60px;">
                                <div class="table-header">*货物类型</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">*产品名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">*产品状态</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*规格</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*返库数量</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*单位</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">*返库仓库</div>
                            </td>
                            <td style="width: 100px; text-align: center;">
                                <div class="table-header">备注</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    var unitNameArr =[];
    $(document).ready(function () {
        var unitNameArr =[];
        rebackInsetTableRow();
        TableTdEvent2();
        var numberId =["#reBackTimeWarn","#reBackStockWarn","#stockInWeight"];
        for(let i =0;i<numberId.length;i++){
            keyUpNumber(numberId[i]);
        }
    });

    //输入的数字不能为负数
    function keyUpNumber(id) {
        var idEl =$(""+id);
        idEl.keyup(function(){
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g,""));
        }).bind("paste",function(){
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g,""));
        }).css("ime-mode", "disabled");
    }
    //插入一个空行
    function rebackInsetTableRow() {
        var len = $("#grid_Field3 tbody tr").length;
        $("#grid_Field3 tbody").append(CreateTableRow2(len + 1)).find('input,select').not('input[name=stockUnitName]').hide();
        $("#enterUnitName"+len).prev("div").text($("#enterUnitName"+len).val());
        var indexrow = 1;
        $("#grid_Field3 tbody tr").each(function () {
            $(this).find('td:eq(0)').html(indexrow);
            $(this).find("input,select").each(function () {
                let indexId = $(this).attr("id");
                let index = indexId.match(/^[a-z|A-Z]+/gi);
                $(this).attr("id", index + indexrow);
            });
            indexrow++;
        });
        $("#stockInWeight"+(len+1)).keyup(function(){
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g,""));
        }).bind("paste",function(){
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g,""));
        }).css("ime-mode", "disabled");
    }

    //清空表格当前行
    function EmptyTableRow() {
            var trobj = $("#grid_Field3 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field3 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        let index = indexId.match(/^[a-z|A-Z]+/gi);
                        $(this).attr("id", index + indexrow);
                    });
                    indexrow++;
                })
            } else {
//                tipCss($(this), "\n");
                tipDialog("请先选择一行！\n", 4, 'warning');
            }
    }

    //表格点击事件
    function TableTdEvent2() {
        $("#TableField2 .grid").on("click",".td-div",function(){
            if($(this).parent().hasClass("selected")){
                $(this).parent().removeClass("selected");
            }else{
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field3 tbody");
        grid.on("click","td",function(){
            if ($(this).find('div').length > 0) {
                var oldText = $(this).find('div').text();
                var obj = $(this).find('input,select');
                if (obj.attr('disabled')) {
                    return false;
                }
                var type = obj.attr('class');
                obj.show();
                obj.parent().find('div').html("");
                obj.trigger("focus");
                obj.blur(function () {
                    if (obj.parent().find('div').length > 0) {
                        var type = obj.attr('type');
                        var objclass = (obj.parent().find('input[type=text]').length > 0) ? obj.parent().find('input[type=text]').attr('class') : "";
                        var newText = "";
                        switch (type) {
                            case "select":
                                newText = obj.find("option:selected").text();
                                if (obj.parent().find('input[type=hidden]').length > 0) {
                                    if(obj.parent().find('input[type=hidden]').attr("class")!="buyaogaiwo"){
                                        obj.parent().find('input[type=hidden]').val(newText);
                                    }
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            } else {
                let obj = $(this).find('input');
                obj.show();
            }
        })
    }

    //创建入库详情行
    function CreateTableRow2(index) {
        var tr = '';
        tr += '<tr class="holdButton">';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockBatchNo' + index + '" type="text" maxlength="10"  class="txt stockBatchNo" name ="stockBatchNo" /></td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsMaterialType(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsProductName(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterProductStatus(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsStandard(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockInWeight' + index + '" type="text" maxlength="10"  class="txt" name ="stockInWeight" /></td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + enterIsUnit(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div>'+stockWareHouse(index)+'</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="stockRemark' + index + '" type="text" maxlength="10"  class="txt" name ="stockRemark"/></td>';
        tr += '</tr>';
        return tr;
    }
    //货物类型
    function enterIsMaterialType(index) {
        var html = '<select id="enterIsMaterial' + index + '" name="stockIsMaterial" class="txtselect stockIsMaterial' + index + '" datacol="no" type="select" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }
    //产品名称
    function enterIsProductName(index){
        var html = '<select id="enterProductName' + index + '" class="txtselect buyaogaiwo productValueId' + index + '" name ="stockProductId"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //规格
    function enterIsStandard(index){
        var html = '<select id="enterProductSpecName' + index + '" name="stockProductSpecName" class="txtselect"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //产品状态
    function enterProductStatus(index){
        var html = '<select id="enterProductStatus' + index + '" name="enterProductStatus" class="txtselect"  datacol="no" type="select" err="产品状态" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }
    //单位
    function enterIsUnit(index){
        var html = '<input id="enterUnitName'+ index +'" autocomplete="off" name="stockUnitName" type="text" class="txt icontree"/>';
        html += '<input id="enterUnitId'+ index +'" name="stockUnitId" type="hidden" class="buyaogaiwo txt icontree"/>';
        return html;
    }

    //仓库类型
    function stockWareHouse(index){
        var html = '<select id="stockWareHouse' + index + '" name="stockWareHouse" class="txtselect"  datacol="no" type="select" err="产品状态" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //入库详情select相互关联
    function initSearch2(num,batchNo) {
        if(batchNo !="") {
            Loading(true, "正在搜索", "#rebackStockModal");
            $.post("${request.contextPath}/stock/getReceiveDetailByBatchNoForReback.json", {batchNo: batchNo}, function (res) {
                $("#enterIsMaterial" + num).find("option").remove();
                var resObj = res.obj;
                if(resObj !=null&&resObj !="") {
                    for (i in resObj) {
                        $("#enterIsMaterial" + num).append("<option value='" + resObj[i].goodsTypeId + "'>" + resObj[i].goodsType + "</option>")
                    }
                }else {
                    tipDialog(res.msg, 2, 'warning');
                }
                Loading(false, "", "#rebackStockModal");
            }, "JSON");
            loadProduct2("#enterIsMaterial" + num, "#enterProductName" + num, "#enterProductSpecName" + num, "#enterUnitName" + num, "#enterUnitId" + num,num,batchNo);
            productStatusList(num, 15);
            getAllWareHouse(num);
        }
    }

    function loadProduct2(typeId, productId, specId, unitName,unitId,num,batchNo) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        var priseEl =$("" + unitId);
        typeEl.prev("div").text("");
        productEl.prev("div").text("");
        specEl.prev("div").text("");
        $("#enterInWeight"+num).prev("div").text("");
        $("#stockUnitName"+num).prev("div").text("");
        $("#enterRelatedType"+num).prev("div").text("");
        typeEl.unbind("click");
        typeEl.click(function () {
            if (typeEl.val() == "") {
                productEl.prev("div").text("");
                specEl.prev("div").text("");
                productEl.find("option").remove();
//                productEl.append("<option value=''>请先选择类型</option>");
            } else {
                productEl.find("option").remove();
//                productEl.append("<option value=''>请先选择类型</option>");
                var goodsTypeId = typeEl.val();
                $.post("${request.contextPath}/stock/getRebackByGoodsType.json", {batchNo:batchNo,goodsTypeId:goodsTypeId}, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
//                        productEl.append("<option value=''>==请选择==</option>")
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productId + "'>" + obj[i].productName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                }, "JSON");
            }
        });
        productEl.unbind("click");
        productEl.click(function () {
            if (typeEl.val() == "") {
                specEl.prev("div").text("");
                specEl.find("option").remove();
//                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                specEl.find("option").remove();
//                specEl.append("<option value=''>请先选择产品</option>");
                var goodsTypeId =typeEl.val();
                var productId = productEl.val();
                $.post("${request.contextPath}/stock/getRebackByProductId.json", {batchNo:batchNo,goodsTypeId:goodsTypeId,productId:productId}, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
//                        specEl.append("<option value=''>==请选择==</option>")
                        var obj = res.obj;
                        for (i in obj) {
                            specEl.append("<option value='" + obj[i].productSpecName + "'>" + obj[i].productSpecName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 2, 'warning');
                    }
                }, "JSON");
            }
        });
        $.post("${request.contextPath}/receiveManagement/getEnterprise.json", {}, function (res) {
            if (res.success) {
                priseEl.find("option").remove();
                priseEl.append("<option value=''>==请选择==</option>")
                var obj = res.obj;
                for (i in obj) {
                    priseEl.append("<option value='" + obj[i].enterpriseId + "'>" + obj[i].cName + "</option>")
                }
            } else {
                tipDialog(res.msg, 2, 'warning');
            }
        }, "JSON");
        $(""+unitName).focus(function () {
            var objId = this.id;
            comboBoxTree(objId, "182px");
            var itemtree = {
                onnodeclick: function (item) {
                    $(""+unitId).attr("value",item.id);
                    $(""+unitName).attr("value",item.text);
                },
                url: "${request.contextPath}/receiveManagement/getUnitName.json"
            };
            $("#comboBoxTree" + objId).treeview(itemtree);
            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
        });
    }

    //查询仓库类型
    function getAllWareHouse(index) {
        $.post("${request.contextPath}/warehouse/getAllZsWarehouseList.json",{},function (res) {
            if(res.success){
                $("#stockWareHouse"+index).find("option").remove();
                let obj =res.obj;
                for(i in obj){
                    if(obj[i].warehouseType !=1) {
                        $("#stockWareHouse" + index).append("<option value='" + obj[i].warehouseId + "'>" + obj[i].cname + "</option>")
                    }
                }
            }
        })
    }
    
    function productStatusList(index,num) {
        $.post("${request.contextPath}/stock/getStockIsMaterialByCodeAndName.json",{id: num},function (res) {
            if(res.success){
                $("#enterProductStatus"+index).find("option").remove();
                let obj =res.obj;
                for(i in obj){
                    $("#enterProductStatus"+index).append("<option value='" + obj[i].dataDictionaryDetailsId + "'>" + obj[i].cname + "</option>");
                }
            }else {
                tipDialog(res.msg, 3, 'warning');
            }
        })
    }

    $("#grid_Field3").on("blur",".stockBatchNo",function () {
        var batchNo =$.trim($(this).val());
        var num =$.trim($(this).parent().prev().text());
        initSearch2(num,batchNo);
    })
</script>