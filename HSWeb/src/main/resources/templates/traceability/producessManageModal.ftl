
<form id="updateWorkProcessForm" style="margin: 1px">
    <div class="bd">
        <div id="updateIsWorkshopRecordDiv">
            <div class="tipstitle_title settingtable bold bd todayInfoPanelTab rightPanelTitle_normal">
                <div class="tab_list_top" style="position: absolute">
                    <div id="TabTableField1" class="tab_list bd actived">生产过程</div>
                    <div id="TabTableField2" class="tab_list bd ">生产过程详情</div>
                </div>
                <div style="float: right;">
                    <div class="tools_bar_icon">
                        <div class="tools_separator" style="height: 23px; margin-right: 5px;"></div>
                        <div class="icon-botton" title="插入行" onclick="InsetProcessTableRow()">
                            <img src="${staticImg}/images/Icon16/plus.png" />
                        </div>
                        <div class="icon-botton" title="清空行" onclick="EmptyProcessTableRow()">
                            <img src="${staticImg}/images/Icon16/basket_delete.png" />
                        </div>
                    </div>
                </div>
            </div>
            <div id="UpdateTableFieldDiv" class="ScrollBar" style="margin-top: 1px;">
                <div id="TableField1" class="tabPanel">
                    <table id="grid_Field1" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 30px; text-align: center; border-left: none;">
                                <div class="table-header">编号</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">名称</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <#if productionProcessList??&&productionProcessList?size!=0>
                        <tr>
                            <#list productionProcessList as productionProcess>
                                <td class="td-div" style="width: 30px; text-align: center;border-left: none;">${(productionProcess_index)+1}</td>
                                <td style="width: 60px; text-align: center;">
                                    <#--<div></div>-->
                                    <#--<input type="text"  class="txt enterBatchNo" name ="productionProcessName" value="${productionProcess.productionProcessName}"/>-->
                                ${productionProcess.productionProcessName}
                                </td>
                            </#list>
                        </tr>
                        <#else >
                            <tr>
                                <td style="width: 60px; text-align: center;" colspan="2">暂无数据</td>
                            </tr>
                        </#if>
                        </tbody>
                    </table>
                </div>
                <div id="TableField2" class="tabPanel hidden">
                    <table id="grid_Field2" class="grid" style="width: 100%">
                        <thead>
                        <tr>
                            <td style="width: 60px; text-align: center;">
                                <div class="table-header">编号</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">过程名称</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">过程描述</div>
                            </td>
                            <td style="width: 80px; text-align: center;">
                                <div class="table-header">图片详情</div>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>

