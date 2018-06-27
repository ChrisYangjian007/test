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
<style>
    .media-left>img{
        width:100%;
    }
</style>
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
          <b>聊参论道</b>
      </div>
      <div class="details" id="kitchen1">
          <img src="${staticImg}/images/yly/cooking.png" alt="">
          <span>晓芹厨房</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>

      <div class="productBox">
      <#list xqKitchen as Kitchen>
          <div class="media pageList">
          <#if Kitchen.imagesJson??&&Kitchen.imagesJson[0]??&&Kitchen.imagesJson[0].imageUrl!="">
              <div class="media-left media-left-list">
                  <img src="${Kitchen.imagesJson[0].imageUrl}" alt=""/>
              </div>
          </#if>
              <div class="media-body">
                  <h4 class="media-heading"><#if Kitchen.kitchenName??>${Kitchen.kitchenName}</#if></h4>
                  <p><#if Kitchen.features??>${Kitchen.features}</#if></p>
                  <#if Kitchen.kitchenId??>
                        <input type="hidden" class="kitchenId_${Kitchen_index}" value="${Kitchen.kitchenId}">
                  </#if>
              </div>
          </div>
      </#list>
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
      <div class="details" id="kitchen2">
          <img src="${staticImg}/images/yly/hat.png" alt="">
          <span>晓芹教您辨海参</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
          <div class="teach">
              <div class="ban">真金不怕火炼，好参不怕考验！晓芹海参教您如何辨别好海参！</div>
              <p>在大连当地，许多百姓家中都有“日食一参”的习惯。不仅是秋冬季节，即食春夏选择每天食用一根海参的百姓也有很多。其中原因，除了海参滋味鲜美以外，最主要的还是在于其富含丰富的营养元素。而且，早在古代海参就被中医认为是“药食同源，阴阳双补”的食品，是帝王贵族享用的宫廷御膳。</p>
              <p>正所谓“真金不怕火炼，好参不怕考验”，晓芹海参今天就教给您如何辨别高品质半干海参的方法。</p>
          </div>
          <div class="productList way pageList">
              <img src="${staticImg}/images/yly/distinguish/look.jpg" alt="">
              <div class="cookingList">
                  <div class="dish-name"><i>1.</i>望</div>
                  <p>体型中等、具圆锥状肉刺，排列成4至6排不规则纵行；刺的根部粗壮，梢部尖。腹部管足较多且粗壮。</p>
              </div>
          </div>
          <div class="productList way pageList">
              <img src="${staticImg}/images/yly/distinguish/smell.jpg" alt="">
              <div class="cookingList">
                  <div class="dish-name"><i>2.</i>闻</div>
                  <p>正宗大连海刺参闻起来是一股淡淡的鲜香味，因为海参吃的是泥沙和海藻。煮好的海参闻起来，同样有一股鲜香的味道。</p>
              </div>
          </div>
          <div class="productList way pageList">
              <img src="${staticImg}/images/yly/distinguish/touch.jpg" alt="">
              <div class="cookingList">
                  <div class="dish-name"><i>3.</i>摸</div>
                  <p>摸海参有两种方法，一种是握在手里，感受海参的弹性；另一种是用拇指和食指掐住海参的侧壁，感受海参肉质的厚度。</p>
              </div>
          </div>
          <div class="productList way pageList">
              <img src="${staticImg}/images/yly/distinguish/cut.jpg" alt="">
              <div class="cookingList">
                  <div class="dish-name"><i>4.</i>切</div>
                  <p>将发好的海参顺着横切面切开，可以看到大连的海刺参肉都是满满的，基本上没有什么缝隙，肉质的紧实度和厚度都是好海参必须具备的基本条件。</p>
                  <br>
                  <p>另外，品质好的半干海参，轻轻摔在桌面上，海参会再次弹起来。这是说明海参的肉质紧密且有弹性，我们称它是“会跳舞的海参”。</p>
              </div>
          </div>
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
                      共 <span class="total second">3</span>页
                      当前 <span class="current">1</span>页
                  </p>
                  <a class="nextPage nextPage2">下一页</a>
              </div>
          </div>
      </div>
      <div class="details">
          <img src="${staticImg}/images/yly/teach.png" alt="">
          <span>晓芹教您发海参</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
          <div class="ban" id="kitchen3">半干海参</div>
          <div>
              <div class="productList fa pageList">
                  <img src="${staticImg}/images/yly/fa/fa-cut.png" alt="">
                  <div class="cookingList">
                      <div class="dish-name"><i>1.</i>剪</div>
                      <p>将泡好的干海参沿开口剪通，逐根去掉沙嘴（即海参牙齿部分，呈白色），避免沙子硌牙。有顾客习惯剪沙嘴时一并将海参筋剪断，以求得更大的发制量。晓芹海参建议不要剪断海参筋，以免在“煮”和“发”过程中，造成营养流失。</p>
                  </div>
              </div>
              <div class="productList fa pageList">
                  <img src="${staticImg}/images/yly/fa/fa-cooking.png" alt="">
                  <div class="cookingList">
                      <div class="dish-name"><i>2.</i>煮</div>
                      <span>普通煮制法</span>
                      <p>将海参放入深底无油的锅中，加入凉水（水没过海参至少10厘米左右）盖锅盖。然后按下列步骤操作：</p>
                      <p>①用大火将水烧开后换为小火煮20分钟后关火，焖30分钟；</p>
                      <p>②用小火继续煮20分钟后关火，焖25分钟；</p>
                      <p>③然后逐根检查海参，煮好的海参用手掐其内壁容易掐透，并呈现“筷子夹中间，两头自然弯”的状态；</p>
                      <p>④将煮好的海参挑出，放置一边，将尚未煮好的海参继续煮焖（煮焖时间视海参情况适当缩短），直至每根海参都掐透为止。</p>
                      <span>电饭锅煮制法</span>
                      <p>将处理好的海参放入洁净无油的电饭煲中，加入凉水（水没过海参至少10厘米左右）。然后按下列步骤操作：</p>
                      <p>①加热至开锅后，调至保温状态，焖两个小时；</p>
                      <p>②再重复加热至开锅，然后依然调至保温状态，焖一个小时左右；</p>
                      <p>③逐根检查海参，煮好的海参用手掐其内壁容易掐透，并呈现“筷子夹中间，两头自然弯”的状态；</p>
                      <p>④将煮好的海参挑出，放置一边，将尚未煮好的海参继续煮焖（煮焖时间视海参情况适当缩短），直至每根海参都掐透为止。</p>
                  </div>
              </div>
              <div class="productList fa pageList">
                  <img src="${staticImg}/images/yly/fa/fa-fa.png" alt="">
                  <div class="cookingList">
                      <div class="dish-name"><i>3.</i>发</div>
                      <p>将煮好的海参放入凉水中，浸泡1小时后换水加入冰块，放入冷藏箱中，每昼夜换一次水和冰，泡发3~4天即泡发好。最后将发制好的海参用保鲜袋单根包装，放入冰箱中冷冻储藏。</p>
                      <br>
                      <p>▲注意：发制过程中必须保证锅中、水中无油，手上尽量不要有化妆品；时间允许尽量选择上午发制，才能更好掌握发制时间。</p>
                  </div>
              </div>
              <div class="more">
                  <a>
                      <span>查看更多</span>
                      <img src="${staticImg}/images/yly/moreDown.png" alt="">
                  </a>
              </div>
              <div class="pageBox">
                  <div class="page">
                      <a class="prevPage prevPage3">上一页</a>
                      <p>
                          共 <span class="total third"></span>页
                          当前 <span class="current">1</span>页
                      </p>
                      <a class="nextPage nextPage3">下一页</a>
                  </div>
              </div>
          </div>
      </div>
      <div class="productBox">
          <div class="ban" id="kitchen4">淡干海参</div>
          <div class="videoBox">
              <video src="" style="width:100%;height:100%"></video>
              <img src="${staticImg}/images/yly/start.png" alt="" class="start">
          </div>
          <div>
              <div class="productList gan pageList">
                  <div class="cookingList">
                      <div class="dish-name"><i>1.</i>泡</div>
                      <p>将干海参放入干净无油的器皿中，用凉水将干海参浸泡36-48小时左右（浸泡过程在冷藏状态下进行），直到每根海参无硬芯，期间一定要换3-4次水。</p>
                  </div>
              </div>
              <div class="productList gan pageList">
                  <div class="cookingList">
                      <div class="dish-name"><i>2.</i>剪</div>
                      <p>将泡好的干海参沿开口剪通，逐根去掉沙嘴（即海参牙齿部分，呈白色），避免沙子硌牙。有顾客习惯剪沙嘴时一并将海参筋剪断，以求得更大的发制量。晓芹海参建议不要剪断海参筋，以免在“煮”和“发”过程中，造成营养流失。</p>
                  </div>
              </div>
              <div class="productList gan pageList">
                  <div class="cookingList">
                      <div class="dish-name"><i>3.</i>煮</div>
                      <span>普通煮制法</span>
                      <p>将海参放入深底无油的锅中，加入凉水（水没过海参至少10厘米左右）盖锅盖。然后按下列步骤操作：</p>
                      <p>①用大火将水烧开后换为小火煮20分钟后关火，焖30分钟；</p>
                      <p>②用小火继续煮20分钟后关火，焖25分钟；</p>
                      <p>③然后逐根检查海参，煮好的海参用手掐其内壁容易掐透，并呈现“筷子夹中间，两头自然弯”的状态；</p>
                      <p>④将煮好的海参挑出，放置一边，将尚未煮好的海参继续煮焖（煮焖时间视海参情况适当缩短），直至每根海参都掐透为止。</p>
                      <span>电饭锅煮制法</span>
                      <p>将处理好的海参放入洁净无油的电饭煲中，加入凉水（水没过海参至少10厘米左右）。然后按下列步骤操作：</p>
                      <p>①加热至开锅后，调至保温状态，焖两个小时；</p>
                      <p>②再重复加热至开锅，然后依然调至保温状态，焖一个小时左右；</p>
                      <p>③逐根检查海参，煮好的海参用手掐其内壁容易掐透，并呈现“筷子夹中间，两头自然弯”的状态；</p>
                      <p>④将煮好的海参挑出，放置一边，将尚未煮好的海参继续煮焖（煮焖时间视海参情况适当缩短），直至每根海参都掐透为止。</p>
                  </div>
              </div>
              <div class="productList gan pageList">
                  <div class="cookingList">
                      <div class="dish-name"><i>4.</i>发</div>
                      <p>将煮好的海参放入凉水中，浸泡1小时后换水加入冰块，放入冷藏箱中，每昼夜换一次水和冰，泡发3~4天即泡发好。最后将发制好的海参用保鲜袋单根包装，放入冰箱中冷冻储藏。</p>
                      <br>
                      <p>▲注意：发制过程中必须保证锅中、水中无油，手上尽量不要有化妆品；时间允许尽量选择上午发制，才能更好掌握发制时间。</p>
                  </div>
              </div>
              <div class="more">
                  <a>
                      <span>查看更多</span>
                      <img src="${staticImg}/images/yly/moreDown.png" alt="">
                  </a>
              </div>
              <div class="pageBox">
                  <div class="page">
                      <a class="prevPage prevPage4">上一页</a>
                      <p>
                          共 <span class="total four"></span>页
                          当前 <span class="current">1</span>页
                      </p>
                      <a class="nextPage nextPage4">下一页</a>
                  </div>
              </div>
          </div>
      </div>
  </div>

  <script src="${staticImg}/js/jquery.min.js"></script>
  <script src="${staticImg}/js/bootstrap/js/bootstrap.js"></script>
  <script>

      $(".productBox").on("click",".media",function(){
           var Id = $(this).find("input").val();
          window.location.href="${request.contextPath}/PreviewPage/oxtail.htm?Id="+Id;
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
              $(this).siblings().eq(i).show();
              $(this).next().show();
          }
      });
      $(function(){
          var isShowF=$(".media").siblings(".pageBox").find(".nextPage");
          totalPage1>1?isShowF.css("visibility","visible"):isShowF.css("visibility","hidden");
          var isShowS=$(".way").siblings(".pageBox").find(".nextPage");
          totalPage2>1?isShowS.css("visibility","visible"):isShowS.css("visibility","hidden");
          var isShowT=$(".fa").siblings(".pageBox").find(".nextPage");
          totalPage3>1?isShowT.css("visibility","visible"):isShowT.css("visibility","hidden");
          var isShowFour=$(".gan").siblings(".pageBox").find(".nextPage");
          totalPage4>1?isShowFour.css("visibility","visible"):isShowFour.css("visibility","hidden");
          $(".prevPage").css("visibility","hidden");
          $(".media").eq(0).show();
          $(".media").eq(1).show();
          $(".way").eq(0).show();
          $(".way").eq(1).show();
          $(".fa").eq(0).show();
          $(".gan").eq(0).show();
      });

      //菜品分页
      var index1=$(".media").length;
      var totalPage1=Math.ceil(index1/4);
      $(".first").text(totalPage1);
      //
      var index2=$(".way").length;
      var totalPage2=Math.ceil(index2/4);
      $(".second").text(totalPage2);
      //
      var index3=$(".fa").length;
      var totalPage3=Math.ceil(index3/4);
      $(".third").text(totalPage3);
      //
      var index4=$(".gan").length;
      var totalPage4=Math.ceil(index4/4);
      $(".four").text(totalPage4);
      var currentPage1=1;
      var currentPage2=1;
      var currentPage3=1;
      var currentPage4=1;
      //点击下一页
      //1111111111
      $(".nextPage1").click(function(){
          currentPage1<totalPage1&&currentPage1++;
          $(".media").hide();
          $(".prevPage1").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage1);
          var indexPage=(currentPage1-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".media").eq(i).show();
          }
          if(currentPage1==totalPage1){
              $(".nextPage1").css("visibility","hidden");
          }else{
              $(".nextPage1").css("visibility","visible");
          }
          location.href="#kitchen1";
      });

      //2222222222
      $(".nextPage2").click(function(){
          currentPage2<totalPage2&&currentPage2++;
          $(".way").hide();
          $(".prevPage2").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage2);
          var indexPage=(currentPage2-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".way").eq(i).show();
          }
          if(currentPage2==totalPage2){
              $(".nextPage2").css("visibility","hidden");
          }else{
              $(".nextPage2").css("visibility","visible");
          }
          location.href="#kitchen2";
      });
      

      //33333333333
      $(".nextPage3").click(function(){
          currentPage3<totalPage3&&currentPage3++;
          $(".fa").hide();
          $(".prevPage3").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage3);
          var indexPage=(currentPage3-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".fa").eq(i).show();
          }
          if(currentPage3==totalPage3){
              $(".nextPage3").css("visibility","hidden");
          }else{
              $(".nextPage3").css("visibility","visible");
          }
          location.href="#kitchen3";
      });
      //44444444444
      $(".nextPage4").click(function(){
          currentPage4<totalPage4&&currentPage4++;
          $(".gan").hide();
          $(".prevPage4").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage4);
          var indexPage=(currentPage4-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".gan").eq(i).show();
          }
          if(currentPage4==totalPage4){
              $(".nextPage4").css("visibility","hidden");
          }else{
              $(".nextPage4").css("visibility","visible");
          }
          location.href="#kitchen4";
      });
      //点击上一页
      //11111111111
      $(".prevPage1").click(function(){
          currentPage1>1&&currentPage1--;
          $(".media").hide();
          $(".nextPage1").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage1);
          var indexPage=(currentPage1-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".media").eq(i).show();
          }
          if(currentPage1==1){
              $(".prevPage1").css("visibility","hidden");
          }else{
              $(".prevPage1").css("visibility","visible");
          }
          location.href="#kitchen1";
      });
      //222222222
      $(".prevPage2").click(function(){
          currentPage2>1&&currentPage2--;
          $(".way").hide();
          $(".nextPage2").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage2);
          var indexPage=(currentPage2-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".way").eq(i).show();
          }
          if(currentPage2==1){
              $(".prevPage2").css("visibility","hidden");
          }else{
              $(".prevPage2").css("visibility","visible");
          }
          location.href="#kitchen2";
      });
      //333333333
      $(".prevPage3").click(function(){
          currentPage3>1&&currentPage3--;
          $(".fa").hide();
          $(".nextPage3").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage3);
          var indexPage=(currentPage3-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".fa").eq(i).show();
          }
          if(currentPage3==1){
              $(".prevPage3").css("visibility","hidden");
          }else{
              $(".prevPage3").css("visibility","visible");
          }
          location.href="#kitchen3";
      });
      //444444444
      $(".prevPage4").click(function(){
          currentPage4>1&&currentPage4--;
          $(".gan").hide();
          $(".nextPage4").css("visibility","visible");
          $(this).siblings("p").children(".current").text(currentPage4);
          var indexPage=(currentPage4-1)*4;
          console.log(indexPage);
          for(var i=indexPage;i<indexPage+4;i++){
              $(".gan").eq(i).show();
          }
          if(currentPage4==1){
              $(".prevPage4").css("visibility","hidden");
          }else{
              $(".prevPage4").css("visibility","visible");
          }
          location.href="#kitchen4";
      });
      //点击视频
      $(".start").click(function(){
          $(this).prev("video").trigger('play');
          $(this).hide();
      });
      $("video").click(function(){
          $(this).trigger('pause');
          $(this).next(".start").show();
      });
  </script>
</body>
</html>