
<form id="qrCodeDetailForm" style="margin: 1px">
<#if qrCodeInterval??>
<div class="line"></div>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#qrCodeDiv" id="qrCodeA"> 二维码使用明细 </a>
    </li>
    <li>
        <a id="qrCodeUseA"> 二维码使用记录 </a>
    </li>
</ul>
<div class="line"></div>
<div class="tab-content">
    <div class="tab-pane active" id="qrCodeDiv">
        <table id="qrCode-gridTable"></table>
        <div id="qrCode-gridPager"></div>
    </div>
    <div class="tab-pane" id="qrCodeUseDiv">
        <table id="qrCodeUse-gridTable"></table>
        <div id="qrCodeUse-gridPager"></div>
    </div>
</div>
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

    $("#qrCodeDetailModal").on("click","#qrCodeA",function(){
        $("#qrCodeA").parent().addClass("active");
        $("#qrCodeDiv").addClass("active");
        $("#qrCodeUseA").parent().removeClass("active");
        $("#qrCodeUseDiv").removeClass("active");
    });

    $("#qrCodeDetailModal").on("click","#qrCodeUseA",function(){
        let id = GetJqGridRowValue("#qrCode-gridTable", "qrCodeId");
        if (IsChecked(id)) {
            $("#qrCodeA").parent().removeClass("active");
            $("#qrCodeDiv").removeClass("active");
            $("#qrCodeUseA").parent().addClass("active");
            $("#qrCodeUseDiv").addClass("active");
            qrCodeUseGetGridReload(id);
        }
    });

    function qrCodeUseGetGridReload(id) {
        $("#qrCodeUse-gridTable").jqGrid('setGridParam', {
            url: "${request.contextPath}/qrCodeUse/GridJson.json?qrCodeId="+id,
            page:1
        }).trigger('reloadGrid');
    }


</script>





