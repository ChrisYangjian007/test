<script>
    $(function () {
        GetTree();
    });
    //加载左边树
    function GetTree() {
        var itemtree = {
            showcheck: true,
            url: "/Home/ShortcutsModuleTreeJson"
        };
        $("#ItemsTree").treeview(itemtree);
    }
    //保存事件
    function AcceptClick() {
        Loading(true, "正在提交数据...");
        window.setTimeout(function () {
            var moduleId = $("#ItemsTree").getCheckedNodes();
            AjaxJson("/Home/SubmitShortcuts?ModuleId=" + moduleId, {}, function (data) {
                tipDialog(data.Message, 3, data.Code);
                top.ShortcutsList();
                closeDialog();
            });
        }, 200);
    }
</script>
<form id="form1" style="margin: 10px">
    <div style="height: 29px;">
        <div style="line-height: 26px; font-size: 14px;">
            菜单导航
        </div>
    </div>
    <div id="ItemsTree" class="border" style="height: 248px; overflow: auto;">
    </div>
</form>