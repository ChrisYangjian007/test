<style>
    body {
        overflow: auto;
    }

    .main-box-fixed {
        height: 245px;
    }

    #addScheduleModal, #sweepAreaDetail, #scheduleHistoryModal, #updateScheduleModal {
        position: absolute;
        left: 50%;

    }
</style>

<link rel="stylesheet" href="${staticCss}/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${staticCss}/css/comment.css">
<link rel="stylesheet" href="${request.contextPath}/css/main.css">


<div class="main-container">
    <div class="col-md-6">
        <div class="main-box main-box-fixed">
            <div class="main-title">
                <h3 class="pull-left">最新生产任务</h3>
                <a onclick="AddTabMenu('128', '${request.contextPath}/stock/stockManage.htm?isShowModal='+1, '库存管理', 'server_uncompress.png','true','http://lvdu.lduwa.com/static')">
                    <img src="${staticImg}/images/main/add.png" alt="" class="pull-right">
                </a>
            </div>
        <#if produceTaskList??&&produceTaskList?size!=0>
            <ul class="task">
                <#list produceTaskList as produceTask>
                    <li>
                        <p><b class="vertical-line"></b>${produceTask.produceTaskName}</p>
                        <span>
                            <#if produceTask.currentOperatingProcedures??>${produceTask.currentOperatingProcedures}
                            <#else >
                                无当前工序
                            </#if>
                                </span>
                        <div class="progress">
                            <div class="<#if produceTask.percent==1>progress-bar progress-bar-info<#else >progress-bar progress-bar-danger</#if>"
                                 role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
                                 style="width: ${produceTask.percent?string("0.00#%")}">

                            </div>
                            <span class="percent">${produceTask.percent?string("0.00#%")}</span>
                        </div>
                    </li>
                </#list>
            </ul>
        <#else >
            <p><b class="vertical-line"></b>暂无生产任务</p>
        </#if>
        </div>
    </div>
    <div class="col-md-6">
        <div class="main-box main-box-fixed">
            <div class="main-title">
                <h3 class="pull-left">今日订单任务</h3>
                <p class="pull-right"><a
                        onclick="AddTabMenu('203', '${request.contextPath}/saOrder/orderManage.htm', '订单管理', 'cross_reference.png','true','http://lvdu.lduwa.com/static')"
                        class="details">查看更多</a>&nbsp;&nbsp;&nbsp;共<em>${count!"0"}</em>个订单，已完成<em>${finishCount!"0"}</em>个
                </p>
            </div>
            <ul class="task">
            <#if saOrderList??>
                <#list saOrderList as sa>
                    <li>
                        <p><b class="vertical-line"></b>${sa.orderName}</p>
                        <span>${sa.orderSsmd}</span>
                        <div>
                            <#if sa.orderIspack==0>
                                <span class="not">未完成</span>
                            <#else>
                                <span class="yes">已完成</span>
                            </#if>
                        </div>
                    </li>
                </#list>
            <#else>
                <li>
                    <p><b class="vertical-line"></b>今日暂无订单</p>
                </li>
            </#if>
            </ul>
        </div>
    </div>
    <div class="col-md-8">
        <div class="main-box">
            <div class="main-title">
                <h3 class="pull-left">成品包装标签统计</h3>
                <p class="pull-right">
                    扫码异常共<em>${sweepExceptionTotal?c!"0"}</em>条，今日新增<em>${todaySweepException?c!"0"}</em>条
                    <a class="details"
                       onclick="AddTabMenu('131', '${request.contextPath}/tagGenerate/generateManage.htm', '成品包装标签', 'tag_hash.png','true','http://lvdu.lduwa.com/static')">查看详情</a>
                </p>
            </div>
            <div class="main-body">
                <div class="main-body-box">
                    <div class="img-box">
                        <img src="${staticImg}/images/main/total-sao.png" alt="">
                        <div class="text-box">
                            <h4>${sweepTotal?c!"0"}</h4>
                            <p>总扫码量</p>
                        </div>
                    </div>
                    <div class="img-box">
                        <img src="${staticImg}/images/main/month-sao.png" alt="">
                        <div class="text-box">
                            <h4>${monthSweep?c!"0"}</h4>
                            <p>当月扫码量</p>
                        </div>
                    </div>
                </div>
                <div id="main1">

                </div>
                <div class="date-box">
                    <span class="date-chosen" id="weekSweep">周</span>
                    <span id="monthSweep">月</span>
                    <span id="yearSweep">年</span>
                </div>
                <div id="main2">

                </div>
            </div>
            <div class="main-body">
                <div class="main-body-box" style="border-bottom:1px solid #e7ecf1">
                    <div class="img-box">
                        <img src="${staticImg}/images/main/total-make.png" alt="">
                        <div class="text-box">
                        <#if tagCountTotal??>
                            <h4>${tagCountTotal?c}</h4>
                        <#else>
                            <h4>0</h4>
                        </#if>
                            <p>总生成量</p>
                        </div>
                    </div>
                    <div class="img-box">
                        <img src="${staticImg}/images/main/month-make.png" alt="">
                        <div class="text-box">
                        <#if monthTagCount??>
                            <h4>${monthTagCount?c}</h4>
                        <#else>
                            <h4>0</h4>
                        </#if>
                            <p>当月生成量</p>
                        </div>
                    </div>
                    <div class="img-box">
                        <img src="${staticImg}/images/main/total-bang.png" alt="">
                        <div class="text-box">
                        <#if alreadyCount??>
                            <h4>${alreadyCount?c}</h4>
                        <#else>
                            <h4>0</h4>
                        </#if>
                            <p>总绑定量</p>
                        </div>
                    </div>
                    <div class="img-box">
                        <img src="${staticImg}/images/main/month-bang.png" alt="">
                        <div class="text-box">
                        <#if monthLabelNumber??>
                            <h4>${monthLabelNumber?c}</h4>
                        <#else>
                            <h4>0</h4>
                        </#if>
                            <p>当月绑定量</p>
                        </div>
                    </div>
                </div>
                <div class="date-box">
                    <span class="date-chosen" id="weekTag">周</span>
                    <span id="monthTag">月</span>
                    <span id="yearTag">年</span>
                </div>
                <div id="main3">

                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="main-box">
            <div class="main-title">
                <h3 class="pull-left">收货来料统计</h3>
                <p class="pull-right">
                    <a class="details"
                       onclick="AddTabMenu('61', '${request.contextPath}/receiveManagement/receipt.htm', '收货单', 'application_view_tile.png','true','http://lvdu.lduwa.com/static')">查看更多</a>
                </p>
            </div>
            <div class="main-body-right">
                <div class="date-box">
                    <span class="date-chosen" id="weekReceive">周</span>
                    <span id="monthReceive">月</span>
                    <span id="yearReceive">年</span>
                </div>
                <div id="main4">

                </div>
            </div>
            <div class="main-title">
                <h3 class="pull-left">
                    当月生产计划
                    <a onclick="updateSchedule()"><img src="${staticImg}/images/main/write.png" alt=""
                                                       class="pull-right"></a>
                    <a onclick="addSchedule()"><img src="${staticImg}/images/main/add.png" alt=""
                                                    class="pull-right"></a>
                </h3>
                <p class="pull-right">
                    <a class="details" onclick="getScheduleHistory()">查看历史</a>
                </p>
            </div>
            <div class="main-body-right">
                <div class="pull-left product-left">
                    <span class="product">成品</span>
                </div>
                <ul class="product-right">
                <#if zsScheduleParaList??>
                    <#list zsScheduleParaList as zs>
                        <li>
                            <p class="product-title">
                                <span class="pull-left">${zs.productType}</span>
                                <span class="pull-right">${zs.amount?c}<b>${zs.unitName}</b></span>
                            </p>
                            <div class="progress">
                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                     aria-valuemin="0" aria-valuemax="100"
                                     style="width: ${zs.percent?string("0.00#%")}">
                                </div>
                                <span class="percent">${zs.percent?string("0.00#%")}</span>
                            </div>
                        </li>
                    </#list>
                <#else>
                    <li>
                        <p><b class="vertical-line"></b>本月暂无生产计划</p>
                    </li>
                </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

