<form id="returnManageModalForm" style="margin: 1px">
    <table class="form">
        <tr>
            <th class="formTitle">
                返货类型：
            </th>
            <td class="formValue">
                <select id="returnGoodsType" name="goodsTypeId" class="txtselect" datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">==请选择==</option>
                <#if returnGoodsType??>
                    <#list returnGoodsType as rg>
                        <option value="${rg.isMaterial}">${rg.materialName}</option>
                    </#list>
                <#else>
                    <option value="">无货物类型</option>
                </#if>
                </select>
                <input type="hidden" name="goodsType"/>
            </td>
            <th class="formTitle">
                产品名称：
            </th>
            <td class="formValue">
                <select id="returnProduct" name="productId" class="txtselect" datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">==请先选择返货类型==</option>
                </select>
                <input type="hidden" name="productName"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                规格：
            </th>
            <td class="formValue">
                <select id="returnProductSpecName" name="productSpecName" class="txtselect" datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">==请先选择产品==</option>
                </select>
            </td>
            <th class="formTitle">
                批次号：
            </th>
            <td class="formValue">
                <input id="returnBatchNo" name="batchNo" type="text" class="txt" style="width: 100%"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                提货日期：
            </th>
            <td class="formValue">
                <input id="takeDate" name="takeDate" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
            <th class="formTitle">
                卡号：
            </th>
            <td class="formValue">
                <input id="cardNumber" name="cardNumber" type="text" class="txt" style="width: 100%"
                       onKeyUp="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字">
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                顾客地址：
            </th>
            <td class="formValue">
                <input id="customerAddress" name="customerAddress" type="text" class="txt" style="width: 100%">
            </td>
            <th class="formTitle">
                返货日期：
            </th>
            <td class="formValue">
                <input id="returnDate" name="returnDate" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                快递单号：
            </th>
            <td class="formValue">
                <input id="expressNumber" name="expressNumber" type="text" class="txt" style="width: 100%">
            </td>
            <th class="formTitle">
                顾客姓名：
            </th>
            <td class="formValue">
                <input id="customerName" name="customerName" type="text" class="txt" style="width: 100%">
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                不合格情况</br>描述：
            </th>
            <td class="formValue" colspan="3">
                <textarea id="unqualifiedDescription" name="unqualifiedDescription"
                          style="width: 100%;height: 50px;"></textarea>
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                检察员1：
            </th>
            <td class="formValue">
                <input id="inspectorOne" name="inspectorOne" type="text" class="txt" style="width: 100%">
            </td>
            <th class="formTitle">
                检查日期1：
            </th>
            <td class="formValue">
                <input id="checkDateOne" name="checkDateOne" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                不合格处</br>理措施：
            </th>
            <td class="formValue" colspan="3">
                <textarea id="treatmentMeasures" name="treatmentMeasures" style="width: 100%;height: 50px;"></textarea>
            </td>
        </tr>
        <tr>
        <tr>
            <th class="formTitle">
                检察员2：
            </th>
            <td class="formValue">
                <input id="inspectorTwo" name="inspectorTwo" type="text" class="txt" style="width: 100%">
            </td>
            <th class="formTitle">
                检查日期2：
            </th>
            <td class="formValue">
                <input id="checkDateTwo" name="checkDateTwo" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <td class="formValue" colspan="4" style="text-align: left">
                &nbsp;&nbsp;&nbsp;
                <strong>不合格评审意见</strong>
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                不合格性质：
            </th>
            <td class="formValue">
                <input id="nonconformance" name="nonconformance" type="text" class="txt" style="width: 100%">
            </td>
            <th class="formTitle">
                处置方式：
            </th>
            <td class="formValue">
                <input id="disposalWay" name="disposalWay" type="text" class="txt" style="width: 100%">
            </td>
        </tr>
        <tr>
            <th class="formTitle">
                主持人：
            </th>
            <td class="formValue">
                <input id="host" name="host" type="text" class="txt" style="width: 100%">
            </td>
        </tr>
        <tr>
            <th class="formTitle">参加人员：</th>
            <td class="formValue" colspan="3">
                <textarea id="participants" name="participants" style="width: 100%;height: 80px;"></textarea>
                <input name="resourceId" type="hidden" value="${resourceId!}"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">备注：</th>
            <td class="formValue" colspan="3">
                <textarea id="remark" name="remark" style="width: 100%;height: 50px;"></textarea>
            </td>
        </tr>
    </table>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images1"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images2"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images3"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
        </div>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images4"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images5"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                    <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images6"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    //获取产品
    $("#returnGoodsType").on("change", function () {
        let isMaterial = $("#returnGoodsType option:selected").val();
        $("#returnProductSpecName").val("");
        Loading(true, "加载中","#returnManageModal");
        $.post("${request.contextPath}/stockRecord/getReturnProduct.json", {isMaterial: isMaterial}, function (res) {
            if (res.success) {
                $("#returnProduct").find("option").remove();
                $("#returnProduct").append("<option value=''>==请选择==</option>");
                var obj = res.obj;
                for (i in obj) {
                    $("#returnProduct").append("<option value='" + obj[i].productId + "'>" + obj[i].productName + "</option>")
                    Loading(false,"","#returnManageModal");
                }
            } else {
                Loading(false,"","#returnManageModal");
                tipDialog(res.msg, 2, 'warning');

            }
        }, "json");
    });
    //获取规格
    $("#returnProduct").on("change", function () {
        let productId = $("#returnProduct option:selected").val();
        Loading(true, "加载中","#returnManageModal");
        $.post("${request.contextPath}/stockRecord/getReturnProductSpecName.json", {productId: productId}, function (res) {
            if (res.success) {
                $("#returnProductSpecName").find("option").remove();
                $("#returnProductSpecName").append("<option value=''>==请选择==</option>");
                var obj = res.obj;
                for (i in obj) {
                    $("#returnProductSpecName").append("<option value='" + obj[i].productSpecName + "'>" + obj[i].productSpecName + "</option>")
                    Loading(false,"","#returnManageModal");
                }
            } else {
                Loading(false,"","#returnManageModal");
                tipDialog(res.msg, 3, 'warning');
            }
        }, "json");
    });

    //提交返货记录登记
    $("#addReturnGoods").unbind("click");
    $("#addReturnGoods").click(function () {
        $("input[name=productName]").val("");
        $("input[name=goodsType]").val("");
        Loading(true, "正在提交数据", "#returnManageModal");
        let goodsTypeId = $("#returnGoodsType option:selected").val();
        let goodsType = $("#returnGoodsType option:selected").text();
        if ("" == goodsTypeId) {
            $("#returnGoodsType").focus();
            tipDialog("请选择返货类型", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        } else {
            $("input[name=goodsType]").val(goodsType);
        }
        let productId = $("#returnProduct option:selected").val();
        let productName = $("#returnProduct option:selected").text();
        if ("" == productId) {
            $("#returnProduct").focus();
            tipDialog("请选择产品", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        } else {
            $("input[name=productName]").val(productName);
        }
        let productSpecName = $("#returnProductSpecName option:selected").val();
        if ("" == productSpecName) {
            $("#returnProductSpecName").focus();
            tipDialog("请选择规格", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let batchNo = $("#returnBatchNo").val();
        if ("" == batchNo) {
            $("#returnBatchNo").focus();
            tipDialog("请填写批次号", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let takeDate = $("#takeDate").val();
        if ("" == takeDate) {
            $("#takeDate").focus();
            tipDialog("请填写提货时间", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let cardNumber = $("#cardNumber").val();
        let reg = /^[0-9]*$/;
        if ("" == cardNumber || !reg.test(cardNumber)) {
            $("#cardNumber").focus();
            tipDialog("卡号未填写或格式有误", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let returnDate = $("#returnDate").val();
        if ("" == returnDate) {
            $("#returnDate").focus();
            tipDialog("请填写返货时间", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let expressNumber = $("#expressNumber").val();
        if ("" == expressNumber) {
            $("#expressNumber").focus();
            tipDialog("请填写快递单号", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let customerName = $("#customerName").val();
        if ("" == customerName) {
            $("#customerName").focus();
            tipDialog("请填写顾客姓名", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let customerAddress = $("#customerAddress").val();
        if ("" == customerAddress) {
            $("#customerAddress").focus();
            tipDialog("请填写顾客地址", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let unqualifiedDescription = $("#unqualifiedDescription").val();
        if ("" == unqualifiedDescription) {
            $("#unqualifiedDescription").focus();
            tipDialog("请填写不合格情况描述", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let inspectorOne = $("#inspectorOne").val();
        if ("" == inspectorOne) {
            $("#inspectorOne").focus();
            tipDialog("请填写检察员1", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let checkDateOne = $("#checkDateOne").val();
        if ("" == checkDateOne) {
            $("#checkDateOne").focus();
            tipDialog("请填写检查时间1", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let treatmentMeasures = $("#treatmentMeasures").val();
        if ("" == treatmentMeasures) {
            $("#treatmentMeasures").focus();
            tipDialog("请填写不合格处理措施", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let inspectorTwo = $("#inspectorTwo").val();
        if ("" == inspectorTwo) {
            $("#inspectorTwo").focus();
            tipDialog("请填写检察员2", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let checkDateTwo = $("#checkDateTwo").val();
        if ("" == checkDateTwo) {
            $("#checkDateTwo").focus();
            tipDialog("请填写检查时间2", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let nonconformance = $("#nonconformance").val();
        if ("" == nonconformance) {
            $("#nonconformance").focus();
            tipDialog("请填写不合格性质", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let disposalWay = $("#disposalWay").val();
        if ("" == disposalWay) {
            $("#disposalWay").focus();
            tipDialog("请填写处置方式", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let host = $("#host").val();
        if ("" == host) {
            $("#host").focus();
            tipDialog("请填写主持人", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }
        let participants = $("#participants").val();
        if ("" == participants) {
            $("#participants").focus();
            tipDialog("请填写参加人员", 4, "warning");
            Loading(false, "", "#returnManageModal");
            return;
        }

        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/stockRecord/subReturnGoodsRecord.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 3, 1);
                    $("#gridTable4").trigger("reloadGrid");
                    Loading(false, "", "#returnManageModal");
                    $("#returnManageModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 3, 0);
                    Loading(false, "", "#returnManageModal");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 3, 0);
                Loading(false, "", "#returnManageModal");
            }
        };
        $('#returnManageModalForm').ajaxSubmit(options);
    });
</script>