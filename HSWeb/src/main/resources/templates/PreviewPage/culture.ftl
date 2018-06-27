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
          <img src="${staticImg}/images/yly/center.png" alt="" class="productBG">
      </div>
  </header>
  <div class="main-product">
      <div class="mainTop">
          <span class="SL"></span>
          <b>企业介绍</b>
      </div>
      <div class="details">
          <img src="${staticImg}/images/yly/company-intro.png" alt="">
          <span>企业介绍</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
          <div class="productList showList">
              <#if pBaCompany.enterpriseImageVideo!="">
              <h4 class="ban">企业形象片</h4>
              <div class="videoBox">
                 <a href="http://v.youku.com/v_show/id_XOTE1OTk2OTA0.html">
                    <img src="${staticImg}/images/yly/propagate.jpg" style="width:100%">
                     <img src="${staticImg}/images/yly/start.png" alt="" class="start">
                 </a>
              </div>
              </#if>
              <p class="culture">
                <#if pBaCompany.enterpriseIntroduction??>${pBaCompany.enterpriseIntroduction}</#if>
              </p>
              <div class="more">
                  <a>
                      <span>查看更多</span>
                      <img src="${staticImg}/images/yly/moreDown.png" alt="">
                  </a>
              </div>
          </div>
      </div>
      <div class="details">
          <img src="${staticImg}/images/yly/book.png" alt="">
          <span>企业文化</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
      <#if companyCultureList??&&companyCultureList?size!=0>
      <#list companyCultureList as companyCultureList>
          <div class="productList showList">
              <#if companyCultureList.imageJsonList??&&companyCultureList.imageJsonList?size!=0>
                <#list companyCultureList.imageJsonList as imageJson>
                  <img src="${imageJson.imageUrl}" alt="${imageJson.imageName}">
                </#list>
              </#if>
              <div class="ban">
              <#if companyCultureList.companyCultureName??>
              ${companyCultureList.companyCultureName}
              </#if>
              </div>
              <#if companyCultureList.remark??>
              <p class="culture">
              ${companyCultureList.remark}
              </p>
              <div class="more">
                  <a>
                      <span>查看更多</span>
                      <img src="${staticImg}/images/yly/moreDown.png" alt="">
                  </a>
              </div>
              </#if>
          </div>
      </#list>
          </#if>
      </div>
  </div>

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

      //点击视频
      $(".start").click(function(){
          $(this).prev("video").trigger('play');
          $(this).hide();
      });
      $("video").click(function(){
          $(this).trigger('pause');
          $(this).next(".start").show();
      });
//点击更多加载文本
      $(".more").click(function(){
          $(this).prev("p").removeClass("culture");
          $(this).prev("p").addClass("cultureShow");
          $(this).hide();
      })
  </script>
</body>
</html>