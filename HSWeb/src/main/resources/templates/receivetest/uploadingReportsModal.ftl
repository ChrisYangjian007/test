
<#--上传报告-->
<form id="uploadingReportsForm" style="margin: 1px" xmlns="http://www.w3.org/1999/html">
    <table class="form">
        <tr>
            <th class="formTitle">货物类型：
            </th>
            <td class="formValue">
                <input id="role" type="hidden" class="txt" name="receiveDetailId" value="<#if puReceiveTest.receiveDetailId??>${puReceiveTest.receiveDetailId}</#if>" />
                <input id="role" type="text" class="txt" value="<#if puReceiveTest.goodsType??>${puReceiveTest.goodsType}</#if>" readonly disabled/>
            </td>
            <th class="formTitle">批次号：
            </th>
            <td class="formValue">
                <input id="role" type="text" class="txt" value="<#if puReceiveTest.batchNo??>${puReceiveTest.batchNo}</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">产品名称：
            </th>
            <td class="formValue" >
                <input id="role" type="text" class="txt" value="<#if puReceiveTest.productName??>${puReceiveTest.productName}</#if>" readonly disabled/>
            </td>
            <th class="formTitle">规格：
            </th>
            <td class="formValue" >
                <input id="role" type="text" class="txt" value="<#if puReceiveTest.productSpecName??>${puReceiveTest.productSpecName}</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">数量：
            </th>
            <td class="formValue" >
                <input id="role" type="text" class="txt" value="<#if puReceiveTest.weight??>${puReceiveTest.weight}</#if>" readonly disabled/>
            </td>
            <th class="formTitle">单位：
            </th>
            <td class="formValue" >
                <input id="role" type="text" class="txt" value="<#if puReceiveTest.unitName??>${puReceiveTest.unitName}</#if>" readonly disabled/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">检验方式：
            </th>
            <td class="formValue">
                <select   class="txt required" id="inspectionMethod" class="txtselect" name="inspectionMethod">
                <#if puReceiveTests??>
                    <option value="">==请选择==</option>
                    <#list puReceiveTests as puReceive>
                        <option value="${puReceive.dataDictionaryDetailsId}"
                            <#if puReceiveTest.inspectionMethod??>
                            <#if puReceive.dataDictionaryDetailsId==puReceiveTest.inspectionMethod>selected</#if>
                            </#if>>
                        ${puReceive.dataDictionaryDetailscName}
                        </option>
                    </#list>
                <#else >
                    <option value="">==无数据==</option>
                </#if>
                </select>
            </td>
            <th class="formTitle">检验结果：
            </th>
            <td class="formValue">
                <select   class="txt required" id="testResult" class="txtselect" name="testResult">
                    <option value="">==请选择==</option>
                    <option value="1" <#if puReceiveTest.testResult??><#if 1==puReceiveTest.testResult>selected</#if></#if>>待检验</option>
                    <option value="2" <#if puReceiveTest.testResult??><#if 2==puReceiveTest.testResult>selected</#if></#if>>合格</option>
                    <option value="3" <#if puReceiveTest.testResult??><#if 3==puReceiveTest.testResult>selected</#if></#if>>不合格</option>
                </select>
            </td>
        </tr>
        <tr>
            <th class="formTitle">检验人：
            </th>
            <td class="formValue" >
                <input  class="txt required" id="inspectors" type="text" class="txt" name="inspectors"
                       value="<#if puReceiveTest.inspectors??>${puReceiveTest.inspectors}</#if>"/>
            </td>
            <th class="formTitle">检验时间：
            </th>
            <td class="formValue">
                <input  class="txt required" id="inspectionDate"  name="inspectionDate" type="text" class="txt Wdate" style="width: 100%; "
                        <#if puReceiveTest.inspectionDate?? >
                        value="${puReceiveTest.inspectionDate?string('yyyy-MM-dd HH:mm:ss')}"
                        </#if>
                        onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">备注：
            </th>
            <td class="formValue" colspan="3">
                <textarea class="txtArea" style="resize: none" id="remarks" name="remarks"  maxlength="200" rows="5"
<#if puReceiveTest.remarks??> placeholder="${puReceiveTest.remarks}"</#if>><#if puReceiveTest.remarks??>${puReceiveTest.remarks}</#if></textarea>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
    <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                <#if puReceiveTest.iamges??><img src="${puReceiveTest.iamges}"/></#if>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                            <#if puReceiveTest.iamges??>
                                <span class="fileinput-new"> 修改 </span>
                            <#else>
                                <span class="fileinput-new"> 添加 </span>
                            </#if>
                                <span class="fileinput-exists"> 修改 </span>
                            <input type="file" name="iamge" />
                        </span>
                <#if puReceiveTest.iamges??>
                    <a href="javascript:;" class="btn red fileinput-new" data-dismiss="fileinput"> 删除 </a>
                </#if>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
            <input type="hidden" id="imageId" name="imageId">
        </div>
    </div>
</form>
<script>
    $("input[name = iamge]").on("change",function(){
        var idx = $("input[id = iamge]").index($(this));
        var idxs = $("#imageId").val();
        idxs = ""+idxs+idx;
        $("#imageId").val(idxs);
    });
</script>
