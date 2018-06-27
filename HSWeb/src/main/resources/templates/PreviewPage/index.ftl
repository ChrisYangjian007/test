<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>晓芹食品追溯信息查询</title>
    <link rel="stylesheet" href="${staticImg}/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${staticImg}/css/animate.css">
    <link rel="stylesheet" href="${staticImg}/css/comment.css">
    <link rel="stylesheet" href="${staticImg}/css/index.css">
</head>
<body>
    <img src="${staticImg}/images/yly/logo.png" alt="" class="logo">
    <div class="circle1 animated bounceInUp">
        <img src="${staticImg}/images/yly/circle1.png" alt="">
        <div class="circleBox">
            <img src="${staticImg}/images/yly/produce.png" alt="">
            <p>生产信息</p>
            <input type="hidden" id="productionProcessId" value="<#if productionProcessId??>${productionProcessId}</#if>">
        </div>
    </div>
    <div class="circle2 animated bounceInUp">
        <img src="${staticImg}/images/yly/circle2.png" alt="">
        <div class="circleBox">
            <img src="${staticImg}/images/yly/check.png" alt="">
            <p>检测中心</p>
        </div>
    </div>
    <div class="circle3 animated bounceInUp">
        <img src="${staticImg}/images/yly/circle3.png" alt="">
        <div class="circleBox">
            <img src="${staticImg}/images/yly/product.png" alt="">
            <p>产品信息</p>
            <input type="hidden" id="batchId" value="<#if batchId??>${batchId}</#if>">
        </div>
    </div>
    <div class="circle4 animated bounceInUp">
        <img src="${staticImg}/images/yly/circle4.png" alt="">
        <div class="circleBox">
            <img src="${staticImg}/images/yly/company.png" alt="">
            <p>企业介绍</p>
        </div>
    </div>
    <div class="circle5 animated bounceInUp">
        <img src="${staticImg}/images/yly/circle5.png" alt="">
        <div class="circleBox">
            <img src="${staticImg}/images/yly/communicate.png" alt="">
            <p>聊参论道</p>
        </div>
    </div>
    <div class="circle6 animated bounceInUp">
        <img src="${staticImg}/images/yly/circle6.png" alt="">
        <div class="circleBox">
            <img src="${staticImg}/images/yly/news.png" alt="">
            <p>企业新闻</p>
        </div>
    </div>

    <script src="${staticImg}/js/jquery.min.js"></script>
    <script>

        $(".circle1").click(function(){
            var productionProcessId = $("#productionProcessId").val();
            if(""!=productionProcessId){
                window.location.href="${request.contextPath}/PreviewPage/product.htm?id="+productionProcessId
            }
//            else {
//                tipDialog("未绑定生产信息，无法查看",4,"warning");
//            }
        });
        $(".circle2").click(function(){
            window.location.href="${request.contextPath}/PreviewPage/check.htm"
        });
        $(".circle3").click(function(){
            var batchId = $("#batchId").val();
            if(""!=batchId){
                window.location.href="${request.contextPath}/PreviewPage/details.htm?batchId="+batchId+"&qrCode=${qrCode}"
            }
//            else {
//                tipDialog("未绑定产品信息，无法查看",4,"warning");
//            }
        });
        $(".circle4").click(function(){
            window.location.href="${request.contextPath}/PreviewPage/culture.htm"
        });
        $(".circle5").click(function(){
            window.location.href="${request.contextPath}/PreviewPage/kitchen.htm"
        });
        $(".circle6").click(function(){
            window.location.href="${request.contextPath}/PreviewPage/news.htm  "
        })
    </script>
</body>
</html>