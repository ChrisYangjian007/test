﻿<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>欢迎使用</title>
    <!--框架必需start-->
    <link href="/styles/learunui-framework.css" rel="stylesheet" />
    <link href="/styles/learunui-accordion.css" rel="stylesheet" />
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
            Browser();
            Speedometer();
        })
        function LodaChartData() {
            $('#container').highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: 'top5 销售产品统计表'
                },
                xAxis: {
                    categories: ['1月份', '2月份', '3月份', '4月份', '5月份', '6月份',
                        '7月份', '8月份', '9月份', '10月份', '11月份', '12月份']
                },
                yAxis: {
                    title: {
                        text: '销售产品'
                    },
                    labels: {
                        formatter: function () {
                            return this.value + '°'
                        }
                    }
                },
                tooltip: {
                    crosshairs: true,
                    shared: true
                },
                plotOptions: {
                    spline: {
                        marker: {
                            radius: 4,
                            lineColor: '#666666',
                            lineWidth: 1
                        }
                    }
                },
                series: [{
                    name: 'Tokyo',
                    marker: {
                        symbol: 'square'
                    },
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, {
                        y: 26.5,
                        marker: {
                            symbol: 'url(/images/Icon16/ruby.png)'
                        }
                    }, 23.3, 18.3, 13.9, 9.6]

                }, {
                    name: 'London',
                    marker: {
                        symbol: 'diamond'
                    },
                    data: [{
                        y: 3.9,
                        marker: {
                            symbol: 'url()'
                        }
                    }, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                }]
            });
        };
        function Browser() {
            var colors = Highcharts.getOptions().colors,
            categories = ['MSIE', 'Firefox', 'Chrome', 'Safari', 'Opera'],
            name = 'Browser brands',
            data = [{
                y: 55.11,
                color: colors[0],
                drilldown: {
                    name: 'MSIE versions',
                    categories: ['MSIE 6.0', 'MSIE 7.0', 'MSIE 8.0', 'MSIE 9.0'],
                    data: [10.85, 7.35, 33.06, 2.81],
                    color: colors[0]
                }
            }, {
                y: 21.63,
                color: colors[1],
                drilldown: {
                    name: 'Firefox versions',
                    categories: ['Firefox 2.0', 'Firefox 3.0', 'Firefox 3.5', 'Firefox 3.6', 'Firefox 4.0'],
                    data: [0.20, 0.83, 1.58, 13.12, 5.43],
                    color: colors[1]
                }
            }, {
                y: 11.94,
                color: colors[2],
                drilldown: {
                    name: 'Chrome versions',
                    categories: ['Chrome 5.0', 'Chrome 6.0', 'Chrome 7.0', 'Chrome 8.0', 'Chrome 9.0',
                        'Chrome 10.0', 'Chrome 11.0', 'Chrome 12.0'],
                    data: [0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22],
                    color: colors[2]
                }
            }, {
                y: 7.15,
                color: colors[3],
                drilldown: {
                    name: 'Safari versions',
                    categories: ['Safari 5.0', 'Safari 4.0', 'Safari Win 5.0', 'Safari 4.1', 'Safari/Maxthon',
                        'Safari 3.1', 'Safari 4.1'],
                    data: [4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14],
                    color: colors[3]
                }
            }, {
                y: 2.14,
                color: colors[4],
                drilldown: {
                    name: 'Opera versions',
                    categories: ['Opera 9.x', 'Opera 10.x', 'Opera 11.x'],
                    data: [0.12, 0.37, 1.65],
                    color: colors[4]
                }
            }];

            function setChart(name, categories, data, color) {
                chart.xAxis[0].setCategories(categories, false);
                chart.series[0].remove(false);
                chart.addSeries({
                    name: name,
                    data: data,
                    color: color || 'white'
                }, false);
                chart.redraw();
            }

            var chart = $('#containerA').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '浏览器的市场份额,2011年4月'
                },
                xAxis: {
                    categories: categories
                },
                yAxis: {
                    title: {
                        text: '总市场份额百分比'
                    }
                },
                plotOptions: {
                    column: {
                        cursor: 'pointer',
                        point: {
                            events: {
                                click: function () {
                                    var drilldown = this.drilldown;
                                    if (drilldown) { // drill down
                                        setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                    } else { // restore
                                        setChart(name, categories, data);
                                    }
                                }
                            }
                        },
                        dataLabels: {
                            enabled: true,
                            color: colors[0],
                            style: {
                                fontWeight: 'bold'
                            },
                            formatter: function () {
                                return this.y + '%';
                            }
                        }
                    }
                },
                tooltip: {
                    formatter: function () {
                        var point = this.point,
                            s = this.x + ':<b>' + this.y + '% market share</b><br />';
                        if (point.drilldown) {
                            s += 'Click to view ' + point.category + ' versions';
                        } else {
                            s += 'Click to return to browser brands';
                        }
                        return s;
                    }
                },
                series: [{
                    name: name,
                    data: data,
                    color: 'white'
                }],
                exporting: {
                    enabled: false
                }
            })
            .highcharts(); // return chart
        }
        function Speedometer() {
            $('#containerB').highcharts({
                chart: {
                    type: 'gauge',
                    plotBackgroundColor: null,
                    plotBackgroundImage: null,
                    plotBorderWidth: 0,
                    plotShadow: false
                },

                title: {
                    text: '仪表盘'
                },

                pane: {
                    startAngle: -150,
                    endAngle: 150,
                    background: [{
                        backgroundColor: {
                            linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                            stops: [
                                [0, '#FFF'],
                                [1, '#333']
                            ]
                        },
                        borderWidth: 0,
                        outerRadius: '109%'
                    }, {
                        backgroundColor: {
                            linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                            stops: [
                                [0, '#333'],
                                [1, '#FFF']
                            ]
                        },
                        borderWidth: 1,
                        outerRadius: '107%'
                    }, {
                        // default background
                    }, {
                        backgroundColor: '#DDD',
                        borderWidth: 0,
                        outerRadius: '105%',
                        innerRadius: '103%'
                    }]
                },

                // the value axis
                yAxis: {
                    min: 0,
                    max: 200,

                    minorTickInterval: 'auto',
                    minorTickWidth: 1,
                    minorTickLength: 10,
                    minorTickPosition: 'inside',
                    minorTickColor: '#666',

                    tickPixelInterval: 30,
                    tickWidth: 2,
                    tickPosition: 'inside',
                    tickLength: 10,
                    tickColor: '#666',
                    labels: {
                        step: 2,
                        rotation: 'auto'
                    },
                    title: {
                        text: 'km/h'
                    },
                    plotBands: [{
                        from: 0,
                        to: 120,
                        color: '#55BF3B' // green
                    }, {
                        from: 120,
                        to: 160,
                        color: '#DDDF0D' // yellow
                    }, {
                        from: 160,
                        to: 200,
                        color: '#DF5353' // red
                    }]
                },

                series: [{
                    name: 'Speed',
                    data: [80],
                    tooltip: {
                        valueSuffix: ' km/h'
                    }
                }]

            },
        // Add some life
        function (chart) {
            if (!chart.renderer.forExport) {
                setInterval(function () {
                    var point = chart.series[0].points[0],
                        newVal,
                        inc = Math.round((Math.random() - 0.5) * 20);

                    newVal = point.y + inc;
                    if (newVal < 0 || newVal > 200) {
                        newVal = point.y - inc;
                    }

                    point.update(newVal);

                }, 3000);
            }
        });
        }
    </script>
