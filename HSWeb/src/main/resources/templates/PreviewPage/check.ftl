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
              <img src="${staticImg}/images/yly/back.png" alt="" >
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
          <b>检测实验</b>
      </div>
      <div class="details" id="testingEquipment">
          <img src="${staticImg}/images/yly/equipment.png" alt="">
          <span>检测设备介绍</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox processBox">
          <#list ptestingEquipment as Equipment>
          <div class="productList">
              <div>
              <#if Equipment.id??><i>${Equipment.id}.</i></#if>
              <#if Equipment.testCName??>${Equipment.testCName}</#if>
              </div>
              <#if Equipment.remark??><p>${Equipment.remark}</p></#if>
              <#if Equipment.images??><img src="${Equipment.images}" alt=""></#if>
          </div>
           <#list testingEquipmentDetail as EquipmentDetail>
           <#if Equipment.testingEquipmentId==EquipmentDetail.testingEquipmentId>
              <div class="productList">
                  <p><#if EquipmentDetail.testingEquipmentDetailCname??>${EquipmentDetail.testingEquipmentDetailCname}</#if>
                      :
                      <#if EquipmentDetail.remark??>${EquipmentDetail.remark}</#if>
                  </p>
                  <#if EquipmentDetail.images??>
                     <img src="${EquipmentDetail.images}" alt="">
                   </#if>
              </div>
           </#if>
            </#list>
          </#list>
          <div class="more">
              <a>
                  <span>查看更多</span>
                  <img src="${staticImg}/images/yly/moreDown.png" alt="">
              </a>
          </div>
          <div class="pageBox">
              <div class="page">
                  <a class="prevPage">上一页</a>
                  <p>
                      共 <span class="total"></span>页
                      当前 <span class="current">1</span>页
                  </p>
                  <a class="nextPage">下一页</a>
              </div>
          </div>
      </div>
      <div class="details">
          <img src="${staticImg}/images/yly/test-center.png" alt="">
          <span>检测中心介绍</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
          <div style="display:block;font-size: 3em;
    color: #9a9a9a;
    line-height: 2em;">
              <p class="indent">2013年，晓芹公司投资7000余万元，建设占地面积约为17000平方米的全新科研生产加工基地。其中，仅质检化验室就投资约200余万元。设备先进的质检化验室，为晓芹公司从原料选择、成品检测等多方面的质检工作提供了硬件保障，也为晓芹公司严格把控食品安全问题，提供了最坚实的基础！</p>
              <p class="indent">其中，理化分析室可及时检测出产品的水分、盐分、蛋白质、PH值等理化指标。气相色谱分析室及液相色谱分析室可精准地检测出产品原料是否含有农药残留。原子荧光分析室及原子吸收分析室则可检测产品原料中的重金属含量。微生物室，可精准检测出产品中大肠杆菌是否控制在指标范围内及产品的菌落总数。</p>
              <p class="indent">经过严格检验合格后的产品原料，才能进入晓芹的加工车间，符合标准的产品才能流向市场。晓芹公司为保证产品原料的品质，除了批批自检以外，还会定期将原料及成品送至相关质检部门委托其进行检验。真正做到对消费者负责，对企业自身负责，对行业负责。把维护“大连海参”金字招牌作为己任，让消费者能够吃到放心的大连海参！</p>
              <img src="${staticImg}/images/yly/center.png" style="margin: 2em 0;
    width: 100%;">
          </div>
      </div>
  </div>

  <script src="${staticImg}/js/jquery.min.js"></script>
  <script src="${staticImg}/js/bootstrap/js/bootstrap.js"></script>
  <script>
      $(function(){
          showNext();
          showPrev();
          $(".processBox>.productList").eq(0).show();
          $(".processBox>.productList").eq(1).show();
      });
          var index=$(".productList").length;
          //分页
         var totalPage=Math.ceil(index/4);
          $(".total").text(totalPage);
          //点击下一页
          var currentPage=1;
          $(".nextPage").click(function(){
              currentPage<totalPage&&currentPage++;
                  $(".productList").hide();
                  $(".prevPage").css("visibility","visible");
                  $(".current").text(currentPage);
                  var indexPage=(currentPage-1)*4;
                  console.log(indexPage);
                  for(var i=indexPage;i<indexPage+4;i++){
                      $(".productList").eq(i).show();
                  }
                showNext();
              location.href="#testingEquipment";
          });

          //点击上一页
          $(".prevPage").click(function(){
              currentPage>1&&currentPage--;
                  $(".productList").hide();
              $(".nextPage").css("visibility","visible");
                  $(".current").text(currentPage);
                  var indexPage=(currentPage-1)*4;
                  console.log(indexPage);
                  for(var i=indexPage;i<indexPage+4;i++){
                      $(".productList").eq(i).show();
                  }
              showPrev();
              location.href="#testingEquipment";
          });
          //是否显示下一页
          function showNext(){
              if(currentPage==totalPage){
                  $(".nextPage").css("visibility","hidden");
              }else{
                  $(".nextPage").css("visibility","visible");
              }
          }
          //是否显示上一页
          function showPrev(){
              if(currentPage==1){
                  $(".prevPage").css("visibility","hidden");
              }else{
                  $(".prevPage").css("visibility","visible");
              }
          }
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
              $(this).siblings().eq(i).show();
              $(this).next().show();
          }
      });
  </script>
</body>
</html>