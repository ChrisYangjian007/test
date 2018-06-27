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
          <span>${xqKitchenById.kitchenName}</span>
      </div>
      <#if xqKitchenById.images??>
      <div class="sea" style="padding:2em 2em 0">
              <div id="seaList" class="carousel slide" data-ride="carousel">
                  <div class="carousel-inner" role="listbox">
                      <#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson?size!=0>
                        <#list xqKitchenById.imagesJson as images>
                            <#if images_index==0>
                                <div class="item active">
                                    <img src="${images.imageUrl}" alt="${images.imageName}"/>
                                </div>
                            <#else >
                                <div class="item">
                                    <img src="${images.imageUrl}" alt="${images.imageName}"/>
                                </div>
                            </#if>
                        </#list>
                      </#if>
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[0]??&&xqKitchenById.imagesJson[0].imageUrl!="">-->
                      <#--<div class="item active">-->
                          <#--<img src="${xqKitchenById.imagesJson[0].imageUrl}" alt=""/>-->

                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[1]??&&xqKitchenById.imagesJson[1].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[1].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[2]??&&xqKitchenById.imagesJson[2].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[2].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[3]??&&xqKitchenById.imagesJson[3].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[3].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[4]??&&xqKitchenById.imagesJson[4].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[4].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[5]??&&xqKitchenById.imagesJson[5].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[5].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[6]??&&xqKitchenById.imagesJson[6].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[6].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[7]??&&xqKitchenById.imagesJson[7].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[7].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[8]??&&xqKitchenById.imagesJson[8].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[8].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                        <#--<#if xqKitchenById.imagesJson??&&xqKitchenById.imagesJson[9]??&&xqKitchenById.imagesJson[9].imageUrl!="">-->
                      <#--<div class="item">-->
                          <#--<img src="${xqKitchenById.imagesJson[9].imageUrl}" alt=""/>-->
                      <#--</div>-->
                        <#--</#if>-->
                  </div>
                  <a class="prev" href="#seaList" role="button" data-slide="prev">
                      <img src="${staticImg}/images/yly/left.png" alt="">
                  </a>
                  <a class="next" href="#seaList" role="button" data-slide="next">
                      <img src="${staticImg}/images/yly/right.png" alt="">
                  </a>
              </div>
          </div>
      </#if>
  </header>
  <div class="main-product">
      <div class="mainTop">
          <span class="SL"></span>
          <b>${xqKitchenById.kitchenName}</b>
      </div>
      <div class="productBox">
          <div class="productList showList">
              <div class="cookingList">
              <#if xqKitchenById.features??>
                  <div class="cooking-title"><span class="dishSL"></span>特点：</div>
                  <p>${xqKitchenById.features}</p>
              </#if>
              <#if xqKitchenById.ingredients??>
                  <div class="cooking-title"><span class="dishSL"></span>配料：</div>
                  <p>${xqKitchenById.ingredients}</p>
              </#if>
              <#if xqKitchenById.productionMethod??>
                  <div class="cooking-title"><span class="dishSL"></span>制作方法：</div>
                  <p>${xqKitchenById.productionMethod}</p>
              </#if>
              </div>
          </div>
      </div>
  </div>

  <script src="${staticImg}/js/jquery.min.js"></script>
  <script src="${staticImg}/js/bootstrap/js/bootstrap.js"></script>
  <script>

  </script>
</body>
</html>