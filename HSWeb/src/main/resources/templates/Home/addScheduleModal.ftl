<style>
    .txtselect {
        white-space: normal;
        word-break: break-all;
        word-wrap: break-word;
        height: 100% !important;
    }

    #addScheduleTable th, #addScheduleTable td {
        font-size: 15px;
    }
</style>
<form id="" style="margin: 1px">
    <table class="form" id="addScheduleTable">
        <thead>
        <th class="formTitle" style="width: 40px;height: 50px;text-align:center">编号</th>
        <th class="formTitle" style="width: 60px;height: 50px;text-align:center">产品大类</th>
        <th class="formTitle" style="width: 70px;height: 50px;text-align:center">规格</th>
        <th class="formTitle" style="width: 60px;height: 50px;text-align:center">数量</th>
        <th class="formTitle" style="width: 60px;height: 50px;text-align:center">单位</th>
        </thead>
        <tbody>
        <tr>
            <td class="formValue" style="text-align:center;height:40px">
                01
            </td>
            <td id="jshsProductType" class="formValue" style="text-align:center;height:40px;">
                <input id="jshsProductTypeId" type="hidden" value="4"/>
                即食海参
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="jshsSpec" class="txtselect  selectOption" multiple>
                <#if jshsSpecList??>
                    <#list jshsSpecList as spec >
                        <#if spec??>
                            <option value="${spec.productSpecification!}">${spec.productSpecification!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无规格</option>
                </#if>
                </select>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <input id="jshsCount" type="text" class="txt" style="width: 100%"
                       onKeyUp="clearNoNum(this)" maxlength="10" value="" placeholder="请输入"/>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="jshsUnit" name="" class="txtselect"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">请选择</option>
                <#if jshsUnitList??>
                    <#list jshsUnitList as unit >
                        <#if unit??>
                            <option value="${unit.sysUnitId!}">${unit.sysUnitName!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无单位</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td class="formValue" style="text-align:center;height:40px;">
                02
            </td>
            <td id="jsbyProductType" class="formValue" style="text-align:center;height:40px;">
                <input id="jshsProductTypeId" type="hidden" value="9"/>
                即食鲍鱼
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="jsbySpec" name="" class="txtselect  selectOption" multiple>
                <#if jsbySpecList??>
                    <#list jsbySpecList as spec >
                        <#if spec??>
                            <option value="${spec.productSpecification!}">${spec.productSpecification!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无规格</option>
                </#if>
                </select>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <input id="jsbyCount" type="text" class="txt" style="width: 100%"
                       onKeyUp="clearNoNum(this)" maxlength="10" value="" placeholder="请输入"/>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="jsbyUnit" name="" class="txtselect"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">请选择</option>
                <#if jsbyUnitList??>
                    <#list jsbyUnitList as unit >
                        <#if unit??>
                            <option value="${unit.sysUnitId!}">${unit.sysUnitName!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无单位</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td class="formValue" style="text-align:center;height:40px;">
                03
            </td>
            <td id="ghsProductType" class="formValue" style="text-align:center;height:40px;">
                <input id="ghsProductTypeId" type="hidden" value="11"/>
                干海参
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="ghsSpec" name="" class="txtselect  selectOption" multiple>
                <#if ghsSpecList??>
                    <#list ghsSpecList as spec >
                        <#if spec??>
                            <option value="${spec.productSpecification!}">${spec.productSpecification!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无规格</option>
                </#if>
                </select>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <input id="ghsCount" type="text" class="txt" style="width: 100%"
                       onKeyUp="clearNoNum(this)" maxlength="10" value="" placeholder="请输入"/>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="ghsUnit" name="" class="txtselect"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">请选择</option>
                <#if ghsUnitList??>
                    <#list ghsUnitList as unit >
                        <#if unit??>
                            <option value="${unit.sysUnitId!}">${unit.sysUnitName!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无单位</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td class="formValue" style="text-align:center;height:40px;">
                04
            </td>
            <td id="bghsProductType" class="formValue" style="text-align:center;height:40px;">
                <input id="bghsProductTypeId" type="hidden" value="7"/>
                半干海参
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="bghsSpec" name="" class="txtselect  selectOption" multiple>
                <#if bghsSpecList??>
                    <#list bghsSpecList as spec >
                        <#if spec??>
                            <option value="${spec.productSpecification!}">${spec.productSpecification!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无规格</option>
                </#if>
                </select>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <input id="bghsCount" type="text" class="txt" style="width: 100%"
                       onKeyUp="clearNoNum(this)" maxlength="10" value="" placeholder="请输入"/>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="bghsUnit" name="" class="txtselect"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">请选择</option>
                <#if bghsUnitList??>
                    <#list bghsUnitList as unit >
                        <#if unit??>
                            <option value="${unit.sysUnitId!}">${unit.sysUnitName!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无单位</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td class="formValue" style="text-align:center;height:40px;">
                05
            </td>
            <td id="ldbyProductType" class="formValue" style="text-align:center;height:40px;">
                <input id="ldbyProductTypeId" type="hidden" value="28"/>
                冷冻鲍鱼
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="ldbySpec" name="" class="txtselect  selectOption" multiple>
                <#if ldbySpecList??>
                    <#list ldbySpecList as spec >
                        <#if spec??>
                            <option value="${spec.productSpecification!}">${spec.productSpecification!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无规格</option>
                </#if>
                </select>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <input id="ldbyCount" type="text" class="txt" style="width: 100%"
                       onKeyUp="clearNoNum(this)" maxlength="10" value="" placeholder="请输入"/>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="ldbyUnit" name="" class="txtselect"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">请选择</option>
                <#if ldbyUnitList??>
                    <#list ldbyUnitList as unit >
                        <#if unit??>
                            <option value="${unit.sysUnitId!}">${unit.sysUnitName!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无单位</option>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td class="formValue" style="text-align:center;height:40px;">
                06
            </td>
            <td id="qthpProductType" class="formValue" style="text-align:center;height:40px;">
                <input id="qthpProductTypeId" type="hidden" value="44"/>
                其他货品
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="qthpSpec" name="" class="txtselect  selectOption" multiple>
                <#if qthpSpecList??>
                    <#list qthpSpecList as spec >
                        <#if spec??>
                            <option value="${spec.productSpecification!}">${spec.productSpecification!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无规格</option>
                </#if>
                </select>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <input id="qthpCount" type="text" class="txt" style="width: 100%"
                       onKeyUp="clearNoNum(this)" maxlength="10" value="" placeholder="请输入"/>
            </td>
            <td class="formValue" style="text-align:center;height:40px;">
                <select id="qthpUnit" name="" class="txtselect"
                        datacol="yes" err="分类"
                        checkexpession="NotNull">
                    <option value="">请选择</option>
                <#if qthpUnitList??>
                    <#list qthpUnitList as unit >
                        <#if unit??>
                            <option value="${unit.sysUnitId!}">${unit.sysUnitName!}</option>
                        <#else>
                            <option value=""></option>
                        </#if>
                    </#list>
                <#else>
                    <option value="">无单位</option>
                </#if>
                </select>
            </td>
        </tr>
        </tbody>
    </table>
</form>

<script>
    $(".selectOption").select2({
        language: "zh-CN",
        placeholder: '请选择'
    });

    $("#addScheduleButton").unbind("click");
    $("#addScheduleButton").click(function () {
        Loading(true, "正在提交", "#addScheduleModal");
        let allData = new Array();
        let length = $("#addScheduleTable tbody>tr").length;
        for (let i = 0; i < length; i++) {
            var data = {
                productTypeId: "",//产品类型id
                productType: "",//产品类型名称
                productSpec: "",//产品规格
                amount: "",//数量
                unitId: "",//单位Id
                unitName: "",//单位名称
            };
            data.productTypeId = $("#addScheduleTable tbody>tr").eq(i).find("td:eq(1)").find("input").val();
            data.productType = $.trim($("#addScheduleTable tbody>tr").eq(i).find("td:eq(1)").text());
            let arr = $.trim($("#addScheduleTable tbody>tr").eq(i).find("td:eq(2)").find("select").val());
            data.productSpec = arr.toString();
            data.amount = $.trim($("#addScheduleTable tbody>tr").eq(i).find("td:eq(3)").find("input").val());
            data.unitId = $("#addScheduleTable tbody>tr").eq(i).find("td:eq(4)").find("option:selected").val();
            data.unitName = $("#addScheduleTable tbody>tr").eq(i).find("td:eq(4)").find("option:selected").text();
            allData.push(data);
        }
        for (var i = 0; i < allData.length; i++) {
            var rece = allData[i], num = i + 1;
            if (rece.productSpec == "" || null == rece.productSpec) {
                tipDialog("第" + num + "行规格为空！", 3, 'warning');
                Loading(false, "", "#addScheduleModal");
                return;
            }
            if (rece.amount == "" || null == rece.amount) {
                tipDialog("第" + num + "行数量为空！", 3, 'warning');
                Loading(false, "", "#addScheduleModal");
                return;
            }
            let reg = /^(?:[1-9][0-9]*\.[0-9]+|0\.(?!0+$)[0-9]+|[1-9]+\d*)$/;
            if (!reg.test(rece.amount)) {
                tipDialog("第" + num + "行数量格式不正确！", 3, 'warning');
                Loading(false, "", "#addScheduleModal");
                return;
            }
            if (rece.unitId == "" || null == rece.unitName) {
                tipDialog("第" + num + "行单位为空！", 3, 'warning');
                Loading(false, "", "#addScheduleModal");
                return;
            }
            if (rece.unitName == "" || null == rece.unitName) {
                tipDialog("第" + num + "行单位为空！", 3, 'warning');
                Loading(false, "", "#addScheduleModal");
                return;
            }
        }

        $.ajax({
            type: 'post',
            url: '${request.contextPath}/Home/addSchedule.json',
            data: JSON.stringify(allData),
            contentType: "application/json;charset=utf-8;",
            dataType: 'json',
            success: function (res) {
                if (res) {
                    tipDialog(res.msg, 3, 2);
                    $("#addScheduleModal").modal('hide');
                    Loading(false, "", "#addScheduleModal");
                    location.reload();
                } else {
                    tipDialog(res.msg, 3, 'warning');
                    Loading(false, "", "#addScheduleModal");
                }
            },
            error: function () {
                tipDialog("网络异常", 3, -1);
                Loading(false, "", "#addScheduleModal");
            }
        });

    });
</script>