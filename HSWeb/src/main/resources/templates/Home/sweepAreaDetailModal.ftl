<style>
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height: auto;
        vertical-align: text-top;
        padding-top: 2px;
        word-break: break-all;
    }
</style>
<table id="sweepGridTable" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
       aria-multiselectable="false" aria-labelledby="gbox_gridTable" class="ui-jqgrid-btable"
       style="width: 100%;">
</table>
<div id="sweepGridPager" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
     style="width: 100%;height: 40px">
</div>

<script>
    $(document).ready(function () {
        GetGrid("${request.contextPath}/Home/getSweepGridTable.json", "#sweepGridPager", "#sweepGridTable");
    });

    /**
     * 加载进行中表格
     * @param url 请求地址
     * @param id 分页控件id
     * @param table 表格id
     * */
    function GetGrid(url, id, table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: 410,
            autowidth: true,
            colModel: [
                {label: "最新扫码区域", name: "updateArea", index: "updateArea", width: 100, align: "center"},
                {label: "二维码编码", name: "qrCode", index: "qrCode", width: 100, align: "center"},
                {label: "成品批次号", name: "batchNo", index: "batchNo", width: 100, align: "center"},
                {label: "产品名称", name: "productName", index: "productName", width: 100, align: "center"},
                {label: "产品规格", name: "productSpecName", index: "productSpecName", width: 100, align: "center"},
                {label: "最新扫码IP", name: "createIp", index: "createIp", width: 140, align: "center"},
                {label: "二维码扫描次数", name: "sweepCount", index: "sweepCount", width: 150, align: "center"},
                {label: "最新扫码时间", name: "latestTime", index: "latestTime", width: 150, align: "center"}
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "" + id,
            rowNum: 10,
            rowList: [10, 20, 30, 50, 100],
            jsonReader: {
                root: "root", page: "page", total: "total", records: "records"
            },
            rownumbers: true,
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
</script>