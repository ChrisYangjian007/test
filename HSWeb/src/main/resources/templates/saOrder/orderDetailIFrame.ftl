<style>
    .average {
        float: left;
        width: 50%;
        overflow: hidden;
        border: 1px solid #ccc;
    }

    .average-left, .average-right {
        float: left;
        padding: 5px;
        text-align: center;
    }

    .average-left {
        width: 30%;
        border-right: 1px solid #ccc;
        background: #f6f8fa;
    }

    .average-right {
        width: 70%;
    }

    h4 {
        margin: 10px;
    }

    .application {
        overflow: hidden;
        padding: 0 20px;
    }
</style>
<div class="tools_bar leftline rightline">
    <div class="PartialButton">
    <@shiro.hasPermission name="/orderDetail/reload">
        <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                <span>
                    <b class="btn-reload">刷新</b>
                </span>
        </a>
        <div class="tools_separator"></div>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="/orderDetail/back">
        <div class="tools_separator"></div>
        <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                <span>
                    <b class="btn-back">离开</b>
                </span>
        </a>
    </@shiro.hasPermission>
    </div>
</div>
<div class="rightline" style="margin: 1px; margin-top: -1px;">
    <div class="application">
        <h4>订货申请</h4>
        <div class="average">
            <div class="average-left">订货申请编码</div>
            <div class="average-right"><#if saOrder.orderName??>
            ${saOrder.orderName}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">审核状态</div>
            <div class="average-right"><#if saOrder.orderSpzt??>
            ${saOrder.orderSpzt}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">所属门店</div>
            <div class="average-right"><#if saOrder.orderSsmd??>
                ${saOrder.orderSsmd}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">申请人</div>
            <div class="average-right"><#if saOrder.orderOwnerid??>
                ${saOrder.orderOwnerid}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">订货日期</div>
            <div class="average-right"><#if saOrder.orderDhrq??>
${saOrder.orderDhrq?string("yyyy-MM-dd")}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">经销商名称</div>
            <div class="average-right"><#if saOrder.orderJxs??>
                 ${saOrder.orderJxs}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">联系电话</div>
            <div class="average-right"><#if saOrder.orderLxdh??>
                ${saOrder.orderLxdh}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">收货人</div>
            <div class="average-right"><#if saOrder.orderShr??>
          ${saOrder.orderShr}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">收货地址</div>
            <div class="average-right"><#if saOrder.orderShdz??>
                ${saOrder.orderShdz}
            </#if></div>
        </div>
        <div class="average">
            <div class="average-left">备注</div>
            <div class="average-right"></div>
        </div>
    </div>
    <div class="application">
        <h4>订货申请明细</h4>
        <table id="orderDetail-gridTable"></table>
        <div id="orderDetail-gridPager"></div>
    </div>
</div>


<script>

    $(document).ready(function () {
        orderDetailGrid();
        orderDetailPagerStyle();
        Loading(false);
    });

    function orderDetailGrid() {
        $("#orderDetail-gridTable").jqGrid({
            url: "${request.contextPath}/saOrder/orderDetailGrid.json?orderDetailDhsq=${saOrder.orderName}",
            datatype: "json",
            height: $(window).height() - 330,
            autowidth: true,
            colModel: [
                {label: "明细编号", name: "orderDetailName", index: "orderDetailName", width: 120},
                {label: "订货申请编号", name: "orderDetailDhsq", index: "orderDetailDhsq", width: 100},
                {label: "产品编码", name: "orderDetailCpbh", index: "orderDetailCpbh", width: 80},
                {label: "产品名称", name: "productName", index: "productName", width: 150},
                {label: "规格", name: "orderDetailGg", index: "orderDetailGg", width: 150},
                {label: "单位", name: "orderDetailDw", index: "orderDetailDw", width: 80},
                {label: "数量", name: "orderDetailSl", index: "orderDetailSl", width: 180},
                {label: "二维码", name: "orderDetailQrcode", index: "orderDetailQrcode", width: 100},
                {label: "箱码", name: "boxCode", index: "boxCode", width: 100},
                {label: "快递单", name: "expressForm", index: "expressForm", width: 100}
            ],
            pagerpos: "right",
            recordpos: "left",
            viewrecords: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#orderDetail-gridPager",
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            shrinkToFit: false,
            gridview: true
        });
    }

    function orderDetailPagerStyle() {
        let width = document.getElementById("orderDetail-gridPager_right").offsetWidth + 5;
        $("#orderDetail-gridPager_right").attr("style", "width:" + width);
    }

</script>


