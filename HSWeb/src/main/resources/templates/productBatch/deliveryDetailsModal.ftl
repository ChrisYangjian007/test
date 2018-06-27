
<form id="deliveryDetailsForm" style="margin: 1px">
<#if batchId??>
    <table id="deliveryDetails-gridTable"></table>
    <div id="deliveryDetails-gridPager"></div>
<#else >
<table class="form">
    <tr >
        <th class="formTitle">错误：
        </th>
        <td class="formValue" colspan="3">
            <input style="color: red" type="text" class="txt" value="请重试，再不行请联系管理员" disabled />
        </td>
    </tr>
</table>
</#if>
</form>

<script>
    $(document).ready(function () {
        orderBatchGrid();
        orderBatchPagerStyle();
        $("#deliveryDetails-gridTable").on("click",".OpentOrderDetail",function () {
            OpentOrderDetail($(this)[0].id);
        });
        Loading(false);
    });


    function orderBatchGrid() {
        $("#deliveryDetails-gridTable").jqGrid({
            url: "${request.contextPath}/productBatch/GridJsonPack.json?batchId=${batchId}",
            datatype: "json",
            height: 400,
            width: 730,
            colModel: [
                {label: "订货申请编码", name: "orderName", index: "orderName", width: 150},
                {label: "所属门店", name: "orderSsmd", index: "orderSsmd", width: 120},
                {label: '经销商名称', name: "orderJxs", index: "orderJxs", width: 120},
                {label: "打包量", name: "packNumber", index: "packNumber", width: 80},
                {
                    label: '详情', name: 'orderName', index: 'orderName', width: 100 ,
                    formatter: function (cellvalue, options, rowObject) {
//                        return  '<a onclick="OpentOrderDetail('+ v + ')">查看详情</a>';
                        return "<a id='"+ cellvalue + "' class = 'OpentOrderDetail' >查看详情</a>";
                    }
                }
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 10,
            rowList: [10, 30, 50, 100, 500],
            pager: "#deliveryDetails-gridPager",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            shrinkToFit: false,
            gridview: true
        });
    }

    function orderBatchPagerStyle() {
        let width = document.getElementById("deliveryDetails-gridPager_right").offsetWidth + 5;
        $("#deliveryDetails-gridPager_right").attr("style", "width:" + width);
    }
    function OpentOrderDetail(orderName) {
        $.ajax({
            url: "${request.contextPath}/saOrder/getIsOrder.json?orderName="+orderName,
            type: "POST",
            success: function (res) {
                if (res.success) {
                    AddTabMenu(orderName + 'orderDetailIFrame', '${request.contextPath}/saOrder/orderDetailIFrame.htm?orderName=' + orderName, '订单详情', "application_view_detail.png", 'true', "${staticImg}");
                } else {
                    tipDialog("无订单详情", 4, 0);
                    Loading(false)
                }
            },
            error: function () {
                tipDialog("网络异常", 0, 2);
                Loading(false)
            }
        })

    }
</script>