</head>
<body>
    <div style="margin: 10px;">
        <table style="width: 100%">
            <tr>
                <td>
                    <div class="Task" style="background-color: #E7191B">
                        <i></i>
                        <div class="centre">
                            <div class="icon">
                                <img src="/images/task-1.png" />
                            </div>
                            <div class="Number">10</div>
                        </div>
                        <div class="text">
                            审核待处理任务
                        </div>
                    </div>
                </td>
                <td>
                    <div class="Task" style="background-color: #EEAE44">
                        <i></i>
                        <div class="centre">
                            <div class="icon">
                                <img src="/images/task-2.png" />
                            </div>
                            <div class="Number">11</div>
                        </div>
                        <div class="text">
                            消息待处理任务
                        </div>
                    </div>
                </td>
                <td>
                    <div class="Task" style="background-color: #28B779">
                        <i></i>
                        <div class="centre">
                            <div class="icon">
                                <img src="/images/task-3.png" />
                            </div>
                            <div class="Number">20</div>
                        </div>
                        <div class="text">
                            对私待处理任务
                        </div>
                    </div>
                </td>
                <td>
                    <div class="Task" style="background-color: #2BA9E3">
                        <i></i>
                        <div class="centre">
                            <div class="icon">
                                <img src="/images/task-4.png" />
                            </div>
                            <div class="Number">78</div>
                        </div>
                        <div class="text">
                            订单变更待处理任务
                        </div>
                    </div>
                </td>
                <td>
                    <div class="Task" style="background-color: #C07FE7">
                        <div class="centre">
                            <div class="icon">
                                <img src="/images/task-5.png" />
                            </div>
                            <div class="Number">18</div>
                        </div>
                        <div class="text">
                            本周销售订单
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <div style="margin-left: 20px; margin-right: 30px;">
            <table style="width: 100%">
                <tr>
                    <td style="width: 60%">
                        <div id="containerA"></div>
                    </td>
                    <td style="width: 40%">
                        <div id="containerB"></div>
                    </td>
                </tr>
            </table>
        </div>
        <div style="margin-left: 20px; margin-right: 30px;">
            <div id="container"></div>
        </div>
    </div>
</body>
</html>