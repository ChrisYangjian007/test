<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>晓芹食品追溯信息查询</title>
    <link rel="stylesheet" href="${staticImg}/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${staticImg}/css/animate.css">
    <link rel="stylesheet" href="${staticImg}/css/comment.css">
    <link rel="stylesheet" href="${staticImg}/css/details.css">
</head>
<body>
  <header class="header-product">
      <div class="headerTop">
          <a class="pull-left" onclick="history.go(-1)">
              <img src="${staticImg}/images/yly/back.png" alt="">
          </a>
          <span>晓芹食品追溯信息查询</span>
      </div>
      <div>
          <img src="${staticImg}/images/yly/productBG.png" alt="" class="productBG">
      </div>
  </header>
  <div class="main-product">
      <div class="mainTop">
          <span class="SL"></span>
          <b>生产信息</b>
      </div>
      <div class="details" id="productionProcess">
          <img src="${staticImg}/images/yly/process.png" alt="">
          <span>产品生产过程</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
        <#if (zsProductionProcess.productionProcessName)??><h4 class="type">${zsProductionProcess.productionProcessName}：</h4></#if>
         <#if zsProductionProcessDetails??>
          <#list zsProductionProcessDetails as InformationDetaileds>
          <div class="productList step">
              <div><i>${InformationDetaileds_index+1}.</i><#if InformationDetaileds.productionProcessDetailName??>${InformationDetaileds.productionProcessDetailName}</#if></div>
              <p>
               <#if InformationDetaileds.processDescription??>
                ${InformationDetaileds.processDescription}
                </#if>
              </p>
              <#if InformationDetaileds.detailImage??>
                  <img src="${InformationDetaileds.detailImage}" alt="">
              </#if>
          </div>
          </#list>
         </#if>
          <div class="more">
              <a>
                  <span>查看更多</span>
                  <img src="${staticImg}/images/yly/moreDown.png" alt="">
              </a>
          </div>
          <div class="pageBox">
              <div class="page">
                  <a class="prevPage prevPage1">上一页</a>
                  <p>
                      共 <span class="total first"></span>页
                      当前 <span class="current">1</span>页
                  </p>
                  <a class="nextPage nextPage1">下一页</a>
              </div>
          </div>
      </div>
      <div class="details" id="productionInformation">
          <img src="${staticImg}/images/yly/control.png" alt="">
          <span>生产关键控制点</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
          <#if productionInformation??&&productionInformation?size!=0>
          <#list productionInformation as Information>
          <div class="productList controlList">
              <div>
              <i>${Information.id}.</i><#if Information.productionInformationName??>${Information.productionInformationName}</#if>
              </div>
              <#if Information.remark??><p>${Information.remark}</p></#if>
              <#if Information.images??><img src="${Information.images}" alt=""></#if>
          </div>
          </#list>
          </#if>
          <div class="more">
              <a>
                  <span>查看更多</span>
                  <img src="${staticImg}/images/yly/moreDown.png" alt="">
              </a>
          </div>
          <div class="pageBox">
              <div class="page">
                  <a class="prevPage prevPage2">上一页</a>
                  <p>
                      共 <span class="total second"></span>页
                      当前 <span class="current">1</span>页
                  </p>
                  <a class="nextPage nextPage2">下一页</a>
              </div>
          </div>

      </div>
  </div>

  <script src="${staticImg}/js/jquery.min.js"></script>
  <script src="${staticImg}/js/bootstrap/js/bootstrap.js"></script>
  <script>
      $(function(){
          var isShowF=$(".step").siblings(".pageBox").find(".nextPage");
          totalPage1>1?isShowF.css("visibility","visible"):isShowF.css("visibility","hidden");
          var isShowS=$(".controlList").siblings(".pageBox").find(".nextPage");
          totalPage2>1?isShowS.css("visibility","visible"):isShowS.css("visibility","hidden");
          $(".prevPage").css("visibility","hidden");
          $(".step").eq(0).show();
          $(".step").eq(1).show();
          $(".controlList").eq(0).show();
          $(".controlList").eq(1).show();
      });

      //生产过程
      var index1=$(".step").length;
      var totalPage1=Math.ceil(index1/4);
      $(".first").text(totalPage1);
      //关键点
      var index2=$(".controlList").length;
      var totalPage2=Math.ceil(index2/4);
      $(".second").text(totalPage2);
      //点击下一页
      var currentPage1=1;
      var currentPage2=1;
      $(".nextPage1").click(function(){
          currentPage1<totalPage1&&currentPage1++;
          $(".step").hide();
          $(".prevPage1").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage1);
          var indexPage=(currentPage1-1)*4;
          for(var i=indexPage;i<indexPage+4;i++){
              $(".step").eq(i).show();
          }
          if(currentPage1==totalPage1){
              $(".nextPage1").css("visibility","hidden");
          }else{
              $(".nextPage1").css("visibility","visible");
          }
          location.href="#productionProcess";
      });

      //2222222222
      $(".nextPage2").click(function(){
          currentPage2<totalPage2&&currentPage2++;
          $(".controlList").hide();
          $(".prevPage2").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage2);
          var indexPage=(currentPage2-1)*4;
          for(var i=indexPage;i<indexPage+4;i++){
              $(".controlList").eq(i).show();
          }
          if(currentPage2==totalPage2){
              $(".nextPage2").css("visibility","hidden");
          }else{
              $(".nextPage2").css("visibility","visible");
          }
          location.href="#productionInformation";
      });
      //点击上一页
      //11111111111
      $(".prevPage1").click(function(){
          currentPage1>1&&currentPage1--;
          $(".step").hide();
          $(".nextPage1").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage1);
          var indexPage=(currentPage1-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".step").eq(i).show();
          }
          if(currentPage1==1){
              $(".prevPage1").css("visibility","hidden");
          }else{
              $(".prevPage1").css("visibility","visible");
          }
          location.href="#productionProcess";
      });
      //222222222
      $(".prevPage2").click(function(){
          currentPage2>1&&currentPage2--;
          $(".controlList").hide();
          $(".nextPage2").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage2);
          var indexPage=(currentPage2-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".controlList").eq(i).show();
          }
          if(currentPage2==1){
              $(".prevPage2").css("visibility","hidden");
          }else{
              $(".prevPage2").css("visibility","visible");
          }
          location.href="#productionInformation";
      });

      //展开-收起
      $(".upDown").click(function(){
          if($(this).children("span").text()=="收起"){
              $(this).parents(".details").next(".productBox").hide();
              $(this).children("span").text("展开");
              $(this).children("img").attr("src","${staticImg}/images/yly/up.png");
          }else{
              $(this).parents(".details").next(".productBox").show();
              $(this).children("span").text("收起");
              $(this).children("img").attr("src","${staticImg}/images/yly/down.png");
          }
      });
      //查看更多
      $(".more").click(function(){
          $(this).hide();
          for(var i=0;i<4;i++){
              $(this).siblings().not("h4").eq(i).show();
              $(this).next().show();
          }
      });
  </script>
</body>
</html>