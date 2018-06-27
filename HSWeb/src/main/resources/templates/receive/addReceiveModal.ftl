<form id="form1" autocomplete="off" style="margin: 1px">
    <div class="bd">
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableProperty">
                <table class="form">
                    <tr>
                        <th class="formTitle">
                            <a onclick="addenterpriseModal()">
                                <img src="${staticImg}/images/Icon16/add.png" alt="" class="pull-left">
                            </a>
                            供应商：
                        </th>
                        <td class="formValue">
                            <select id="enterpriseId3" class="txtselect" datacol="yes" err="分类"
                                    checkexpession="NotNull">
                                <option value="">==请选择==</option>
                            <#if enterprises??>
                                <#list enterprises as ep>
                                    <!--1为原料供应商，2为其他-->
                                    <!--1为有该执照-->
                                    <option value="${ep.enterpriseId}"
                                        <#if ep.enterpriseType??>
                                            data-type="${ep.enterpriseType}"
                                            <#if ep.enterpriseType==2>
                                                <#if ep.businessLicenseImage??>
                                            data-businessLicense="1"
                                            data-businessLicenseDate="${ep.businessLicenseDate?string("yyyy-MM-dd HH:mm:ss")}"
                                                </#if>
                                                <#if ep.productionLicenseDate??>
                                            data-productionLicense="1"
                                            data-productionLicenseDate="${ep.productionLicenseDate?string("yyyy-MM-dd HH:mm:ss")}"
                                                </#if>
                                            </#if>
                                        </#if>
                                    ><#if ep.CName??>${ep.CName}</#if></option>
                                </#list>
                            </#if>
                            </select>
                        </td>
                        <th class="formTitle">
                            发货人：
                        </th>
                        <td class="formValue">
                            <input id="deliverName" type="text" class="txt" style="width: 100px">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            收货编号：
                        </th>
                        <td class="formValue">
                        <#if receiveNode??>
                            ${receiveNode}
                        </#if>
                        </td>
                        <th class="formTitle">
                            收货人：
                        </th>
                        <td class="formValue">
                            <input id="consignee" type="text" class="txt" style="width: 100px">
                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">
                            收货时间：
                        </th>
                        <td class="formValue">
                            <input id="deliverDate" type="text" class="txt Wdate" style="width: 100%; "
                                   onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        </td>
                        <th class="formTitle">
                            供应商类型：
                        </th>
                        <td class="formValue" id="enterpriesTypeT">
                        </td>
                    </tr>
                    <tr id="about-certificate" class="hide">
                        <th class="formTitle">
                            供应商资质：
                        </th>
                        <td class="formValue">

                        </td>
                        <th class="formTitle">
                            资质有效期：
                        </th>
                        <td class="formValue">

                        </td>
                    </tr>
                    <tr>
                        <th class="formTitle">备注：</th>
                        <td class="formValue" colspan="3">
                            <textarea id="remark" style="width: 100%;height: 50px;"></textarea>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
            <div class="tab_list_top" style="position: absolute">
                <div id="TabTableField" class="tab_list bd actived">收货明细</div>
            </div>
            <div style="float: right;">
                <div class="tools_bar_icon">
                    <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                    <div class="icon-botton" title="插入行" onclick="InsetTableRow()">
                        <img src="${staticImg}/images/Icon16/table_row_insert.png"/>
                    </div>
                    <div class="icon-botton" title="清空行" onclick="EmptyTableRow()">
                        <img src="${staticImg}/images/Icon16/table_row_delete.png"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ScrollBar" style="margin-top: 1px;">
            <div id="TableField" class="tabPanel">
                <table id="grid_Field" class="grid" style="width: 100%">
                    <thead>
                    <tr>
                        <td style="width: 30px; text-align: center; border-left: none;">
                            <div class="table-header">&nbsp;</div>
                        </td>
                        <td style="width: 60px;">
                            <div class="table-header">批次号</div>
                        </td>
                        <td style="width: 80px; text-align: center;">
                            <div class="table-header">货物类型</div>
                        </td>
                        <td style="width: 80px; text-align: center;">
                            <div class="table-header">产品名称</div>
                        </td>
                        <td style="width: 100px; text-align: center;">
                            <div class="table-header">规格</div>
                        </td>
                        <td style="width: 100px; text-align: center;">
                            <div class="table-header">数量</div>
                        </td>
                        <td style="width: 100px; text-align: center;">
                            <div class="table-header">单位</div>
                        </td>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</form>
