
<form id="warehouseImagesHistoryForm" style="margin: 1px">
<#if warehouseId??>
    <div class="line"></div>
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="#warehouseImagesHistoryDiv" id="warehouseImagesHistoryA"> 记录 </a>
        </li>
        <li>
            <a id="warehouseImagesA"> 图片 </a>
        </li>
    </ul>
    <div class="line"></div>
    <div class="tab-content">
        <div class="tab-pane active" id="warehouseImagesHistoryDiv">
            <table id="warehouseImagesHistory-gridTable"></table>
            <div id="warehouseImagesHistory-gridPager"></div>
        </div>
        <div class="tab-pane" id="warehouseImagesDiv"><#--
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail warehouseImagesJson" data-trigger="fileinput" style="width: 170px; height: 160px;">
                        &lt;#&ndash;<#if warehouseImages??&&warehouseImages.imagesJson??&&warehouseImages.warehouseImagesJson[0]??&&warehouseImages.warehouseImagesJson[0].imageUrl!="">
                            <img src="${warehouseImages.warehouseImagesJson[0].imageUrl}" alt="${warehouseImages.warehouseImagesJson[0].imageName}"/>
                        </#if>&ndash;&gt;
                </div>
            </div>-->
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

    $("#warehouseImagesHistoryModal").on("click","#warehouseImagesHistoryA",function(){
        $("#warehouseImagesHistoryA").parent().addClass("active");
        $("#warehouseImagesHistoryDiv").addClass("active");
        $("#warehouseImagesA").parent().removeClass("active");
        $("#warehouseImagesDiv").removeClass("active");
    });

    $("#warehouseImagesHistoryModal").on("click","#warehouseImagesA",function(){
        let id = GetJqGridRowValue("#warehouseImagesHistory-gridTable", "warehouseImageId");
        if (IsChecked(id)) {
            $("#warehouseImagesHistoryA").parent().removeClass("active");
            $("#warehouseImagesHistoryDiv").removeClass("active");
            $("#warehouseImagesA").parent().addClass("active");
            $("#warehouseImagesDiv").addClass("active");
            warehouseImagesGetGridReload(id);
        }
    });

    function warehouseImagesGetGridReload(id) {
        Loading(true,"正在获取数据...","#warehouseImagesHistoryModal");
        $.post("${request.contextPath}/warehouseImage/getWarehouseImagesById.json", {id:id}, function (result) {
            if(result.success){
                var warehouseImages=result.obj.warehouseImagesJson;
                var images="";
                    for (var i=0;i<warehouseImages.length;i++){
                        if (""!=warehouseImages[i].imageUrl) {
                            images += '<div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">' +
                                    '<div class="fileinput-preview thumbnail warehouseImagesJson" data-trigger="fileinput" style="width: 170px; height: 160px;">';
                            images += '<img src="' + warehouseImages[i].imageUrl + '" alt="' + warehouseImages[i].imageName + '"/>';
                            images += '</div></div>';
                        }
                    }
                $("#warehouseImagesDiv").html(images);
            }else {
                tipDialog(result.msg,4,'0');
            }
            Loading(false,"","#warehouseImagesHistoryModal");
        }, "JSON");
    }


</script>





