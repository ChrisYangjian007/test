<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>晓芹食品追溯信息查询</title>
    <link rel="stylesheet" href="${staticImg}/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${staticImg}/css/animate.css">
    <link rel="stylesheet" href="${staticImg}/css/comment.css">
    <link rel="stylesheet" href="${staticImg}/css/details.css">
    <style>
        .media-left>img{
            width:100%;
        }
    </style>
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
          <img src="${staticImg}/images/yly/center.png" alt="" class="productBG">
      </div>
  </header>


  <div class="main-product">
      <div class="mainTop">
          <span class="SL"></span>
          <b>企业新闻</b>
      </div>
      <div class="details">
          <img src="${staticImg}/images/yly/company-intro.png" alt="" id="kitchen1">
          <span>企业新闻</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
      <#list corporateNewsBy as list>
      <a onclick="<#if list.link??&&list.link!="">showUrl('${list.link}')</#if>">
          <div class="media pageList">
              <#if list.image??&&list.image!="">
                  <div class="media-left media-left-list">
                      <img src="${list.image}" alt=""/>
                  </div>
              </#if>
              <div class="media-body">
                  <h4 class="media-heading"><#if list.title??>${list.title}</#if></h4>
                  <p><#if list.corporateNewsDate??>${list.corporateNewsDate?string("yyyy-MM-dd")}</#if></p>
              </div>
          </div>
      </a>
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
  </div>
  <footer>
     <ul>
         <li>
             <img src="${staticImg}/images/yly/footer-1.png" alt="">
             <span>
                 晓芹企业官方网站：www.xiaoqin.com.cn
             </span>
         </li>
         <li>
             <img src="${staticImg}/images/yly/footer-2.png" alt="">
             <span>
                 关注微信：【晓芹】  微信公众账号：dlxiaoqin
             </span>
         </li>
         <li>
             <img src="${staticImg}/images/yly/footer-3.png" alt="">
             <span>
                 全国服务热线：400-667-5977
             </span>
         </li>
         <li>
             <img src="${staticImg}/images/yly/footer-4.png" alt="">
             <span>
                 公司地址：大连市经济技术开发区福泉北路9号
             </span>
         </li>
     </ul>
  </footer>

  <script src="${staticImg}/js/jquery.min.js"></script>
  <script src="${staticImg}/js/bootstrap/js/bootstrap.js"></script>
  <script>

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
              $(this).siblings().eq(i).children("div").show();
              $(this).next().show();
          }
      });
      $(function(){
          var isShowF=$(".media").siblings(".pageBox").find(".nextPage");
          totalPage1>1?isShowF.css("visibility","visible"):isShowF.css("visibility","hidden");
          $(".prevPage").css("visibility","hidden");
          $(".media").eq(0).show();
          $(".media").eq(1).show();
      });

      //菜品分页
      var index1=$(".media").length;
      var totalPage1=Math.ceil(index1/4);
      $(".first").text(totalPage1);
      var currentPage1=1;
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
      
      function showUrl(url) {
          url=url.substr(0,7).toLowerCase()=="http://"?url:"http://"+url;
          console.log(url);
          window.location.href = url;
      }

  </script>
</body>
</html>