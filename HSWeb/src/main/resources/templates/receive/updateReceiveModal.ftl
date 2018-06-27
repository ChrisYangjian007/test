<#--编辑用户-->
<style>
    .txtselect {
        white-space: normal;
        word-break: break-all;
        word-wrap: break-word;
        height: 100% !important;
    }
</style>
<form id="updateUserForm" style="margin: 1px">
    <table class="form">
        <thead>
        <th class="formTitle" style="width: 60px;text-align:center">*货物类型</th>
        <th class="formTitle" style="width: 50px;text-align:center">批次号</th>
        <th class="formTitle" style="width: 59px;text-align:center">*产品名称</th>
        <th class="formTitle" style="width: 59px;text-align:center">*规格</th>
        <th class="formTitle" style="width: 30px;text-align:center">*数量</th>
        <th class="formTitle" style="width: 30px;text-align:center">*单位</th>
        <th class="formTitle" style="width: 45px;text-align:center">*供应商</th>
        <th class="formTitle" style="width: 40px;text-align:center">发货人</th>
        <th class="formTitle" style="width: 40px;text-align:center">收货人</th>
        <th class="formTitle" style="width: 90px;text-align:center">收货时间</th>
        <th class="formTitle" style="width: 90px;text-align:center">备注</th>
        </thead>
        <tbody>
        <#list puReceiveDetaildParaList as receiveDetail>
        <tr>
            <!--货物类型-->
            <td class="formValue" style="text-align:center">
                <input type="hidden" value="${receiveDetail.receiveDetailId}">
                <input type="hidden" value="${receiveDetail.receiveId}">
                <select id="goodsTypeIdm${receiveDetail_index}" name="goodsTypeId" class="txtselect goodsTypeIdm123"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="${receiveDetail.goodsTypeId!}">${receiveDetail.goodsType!}</option>
                </select>
            </td>

            <!--批次号-->
            <td class="formValue" style="text-align:center">
                <input id="batchNo" type="text" class="txt" style="width: 100%" value="${receiveDetail.batchNo!}"/>
            </td>
            <!--产品名称-->
            <td class="formValue" style="text-align:center">
                <select id="productIdm${receiveDetail_index}" name="productId" class="txtselect productIdm123"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="${receiveDetail.productId!}">${receiveDetail.productName!}</option>
                </select>
            </td>
            <!--规格-->
            <td class="formValue" style="text-align:center">
                <select id="productSpecNamem${receiveDetail_index}" name="productSpecName"
                        class="txtselect productSpecNamem123" datacol="yes"
                        err="状态"
                        checkexpession="NotNull">
                    <option value="${receiveDetail.productSpecName!}">${receiveDetail.productSpecName!}</option>
                </select>
            </td>
            <!--数量-->
            <td class="formValue" style="text-align:center">
                <input id="weight" type="text" class="txt" style="width: 100%" maxlength="10" onkeyup="clearNoNum(this)"
                       value=" ${receiveDetail.weight?c}"/>
            </td>
            <td class="formValue" style="text-align:center">
                <select id="unitId" class="txtselect unitId123" datacol="yes" err="分类" checkexpession="NotNull">
                    <#if units??>
                        <option value="">==请选择==</option>
                        <#list units as a>
                            <option value="${a.unitId!}"
                                    <#if receiveDetail.unitId==a.unitId>selected</#if>
                            >${a.CName!}</option>
                        </#list>
                    </#if>
                </select>
            </td>
            <!--供应商-->
            <td class="formValue" style="text-align:center">
                <select id="enterpriseId" class="txtselect enterpriseId123" datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <#if enterprises??>
                        <option value="">==请选择==</option>
                        <#list enterprises as ep>
                            <option value="${ep.enterpriseId!}"
                                    <#if receiveDetail.puReceive.enterpriseId==ep.enterpriseId>selected</#if>
                            >${ep.CName!}</option>
                        </#list>
                    </#if>
                </select>
            </td>
            <!--发货人-->
            <td class="formValue" style="text-align:center">
                <input id="deliverName" type="text" class="txt" style="width: 100%"
                       value="${receiveDetail.puReceive.deliverName!}"/>
            </td>
            <!--收货人-->
            <td class="formValue" style="text-align:center">
                <input id="consignee" type="text" class="txt" style="width: 100%"
                       value=" ${receiveDetail.puReceive.consignee!}"/>
            </td>
            <!--收货时间-->
            <td class="formValue" style="text-align:center">
                <input id="deliverDate123" name="deliverDate123" type="text" class="txt Wdate"
                       value="${(receiveDetail.puReceive.deliverDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
            <!--备注-->
            <td class="formValue" style="text-align:center">
                <input id="remark" type="text" class="txt" style="width: 100%"
                       value=" ${receiveDetail.puReceive.remark!}"/>
                <input type="hidden" value="${resourceId!}"/>
                <input type="hidden" value="${receiveDetail.puReceive.receiveNo!}"/>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</form>

<script>
    $(document).ready(function () {
        var trLen = ($("table.form tr").length) - 1;
        for (var i = 0; i < trLen; i++) {
            initSearchUpdate(i);
        }
    });
    $(".goodsTypeIdm123").click(function () {
        $(this).find("option:first").text("请选择");
        $(this).find("option:first").val("");
    });

    /**
     * 初始化
     * */
    function initSearchUpdate(num) {
        $.post("${request.contextPath}/receiveManagement/getGoodsType.json", "", function (res) {
            for (i in res) {
                $("#goodsTypeIdm" + num).append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
            }
        }, "JSON");
        loadProductUpdate("#goodsTypeIdm" + num, "#productIdm" + num, "#productSpecNamem" + num)
    }

    /**
     * 货物类型，产品，规格名称
     * @param typeId 货物类型dom元素id
     * @param productId 产品dom元素id
     * @param specId 规格名称dom元素id
     * */
    function loadProductUpdate(typeId, productId, specId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
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
            if (typeEl.val() == "") {
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
            } else {
                specEl.find("option").remove();
                specEl.append("<option value=''>请先选择产品</option>");
                var type = productEl.val();
                $.post("${request.contextPath}/receiveManagement/getProductSpecName.json", {productTypeId: type}, function (res) {
                    if (res.success) {
                        specEl.find("option").remove();
                        specEl.append("<option value=''>==请选择==</option>")
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
</script>