<script>

    $(document).ready(function () {
        RowIndex =1;
        TabTable =1;
        TableTdEventBatch1();
        TableTdEventBatch2();
    });

    function cancelButton(num){
        TabTable=1;
        $("#TableField2").addClass("hidden");
        $("#TableField1").removeClass("hidden");
        $("#TabTableField2").removeClass("actived");
        $("#TabTableField1").addClass("actived");
        if(num ==1) {
            $(".holdButton").remove();
        }else if(num ==2){
            $(".holdButton").removeClass("holdButton");
        }else if(num ==3){
            $("#grid_Field2 tbody tr").remove();
        }
        $("#buttonOne").removeClass("hidden");
        $("#buttonTwo").addClass("hidden");
    }

    //插入一个空行
    function InsetProcessTableRow() {
        if(1 ==TabTable) {
            var len = $("#grid_Field1 tbody tr").length;
            $("#grid_Field1 tbody").append(CreateTableRow(len + 1)).find('input,select').hide();
            var indexrow = 1;
            $("#grid_Field1 tbody tr").each(function () {
                $(this).find('td:eq(0)').html(indexrow);
                $(this).find("input,select").each(function () {
                    let indexId = $(this).attr("id");
//                    let index = indexId.match(/^[a-z|A-Z]+/gi);
//                    let index = indexId.split("-");
                    $(this).attr("id", indexId + indexrow);
                });
                indexrow++;
            })
        }else if(2 ==TabTable){
            var len = $("#grid_Field2 tbody tr").length;
            $("#grid_Field2 tbody").append(CreateTableRow2(len + 1)).find('input,select').hide();
            var indexrow = 1;
            $("#grid_Field2 tbody tr").each(function () {
                $(this).find('td:eq(0)').html(indexrow);
                $(this).find("input,select").each(function () {
                    let indexId = $(this).attr("id");
//                    let index = indexId.match(/^[a-z|A-Z]+/gi);
//                    let index = indexId.split("-");
                    $(this).attr("id", indexId + indexrow);
                });
                indexrow++;
            })
        }
    }

    //清空表格当前行
    function EmptyProcessTableRow() {
        if(TabTable ==1) {
            var trobj = $("#grid_Field1 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field1 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        if(null==indexId){
                            indexId = 0;
                        }
                        let index = indexId.match(/^[a-z|A-Z]+/gi);
                        $(this).attr("id", index + indexrow);
                    });
                    indexrow++;
                })
            } else {
                tipDialog("请先选择一行",4,"warning");
            }
        }else if(TabTable ==2){
            var trobj = $("#grid_Field2 tbody .selected");
            if (trobj != null && trobj != undefined && trobj.length > 0) {
                trobj.remove();
                var indexrow = 1;
                $("#grid_Field2 tbody tr").each(function () {
                    $(this).find('td:eq(0)').html(indexrow);
                    $(this).find("input,select").each(function () {
                        let indexId = $(this).attr("id");
                        if(null==indexId){
                            indexId = 0;
                        }
                        let index = indexId.match(/^[a-z|A-Z]+/gi);
                        $(this).attr("id", index + indexrow);
                    });
                    indexrow++;
                })
            } else {
                tipDialog("请先选择一行",4,"warning");
            }
        }
    }
    //表格点击事件
    function TableTdEventBatch1() {
        $("#TableField1 .grid").on("click",".td-div",function(){
            if($(this).parent().hasClass("selected")){
                $(this).parent().removeClass("selected");
            }else{
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field1 tbody");
        grid.on("click","td",function(){
            if ($(this).find('div').length > 0) {
                var oldText = $(this).find('div').text();
                var obj = $(this).find('input,select');
                if (obj.attr('disabled')) {
                    return false;
                }
                var type = obj.attr('class');
                obj.show();
                obj.parent().find('div').html("");
                obj.trigger("focus");
                obj.blur(function () {
                    if (obj.parent().find('div').length > 0) {
                        var type = obj.attr('type');
                        var objclass = (obj.parent().find('input[type=text]').length > 0) ? obj.parent().find('input[type=text]').attr('class') : "";
                        var newText = "";
                        switch (type) {
                            case "select":
                                newText = obj.find("option:selected").text();
                                if (obj.parent().find('input[type=hidden]').length > 0) {
                                    if(obj.parent().find('input[type=hidden]').attr("class")!="buyaogaiwo"){
                                        obj.parent().find('input[type=hidden]').val(newText);
                                    }
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        })
    }

    //表格点击事件
    function TableTdEventBatch2() {
        $("#TableField2 .grid").on("click",".td-div",function(){
            if($(this).parent().hasClass("selected")){
                $(this).parent().removeClass("selected");
            }else{
                $(this).parent().addClass("selected");
            }
        });
        var grid = $("#grid_Field2 tbody");
        grid.on("click","td",function(){
            if ($(this).find('div').length > 0) {
                var oldText = $(this).find('div').text();
                var obj = $(this).find('input,select');
                if (obj.attr('disabled')) {
                    return false;
                }
                var type = obj.attr('class');
                obj.show();
                obj.parent().find('div').html("");
                obj.trigger("focus");
                obj.blur(function () {
                    if (obj.parent().find('div').length > 0) {
                        var type = obj.attr('type');
                        var objclass = (obj.parent().find('input[type=text]').length > 0) ? obj.parent().find('input[type=text]').attr('class') : "";
                        var newText = "";
                        switch (type) {
                            case "select":
                                newText = obj.find("option:selected").text();
                                if (obj.parent().find('input[type=hidden]').length > 0) {
                                    if(obj.parent().find('input[type=hidden]').attr("class")!="buyaogaiwo"){
                                        obj.parent().find('input[type=hidden]').val(newText);
                                    }
                                }
                                obj.hide();
                                obj.parent().find('div').html(newText);
                                break;
                            default:
                                if (objclass != "txt icontree") {
                                    newText = obj.val();
                                    obj.hide();
                                    obj.parent().find('div').html(newText);
                                }
                                break;
                        }
                    }
                });
            }
        })
    }

    //创建生产过程
    function CreateTableRow(index) {
        var tr = '';
        tr += '<tr>';
        tr += '<td class="td-div" style="text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="text-align: center;"><input id="productionProcessName' + index + '" type="text"  class="txt enterBatchNo" name ="productionProcessName" /></td>';
        tr += '</tr>';
        return tr;
    }
    //创建生产过程详情
    function CreateTableRow2(index) {
        var tr = '';
        tr += '<tr>';
        tr += '<td class="td-div" style="text-align: center;border-left: none;">' + index + '</td>';
        tr += '<td style="text-align: center;"><input id="productionProcessNameTable' + index + '" type="text"  class="txt enterBatchNo"/></td>';
        tr += '<td style="text-align: center;"><input id="productionProcessRemark' + index + '" type="text"  class="txt enterBatchNo"/></td>';
        tr += '<td style="text-align: center;">';
        tr += '<div class="image">';
        tr += '<div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">';
        tr += '<div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">';
        tr += '<img src=""/>';
        tr += '</div>';
        tr += '<div>';
        tr += '<span class="btn green-jungle btn-outline btn-file">';
        tr += '<span class="fileinput-new"> 添加 </span>';
        tr += '<span class="fileinput-exists"> 修改 </span>';
        tr += '<input type="file" name="image"/>';
        tr += '</span>';
        tr += '<a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>';
        tr += '</div>';
        tr += '</div>';
        tr += '</div>';
        tr += '</td>';
        tr += '</tr>';
        return tr;
    }
</script>