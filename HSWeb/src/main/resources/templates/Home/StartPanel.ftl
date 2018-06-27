﻿<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>欢迎使用</title>
    <!--框架必需start-->
    <link href="/styles/learunui-framework.css" rel="stylesheet" />
    <script type="text/javascript" src="/scripts/jquery/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="/scripts/learunui-framework.js"></script>
    <!--框架必需end-->
    <!--第三方统计图start-->
    <script type="text/javascript" src="/scripts/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="/scripts/highcharts/highcharts-more.js"></script>
    <script type="text/javascript" src="/scripts/highcharts/modules/exporting.js"></script>
    <!--第三方统计图end-->
    <script type="text/javascript">
        $(function () {
            LodaChartData();
            Loadpie();
        });
        function LodaChartData() {
            $('#container').highcharts({
                chart: {
                    type: 'line'//图表类型line, spline, area, areaspline, column, bar, pie , scatter
                },
                title: {
                    text: '月平均气温',
                    x: -20 //center
                },
                xAxis: {
                    categories: ['一月份', '二月份', '三月份', '四月份', '五月份', '六月份', '七月份', '八月份', '九月份', '十月份', '十一月份', '十二月份']

                },
                yAxis: {
                    title: {
                        text: '温度(°C)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: '°C'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '杭州',
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                }, {
                    name: '上海',
                    data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
                }, {
                    name: '北京',
                    data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
                }, {
                    name: '福建',
                    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                }]
            });
        }
        //JQuery饼图演示
        function Loadpie() {
            $('#piecontainer').highcharts({
                chart: {
                    type: 'bubble',
                    zoomType: 'xy'
                },

                title: {
                    text: '泡沫'
                },

                series: [{
                    data: [[97, 36, 79], [94, 74, 60], [68, 76, 58], [64, 87, 56], [68, 27, 73], [74, 99, 42], [7, 93, 87], [51, 69, 40], [38, 23, 33], [57, 86, 31]]
                }, {
                    data: [[25, 10, 87], [2, 75, 59], [11, 54, 8], [86, 55, 93], [5, 3, 58], [90, 63, 44], [91, 33, 17], [97, 3, 56], [15, 67, 48], [54, 25, 81]]
                }, {
                    data: [[47, 47, 21], [20, 12, 4], [6, 76, 91], [38, 30, 60], [57, 98, 64], [61, 17, 80], [83, 60, 13], [67, 78, 75], [64, 12, 10], [30, 77, 82]]
                }]

            });
        }
    </script>
</head>
<body>
    <div>
        <table style="width: 100%;">
            <tr>
                <td colspan="2" style="width: 80%">
                    <div class="box">
                        <div class="box-title">
                            <div style="float: left">
                                <img src="/images/Icon32/chart_bar.png" alt="" width="20" height="20" />
                                数据统计
                            </div>
                            <div id="SeriesType" title="点击切换图表" style="float: right; padding-right: 10px; cursor: pointer;">更多图表</div>
                        </div>
                        <div class="box-content" style="height: 300px;">
                            <div id="container" style="height: 300px;">
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="box">
                        <div class="box-title">
                            <div style="float: left">
                                <img src="/images/Icon32/sound.png" alt="" width="20" height="20" />
                                通知公告
                            </div>
                            <div style="float: right; padding-right: 10px;">更多</div>
                        </div>
                        <div class="box-content" style="height: 300px;">
                            <a  target="_blank">1、热烈庆祝通用权限管理系统框架上线 （2013-11-06） </a>
                            <br />
                            <a  target="_blank">2、力软信息签约武钢旗下武新建材（2014-03-25） </a>
                            <br />
                            <a  target="_blank">3、力软信息签约春风物流（2014-05-28） </a>
                            <br />
                            <a  target="_blank">4、韵达渝北分公司转单项目顺利上线（2014-07-01） </a>
                            <br />
                            <a  target="_blank">5、力软信息签约重庆誉威（2014-07-15） </a>
                            <br />
                            <a  target="_blank">6、力软誉威物联网项目二期正式启动（2014-08-28） </a>
                            <br />
                            <a  target="_blank">7、微软宣布.NET开发环境开源 支持三大操作系统（2014-11-13） </a>
                            <br />
                            <a  target="_blank">8、力软信息化快速开发框架正式上线（2014-11-16） </a>
                            <br />
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="box">
                        <div class="box-title">
                            <div style="float: left">
                                <img src="/images/Icon32/hourglass_go.png" alt="" width="20" height="20" />
                                待办任务
                            </div>
                            <div style="float: right; padding-right: 10px;">更多</div>
                        </div>
                        <div class="box-content" style="height: 250px; width: 550px">
                            <div id="piecontainer" style="height: 250px;"></div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="box">
                        <div class="box-title">
                            <div style="float: left">
                                <img src="/images/Icon32/tag_blue.png" alt="" width="20" height="20" />
                                知识库最新文章
                            </div>
                            <div style="float: right; padding-right: 10px;">更多</div>
                        </div>
                        <div class="box-content" style="height: 250px; width: 550px">
                            <a  target="_blank">1、Javascript中的domReady引入机制</a><br />
                            <a  target="_blank">2、过早的优化是万恶之源</a><br />
                            <a  target="_blank">3、IIS 内部运行机制</a><br />
                            <a  target="_blank">4、Windows异步I/O和完成端口</a><br />
                            <a  target="_blank">5、NET中常见的内存泄露问题——GC、委托</a><br />
                            <a  target="_blank">6、项目代码风格要求</a><br />
                            <a  target="_blank">7、IIS是如何处理ASP.NET请求的</a><br />
                            <a  target="_blank">8、使用Phalanger整合PHP和.Net</a><br />
                        </div>
                    </div>
                </td>
                <td>
                    <div class="box">
                        <div class="box-title">
                            <div style="float: left">
                                <img src="/images/Icon32/lightbulb_off.png" alt="" width="20" height="20" />
                                讨论话题
                            </div>
                            <div style="float: right; padding-right: 10px;">更多</div>
                        </div>
                        <div class="box-content" style="height: 250px;">
                            <a  target="_blank">1、人才后备计划意见征集</a><br />
                            <a  target="_blank">2、力软移动物联网产品意见征集</a><br />
                            <a  target="_blank">3、广州分公司选址意见征集</a><br />
                            <a  target="_blank">4、如何应对信息化产业巨头垄断</a><br />
                            <a  target="_blank">5、现代化人力资源管理的重要意义</a><br />
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>