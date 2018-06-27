
<style>
    .productList{
        overflow: hidden;
    }
    .productTitle{
        font-size:0;
        padding:10px 10px 5px 0;
        border-bottom:1px solid #ddd;
    }
    .productTitle>a{
        font-size:14px;
        padding:7px;
    }
    .productList{
        padding-top:20px;
    }
    .productList>li{
        float:left;
        width:16.66%;
        text-align:center;
        margin:5px 0;
    }
    .productList>li>a{
        display: inline-block;
        width:90%;
        padding:10px 5%;
        border:1px solid #ddd;
        border-radius:5px;
    }
    #productBox-2{
        display:none;
    }
    .productActive{
        border:1px solid #ddd;
        border-bottom:0;
        background:#fff;
    }
    .clickBg{
        background:#ccc;
    }
</style>

<div id="productDiv">
    <div class="productTitle">
        <a id="product-1" class="productActive">半成品加工</a>
        <a id="product-2">成品加工</a>
        <span class="pull-right"><em style="color:red;margin-right:5px;">*</em>仅限选择一个</span>
    </div>
    <div id="productBox-1">
        <ul class="productList">
        <#if semiFinished??>
            <#list semiFinished as semiFinished>
                <li>
                    <a>${semiFinished.CName}<input type="hidden" id="${semiFinished.dataDictionaryDetailsId}"/>
                    </a>
                </li>
            </#list>
        </#if>
        </ul>
    </div>
    <div id="productBox-2">
        <ul class="productList">
        <#if finished??>
            <#list finished as finished>
                <li>
                    <a >${finished.CName}<input type="hidden" id="${finished.dataDictionaryDetailsId}"/>
                    </a>
                </li>
            </#list>
        </#if>
        </ul>
    </div>
</div>

<script>

    $(document).ready(function () {
        var nameId = $("#nameId").val();
        if (""!=nameId) {
            var productBoxID = $("#" + nameId).parent().parent().parent().parent().attr("id");
            if ("productBox-1" == productBoxID) {
                $("#productBox-2").hide();
                $("#productBox-1").show();
                $("#product-2").removeClass("productActive");
                $("#product-1").addClass("productActive");
            } else if ("productBox-2" == productBoxID) {
                $("#productBox-1").hide();
                $("#productBox-2").show();
                $("#product-1").removeClass("productActive");
                $("#product-2").addClass("productActive");
            }
            $("#" + nameId).parent().addClass("clickBg");
        }
    });


    $("#product-1").click(function(){
        $("#productBox-2").hide();
        $("#productBox-1").show();
        $("#product-2").removeClass("productActive");
        $(this).addClass("productActive");
    });
    $("#product-2").click(function(){
        $("#productBox-1").hide();
        $("#productBox-2").show();
        $("#product-1").removeClass("productActive");
        $(this).addClass("productActive");
    });
    $(".productList>li>a").click(function(){
        $(".productList>li>a").removeClass("clickBg");
        $(this).addClass("clickBg");
    })

</script>