<script>
    var RowIndex = 1;

    $(document).ready(function () {
        CreateTable();
        TableTdEvent();
    });

    //插入一个空行
    function InsetTableRow() {
        var len = $("#grid_Field tbody tr").length;
        $("#grid_Field tbody").append(CreateTableRow(len + 1));
        $("#grid_Field tbody tr").eq(len).find('input,select').hide();
        initSearchReceive(len + 1);
        var indexrow = 1;
        $("#grid_Field tbody tr").each(function () {
            $(this).find('td:eq(0)').html(indexrow);
            $(this).find("input,select").each(function () {
                let indexId = $(this).attr("id");
                let index = indexId.match(/^[a-z|A-Z]+/gi);
                $(this).attr("id", index + indexrow);
            });
            indexrow++;
        })
    }

    //清空表格当前行
    function EmptyTableRow() {
        var trobj = $("#grid_Field tbody .selected");
        if (trobj != null && trobj != undefined && trobj.length > 0) {
            trobj.remove();
            var indexrow = 1;
            $("#grid_Field tbody tr").each(function () {
                $(this).find('td:eq(0)').html(indexrow);
                $(this).find("input,select").each(function () {
                    let indexId = $(this).attr("id");
                    let index = indexId.match(/^[a-z|A-Z]+/gi);
                    $(this).attr("id", index + indexrow);
                });
                indexrow++;
            })
        } else {
            tipDialog("请先选择一行！\n", 4, 'warning');
        }
    }

    //表格点击事件
    function TableTdEvent() {
        $(".grid").on("click", ".td-div", function () {
            if ($(this).parent().hasClass("selected")) {
                $(this).parent().removeClass("selected");
            } else {
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field tbody");
        grid.on("click", "td", function () {
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
                                    if (obj.parent().find('input[type=hidden]').attr("class") != "buyaogaiwo") {
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
            }
        })
    }

    //创建行
    function CreateTableRow(index) {
        var tr = '';
        tr += '<tr>';
        tr += '<td class="td-div" style="width: 30px; text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="batchNo' + index + '" type="text" maxlength="10"  class="txt" name ="batchNo" /></td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsMaterialType(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsProductName(index) + '</td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsStandard(index) + '</td>';
        tr += '<td style="width: 50px; text-align: center;"><div></div><input id="weight' + index + '" type="text" maxlength="10" class="txt" name ="weight" onkeyup="clearNoNum(this)"/></td>';
        tr += '<td style="width: 60px; text-align: center;"><div></div>' + IsUnit(index) + '</td>';
        tr += '</tr>';
        return tr;
    }

    //货物类型
    function IsMaterialType(index) {
        var html = '<select id="goodsType' + index + '" class="txtselect goodsType' + index + '" datacol="no" type="select" checkexpession="NotNull">';
//        html += '<option value="">==请选择==</option>';
        html += '</select>';
        return html;
    }

    //产品名称
    function IsProductName(index) {
        var html = '<select id="productName' + index + '" class="txtselect buyaogaiwo productValueId' + index + '" name ="productName"  datacol="no" type="select" err="产品名称" checkexpession="NotNull">';
        html += '</select>';
        return html;
    }

    //规格
    function IsStandard(index) {
        var html = '<select id="productSpecName' + index + '" class="txtselect" name ="productSpecName"  datacol="no" type="select" err="规格" checkexpession="NotNull">';
//        html='<option value="">请先选择产品</option>';
        html += '</select>';
        return html;
    }

    //单位
    function IsUnit(index) {
        var html = '<input id="unitName' + index + '" autocomplete="off" type="text" class="txt icontree"/>';
        html += '<input id="unitId' + index + '" type="hidden" class="buyaogaiwo txt icontree"/>';
        return html;
    }

    //默认创建一行
    function CreateTable() {
        for (var i = 0; i < 1; i++) {
            $("#grid_Field tbody").append(CreateTableRow(RowIndex));
            RowIndex++;
        }
        ;
        $("#grid_Field tbody tr").find('input,select').attr('disabled', 'disabled').hide();
        $("#grid_Field tbody tr:eq(0)").find('input,select').removeAttr('disabled');
        initSearchReceive(1);
    };


    var rawData = new Array();
    var allData = {
        enterpriseId: "",//供应商
        enterpriseName: "",//供应商
        deliverName: "",//发货人
        receiveNo: "<#if receiveNode??>${receiveNode}</#if>",//收货编号
        consignee: "",//收货人
        deliverDate: "",//收货时间
        remark: "",//备注
        receiveDetails: rawData
    }
    var postData;
    $("#enterpriseId3").unbind("change");
    $("#enterpriseId3").change(function () {
        let el = $('#enterpriseId3 :selected');
        let type = el.data("type")
        if (null != type && type == 2) {
            $("#enterpriesTypeT").text("其他");
            $("#about-certificate").removeClass("hide");
            let certificate = "";
            console.log(el.data("businesslicense"))
            if (null != el.data("businesslicense") && parseInt(el.data("businesslicense")) == 1) {
                certificate += "营业执照已上传，"
            } else {
                certificate += "营业执照未上传，"
            }
            if (null != el.data("productionlicense") && parseInt(el.data("productionlicense")) == 1) {
                certificate += "生产许可证已上传，"
            } else {
                certificate += "生产许可证未上传，"
            }
            $("#about-certificate").find("td:eq(0)").text(certificate.substr(0, certificate.length - 1))
            Date.prototype.Format = function (fmt) {
                var o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "H+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }
            let nowTime = new Date().Format("yyyy-MM-dd HH:mm:ss");
            let certificateDate = "";
            if (null != el.data("businesslicensedate")) {
                let businessLicenseDate = new Date(el.data("businesslicensedate").replace(/-/g, "\/")).Format("yyyy-MM-dd HH:mm:ss");
                if (nowTime > businessLicenseDate) {
                    certificateDate += "营业执照已过期，"
                } else {
                    certificateDate += "营业执照正常，"
                }
                console.log("now:" + nowTime)
                console.log("证书：" + businessLicenseDate)
            } else {
                certificateDate += "未获取到营业执照有效期，"
            }
            if (null != el.data("productionlicensedate")) {
                let productionLicenseDate = new Date(el.data("productionlicensedate").replace(/-/g, "\/")).Format("yyyy-MM-dd HH:mm:ss");
                if (nowTime > productionLicenseDate) {
                    certificateDate += "生产许可证已过期，"
                } else {
                    certificateDate += "生产许可证正常，"
                }
            } else {
                certificateDate += "未获取到生产许可证有效期，"
            }

            $("#about-certificate").find("td:eq(1)").text(certificateDate.substr(0, certificateDate.length - 1))
        } else {
            $("#enterpriesTypeT").text("原料供应商");
            $("#about-certificate").attr("class", "hide");
        }
    });
    $("#addReceive").unbind("click");
    $("#addReceive").click(function () {
        Loading(true, "正在提交数据", "#addReceiveModal");
        if ($.trim($("#enterpriseId3 :selected").val()) != "") {
            allData.enterpriseId = $.trim($('#enterpriseId3').val());
            allData.enterpriseName = $.trim($('#enterpriseId3 :selected').text())
        } else {
            tipDialog("供应商不得为空！", 3, 'warning');
            Loading(false, "", "#addReceiveModal");
            return false;
        }
        if ($.trim($('#deliverName').val()) != "") {
            allData.deliverName = $.trim($('#deliverName').val());
        } else {
            tipDialog("发货人不得为空！", 3, 'warning');
            Loading(false, "", "#addReceiveModal");
            return false;
        }
        if ($.trim($('#consignee').val()) != "") {
            allData.consignee = $.trim($('#consignee').val());
        } else {
            tipDialog("收货人不得为空！", 3, 'warning');
            Loading(false, "", "#addReceiveModal");
            return false;
        }
        if ($.trim($('#deliverDate').val()) != "") {
            allData.deliverDate = $.trim($('#deliverDate').val());
        } else {
            tipDialog("收货时间不得为空！", 3, 'warning');
            Loading(false, "", "#addReceiveModal");
            return false;
        }
        let el = $('#enterpriseId3 :selected');
        let type = el.data("type")
        if (null != type && type == 2) {
            if (null == el.data("businesslicense")) {
                tipDialog("营业执照未上传！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                return false;
            }
            if (null == el.data("productionlicense")) {
                tipDialog("生产许可证未上传！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                return false;
            }
            Date.prototype.Format = function (fmt) {
                var o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "H+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }
            let nowTime = new Date().Format("yyyy-MM-dd HH:mm:ss");
            if (null != el.data("businesslicensedate")) {
                let businessLicenseDate = new Date(el.data("businesslicensedate").replace(/-/g, "\/")).Format("yyyy-MM-dd HH:mm:ss");
                if (nowTime > businessLicenseDate) {
                    tipDialog("营业执照已过期！", 3, 'warning');
                    Loading(false, "", "#addReceiveModal");
                    return false;
                }
            } else {
                tipDialog("未获取到营业执照有效期！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                return false;
            }
            if (null != el.data("productionlicensedate")) {
                let productionLicenseDate = new Date(el.data("productionlicensedate").replace(/-/g, "\/")).Format("yyyy-MM-dd HH:mm:ss");
                if (nowTime > productionLicenseDate) {
                    tipDialog("生产许可证已过期！", 3, 'warning');
                    Loading(false, "", "#addReceiveModal");
                    return false;
                }
            } else {
                tipDialog("未获取到生产许可证有效期！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                return false;
            }
        }
        if ($.trim($('#remark').val()) != "") {
            allData.remark = $.trim($('#remark').val());
        }
        var leng = $("#grid_Field tbody>tr").length;
        for (var i = 0; i < leng; i++) {
            var receive = {
                batchNo: "",
                weight: "",
                unitName: "",
                unitId: "",
                productSpecName: "",
                productName: "",
                productId: "",
                goodsType: "",
                goodsTypeId: ""
            }, num = i + 1;
            receive.batchNo = $("#grid_Field tbody>tr").eq(i).find("div:eq(0)").text();
            receive.goodsType = $("#grid_Field tbody>tr").eq(i).find("div:eq(1)").text();
            receive.goodsTypeId = $(".goodsType" + num + " :selected").val();
            receive.productName = $("#grid_Field tbody>tr").eq(i).find("div:eq(2)").text();
            receive.productId = $(".productValueId" + num).val();
            receive.productSpecName = $("#grid_Field tbody>tr").eq(i).find("div:eq(3)").text();
            receive.weight = $("#grid_Field tbody>tr").eq(i).find("div:eq(4)").text();
            receive.unitName = $("#unitName" + num).val();
            receive.unitId = $("#unitId" + num).val();
            rawData.push(receive);
        }
        for (var i = 0; i < rawData.length; i++) {
            var rece = rawData[i], num = i + 1;
            if (rece.batchNo == '') {
                tipDialog("第" + num + "行批次为空！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                rawData = new Array();
                return false;
            }
            if (rece.goodsType == '' || receive.goodsTypeId == '') {
                tipDialog("第" + num + "行货物类型为空！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                rawData = new Array();
                return false;
            }
            if (rece.productName == '' || receive.productId == '') {
                tipDialog("第" + num + "行产品为空！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                rawData = new Array();
                return false;
            }
            if (rece.productSpecName == '' || rece.productSpecName == "==请选择==") {
                tipDialog("第" + num + "行规格为空！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                rawData = new Array();
                return false;
            }
            if (rece.weight == '') {
                tipDialog("第" + num + "行重量为空！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                rawData = new Array();
                return false;
            }
            if (rece.unitName == '' || receive.unitId == '') {
                tipDialog("第" + num + "行单位为空！", 3, 'warning');
                Loading(false, "", "#addReceiveModal");
                rawData = new Array();
                return false;
            }
        }
        allData.receiveDetails = rawData;
        postData = allData;
        let isOk = false;
        $.ajax({
            type: "post", // 请求方式
            url: "${request.contextPath}/receiveManagement/insertReceive.json?resourceId=" + resourceId, //url地址
            data: JSON.stringify(postData), //数据
            contentType: "application/json;charset=utf-8;",
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    postData = "";
                    tipDialog("插入成功！", 3, 2);
                    $('#addReceiveModal').modal('hide');
                    Loading(false, "", "#addReceiveModal");
                    $("#gridTable").trigger("reloadGrid");
                    if (res.obj != "") {
                        setTimeout(function () {
                            $("#gridTable").trigger("reloadGrid");
                        }, 4 * 1000);
                        window.open("${request.contextPath}/jasper/downloadPdf/pdf?receiveDetails=" + res.obj + "&resourceId=" + resourceId);
                    }
                } else {
                    tipDialog(res.msg, 0, 2);
                    postData = "";
                    $('#addReceiveModal').modal('hide');
                    Loading(false, "", "#addReceiveModal")
                }
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                postData = "";
                $('#addReceiveModal').modal('hide');
                Loading(false, "", "#addReceiveModal")
            }
        })


    });

    //select相互关联
    function initSearchReceive(num) {
        $.post("${request.contextPath}/receiveManagement/getGoodsType.json", "", function (res) {
            $("#goodsType" + num).find("option").remove();
            $("#goodsType" + num).append("<option value=''>==请选择==</option>");
            for (i in res) {
                $("#goodsType" + num).append("<option value='" + res[i].type + "'>" + res[i].typeName + "</option>")
            }
        }, "JSON");
        loadProductReceive("#goodsType" + num, "#productName" + num, "#productSpecName" + num, "#unitName" + num, "#unitId" + num)
        $("#weight" + num).keyup(function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
        }).bind("paste", function () {
            var tmptxt = $(this).val();
            $(this).val(tmptxt.replace(/[^\d.]/g, ""));
        }).css("ime-mode", "disabled");
    }

    function loadProductReceive(typeId, productId, specId, unitName, unitId) {
        var typeEl = $("" + typeId);
        var productEl = $("" + productId);
        var specEl = $("" + specId);
        var priseEl = $("" + unitId);
        typeEl.unbind("click");
        typeEl.click(function () {
            typeEl.prev().text("");
            productEl.prev().text("");
            specEl.prev().text("");
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
            specEl.prev().text("");
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
        $("" + unitName).focus(function () {
            var objId = this.id;
            comboBoxTree(objId, "182px");
            var itemtree = {
                onnodeclick: function (item) {
                    $("" + unitId).attr("value", item.id);
                    $("" + unitName).attr("value", item.text);
                },
                url: "${request.contextPath}/receiveManagement/getUnitName.json"
            };
            $("#comboBoxTree" + objId).treeview(itemtree);
            $("#comboBoxTree" + objId + "_" + GetQuery('KeyValue').replace(/-/g, '_')).parent('li').remove();
        });

    }
    <@shiro.hasPermission name="/companyProduct/addProduct">
    function addenterpriseModal() {
        $("#addenterpriseModal").modal({
            remote: "${request.contextPath}/enterprise/addenterpriseModal.htm?resourceId=" + resourceId
        });
    }

    $("#addZsEnterprise").unbind("click");
    $("#addZsEnterprise").click(function () {
        Loading(true, "正在提交...", "#addenterpriseModal");
        var cName = $("#cName").val();
        if ("" == cName) {
            $("#cName").focus();
            tipDialog("请输入供应商名称", 4, 'a');
            Loading(false, "", "#addenterpriseModal");
            return false;
        }
        var contact = $("#contact").val();
        if ("" == contact) {
            tipDialog("请输入联系人", 4, 'a');
            Loading(false, "", "#addenterpriseModal");
            return false;
        }
        $.ajax({
            url: "${request.contextPath}/enterprise/addZsEnterprise.json?resourceId=" + resourceId,
            async: false,
            data: $("#addenterpriseForm").serialize(),
            success: function (result) {
                if (result.success) {
                    $("#gridTable").trigger("reloadGrid");
                    tipDialog(result.msg, 4, '1');
                    $("#addenterpriseModal").modal('hide');
                    $.post("${request.contextPath}/enterprise/getEnterpriseList.json", "", function (res) {
                        if (res.success) {
                            $("#enterpriseId3").html('<option value="">==请选择==</option>')
                            for (var i in res.obj) {
                                $("#enterpriseId3").append('<option value="' + res.obj[i].enterpriseId + '">' + res.obj[i].cName + '</option>')
                            }
                        } else {
                            tipDialog(res.msg, 4, '0');
                        }
                    }, "json")
                } else {
                    tipDialog(result.msg, 4, '0');
                    $("#addenterpriseModal").modal('hide');
                }
                Loading(false, "", "#addenterpriseModal");
            },
            type: "post",
            dataType: "json"
        })
    });
    </@shiro.hasPermission>
</script>