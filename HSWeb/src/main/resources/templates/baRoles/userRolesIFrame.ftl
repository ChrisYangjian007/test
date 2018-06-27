<@shiro.hasPermission name="/role/userRole">

<head>
    <style>
        #ItemsTree::-webkit-scrollbar{
            width: 15px;
        }
    </style>
</head>

<div class="layoutPanel layout-center" >
    <div class="btnbartitle">
        <div>
            权限列表<span id="CenterTitle"></span>
        </div>
    </div>
    <div class="ScrollBar bbit-tree" id="ItemsTree"></div>

    <div class="modal-footer">
        <button type="button" class="btn btn-default" onclick="ThisCloseTab()">取消</button>
        <button id="updateUserRoles" type="button" class="btn btn-primary">确定</button>
    </div>
</div>


<script>

    $(document).ready(function () {
        GetTree();
    });
    var CompanyId = "", CompanyName = "",isChange=false;
    //加载左边树
    function GetTree() {
        var itemtree = {
            onnodeclick: function (item) {
                CompanyId = item.id;            //ID
                CompanyName = item.text;        //名称
            },
            oncheckboxclick:function () {
                isChange = true;
            },
            showcheck:true,
            url: "${request.contextPath}/roles/userBaRoleTree.json?id=${role.roleId}&type=${role.category}"
        };
        $("#ItemsTree").treeview(itemtree);
        $("#ItemsTree").attr("style","height:"+($(window).height() - 180)+"px;overflow:auto;");
        Loading(false);
    }

    $("#updateUserRoles").unbind("click");
    $("#updateUserRoles").click(function () {
        Loading(true, "正在提交数据...");
        if (!isChange){
            tipDialog('无任何变化，不能修改！', 4, 'warning');
            Loading(false,"");
            return false;
        }
        var data = $("#ItemsTree").getTSNs(true);
        var id = "";
        for (var i = 0;i<data.length;i++){
            id+=parseInt(data[i].id);
            if (i!=(data.length-1)){
                id+=","
            }
        }
        $.post('${request.contextPath}/roles/updateUserRoles.json',{"list":id,"roleId":${role.roleId}}, function (result) {
            if (result.success) {
                tipDialog(result.msg, 4, 1);
                ThisCloseTab();
            } else {
                tipDialog(result.msg, 4, 0);
                Loading(false,"");
            }
        }, "JSON");


    });



</script>

</@shiro.hasPermission>
