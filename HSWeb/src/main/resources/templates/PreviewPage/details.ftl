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
  <header class="header-details">
      <div class="headerTop">
          <a class="pull-left" onclick="history.go(-1)">
              <img src="${staticImg}/images/yly/back.png" alt="">
          </a>
          <span>晓芹食品追溯信息查询</span>
      </div>
      <div class="inquiryBox">
          <img src="${staticImg}/images/yly/headerLogo.png" alt="" class="headerLogo">
          <div class="inquiry">
              <img src="${staticImg}/images/yly/scan.png" alt="">
              <span>顾客已查询<b>
                <#if count??>${count}<#else >0</#if>
              </b>次</span>
          </div>
      </div>
  </header>
  <div class="main">
      <div class="mainTop">
          <span class="SL"></span>
          <b>产品信息</b>
      </div>
      <div class="details">
          <img src="${staticImg}/images/yly/details.png" alt="">
          <span>产品详情</span>
          <div class="pull-right upDown">
              <span>收起</span>
              <img src="${staticImg}/images/yly/down.png" alt="">
          </div>
      </div>
      <div class="productBox">
          <ul class="detailsList">
              <li>
                  <p>产品名称</p>
                  <span><#if pZsBatch??>${pZsBatch.batchName}</#if></span>
              </li>
              <li>
                  <p>产品规格</p>
                  <span><#if pZsBatch??>${pZsBatch.productFormat}</#if></span>
              </li>
              <li>
                  <p>生产日期及批号</p>
                  <span><#if pZsBatch??>${pZsBatch.batchCode}</#if></span>
              </li>
              <#if pZsCertificateManage??>
                  <#if pZsCertificateManage.productStandards??>
                      <li>
                          <p>产品标准号</p>
                          <span>${pZsCertificateManage.productStandards}</span>
                      </li>
                  </#if>
                  <li>
                      <p>生产许可证</p>
                      <span>${pZsCertificateManage.productionLicense}</span>
                  </li>
              </#if>
              <#if pSaOrderDetail??&&pSaOrderDetail.saOrder??&&pSaOrderDetail.saOrder.orderSsmd??>
                  <li>
                      <p>所属门店</p>
                      <span>${pSaOrderDetail.saOrder.orderSsmd}</span>
                  </li>
              </#if>
              <li>
                  <p>检验结果</p>
                  <span>检验合格</span>
              </li>
              <li>
                  <p>海域位置</p>
                  <#if pBaCompany.seaAreaPosition??>
                 <span>${pBaCompany.seaAreaPosition}</span>
                  </#if>
              </li>
          </ul>
          <div id="mymap">

              </div>
          <#if pZsCertificateManage??>
              <#if pZsCertificateManage.imageJsonList??&&pZsCertificateManage.imageJsonList?size!=0>
              <div class="report">
                  <p>
                      <span class="line"></span><i>检测报告</i><span class="line"></span>
                  </p>
                  <!--轮播-->
                  <div id="reportList" class="carousel slide" data-ride="carousel">
                      <div class="carousel-inner" role="listbox">
                          <#list pZsCertificateManage.imageJsonList as imageJson>
                                    <#if imageJson_index==0>
                                        <div class="item active">
                                            <img src="${imageJson.imageUrl}" alt="${imageJson.imageName}">
                                        </div>
                                    <#else >
                                        <div class="item">
                                            <img src="${imageJson.imageUrl}" alt="${imageJson.imageName}">
                                        </div>
                                    </#if>
                                </#list>
                      </div>
                      <a class="prev" href="#reportList" role="button" data-slide="prev">
                          <img src="${staticImg}/images/yly/left.png" alt="">
                      </a>
                      <a class="next" href="#reportList" role="button" data-slide="next">
                          <img src="${staticImg}/images/yly/right.png" alt="">
                      </a>
                  </div>
              </div>
              </#if>
          </#if>
          <div class="sea">
              <p>
                  <span class="line"></span><i>海域图片</i><span class="line"></span>
              </p>
              <#if pBaCompany.seaAreaImages??>
              <div id="seaList" class="carousel slide" data-ride="carousel">
                  <div class="carousel-inner" role="listbox">
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[0]??&&pBaCompany.seaAreaImagesJson[0].imageUrl!="">
                      <div class="item active">
                          <img src="${pBaCompany.seaAreaImagesJson[0].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[0].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[1]??&&pBaCompany.seaAreaImagesJson[1].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[1].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[1].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[2]??&&pBaCompany.seaAreaImagesJson[2].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[2].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[2].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[3]??&&pBaCompany.seaAreaImagesJson[3].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[3].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[3].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[4]??&&pBaCompany.seaAreaImagesJson[4].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[4].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[4].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[5]??&&pBaCompany.seaAreaImagesJson[5].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[5].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[5].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[6]??&&pBaCompany.seaAreaImagesJson[6].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[6].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[6].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[7]??&&pBaCompany.seaAreaImagesJson[7].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[7].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[7].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[8]??&&pBaCompany.seaAreaImagesJson[8].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[8].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[8].imageName}"/>
                      </div>
                  </#if>
                  <#if pBaCompany.seaAreaImagesJson??&&pBaCompany.seaAreaImagesJson[9]??&&pBaCompany.seaAreaImagesJson[9].imageUrl!="">
                      <div class="item">
                          <img src="${pBaCompany.seaAreaImagesJson[9].imageUrl}" alt="${pBaCompany.seaAreaImagesJson[9].imageName}"/>
                      </div>
                  </#if>
                  </div>
                  <a class="prev" href="#seaList" role="button" data-slide="prev">
                      <img src="${staticImg}/images/yly/left.png" alt="">
                  </a>
                  <a class="next" href="#seaList" role="button" data-slide="next">
                      <img src="${staticImg}/images/yly/right.png" alt="">
                  </a>
              </div>
              </#if>
          </div>
      </div>
  </div>

  <script src="${staticImg}/js/jquery.min.js"></script>
  <script src="${staticImg}/js/bootstrap/js/bootstrap.js"></script>
  <script src="https://api.map.baidu.com/api?v=2.0&ak=1SfQ6t4NYl3aGoVUb611ldkZy39eupfK"></script>
  <script>
      var map = new BMap.Map("mymap",{mapType:BMAP_HYBRID_MAP,minZoom:4});
      map.centerAndZoom(new BMap.Point(121.486071,39.741838), 13);//初始化地图
      map.enableScrollWheelZoom(true);
      var marker = new BMap.Marker(new BMap.Point(121.486071,39.741838));
      map.addOverlay(marker);

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
  </script>
</body>
</html>