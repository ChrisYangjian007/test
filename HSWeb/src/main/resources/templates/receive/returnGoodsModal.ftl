<style>
    .txtselect {
        white-space: normal;
        word-break: break-all;
        word-wrap: break-word;
        height: 100% !important;
    }
</style>
<form id="returnGoodsForm" style="margin: 1px">
    <table class="form" id="returnTable1">
        <thead>
        <th class="formTitle" style="width: 30px;text-align:center">*序号</th>
        <th class="formTitle" style="width: 30px;text-align:center">批次号</th>
        <th class="formTitle" style="width: 50px;text-align:center">*货物类型</th>
        <th class="formTitle" style="width: 40px;text-align:center">*产品名称</th>
        <th class="formTitle" style="width: 30px;text-align:center">*规格</th>
        <th class="formTitle" style="width: 30px;text-align:center">*数量</th>
        <th class="formTitle" style="width: 100px;text-align:center">*供应商</th>
        <th class="formTitle" style="width: 45px;text-align:center">*退货数量</th>
        <th class="formTitle" style="width: 35px;text-align:center">单位</th>
        <th class="formTitle" style="width: 40px;text-align:center">备注1</th>
        </thead>
        <tbody>
        <#list puReceiveDetaildParaList as receiveDetail>
        <tr>
            <!--序号-->
            <td class="formValue" style="text-align:center">
            ${receiveDetail_index+1}
                <input type="hidden" value="${receiveDetail.receiveDetailId}"/>
                <input type="hidden" value="${receiveDetail.receiveId}"/>
            </td>
            <!--批次号-->
            <td class="formValue" style="text-align:center">${receiveDetail.batchNo!}</td>
            <!--货物类型-->
            <td class="formValue" style="text-align:center">${receiveDetail.goodsType!}</td>
            <!--产品名称-->
            <td class="formValue" style="text-align:center">${receiveDetail.productName!}</td>
            <!--规格-->
            <td class="formValue" style="text-align:center">${receiveDetail.productSpecName!}</td>
            <!--数量-->
            <td class="formValue" style="text-align:center">${receiveDetail.weight?c}</td>
            <!--供应商-->
            <td class="formValue" style="text-align:center">${receiveDetail.puReceive.enterpriseName!}</td>
            <!--退货数量-->
            <td class="formValue" style="text-align:center">
                <input type="text" class="txt" placeholder="请输入" maxlength="10"
                       onkeyup="clearNoNum(this)"/>
            </td>
            <!--单位-->
            <td class="formValue" style="text-align:center">${receiveDetail.unitName!}</td>
            <!--备注1-->
            <td class="formValue" style="text-align:center">
                <input id="remarkOne" type="text" class="txt" placeholder="选填项"/>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    <br/><br/>
    <table class="form" id="returnTable2">
        <tr>
            <td style="font-size: 15px;text-align: right;">退货编号:&nbsp;&nbsp;</td>
            <td colspan="3" style="font-size: 15px;"><#if receiveNode??>${receiveNode}</#if></td>
        </tr>
        <tr>
            <td rowspan="2" style="font-size: 15px;text-align: right;">退货原因:&nbsp;&nbsp;</td>
            <td colspan="3" rowspan="2" style="font-size: 15px;">
                <textarea type="text"
                          style="width: 450px;height: 60px"/>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td rowspan="2" style="font-size: 15px;text-align: right;">处理方案:&nbsp;&nbsp;</td>
            <td colspan="3" rowspan="2" style="font-size: 15px;">
                <textarea id="remarkyh2" name="" type="text"
                          style="width: 450px;height: 60px"/>
            </td>
        </tr>
        <tr>
        </tr>
        <tr>
            <td style="font-size: 15px;text-align: right;">经手人:&nbsp;&nbsp;</td>
            <td style="font-size: 15px;">
                <input id="" name="" type="text"
                       style="width: 210px;height: 30px"/>
            </td>
            <td style="font-size: 15px;width: 5px;" colspan="2">退货时间
                <input id="" name="" type="text" class="Wdate"
                       style="width: 170px;height: 30px"
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <td style="font-size: 15px;text-align: right;">备注2:&nbsp;&nbsp;</td>
            <td colspan="3" style="font-size: 15px;">
                <textarea id="" name="" type="text" placeholder="选填项"
                          style="width: 450px;height: 50px"/>
                <input type="hidden" value="${resourceId!}"/>
            </td>
        </tr>
    </table>
</form>

<script>

</script>