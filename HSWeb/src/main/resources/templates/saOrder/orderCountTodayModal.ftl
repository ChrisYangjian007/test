<div class="tab-pane" id="tab2">
<div class="bottomline QueryArea" style="margin: 1px; margin-top: 0px; margin-bottom: 0px;">
    <table border="0" class="form-find" style="height: 45px;">
        <tbody>
        <tr>
            <td colspan="2">
                请选择订单开始日期：<input id="startDate" name="startDate" type="text" class="txt Wdate" style="width: 140px; "
                                 onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})">
                请选择订单结束日期：<input id="endDate" name="endDate" type="text" class="txt Wdate" style="width: 140px; "
                                 onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd '})">
            </td>
            <td>
                <input id="btnSearchCount" type="button" class="btnSearch" value="查 询">
            </td>
        </tr>
        </tbody>
    </table>
    <table id="gridTableCount"></table>
    <div id="gridPagerCount"></div>
</div>
<#--<table id="tableOrder" class="grid" style="width: 100%">-->
    <#--<tr>-->
        <#--<th>产品编码</th>-->
        <#--<th>产品名称</th>-->
        <#--<th>规格</th>-->
        <#--<th>单位</th>-->
        <#--<th>订货数量</th>-->
    <#--</tr>-->
<#--<#if orderDetailList??>-->
    <#--<#list orderDetailList as orderDetail>-->
        <#--<tr>-->
            <#--<td><#if orderDetail.orderDetailCpbh??>-->
                  <#--${orderDetail.orderDetailCpbh}-->
                   <#--</#if>-->
            <#--</td>-->
            <#--<td><#if orderDetail.productName??>-->
                    <#--${orderDetail.productName}-->
                    <#--</#if>-->
            <#--</td>-->
            <#--<td><#if orderDetail.orderDetailGg??>-->
                     <#--${orderDetail.orderDetailGg}-->
                      <#--</#if>-->
            <#--</td>-->
            <#--<td><#if orderDetail.orderDetailDw??>-->
             <#--${orderDetail.orderDetailDw}-->
                <#--</#if>-->
            <#--</td>-->
            <#--<td><#if orderDetail.orderDetailSl??>-->
              <#--${orderDetail.orderDetailSl}-->
                <#--</#if>-->
            <#--</td>-->
        <#--</tr>-->
    <#--</#list>-->
<#--</#if>-->
<#--</table>-->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        GetGrid();
        gridPagerStyle();
    });
    function GetGrid() {
        $("#gridTableCount").jqGrid({
            url: "${request.contextPath}/saOrder/GridJsonTongji.json",
            datatype: "json",
            height: $(window).height() - 214,
            autowidth: true,
            colModel: [
                {label: "产品编号", name: "orderDetailCpbh", index: "orderDetailCpbh", width: 150},
                {label: "产品名称", name: "productName", index: "productName", width: 150},
                {label: "规格", name: "orderDetailGg", index: "orderDetailGg", width: 150},
                {label: "单位", name: "orderDetailDw", index: "orderDetailDw", width: 150},
                {label: "订货数量", name: "orderDetailSl", index: "orderDetailSl", width: 150}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPagerCount",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true
        });
    }
    function gridPagerStyle() {
        var width = document.getElementById("gridPagerCount_right").offsetWidth + 5;
        $("#gridPagerCount_right").attr("style", "width:" + width);
    }
    //查询已打包
    $("#btnSearchCount").unbind("click");
    $("#btnSearchCount").click(function () {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if ("" == startDate) {
            tipDialog("请选择订单开始日期", 4, 'a');
            return false;
        }
        if ("" == endDate) {
            tipDialog("请选择订单结束日期", 4, 'a');
            return false;
        }
        var postData = {startDate: startDate, endDate: endDate};
        $("#gridTableCount").jqGrid("setGridParam", {
            postData: postData,
            url: "${request.contextPath}/saOrder/GridJsonTongji.json",
            page: 1
        }).trigger("reloadGrid");
    });
</script>
