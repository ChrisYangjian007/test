<form id="form1" style="margin: 1px">
    <div class="ScrollBar" style="margin-top: 1px;">
                <div id="TableField4" class="tabPane3">
                    <table id="grid_Field4" class="grid" style="width: 100%">
                            <thead>
                                <tr>
                            <th style="width: 50px; text-align: center;">
                                <div class="table-header">批次号</div>
                            </th>
                            <th style="width: 60px;">
                                <div class="table-header">*货物类型</div>
                            </th>
                            <th style="width: 50px;">
                                <div class="table-header">*产品名称</div>
                            </th>
                            <th style="width: 60px; text-align: center;">
                                <div class="table-header">*产品状态</div>
                            </th>
                            <th style="width: 50px; text-align: center;">
                                <div class="table-header">*入库规格</div>
                            </th>
                            <th style="width: 60px; text-align: center;">
                                <div class="table-header">*库存数量</div>
                            </th>
                            <th style="width: 50px; text-align: center;">
                                <div class="table-header">*单位</div>
                            </th>
                            <th style="width: 100px; text-align: center;">
                                <div class="table-header">*入库时间</div>
                            </th>
                            <th style="width: 50px; text-align: center;">
                                <div class="table-header">*库存预警</div>
                            </th>
                        </tr>
                            </thead>
                    <tbody>
                        <#if stockList??>
                            <#list stockList as stock>
                                <tr class="holdButton updateTr">
                                    <!--批次号-->
                                    <td style="width: 50px; text-align: center;">
                                        <div>${stock.batchNo!}</div>
                                        <input type="hidden" value="${stock.stockId}" name="updateStockId">
                                        <input id="updateBatchNo${stock.stockId}" type="hidden" maxlength="10" value="${stock.batchNo}" class="txt updateStockBatchNo" name ="updateStockBatchNo" />
                                    </td>
                                    <!--货物类型-->
                                    <td style="width: 60px; text-align: center;">
                                        <div></div>
                                        <select id="updateIsMaterial${stock.stockId}" name="updateIsMaterial" class="txtselect updateIsMaterial" datacol="no" type="select" checkexpession="NotNull">
                                            <option value="${stock.isMaterial!}">${stock.materialName}</option>
                                        </select>
                                    </td>
                                    <!--产品名称-->
                                    <td style="width: 60px; text-align: center;">
                                        <div></div>
                                        <select id="updateProductId${stock.stockId}" name="updateProductId" class="txtselect updateProductId" datacol="no" type="select" checkexpession="NotNull">
                                                <option value="${stock.productId!}" selected>${stock.productName!}</option>
                                        </select>
                                    </td>
                                    <!--产品状态-->
                                    <td style="width: 60px; text-align: center;">
                                        <div></div>
                                        <select id="updateProductStatus${stock.stockId}" name="updateProductStatus" class="txtselect updateProductStatus" datacol="no" type="select" checkexpession="NotNull">
                                            <option value="${stock.productStatus}">${stock.productStatusName}</option>
                                        </select>
                                    </td>
                                    <!--入库规格-->
                                    <td style="width: 50px; text-align: center;">
                                        <div></div>
                                        <select id="updateProductSpecName${stock.stockId}" name="updateProductSpecName"
                                                class="txtselect updateProductSpecName" datacol="yes"
                                                err="状态"
                                                checkexpession="NotNull">
                                            <option value="${stock.productSpecName!}">${stock.productSpecName!}</option>
                                        </select>
                                    </td>
                                    <!--库存数量-->
                                    <td style="width: 60px; text-align: center;">
                                        <div></div>
                                        <input id="updateWeight${stock.stockId}" class="updateStockWeight" name="updateWeight" type="text" value="${stock.weight?c}" maxlength="7"/>
                                    </td>
                                    <!--单位-->
                                    <td style="width: 60px; text-align: center;">
                                        <select id="updateUnitId${stock.stockId}" name="updateUnitId" class="txtselect unitId123" datacol="yes" err="分类" checkexpession="NotNull">
                                            <#if units??>
                                                <option value="">==请选择==</option>
                                                <#list units as a>
                                                    <option value="${a.unitId!}"
                                                            <#if stock.unitId??><#if stock.unitId==a.unitId>selected</#if></#if>
                                                    >${a.CName!}</option>
                                                </#list>
                                            </#if>
                                        </select>
                                    </td>
                                    <td>
                                        <input id="updateUpdateDate${stock.stockId}" name="updateUpdateDate" type="text" class="txt Wdate"
                                               value="${(stock.updateDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                               onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                    </td>
                                    <!--库存预警-->
                                    <td style="width: 50px; text-align: center;">
                                        <div></div>
                                        <input id="updateStockWarn${stock.stockId}" name="updateStockWarn" type="text" value="${stock.stockWarn!}" maxlength="10"  class="txt" />
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                    </tbody>
                    </table>
                </div>
            </div>