<!--新建生产计划-->
<div id="addScheduleModal" class="modal fade " data-width="1000" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false" style="height:450px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label>
        </button>
        <h4 class="modal-title" style="font-weight:bold">当月生产计划</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addScheduleButton" type="button" class="btn green">确认</button>
    </div>
</div>

<!--修改生产计划-->
<div id="updateScheduleModal" class="modal fade " data-width="1000" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false" style="height:450px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label>
        </button>
        <h4 class="modal-title" style="font-weight:bold">当月生产计划</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateScheduleButton" type="button" class="btn green">确认</button>
    </div>
</div>

<!--扫码地区-->
<div id="sweepAreaDetail" class="modal fade " data-width="1000" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false" style="height:620px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label>
        </button>
        <h4 class="modal-title" style="font-weight:bold">扫码区域分布详情</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn  green">确认</button>
    </div>
</div>

<!--生产计划历史-->
<div id="scheduleHistoryModal" class="modal fade " data-width="960" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false" style="height:620px;">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label>
        </button>
        <h4 class="modal-title" style="font-weight:bold">生产计划历史</h4>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn  green">确认</button>
    </div>
</div>
<script type="text/javascript" charset="utf-8" src="${staticJs}/js/echarts/echarts.min.js"></script>

<script>
    //年月日切换
    $(".date-box>span").click(function () {
        $(this).parent().children("span").removeClass("date-chosen");
        $(this).addClass("date-chosen");
    });
    //图表
    var myChart1 = echarts.init(document.getElementById('main1'));
    var myChart2 = echarts.init(document.getElementById('main2'));
    var myChart3 = echarts.init(document.getElementById('main3'));
    var myChart4 = echarts.init(document.getElementById('main4'));
    var option1 = {
        title: {
            text: '扫码量地区分布图',
            bottom: 0,
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        color: ['#84b761', '#5197c7', '#fdd400'],
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '60%',
                center: ['50%', '50%'],
                labelLine: {
                    normal: {
                        length: 5
                    }
                },
                data: []
            }
        ]
    };
    var option2 = {
        title: {
            text: '扫码量统计图',
            bottom: 0,
            x: 'center'
        },
        color: ['#ec878e'],
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
        ],
        yAxis: [
            {
                show: false
            }
        ],
        series: [
            {
                name: '扫码量',
                type: 'line',
                data: []
            }
        ]
    };
    var option3 = {
        title: {
            text: '标签生成、绑定量统计图',
            bottom: 0,
            x: 'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '15%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '标签生成量',
                type: 'line',
                data: []
            },
            {
                name: '标签绑定量',
                type: 'line',
                data: []
            }
        ]
    };
    var option4 = {
        title: {
            text: '收货数量统计图',
            bottom: 0,
            x: 'center'
        },
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '15%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '来料数量',
                nameTextStyle: {
                    fontSize: 16
                }
            }
        ],
        series: [
            {
                name: '海参',
                type: 'line',
                data: []
            },
            {
                name: '鲍鱼',
                type: 'line',
                data: []
            },
            {
                name: '虾仁',
                type: 'line',
                data: []
            },
            {
                name: '包材',
                type: 'line',
                data: []
            }
        ]
    };

    $(document).ready(function () {
        myChart1.showLoading();
        myChart2.showLoading();
        myChart3.showLoading();
        myChart4.showLoading();
        /**
         *扫码量地区分布
         **/
        $.post('${request.contextPath}/Home/scanAreaDistribution.json', function (res) {
            if (res.success) {
                option = {
                    series: [
                        {
                            data: res.obj
                        }
                    ]
                };
                myChart1.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart1.hideLoading();
        }, "JSON");
        /**
         *获取本周扫码量
         **/
        $.post('${request.contextPath}/Home/getWeekSweep.json', function (res) {
            if (res.success) {
                option = {
                    tooltip: {
                        trigger: 'axis',
                        formatter: "{a} <br/>{c}次"
                    },
                    series: [
                        {
                            data: res.obj
                        }
                    ]
                };
                myChart2.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart2.hideLoading();
        }, "JSON");

        /**
         * 获取本周收货统计
         */
        $.post('${request.contextPath}/Home/getWeekReceive.json', function (res) {
            if (res.success) {
                option = {
                    series: [
                        {
                            name: '海参',
                            type: 'line',
                            data: res.obj.hsList
                        },
                        {
                            name: '鲍鱼',
                            type: 'line',
                            data: res.obj.byList
                        },
                        {
                            name: '虾仁',
                            type: 'line',
                            data: res.obj.xrList
                        },
                        {
                            name: '包材',
                            type: 'line',
                            data: res.obj.bcList
                        }
                    ]
                };
                myChart4.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart4.hideLoading();
        }, "JSON");

        /**
         * 获取本周 标签生成、绑定统计图
         */
        $.post('${request.contextPath}/Home/getWeekTag.json', function (res) {
            if (res.success) {
                option = {
                    series: [
                        {
                            name: '标签生成量',
                            type: 'line',
                            data: res.obj.generateList
                        },
                        {
                            name: '标签绑定量',
                            type: 'line',
                            data: res.obj.bindList
                        }
                    ]
                };
                myChart3.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart3.hideLoading();
        }, "JSON");

    });

    //点击周获取扫码量
    $("#weekSweep").click(function () {
        myChart2.showLoading();
        $.post('${request.contextPath}/Home/getWeekSweep.json', function (res) {
            if (res.success) {
                option = {
                    tooltip: {
                        trigger: 'axis',
                        formatter: "{a} <br/>{c}次"
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        }
                    ],
                    series: [
                        {
                            data: res.obj
                        }
                    ]
                };
                myChart2.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart2.hideLoading();
        }, "JSON");
    });

    //点击月获取扫码量
    $("#monthSweep").click(function () {
        myChart2.showLoading();
        $.post('${request.contextPath}/Home/getMonthSweep.json', function (res) {
            if (res.success) {
                var date = new Date();
                var year = date.getFullYear();
                var mouth = date.getMonth() + 1;
                var days;
                if (mouth == 2) {
                    days = ((0 == year % 4 && 0 != year % 100) || 0 == year % 400) ? 29 : 28;
                }
                else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
                    days = 31;
                }
                else {
                    days = 30;
                }
                var mon = [];
                for (var v = 1; v <= days; v++) {
                    mon.push(v + "号");
                }
                option = {
                    tooltip: {
                        formatter: "{b}:</br>{a} {c} 次"
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: mon
                        }
                    ],
                    series: [
                        {
                            data: res.obj
                        }
                    ]
                };
                myChart2.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart2.hideLoading();
        }, "JSON");
    });

    //点击年获取扫码量
    $("#yearSweep").click(function () {
        myChart2.showLoading();
        $.post('${request.contextPath}/Home/getYearSweep.json', function (res) {
            if (res.success) {
                option = {
                    tooltip: {
                        formatter: "{b}:</br>{a} {c} 次"
                    },
                    xAxis: [
                        {
                            data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"]
                        }
                    ],
                    series: [
                        {
                            data: res.obj

                        }
                    ]
                };
                myChart2.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart2.hideLoading();
        }, "JSON");
    });

    //点击周获取收货数据
    $("#weekReceive").click(function () {
        myChart4.showLoading();
        $.post('${request.contextPath}/Home/getWeekReceive.json', function (res) {
            if (res.success) {
                option = {
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        }
                    ],
                    series: [
                        {
                            name: '海参',
                            type: 'line',
                            data: res.obj.hsList
                        },
                        {
                            name: '鲍鱼',
                            type: 'line',
                            data: res.obj.byList
                        },
                        {
                            name: '虾仁',
                            type: 'line',
                            data: res.obj.xrList
                        },
                        {
                            name: '包材',
                            type: 'line',
                            data: res.obj.bcList
                        }
                    ]
                };
                myChart4.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart4.hideLoading();
        }, "JSON");
    });

    //点击月获取收货数据
    $("#monthReceive").click(function () {
        myChart4.showLoading();
        $.post('${request.contextPath}/Home/getMonthReceive.json', function (res) {
            if (res.success) {
                var date = new Date();
                var year = date.getFullYear();
                var mouth = date.getMonth() + 1;
                var days;
                if (mouth == 2) {
                    days = ((0 == year % 4 && 0 != year % 100) || 0 == year % 400) ? 29 : 28;
                }
                else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
                    days = 31;
                }
                else {
                    days = 30;
                }
                var mon = [];
                for (var v = 1; v <= days; v++) {
                    mon.push(v + "号");
                }
                option = {
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: mon
                        }
                    ],
                    series: [
                        {
                            name: '海参',
                            type: 'line',
                            data: res.obj.hsList
                        },
                        {
                            name: '鲍鱼',
                            type: 'line',
                            data: res.obj.byList
                        },
                        {
                            name: '虾仁',
                            type: 'line',
                            data: res.obj.xrList
                        },
                        {
                            name: '包材',
                            type: 'line',
                            data: res.obj.bcList
                        }
                    ]
                };
                myChart4.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart4.hideLoading();
        }, "JSON");
    });

    //点击年获取收货数据
    $("#yearReceive").click(function () {
        myChart4.showLoading();
        $.post('${request.contextPath}/Home/getYearReceive.json', function (res) {
            if (res.success) {
                option = {
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"]
                        }
                    ],
                    series: [
                        {
                            name: '海参',
                            type: 'line',
                            data: res.obj.hsList
                        },
                        {
                            name: '鲍鱼',
                            type: 'line',
                            data: res.obj.byList
                        },
                        {
                            name: '虾仁',
                            type: 'line',
                            data: res.obj.xrList
                        },
                        {
                            name: '包材',
                            type: 'line',
                            data: res.obj.bcList
                        }
                    ]
                };
                myChart4.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart4.hideLoading();
        }, "JSON");
    });

    //点击周获取标签统计图
    $("#weekTag").click(function () {
        myChart3.showLoading();
        $.post('${request.contextPath}/Home/getWeekTag.json', function (res) {
            if (res.success) {
                option = {
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        }
                    ],
                    series: [
                        {
                            name: '标签生成量',
                            type: 'line',
                            data: res.obj.generateList
                        },
                        {
                            name: '标签绑定量',
                            type: 'line',
                            data: res.obj.bindList
                        }
                    ]
                };
                myChart3.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart3.hideLoading();
        }, "JSON");
    });

    //点击月获取标签统计图
    $("#monthTag").click(function () {
        myChart3.showLoading();
        $.post('${request.contextPath}/Home/getMonthTag.json', function (res) {
            if (res.success) {
                var date = new Date();
                var year = date.getFullYear();
                var mouth = date.getMonth() + 1;
                var days;
                if (mouth == 2) {
                    days = ((0 == year % 4 && 0 != year % 100) || 0 == year % 400) ? 29 : 28;
                }
                else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
                    days = 31;
                }
                else {
                    days = 30;
                }
                var mon = [];
                for (var v = 1; v <= days; v++) {
                    mon.push(v + "号");
                }
                option = {
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: mon
                        }
                    ],
                    series: [
                        {
                            name: '标签生成量',
                            type: 'line',
                            data: res.obj.generateList
                        },
                        {
                            name: '标签绑定量',
                            type: 'line',
                            data: res.obj.bindList
                        }
                    ]
                };
                myChart3.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart3.hideLoading();
        }, "JSON");
    });

    //点击年获取标签统计图
    $("#yearTag").click(function () {
        myChart3.showLoading();
        $.post('${request.contextPath}/Home/getYearTag.json', function (res) {
            if (res.success) {
                option = {
                    xAxis: [
                        {
                            data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"]
                        }
                    ],
                    series: [
                        {
                            name: '标签生成量',
                            type: 'line',
                            data: res.obj.generateList
                        },
                        {
                            name: '标签绑定量',
                            type: 'line',
                            data: res.obj.bindList
                        }
                    ]
                };
                myChart3.setOption(option);
            } else {
                tipDialog(res.msg, 3, -1);
            }
            myChart3.hideLoading();
        }, "JSON");
    });

    myChart1.setOption(option1);
    myChart2.setOption(option2);
    myChart3.setOption(option3);
    myChart4.setOption(option4);

    //点击查看 扫码区域分布详情
    myChart1.on('click', function () {
        $("#sweepAreaDetail").modal({
            remote: '${request.contextPath}/Home/sweepAreaDetailModal.htm'
        });
    });

    //新建生产计划
    function addSchedule() {
        $.post('${request.contextPath}/Home/duringMonthSchedule.json', function (res) {
            if (res.success) {
                tipDialog(res.msg, 3, 'warning');
            } else {
                if (res.msg == "本月无生产计划") {
                    $("#addScheduleModal").modal({
                        remote: '${request.contextPath}/Home/addScheduleModal.htm'
                    });
                } else {
                    tipDialog(res.msg, 3, 'warning');
                }
            }
        });
    }

    //生产计划历史
    function getScheduleHistory() {
        $("#scheduleHistoryModal").modal({
            remote: '${request.contextPath}/Home/scheduleHistoryModal.htm'
        });
    }

    //修改生产计划
    function updateSchedule() {
        $.post('${request.contextPath}/Home/duringMonthSchedule.json', function (res) {
            if (res.success) {
                $("#updateScheduleModal").modal({
                    remote: '${request.contextPath}/Home/updateScheduleModal.htm'
                });
            } else {
                tipDialog(res.msg, 3, 'warning');
            }
        });
    }

    function clearNoNum(obj) {
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
    }
</script>