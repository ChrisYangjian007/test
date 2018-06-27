<style>
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
        height: auto;
        padding-top: 2px;
        word-break: break-all;
    }
</style>
<table id="historyGridTable" tabindex="0" cellspacing="0" cellpadding="0" border="0" role="grid"
       aria-multiselectable="false" aria-labelledby="gbox_gridTable" class="ui-jqgrid-btable"
       style="width: 100%;">
</table>
<div id="historyGridPager" class="ui-state-default ui-jqgrid-pager ui-corner-bottom" dir="ltr"
     style="width: 100%;height: 40px">
</div>

<script>
    $(document).ready(function () {
        GetGrid("${request.contextPath}/Home/getScheduleHistoryModal.json", "#historyGridPager", "#historyGridTable");
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
            height: 400,
            autowidth: true,
            colModel: [
                {label: "产品大类", name: "productType", index: "productType", width: 100, align: "center"},
                {label: "规格", name: "productSpec", index: "productSpec", width: 205, align: "center"},
                {label: "数量", name: "amount", index: "amount", width: 100, align: "center"},
                {label: "完成数量", name: "completeCount", index: "completeCount", width: 100, align: "center"},
                {label: "单位", name: "unitName", index: "unitName", width: 100, align: "center"},
                {label: "创建用户", name: "createUser", index: "createUser", width: 140, align: "center"},
                {label: "创建时间", name: "createDate", index: "createDate", width: 150, align: "center"}
            ],
            pagerpos: "right",
            viewrecords: true,
            recordpos: "left",
            pager: "" + id,
            rowNum: 6,
            rowList: [6, 12, 18, 36, 72],
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