

<div>
    <div id="layoutA" class="layout" onselectstart="return false;" style="position: relative; height: 353px;">
        <!--左边-->
        <div class="layoutPanel layout-west" style="position: absolute; z-index: 99; height: 353px; left: 0px; width: 180px;">
            <div class="btnbartitle">
                <div>
                    归属角色
                </div>
            </div>
            <div class="ScrollBar bbit-tree" id="ItemsTree" style="height: 323px;">${role.CName} </div>
        </div>
        <div class="layoutResize" unselectable="on" style="z-index: 100; position: absolute; user-select: none; cursor: e-resize; height: 353px; left: 180px;">
            <a href="javascript:void(0)" accesskey="L" tabindex="0" title="layoutResize"></a>
        </div>
        <!--中间-->
        <div class="layoutPanel layout-center"
             style="position: absolute; z-index: 99; height: 353px; left: 181px; width: 635px;">
            <div class="btnbartitle">
                <div style="float: left">
                    归属用户 -
                    <span id="CenterTitle">${role.CName}</span>；用户查询：
                    <input id="txt_Search" type="text" class="btnbartitleinput" style="width: 120px;">
                </div>
            </div>
            <div class="ScrollBar" id="MemberList" style="height: 324px;">
                <ul class="sys_spec_text">
                    <#if userList??>
                        <#list userList as user>
                            <li class=" selected">
                                <a class="a_selected" id="${user.userId}" title="工号：${user.code} 账户：${user.account}">
                                    <img src="${staticImg}/images/Icon16/user_green.png">${user.CName}
                                </a>
                                <i></i>
                            </li>
                        </#list>
                    <#else >
                        无任何人员
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

        //模糊查询用户（注：这个方法是理由jquery查询）
        $("#txt_Search").keyup(function () {
            if ($(this).val() != "") {
                var Search = $(this).val();
                window.setTimeout(function () {
                    $(".sys_spec_text li")
                            .hide()
                            .filter(":contains('" + (Search) + "')")
                            .show();
                }, 200);
            } else {
                $(".sys_spec_text li").show();
            }
        }).keyup();

</script>