</form>
<script>
    $(function () {
        $(".updateStockWeight").keyup(function(){
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g,""));
        }).bind("paste",function(){
            var tmptxt=$(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g,""));
        }).css("ime-mode", "disabled");
    });
    $(".updateIsMaterial").click(function () {
        var isMaterialId =$(this).attr("id");
        var stockId =$.trim(isMaterialId.replace("updateIsMaterial",""));
        updateInitSearch2(stockId);
    });

    //select相互关联
    function updateInitSearch2(num) {
        $.post("${request.contextPath}/receiveManagement/getGoodsType.json", "", function (res) {
            $("#updateIsMaterial"+num).find("option").remove();
            $("#updateIsMaterial"+num).append("<option value=''>==请选择==</option>");
            for (i in res) {
                $("#updateIsMaterial"+num).append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
            }
        }, "JSON");
        updateLoadProduct("#updateIsMaterial"+num, "#updateProductId"+num, "#updateProductSpecName"+num);
    }

    function updateLoadProduct(typeId, productId, specId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        typeEl.unbind("click");
        typeEl.click(function () {
            typeEl.prev().text("");
            productEl.prev().text("");
            specEl.prev().text("");
            if (typeEl.val() == "") {
                productEl.find("option").remove();
//                productEl.append("<option value=''>请先选择类型</option>");
            } else {
                productEl.find("option").remove();
//                productEl.append("<option value=''>请先选择类型</option>");
                var type = typeEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductByType.json", {type: type}, function (res) {
                    if (res.success) {
                        productEl.find("option").remove();
//                        productEl.append("<option value=''>==请选择==</option>")
                        var obj = res.obj;
                        for (i in obj) {
                            productEl.append("<option value='" + obj[i].productTypeId + "'>" + obj[i].productTypeName + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 3, 'warning');
                    }
                }, "JSON");
            }
        });
        productEl.unbind("click");
        productEl.click(function () {
            specEl.prev().text("");
            if (typeEl.val() == "") {
                specEl.find("option").remove();
//                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                specEl.find("option").remove();
//                specEl.append("<option value=''>请先选择产品</option>");
                var type = productEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductSpecName.json", {productTypeId: type}, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
//                        specEl.append("<option value=''>==请选择==</option>")
                        var obj = res.obj;
                        for (i in obj) {
                            specEl.append("<option value='" + obj[i].productSpecification + "'>" + obj[i].productSpecification + "</option>")
                        }
                    } else {
                        tipDialog(res.msg, 2, 'warning');
                    }
                }, "JSON");
            }
        });
    }

    //产品状态
    $(".updateProductStatus").focus(function() {
        var productStatus =$(this).attr("id");
        var num =15;
        var stockId =$.trim(productStatus.replace("updateProductStatus",""));
        $.post("${request.contextPath}/stock/getStockIsMaterialByCodeAndName.json",{id: num},function (res) {
            if(res.success){
                $("#updateProductStatus"+stockId).find("option").remove();
                let obj =res.obj;
                for(i in obj){
                    $("#updateProductStatus"+stockId).append("<option value='" + obj[i].dataDictionaryDetailsId + "'>" + obj[i].cname + "</option>");
                }
            }else {
                tipDialog(res.msg, 3, 'warning');
            }
        })
    })
</script>