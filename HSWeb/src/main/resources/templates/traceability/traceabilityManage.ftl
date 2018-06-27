
<style>
    .modal-title {
        text-align: center;
    }
</style>
<div>
    <div class="layoutPanel layout-center"
         style="position: absolute; z-index: 99; height: 100%; width: 100%;overflow: auto;">
        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
            <div class="PartialButton">
            <@shiro.hasPermission name="/traceability/reload">
                <a id="lr-replace" title="刷新当前(Ctrl+Q)" onclick="Replace()" class="tools_btn">
                        <span>
                            <b class="btn-reload">刷新</b>
                        </span>
                </a>
                <div class="tools_separator"></div>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/back">
                <a id="lr-leave" title="关闭当前窗口(Esc)" onclick="btn_back()" class="tools_btn">
                        <span>
                            <b class="btn-back">离开</b>
                        </span>
                </a>
            </@shiro.hasPermission>
            </div>
        </div>
        <div class="line"></div>
        <ul class="nav nav-tabs ">
            <@shiro.hasPermission name="/traceability/corporateNews">
                <li class="active">
                    <a href="#tab1" data-toggle="tab" onclick="changeTab(1)"> 企业新闻 </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/companyCulture">
                <li>
                    <a href="#tab2" data-toggle="tab" onclick="changeTab(2)"> 企业文化 </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/certificateManage">
                <li>
                    <a href="#tab3" data-toggle="tab" onclick="changeTab(3)"> 证书管理 </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/testingEquipment">
                <li>
                    <a href="#tab4" data-toggle="tab" onclick="changeTab(4)"> 检测设备 </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/xqKitchen">
                <li>
                    <a href="#tab5" data-toggle="tab" onclick="changeTab(5)"> 晓芹厨房 </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/productionInformation">
                <li>
                    <a href="#tab6" data-toggle="tab" onclick="changeTab(6)"> 生产关键控制点 </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/traceability/productionProcess">
                <li>
                    <a href="#tab9" data-toggle="tab" onclick="changeTab(9)"> 生产过程管理 </a>
                </li>
            </@shiro.hasPermission>
        </ul>
        <div class="line"></div>
        <div class="tab-content">
            <!--企业新闻-->
            <div class="tab-pane active" id="tab1">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                    <@shiro.hasPermission name="/traceability/addCorporateNews">
                        <a id="lr-add" title="新增" onclick="addTraceability()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/updateCorporateNews">
                        <a id="lr-edit" title="编辑" onclick="upTraceability()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/addCorporateNewsContent">
                        <a id="lr-detail" title="增加内容" onclick="addContent()" class="tools_btn">
                        <span>
                            <b class="btn-detail">增加内容</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/corporateNewsImage">
                        <a id="lr-detail" title="查看图片" onclick="viewimage()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看图片</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/deleteCorporateNews">
                        <a id="lr-delete" title="删除" onclick="deleteTraceability()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    </div>
                </div>
                <table id="gridTable">
                </table>
                <div id="gridPager">
                </div>
            </div>
            <!--企业文化-->
            <div class="tab-pane" id="tab2">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                    <@shiro.hasPermission name="/traceability/addCompanyCulture">
                        <a id="lr-add" title="新增" onclick="addCompanyCulture()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/updateCompanyCulture">
                        <a id="lr-edit" title="编辑" onclick="upCompanyCulture()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/companyCultureImage">
                        <a id="lr-detail" title="查看图片" onclick="viewCompanyCulture()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看图片</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/deleteCompanyCulture">
                        <a id="lr-delete" title="删除" onclick="deleteCompanyCulture()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    </div>
                </div>
                <table id="gridTable1">
                </table>
                <div id="gridPager1">
                </div>
            </div>
            <!--证书管理-->
            <div class="tab-pane" id="tab3">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                    <@shiro.hasPermission name="/traceability/addCertificateManage">
                        <a id="lr-add" title="新建证书" onclick="addCertificateManage()" class="tools_btn">
                        <span>
                            <b class="btn-add">新建证书</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/updateCertificateManage">
                        <a id="lr-edit" title="修改证书" onclick="updateCertificateManageModal()" class="tools_btn">
                        <span>
                            <b class="btn-update">修改证书</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <#--<@shiro.hasPermission name="/traceability/upTraceability">-->
                        <#--<a id="lr-detail" title="生产过程管理" onclick="productionProcessManage()" class="tools_btn">-->
                        <#--<span>-->
                            <#--<b class="btn-detail">生产过程管理</b>-->
                        <#--</span>-->
                        <#--</a>-->
                    <#--</@shiro.hasPermission>-->
                    <@shiro.hasPermission name="/traceability/deleteCertificateManage">
                        <a id="lr-delete" title="删除" onclick="deleteCertificateManage()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/addCertificateManageContent">
                        <a id="lr-delete" title="增加内容" onclick="addCertificateContent()" class="tools_btn">
                        <span>
                            <b class="btn-add">增加内容</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    </div>
                </div>
                <table id="gridTable2">
                </table>
                <div id="gridPager2">
                </div>
            </div>
            <!--检测设备-->
            <div class="tab-pane" id="tab4">
                <ul class="nav nav-tabs ">
                    <@shiro.hasPermission name="/traceability/testingEqpuiments">
                    <li class="active">
                        <a href="#tab7" data-toggle="tab" onclick="changeTab(7)"> 检验室 </a>
                    </li>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/testingEquipmentDetail">
                    <li>
                        <a href="#tab8" data-toggle="tab" onclick="changeTab(8)"> 检验室设备 </a>
                    </li>
                    </@shiro.hasPermission>
                </ul>
                <div class="tab-content" >
                    <!--检验室-->
                    <div class="tab-pane active" id="tab7">
                        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                    <@shiro.hasPermission name="/traceability/addTestingEquipment">
                        <a id="lr-add" title="新增" onclick="addTestingEquipment()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/upTestingEquipment">
                        <a id="lr-edit" title="编辑" onclick="upTestingEquipment()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/testingEquipmentAddTestingTestingEquipmentDetail">
                        <a id="lr-detail" title="增加检验室设备" onclick="addTesting()" class="tools_btn">
                        <span>
                            <b class="btn-add">增加检验室设备</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/testingEquipmentImage">
                        <a id="lr-detail" title="查看图片" onclick="viewTestingEquipment()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看图片</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/deleteTestingEquipment">
                        <a id="lr-delete" title="删除" onclick="deleteTestingEquipment()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    </div>
                </div>
                        <table id="gridTable7">
                        </table>
                        <div id="gridPager7">
                        </div>
                    </div>
                    <!--检验室内容-->
                    <div class="tab-pane" id="tab8">
                        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                            <div class="PartialButton">
                            <@shiro.hasPermission name="/traceability/addTestingEquipmentDetail">
                                <a id="lr-detail" title="增加检验室设备" onclick="addTestingDetail()" class="tools_btn">
                        <span>
                            <b class="btn-add">增加检验室设备</b>
                        </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/updateTestingEquipmentDetail">
                                <a id="lr-edit" title="编辑" onclick="upTestingEquipmentDetail()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/testingEquipmentDetailImage">
                                <a id="lr-detail" title="查看图片" onclick="viewTestingEquipmentDetail()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看图片</b>
                        </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/deleteTestingEquipmentDetail">
                                <a id="lr-delete" title="删除" onclick="deleteTestingEquipmentDetail()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                                </a>
                            </@shiro.hasPermission>
                            </div>
                        </div>
                        <table id="gridTable8">
                        </table>
                        <div id="gridPager8">
                        </div>
                    </div>
                </div>
            </div>
            <!--晓芹厨房-->
            <div class="tab-pane" id="tab5">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                    <@shiro.hasPermission name="/traceability/addXqKitchen">
                        <a id="lr-add" title="新增" onclick="addXqKitchen()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/updateXqKitchen">
                        <a id="lr-edit" title="编辑" onclick="upXqKitchen()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/xqKitchenImage">
                        <a id="lr-detail" title="查看图片" onclick="viewXqKitchen()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看图片</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/deleteXqKitchen">
                        <a id="lr-delete" title="删除" onclick="deleteXqKitchen()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    </div>
                </div>
                <table id="gridTable4">
                </table>
                <div id="gridPager4">
                </div>
            </div>
            <!--生产关键控制点-->
            <div class="tab-pane" id="tab6">
                <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                    <div class="PartialButton">
                    <@shiro.hasPermission name="/traceability/addProductionInformation">
                        <a id="lr-add" title="新增" onclick="addProductionInformation()" class="tools_btn">
                        <span>
                            <b class="btn-add">新增</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/updateProductionInformation">
                        <a id="lr-edit" title="编辑" onclick="upProductionInformation()" class="tools_btn">
                        <span>
                            <b class="btn-update">编辑</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/productionInformationImage">
                        <a id="lr-detail" title="查看图片" onclick="viewProductionInformation()" class="tools_btn">
                        <span>
                            <b class="btn-detail">查看图片</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/deleteProductionInformation">
                        <a id="lr-delete" title="删除" onclick="deleteProductionInformation()" class="tools_btn">
                        <span>
                            <b class="btn-delete">删除</b>
                        </span>
                        </a>
                    </@shiro.hasPermission>
                    </div>
                </div>
                <table id="gridTable5">
                </table>
                <div id="gridPager5">
                </div>
            </div>
            <!--生产过程管理-->
            <div class="tab-pane" id="tab9">
                <ul class="nav nav-tabs ">
                    <@shiro.hasPermission name="/traceability/productionProcesses">
                    <li class="active">
                        <a href="#tab10" data-toggle="tab" onclick="changeTab(10)"> 生产过程 </a>
                    </li>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="/traceability/productionProcessDetail">
                    <li>
                        <a href="#tab11" data-toggle="tab" onclick="changeTab(11)"> 生产过程详情 </a>
                    </li>
                    </@shiro.hasPermission>
                </ul>
                <div class="tab-content" >
                    <!--生产过程-->
                    <div class="tab-pane active" id="tab10">
                        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                            <div class="PartialButton">
                            <@shiro.hasPermission name="/traceability/addProductionProcess">
                                <a id="lr-add" title="新增" onclick="addProductionProcess()" class="tools_btn">
                                    <span>
                                        <b class="btn-add">新增</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/updateProductionProcess">
                                <a id="lr-edit" title="编辑" onclick="updProductionProcess()" class="tools_btn">
                                    <span>
                                        <b class="btn-update">编辑</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/productionProcessAddProductionProcessDetail">
                                <a id="lr-detail" title="增加详情" onclick="addProductionProcessDetail()" class="tools_btn">
                                    <span>
                                        <b class="btn-add">增加详情</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/deleteProductionProcess">
                                <a id="lr-delete" title="删除" onclick="deleteProductionProcess()" class="tools_btn">
                                    <span>
                                        <b class="btn-delete">删除</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            </div>
                        </div>
                        <table id="gridTable10">
                        </table>
                        <div id="gridPager10">
                        </div>
                    </div>
                    <!--生产过程详情-->
                    <div class="tab-pane" id="tab11">
                        <div class="tools_bar" style="border-top: none; margin-bottom: 0px;">
                            <div class="PartialButton">
                            <@shiro.hasPermission name="/traceability/addProductionProcessDetail">
                                <a id="lr-add" title="新增生产过程详情" onclick="addProductionProcessDetailTwo()" class="tools_btn">
                                    <span>
                                        <b class="btn-add">新增生产过程详情</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/updateProductionProcessDetail">
                                <a id="lr-edit" title="编辑" onclick="updateProductionProcessDetail()" class="tools_btn">
                                    <span>
                                        <b class="btn-update">编辑</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/productionProcessImage">
                                <a id="lr-detail" title="查看图片" onclick="viewProductionProcess()" class="tools_btn">
                                    <span>
                                        <b class="btn-detail">查看图片</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            <@shiro.hasPermission name="/traceability/deleteProductionProcessDetail">
                                <a id="lr-delete" title="删除" onclick="deleteProductionProcessDetail()" class="tools_btn">
                                    <span>
                                        <b class="btn-delete">删除</b>
                                    </span>
                                </a>
                            </@shiro.hasPermission>
                            </div>
                        </div>
                        <table id="gridTable11">
                        </table>
                        <div id="gridPager11">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--新增新闻-->
<@shiro.hasPermission name="/traceability/addCorporateNews">
<div id="addCorporateNewsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增企业新闻</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addCorporateNews" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--修改新闻-->
<@shiro.hasPermission name="/traceability/updateCorporateNews">
<div id="updateCorporateNewsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改企业新闻</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateTraceability" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增新闻内容-->
<@shiro.hasPermission name="/traceability/addCorporateNewsContent">
<div id="updateAddCorporateNewsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增内容</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateAddTraceability" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--删除新闻-->
<@shiro.hasPermission name="/traceability/deleteCorporateNews">
<div id="deleteCorporateNewsModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteCorporateNews" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--查看新闻图片-->
<@shiro.hasPermission name="/traceability/corporateNewsImage">
<div id="viewCorporateNewsModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增企业文化-->
<@shiro.hasPermission name="/traceability/addCompanyCulture">
<div id="addCompanyCultureModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增企业文化</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addCompanyCulture" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--修改企业文化-->
<@shiro.hasPermission name="/traceability/updateCompanyCulture">
<div id="updateCompanyCultureModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改企业文化</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateCompanyCulture1" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--查看企业图片-->
<@shiro.hasPermission name="/traceability/companyCultureImage">
<div id="viewCompanyCultureModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--删除企业-->
<@shiro.hasPermission name="/traceability/deleteCompanyCulture">
<div id="deleteCompanyCultureModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteCompanyCulture" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增生产控制点-->
<@shiro.hasPermission name="/traceability/addProductionInformation">
<div id="addProductionInformationModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增生产控制点</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addProductionInformation" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--编辑生产控制点-->
<@shiro.hasPermission name="/traceability/updateProductionInformation">
<div id="updateProductionInformationModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改生产控制点</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateProductionInformation1" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--查看生产控制点图片-->
<@shiro.hasPermission name="/traceability/productionInformationImage">
<div id="viewProductionInformationModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--删除生产控制点-->
<@shiro.hasPermission name="/traceability/deleteProductionInformation">
<div id="deleteProductionInformationModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteProductionInformation" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增晓芹厨房-->
<@shiro.hasPermission name="/traceability/addXqKitchen">
<div id="addXqKitchenModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增晓芹厨房</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addXqKitchen1" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--编辑晓芹厨房-->
<@shiro.hasPermission name="/traceability/updateXqKitchen">
<div id="updateXqKitchenModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改晓芹厨房</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateXqKitchen1" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--查看晓芹厨房图片-->
<@shiro.hasPermission name="/traceability/xqKitchenImage">
<div id="viewXqKitchenModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--删除晓芹厨房-->
<@shiro.hasPermission name="/traceability/deleteXqKitchen">
<div id="deleteXqKitchenModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteXqKitchen" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增检测设备-->
<@shiro.hasPermission name="/traceability/addTestingEquipment">
<div id="addTestingEquipmentModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增检测设备</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addTestingEquipment" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--编辑检测设备-->
<@shiro.hasPermission name="/traceability/upTestingEquipment">
<div id="updateTestingEquipmentModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改检测设备</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateTestingEquipment" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--删除检测设备-->
<@shiro.hasPermission name="/traceability/deleteTestingEquipment">
<div id="deleteTestingEquipmentModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteTestingEquipment" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--查看检测设备图片-->
<@shiro.hasPermission name="/traceability/testingEquipmentImage">
<div id="viewTestingEquipmentModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增检测设备内容-->
<@shiro.hasPermission name="/traceability/testingEquipmentAddTestingTestingEquipmentDetail">
<div id="updateaddTestingModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增内容</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateaddTesting" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--新增检测设备详细内容-->
<@shiro.hasPermission name="/traceability/addTestingEquipmentDetail">
<div id="addTestingEquipmentDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增内容</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addTestingEquipmentDetail" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--编辑检测设备详细内容-->
<@shiro.hasPermission name="/traceability/updateTestingEquipmentDetail">
<div id="updateTestingEquipmentDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改检测设备详细</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updateTestingEquipmentDetail" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>
<!--删除检测设备详细内容-->
<@shiro.hasPermission name="/traceability/deleteTestingEquipmentDetail">
<div id="deleteTestingEquipmentDetailModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteTestingEquipmentDetail" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>
<!--查看检测设备详细内容-->
<@shiro.hasPermission name="/traceability/testingEquipmentDetailImage">
<div id="viewTestingEquipmentDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/addCertificateManage">
<#--新建证书-->
<div id="addCertificateManageModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" onclick="addCertificateManageJson()" class="btn btn-outline green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/updateCertificateManage">
<#--修改证书-->
<div id="updateCertificateManageModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">修改证书</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" onclick="updateCertificateManage()" class="btn btn-outline green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/deleteCertificateManage">
<#--删除证书-->
<div id="deleteCertificateManageModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteCertificateManage" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/certificateManage">
<#--查看证书-->
<div id="certificateManageDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看证书</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline green">关闭</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/addCertificateManageContent">
<#--证书增加内容-->
<div id="addCertificateContentModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增内容</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addCertificateContent" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/addProductionProcess">
<#--新增生产过程-->
<div id="addProductionProcessModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">新增生产过程</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="addProductionProcess" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/updateProductionProcess">
<#--编辑生产过程-->
<div id="updProductionProcessModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">编辑生产过程</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="updProductionProcess" type="button" class="btn green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/deleteProductionProcess">
<#--删除生产过程-->
<div id="deleteProductionProcessModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteProductionProcess" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/productionProcessAddProductionProcessDetail">
<#--增加生产过程详情-->
<div id="addProductionProcessDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">增加详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" onclick="addProductionProcessDetailJson()" class="btn btn-outline green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/updateProductionProcessDetail">
<#--编辑生产过程详情-->
<div id="updateProductionProcessDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">编辑详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" onclick="updateProductionProcessDetailJson()" class="btn btn-outline green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/deleteProductionProcessDetail">
<#--删除生产过程详情-->
<div id="deleteProductionProcessDetailModal" class="modal fade " data-width="400" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header" style="text-align: center">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">删除确认</h4>
    </div>
    <div class="modal-body">
        <p style="text-align: center">删除后数据不可恢复</p>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button id="deleteProductionProcessDetail" type="button" class="btn green">确定</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/productionProcessImage">
<#--查看生产过程详情图片-->
<div id="viewProductionProcessDetailModal" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">查看图片</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<@shiro.hasPermission name="/traceability/addProductionProcessDetail">
<#--增加生产过程详情-->
<div id="addProductionProcessDetailModalTwo" class="modal fade " data-width="760" tabindex="-1" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><label>&times;</label></button>
        <h4 class="modal-title">增加详情</h4>
    </div>
    <div class="modal-body">

    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">取消</button>
        <button type="button" onclick="addProductionProcessDetailJsonTwo()" class="btn btn-outline green">确认</button>
    </div>
</div>
</@shiro.hasPermission>

<script type="text/javascript">
    var resourceId;

    $(document).ready(function () {
        GetGrid("${request.contextPath}/traceability/getCorporateNewsBy.json", "#gridTable");
        resourceId= top.$("#ModuleId").val();
        Loading(false);
    });
    //切换
   function changeTab(table) {
       if(1==table){
           GetGrid("${request.contextPath}/traceability/getCorporateNewsBy.json", "#gridTable");
       }else if(2==table){
            GetGrid1("${request.contextPath}/traceability/getCompanyCulture.json", "#gridTable1");
       }else if(3==table){
           GetGrid2("${request.contextPath}/traceability/getCertificateManage.json", "#gridTable2");
       }else if(4==table){
           GetGrid7("${request.contextPath}/traceability/getTestingEquipment.json", "#gridTable7");
       }else if(5==table){
           GetGrid4("${request.contextPath}/traceability/getXqKitchen.json", "#gridTable4");
       }else if(6==table){
           GetGrid5("${request.contextPath}/traceability/getProductionInformation.json", "#gridTable5");
       }else if(7==table){
           GetGrid7("${request.contextPath}/traceability/getTestingEquipment.json", "#gridTable7");
       }else if(8==table){
           GetGrid8("${request.contextPath}/traceability/getTestingEquipmentDetail.json", "#gridTable8");
       }else if(9==table){
           GetGrid10("${request.contextPath}/traceability/getProductionProcess.json", "#gridTable10");
       }else if(10==table){
           GetGrid10("${request.contextPath}/traceability/getProductionProcess.json", "#gridTable10");
       }else if(11==table){
           GetGrid11("${request.contextPath}/traceability/getProductionProcessDetail.json", "#gridTable11");
       }
   }

    /**
     * 加载企业新闻表格
     * */
    function GetGrid(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "corporateNewsId", index: "corporateNewsId", hidden: true},
                {label: "标题", name: "title", index: "title", width: 300},
                {label: "链接", name: "link", index: "link", width: 400},
                {
                    label: "时间", name: "corporateNewsDate", index: "corporateNewsDate", width: 500, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {label: "图片", name: "image", index: "image", width: 80,
                    formatter: function (cellvalue,options, rowObject) {
                        if(cellvalue==""){
                            return "未上传";
                        }else{
                            return "已上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
//            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'desc',
            shrinkToFit: false,
            gridview: true
        });
    }
    /**
     * 加载企业文化表格
     * */
    function GetGrid1(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "companyCultureId", index: "companyCultureId", hidden: true},
                {label: "名称", name: "companyCultureName", index: "companyCultureName", width: 300},
                {label: "内容", name: "remark", index: "remark", width: 800},
                {label: "图片", name: "isHaveIamge", index: "isHaveIamge", width: 80,
                    formatter: function (cellvalue) {
                        if(1==cellvalue){
                            return "已上传";
                        }else{
                            return "未上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager1",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
    /**
     * 加载检测设备表格
     * */
    function GetGrid7(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "testingEquipmentId", index: "testingEquipmentId", hidden: true},
                {label: "编号", name: "id", index: "id", width: 300},
                {label: "检验室", name: "testCName", index: "testCName", width: 200},
                {label: "文字描述", name: "remark", index: "remark", width: 300},
                {label: "图片", name: "images", index: "images", width: 80,
                    formatter: function (cellvalue) {
                        if(cellvalue==""){
                            return "未上传";
                        }else{
                            return "已上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager7",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
    /**
     * 加载检测设备内容表格
     * */
    function GetGrid8(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "testingEquipmentDetailId", index: "testingEquipmentDetailId", hidden: true},
                {label: "", name: "testingEquipmentId", index: "testingEquipmentId", hidden: true},
                {label: "检验室", name: "testCName", index: "testCName", width: 200},
                {label: "名称", name: "testingEquipmentDetailCname", index: "testingEquipmentDetailCname", width: 300},
                {label: "文字描述", name: "remark", index: "remark", width: 300},
                {label: "图片", name: "images", index: "images", width: 80,
                    formatter: function (cellvalue) {
                        if(cellvalue==""){
                            return "未上传";
                        }else{
                            return "已上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager8",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    /**
     * 加载晓芹厨房表格
     * */
    function GetGrid4(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "kitchenId", index: "kitchenId", hidden: true},
                {label: "名称", name: "kitchenName", index: "kitchenName", width: 200},
                {label: "特点", name: "features", index: "features", width: 300},
                {label: "配料", name: "ingredients", index: "ingredients", width: 300},
                {label: "制作方法", name: "productionMethod", index: "productionMethod", width: 300},
                {label: "图片", name: "isHaveImage", index: "isHaveImage", width: 80,
                    formatter: function (cellvalue) {
                        if(1==cellvalue){
                            return "已上传";
                        }else{
                            return "未上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager4",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
    /**
     * 加载生产控制点表格
     * */
    function GetGrid5(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "productionInformationId", index: "productionInformationId", hidden: true},
                {label: "编号", name: "id", index: "id", width: 300},
                {label: "名称", name: "productionInformationName", index: "productionInformationName", width: 300},
                {label: "文字描述", name: "remark", index: "remark", width: 400},
                {label: "图片", name: "images", index: "images", width: 80,
                    formatter: function (cellvalue) {
                        if(cellvalue==""){
                            return "未上传";
                        }else{
                            return "已上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager5",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    /**
     * 证书管理
     * */
    function GetGrid2(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "编号", name: "certificateManageId", index: "certificateManageId", hidden: true},
                {label: "产品大类", name: "bigProductTypeName", index: "bigProductTypeName", width: 100},
                {label: "产品小类", name: "smallProductTypeName", index: "smallProductTypeName", width: 100},
                {label: "生产许可证", name: "productionLicense", index: "productionLicense", width: 200},
                {label: "产品标准号", name: "productStandards", index: "productStandards", width: 200},
                {label: "产品生产过程", name: "productionProcessName", index: "productionProcessName", width: 150},
                {
                    label: "检测证书", name: "", index: "", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        return"<a onclick='certificateManageDetailModal("+rowObject.certificateManageId+","+rowObject.isHaveIamge+")'><span style='color: blue'>查看证书</span></a>"
                    }
                 },
                {
                    label: "检验结果", name: "testResult", index: "testResult", width: 100,
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == 1) return "检验合格";
                        if (cellvalue == 2) return "检验不合格";
                    }
                },
                {
                    label: "到期日", name: "endDate", index: "endDate", width: 200,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {label: "图片", name: "isHaveIamge", index: "isHaveIamge", width: 80,
                    formatter: function (cellvalue) {
                        if(1==cellvalue){
                            return "已上传";
                        }else{
                            return "未上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager2",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
    /**
     * 生产过程
     * */
    function GetGrid10(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "编号", name: "productionProcessId", index: "productionProcessId", width: 100},
                {label: "生产过程名称", name: "productionProcessName", index: "productionProcessName", width: 200},
                {label: "创建人", name: "createUserName", index: "createUserName", width: 100},
                {
                    label: "创建时间", name: "createDate", index: "createDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                    }
                },
                {label: "修改人", name: "updateUserName", index: "updateUserName", width: 100},
                {
                    label: "修改时间", name: "updateDate", index: "updateDate", width: 150,
                    formatter: function (cellvalue, options, rowObject) {
                        if(""==rowObject.updateUserName){
                            return "";
                        }else {
                            return formatDate(cellvalue, 'yyyy-MM-dd hh:mm:ss');
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager10",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }
    /**
     * 生产过程详情
     * */
    function GetGrid11(url,table) {
        $("" + table).jqGrid({
            url: url + "",
            datatype: "json",
            height: $(window).height() - 212,
            width: $(window).width(),
            colModel: [
                {label: "", name: "productionProcessDetailedId", index: "productionProcessDetailedId", hidden: true},
                {label: "编号", name: "productionProcessDetailNumber", index: "productionProcessDetailNumber", width:80},
                {label: "过程名称", name: "productionProcessName", index: "productionProcessName", width: 200},
                {label: "过程详情名称", name: "productionProcessDetailName", index: "productionDetailProcessName", width: 200},
                {label: "过程描述", name: "processDescription", index: "processDescription", width: 500,height:50},
                {label: "图片", name: "detailImage", index: "detailImage", width: 80,
                    formatter: function (cellvalue) {
                        if(""!=cellvalue){
                            return "已上传";
                        }else{
                            return "未上传";
                        }
                    }
                }
            ],
            pagerpos : "right",
            recordpos : "left",
            viewrecords: true,
            rownumbers: true,
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            pager: "#gridPager11",
            jsonReader:{
                root:"root",page:"page",total:"total",records:"records"
            },
            sortname: 'listIndex',
            sortorder: 'asc',
            shrinkToFit: false,
            gridview: true
        });
    }

    //新增新闻
    <@shiro.hasPermission name="/traceability/addCorporateNews">
    function addTraceability() {
        $("#addCorporateNewsModal").modal({
            remote: "${request.contextPath}/traceability/addCorporateNewsModal.htm?resourceId=" +resourceId
        });
    }
    $("#addCorporateNews").unbind("click");
    $("#addCorporateNews").click(function () {
        Loading(true,"正在提交...","#addCorporateNewsModal");
        var title = $("#newsTitle").val();
        if(""==title){
            $("#newsTitle").focus();
            tipDialog("请输入标题",4,'a');
            Loading(false,"","#addCorporateNewsModal");
            return false;
        }
        var link = $("#newsLink").val();
        if(""==link){
            tipDialog("请输入链接",4,'a');
            Loading(false,"","#addCorporateNewsModal");
            return false;
        }
        var urlP= /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
        if(urlP.test(link)) {
        }else {
            tipDialog("这网址不是以http://https://开头，或者不是网址！", 4, '0');
            Loading(false,"","#addCorporateNewsModal");
            return false;
        }
        var corporateNewsDate = $("#corporateNewsDate").val();
        if(""==corporateNewsDate){
            tipDialog("请选择时间",4,'a');
            Loading(false,"","#addCorporateNewsModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/addCorporateNews.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable").trigger("reloadGrid");
                    $("#addCorporateNewsModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addCorporateNewsModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addCorporateNewsModal");
            }
        };
        $('#addCorporateNewsForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //编辑新闻
    <@shiro.hasPermission name="/traceability/updateCorporateNews">
    function upTraceability() {
        var id = GetJqGridRowValue("#gridTable", "corporateNewsId");
        if(null != id){
            $("#updateCorporateNewsModal").modal({
                remote: "${request.contextPath}/traceability/updateCorporateNewsModal.htm?id="+id+"&resourceId=" +resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateTraceability").unbind("click");
    $("#updateTraceability").click(function () {
        Loading(true,"正在提交...","#updateCorporateNewsModal");
        var title = $("#updTitle").val();
        if(""==title){
            $("#updTitle").focus();
            tipDialog("请输入标题",4,'a');
            Loading(false,"","#updateCorporateNewsModal");
            return false;
        }
        var link = $("#updLink").val();
        if(""==link){
            tipDialog("请输入链接",4,'a');
            Loading(false,"","#updateCorporateNewsModal");
            return false;
        }
        var urlP= /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
        if(urlP.test(link)) {
        }else {
            tipDialog("这网址不是以http://https://开头，或者不是网址！", 4, '0');
            Loading(false,"","#updateCorporateNewsModal");
            return false;
        }
        var corporateNewsDate = $("#updCorporateNewsDate").val();
        if(""==corporateNewsDate){
            tipDialog("请选择时间",4,'a');
            Loading(false,"","#updateCorporateNewsModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateCorporateNews.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
//                    Loading(true, "正在刷新...","#updateCorporateNewsModal");
                    $("#gridTable").trigger("reloadGrid");
                    $("#updateCorporateNewsModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateCorporateNewsModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateCorporateNewsModal");
            }
        };
        $('#updateCorporateNews').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //新增新闻内容
    <@shiro.hasPermission name="/traceability/addCorporateNewsContent">
    function addContent() {
        var id = GetJqGridRowValue("#gridTable", "corporateNewsId");
        if(id!=null){
            $("#updateAddCorporateNewsModal").modal({
                remote: "${request.contextPath}/traceability/updateAddCorporateNewsModal.htm?id="+id+"&resourceId=" +resourceId
            });
        }else{
            tipDialog("请选择您要新增内容的列",4,"warning");
        }
    }
    $("#updateAddTraceability").unbind("click");
    $("#updateAddTraceability").click(function () {
        Loading(true,"正在提交...","#updateAddCorporateNewsModal");
        var corporateNewsName = $("#corporateNewsName").val();
        if(""==corporateNewsName){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#updateAddCorporateNewsModal");
            return false;
        }
        var remark = $("#updateAddRemark").val();
        if(""==remark){
            tipDialog("请输入内容",4,'a');
            Loading(false,"","#updateAddCorporateNewsModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateAddCorporateNews.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable").trigger("reloadGrid");
                    $("#updateAddCorporateNewsModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateAddCorporateNewsModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false);
            }
        };
        $('#updateAddCorporateNews').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //删除新闻
    <@shiro.hasPermission name="/traceability/deleteCorporateNews">
    function deleteTraceability() {
        var id = GetJqGridRowValue("#gridTable", "corporateNewsId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteCorporateNewsModal").modal("show");
        }
    }
    $("#deleteCorporateNews").unbind("click");
    $("#deleteCorporateNews").click(function () {
        var corporateNewsId = GetJqGridRowValue("#gridTable", "corporateNewsId");
        Loading(true,"正在删除...","#deleteCorporateNewsModal");
        $.post("${request.contextPath}/traceability/delectCorporateNews.json", {corporateNewsId: corporateNewsId,resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteCorporateNewsModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteCorporateNewsModal");
        }, "json");
    });
    </@shiro.hasPermission>
    //查看新闻图片
    <@shiro.hasPermission name="/traceability/corporateNewsImage">
    function viewimage() {
        let image = GetJqGridRowValue("#gridTable", "image");
        var id = GetJqGridRowValue("#gridTable", "corporateNewsId");
        if(image=="未上传" ){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }
        if(id!=null){
            $("#viewCorporateNewsModal").modal({
                remote: "${request.contextPath}/traceability/viewCorporateNewsModal.htm?id="+id
            });
        }else{
            tipDialog("请选择您要查看图片的列",4,"warning");
        }
    }
    </@shiro.hasPermission>
    //新增企业文化
    <@shiro.hasPermission name="/traceability/addCompanyCulture">
    function addCompanyCulture() {
        $("#addCompanyCultureModal").modal({
            remote: "${request.contextPath}/traceability/addCompanyCultureModal.htm?resourceId=" +resourceId
        });
    }
    $("#addCompanyCulture").unbind("click");
    $("#addCompanyCulture").click(function () {
        Loading(true,"正在提交...","#addCompanyCultureModal");
        var companyCultureName = $("#companyCultureName").val();
        if(""==companyCultureName){
            $("#companyCultureName").focus();
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#addCompanyCultureModal");
            return false;
        }
        var remark = $("#companyCultureRemark").val();
        if(""==remark){
            tipDialog("请输入内容",4,'a');
            Loading(false,"","#addCompanyCultureModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/addCompanyCulture.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable1").trigger("reloadGrid");
                    $("#addCompanyCultureModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addCompanyCultureModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addCompanyCultureModal");
            }
        };
        $('#addCompanyCulture').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //编辑企业文化
    <@shiro.hasPermission name="/traceability/updateCompanyCulture">
    function upCompanyCulture() {
        var id = GetJqGridRowValue("#gridTable1", "companyCultureId");
        if(null != id){
            $("#updateCompanyCultureModal").modal({
                remote: "${request.contextPath}/traceability/updateCompanyCultureModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateCompanyCulture1").unbind("click");
    $("#updateCompanyCulture1").click(function () {
        Loading(true,"正在提交...","#updateCompanyCultureModal");
        var companyCultureName = $("#updCompanyCultureName").val();
        if(""==companyCultureName){
            $("#updCompanyCultureName").focus();
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#updateCompanyCultureModal");
            return false;
        }
        var remark = $("#updCultureRemark").val();
        if(""==remark){
            tipDialog("请输入内容",4,'a');
            Loading(false,"","#updateCompanyCultureModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateCompanyCulture.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable1").trigger("reloadGrid");
                    $("#updateCompanyCultureModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateCompanyCultureModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateCompanyCultureModal");
            }
        };
        $('#updateCompanyCulture').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //查看企业图片
    <@shiro.hasPermission name="/traceability/companyCultureImage">
    function viewCompanyCulture() {
        let image = GetJqGridRowValue("#gridTable1", "isHaveIamge");
        if(image=="未上传" ){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }else {
            var id = GetJqGridRowValue("#gridTable1", "companyCultureId");
            if(id!=null){
                $("#viewCompanyCultureModal").modal({
                    remote: "${request.contextPath}/traceability/viewCompanyCultureModal.htm?id="+id
                });
            }else{
                tipDialog("请选择您要查看图片的列",4,"warning");
            }
        }
    }
    </@shiro.hasPermission>
    //删除企业
    <@shiro.hasPermission name="/traceability/deleteCompanyCulture">
    function deleteCompanyCulture() {
        var id = GetJqGridRowValue("#gridTable1", "companyCultureId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteCompanyCultureModal").modal("show");
        }
    }
    $("#deleteCompanyCulture").unbind("click");
    $("#deleteCompanyCulture").click(function () {
        var companyCultureId = GetJqGridRowValue("#gridTable1", "companyCultureId");
        Loading(true,"正在删除...","#deleteCompanyCultureModal")
        $.post("${request.contextPath}/traceability/delectCompanyCulture.json", {companyCultureId: companyCultureId,resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable1").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteCompanyCultureModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteCompanyCultureModal");
        }, "json");
    });
    </@shiro.hasPermission>
    //新增生产控制点
    <@shiro.hasPermission name="/traceability/addProductionInformation">
    function addProductionInformation() {
        $("#addProductionInformationModal").modal({
            remote: "${request.contextPath}/traceability/addProductionInformationModal.htm?resourceId="+resourceId
        });
    }
    $("#addProductionInformation").unbind("click");
    $("#addProductionInformation").click(function () {
        Loading(true,"正在提交...","#addProductionInformationModal");
        var id = $("#productionInformationId").val();
        if(""==id){
            tipDialog("请输入编号",4,'a');
            Loading(false,"","#addProductionInformationModal");
            return false;
        }
        var productionInformationName = $("#productionInformationName").val();
        if(""==productionInformationName){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#addProductionInformationModal");
            return false;
        }
        var remark = $("#productionInformationRemark").val();
        if(""==remark){
            tipDialog("请输入文字描述",4,'a');
            Loading(false,"","#addProductionInformationModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/addProductionInformation.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable5").trigger("reloadGrid");
                    $("#addProductionInformationModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addProductionInformationModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addProductionInformationModal");
            }
        };
        $('#addProduction').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //编辑生产控制点
    <@shiro.hasPermission name="/traceability/updateProductionInformation">
    function upProductionInformation() {
        var id = GetJqGridRowValue("#gridTable5", "productionInformationId");
        if(null != id){
            $("#updateProductionInformationModal").modal({
                remote: "${request.contextPath}/traceability/updateProductionInformationModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateProductionInformation1").unbind("click");
    $("#updateProductionInformation1").click(function () {
        Loading(true,"正在提交...","#updateProductionInformationModal");
        var id = $("#updProductionInformationId").val();
        if(""==id){
            tipDialog("请输入编号",4,'a');
            Loading(false,"","#updateProductionInformationModal");
            return false;
        }else {
            $("#updProductionInformationId").val(id.replace(/,/gi,''));
        }
        var productionInformationName = $("#updProductionInformationName").val();
        if(""==productionInformationName){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#updateProductionInformationModal");
            return false;
        }
        var remark = $("#updProductionInformationRemark").val();
        if(""==remark){
            tipDialog("请输入文字描述",4,'a');
            Loading(false,"","#updateProductionInformationModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateProductionInformation.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable5").trigger("reloadGrid");
                    $("#updateProductionInformationModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateProductionInformationModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateProductionInformationModal");
            }
        };
        $('#updateProductionInformation').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //查看生产控制点图片
    <@shiro.hasPermission name="/traceability/productionInformationImage">
    function viewProductionInformation() {
        var id = GetJqGridRowValue("#gridTable5", "productionInformationId");
        let image = GetJqGridRowValue("#gridTable5", "images");
        if(image=="未上传" ){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }
        if(id!=null){
            $("#viewProductionInformationModal").modal({
                remote: "${request.contextPath}/traceability/viewProductionInformationModal.htm?id="+id
            });}else{
            tipDialog("请选择您要查看图片的列",4,"warning");
        }
    }
    </@shiro.hasPermission>
    //删除生产控制点
    <@shiro.hasPermission name="/traceability/deleteProductionInformation">
    function deleteProductionInformation() {
        var id = GetJqGridRowValue("#gridTable5", "productionInformationId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteProductionInformationModal").modal("show");
        }
    }
    $("#deleteProductionInformation").unbind("click");
    $("#deleteProductionInformation").click(function () {
        var productionInformationId = GetJqGridRowValue("#gridTable5", "productionInformationId");
        Loading(true,"正在删除...", "#deleteProductionInformationModal");
        $.post("${request.contextPath}/traceability/delectProductionInformation.json", {productionInformationId: productionInformationId, resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable5").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteProductionInformationModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteProductionInformationModal");
        }, "json");
    });
    </@shiro.hasPermission>
    //新增晓芹厨房
    <@shiro.hasPermission name="/traceability/addXqKitchen">
    function addXqKitchen() {
        $("#addXqKitchenModal").modal({
            remote: "${request.contextPath}/traceability/addXqKitchenModal.htm?resourceId="+resourceId
        });
    }
    $("#addXqKitchen1").unbind("click");
    $("#addXqKitchen1").click(function () {
        Loading(true,"正在提交...","#addXqKitchenModal");
        var kitchenName = $("#kitchenName").val();
        if(""==kitchenName){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#addXqKitchenModal");
            return false;
        }
        var features = $("#features").val();
        if(""==features){
            tipDialog("请输入特点",4,'a');
            Loading(false,"","#addXqKitchenModal");
            return false;
        }
        var ingredients = $("#ingredients").val();
        if(""==ingredients){
            tipDialog("请输入配料",4,'a');
            Loading(false,"","#addXqKitchenModal");
            return false;
        }
        var productionMethod = $("#productionMethod").val();
        if(""==productionMethod){
            tipDialog("请输入制作方法",4,'a');
            Loading(false,"","#addXqKitchenModal");
            return false;
        }

        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/addXqKitchen.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable4").trigger("reloadGrid");
                    $("#addXqKitchenModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addXqKitchenModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addXqKitchenModal");
            }
        };
        $('#addXqKitchen').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //编辑晓芹厨房
    <@shiro.hasPermission name="/traceability/updateXqKitchen">
    function upXqKitchen() {
        var id = GetJqGridRowValue("#gridTable4", "kitchenId");
        if(null != id){
            $("#updateXqKitchenModal").modal({
                remote: "${request.contextPath}/traceability/updateXqKitchenModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateXqKitchen1").unbind("click");
    $("#updateXqKitchen1").click(function () {
        Loading(true,"正在提交...","#updateXqKitchenModal");
        var kitchenName = $("#updKitchenName").val();
        if(""==kitchenName){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#updateXqKitchenModal");
            return false;
        }
        var features = $("#updFeatures").val();
        if(""==features){
            tipDialog("请输入特点",4,'a');
            Loading(false,"","#updateXqKitchenModal");
            return false;
        }
        var ingredients = $("#updIngredients").val();
        if(""==ingredients){
            tipDialog("请输入配料",4,'a');
            Loading(false,"","#updateXqKitchenModal");
            return false;
        }
        var productionMethod = $("#updProductionMethod").val();
        if(""==productionMethod){
            tipDialog("请输入制作方法",4,'a');
            Loading(false,"","#updateXqKitchenModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateXqKitchen.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable4").trigger("reloadGrid");
                    $("#updateXqKitchenModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateXqKitchenModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateXqKitchenModal");
            }
        };
        $('#updateXqKitchenForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //查看晓芹厨房图片
    <@shiro.hasPermission name="/traceability/xqKitchenImage">
    function viewXqKitchen() {
        let image = GetJqGridRowValue("#gridTable4", "isHaveImage");
        if(image=="未上传" ){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }else {
            var id = GetJqGridRowValue("#gridTable4", "kitchenId");
            if(id!=null){
                $("#viewXqKitchenModal").modal({
                    remote: "${request.contextPath}/traceability/viewXqKitchenModal.htm?id="+id
                });
            }else{
                tipDialog("请选择您要查看图片的列",4,"warning");
            }
        }
    }
    </@shiro.hasPermission>
    //删除晓芹厨房
    <@shiro.hasPermission name="/traceability/deleteXqKitchen">
    function deleteXqKitchen() {
        var id = GetJqGridRowValue("#gridTable4", "kitchenId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteXqKitchenModal").modal("show");
        }
    }
    $("#deleteXqKitchen").unbind("click");
    $("#deleteXqKitchen").click(function () {
        var kitchenId = GetJqGridRowValue("#gridTable4", "kitchenId");
        Loading(true,"正在删除...","#deleteXqKitchenModal");
        $.post("${request.contextPath}/traceability/delectXqKitchen.json", {kitchenId: kitchenId, resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable4").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteXqKitchenModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteXqKitchenModal");
        }, "json");
    });
    </@shiro.hasPermission>
    //新增检测设备
    <@shiro.hasPermission name="/traceability/addTestingEquipment">
    function addTestingEquipment() {
        $("#addTestingEquipmentModal").modal({
            remote: "${request.contextPath}/traceability/addTestingEquipmentModal.htm?resourceId="+resourceId
        });
    }
    $("#addTestingEquipment").unbind("click");
    $("#addTestingEquipment").click(function () {
        Loading(true,"正在提交...","#addTestingEquipmentModal");
        var enterpriseId = $("#enterpriseId").val();
        if(""==enterpriseId){
            tipDialog("请选择检验室",4,'a');
            Loading(false,"","#addTestingEquipmentModal");
            return false;
        }
        var id = $("#testEqId").val();
        if(""==id){
            tipDialog("请输入编号",4,'a');
            Loading(false,"","#addTestingEquipmentModal");
            return false;
        }
        var remark = $("#testEqRemark").val();
        if(""==remark){
            tipDialog("请输入文字描述",4,'a');
            Loading(false);
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/addTestingEquipment.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable7").trigger("reloadGrid");
                    $("#addTestingEquipmentModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addTestingEquipmentModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addTestingEquipmentModal");
            }
        };
        $('#addTestingEquipmentForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //编辑检测设备
    <@shiro.hasPermission name="/traceability/upTestingEquipment">
    function upTestingEquipment() {
        var id = GetJqGridRowValue("#gridTable7", "testingEquipmentId");
        if(null != id){
            $("#updateTestingEquipmentModal").modal({
                remote: "${request.contextPath}/traceability/updateTestingEquipmentModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateTestingEquipment").unbind("click");
    $("#updateTestingEquipment").click(function () {
        Loading(true,"正在提交...","#updateTestingEquipmentModal");
        var enterpriseId = $("#updEnterpriseId").val();
        if(""==enterpriseId){
            tipDialog("请选择检验室",4,'a');
            Loading(false,"","#updateTestingEquipmentModal");
            return false;
        }
        var id = $("#updTestEqId").val();
        if(""==id){
            tipDialog("请输入编号",4,'a');
            Loading(false,"","#updateTestingEquipmentModal");
            return false;
        }else {
            $("#updTestEqId").val(id.replace(/,/gi,''));
        }
        var remark = $("#updTestEqRemark").val();
        if(""==remark){
            tipDialog("请输入文字描述",4,'a');
            Loading(false,"","#updateTestingEquipmentModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateTestingEquipment.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable7").trigger("reloadGrid");
                    $("#updateTestingEquipmentModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateTestingEquipmentModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateTestingEquipmentModal");
            }
        };
        $('#updateTestingEquipmentForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //删除检测设备
    <@shiro.hasPermission name="/traceability/deleteTestingEquipment">
    function deleteTestingEquipment() {
        var id = GetJqGridRowValue("#gridTable7", "testingEquipmentId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteTestingEquipmentModal").modal("show");
        }
    }
    $("#deleteTestingEquipment").unbind("click");
    $("#deleteTestingEquipment").click(function () {
        var testingEquipmentId = GetJqGridRowValue("#gridTable7", "testingEquipmentId");
        Loading(true,"正在删除...","#deleteTestingEquipmentModal");
        $.post("${request.contextPath}/traceability/delectTestingEquipment.json", {testingEquipmentId: testingEquipmentId, resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable7").trigger("reloadGrid");
                $("#gridTable8").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteTestingEquipmentModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
            }
            Loading(false,"","#deleteTestingEquipmentModal");
        }, "json");
    });
    </@shiro.hasPermission>
    //查看检测设备图片
    <@shiro.hasPermission name="/traceability/testingEquipmentImage">
    function viewTestingEquipment() {
        var id = GetJqGridRowValue("#gridTable7", "testingEquipmentId");
        let image = GetJqGridRowValue("#gridTable7", "images");
        if(image=="未上传" ){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }
        if(id!=null){
            $("#viewTestingEquipmentModal").modal({
                remote: "${request.contextPath}/traceability/viewTestingEquipmentModal.htm?id="+id
            });}else{
            tipDialog("请选择您要查看图片的列",4,"warning");
        }
    }
    </@shiro.hasPermission>
    //新增检测设备内容
    <@shiro.hasPermission name="/traceability/testingEquipmentAddTestingTestingEquipmentDetail">
    function addTesting() {
        var id = GetJqGridRowValue("#gridTable7", "testingEquipmentId");
        if(id!=null){
            $("#updateaddTestingModal").modal({
                remote: "${request.contextPath}/traceability/updateAddTestingEquipmentDetailModal.htm?testingEquipmentId=" +id+"&resourceId="+resourceId
            });
        }else{
            tipDialog("请选择您要新增内容的列",4,"warning");
        }
    }
    $("#updateaddTesting").unbind("click");
    $("#updateaddTesting").click(function () {
        Loading(true,"正在提交...","#updateaddTestingModal");
        var detailTestingEquipmentId = $("#detailTestingEquipmentId").val();
        if(""==detailTestingEquipmentId){
            tipDialog("数据异常",4,"warning");
            Loading(false,"","#updateaddTestingModal");
            return false;
        }
        var testingEquipmentDetailCname = $("#testingEquipmentDetailCname").val();
        if(""==testingEquipmentDetailCname){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#updateaddTestingModal");
            return false;
        }
        var remark = $("#updAddEqRemark").val();
        if(""==remark){
            tipDialog("请输入内容",4,'a');
            Loading(false,"","#updateaddTestingModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateAddTestingEquipmentDetail.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable8").trigger("reloadGrid");
                    $("#updateaddTestingModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateaddTestingModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateaddTestingModal");
            }
        };
        $('#updateAddTestingEquipmentDetailForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //编辑检测设备详细内容
    <@shiro.hasPermission name="/traceability/updateTestingEquipmentDetail">
    function upTestingEquipmentDetail() {
        var id = GetJqGridRowValue("#gridTable8", "testingEquipmentDetailId");
        if(null != id){
            $("#updateTestingEquipmentDetailModal").modal({
                remote: "${request.contextPath}/traceability/updateTestingEquipmentDetailModal.htm?id="+id+"&resourceId="+resourceId
            });
        }else {
            tipDialog("请选择您要修改的列",4,"warning");
        }
    }
    $("#updateTestingEquipmentDetail").unbind("click");
    $("#updateTestingEquipmentDetail").click(function () {
        Loading(true,"正在提交...","#updateTestingEquipmentDetailModal");
        var testingEquipmentDetailCname = $("#updTestingEquipmentDetailCname").val();
        if(""==testingEquipmentDetailCname){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#updateTestingEquipmentDetailModal");
            return false;
        }
        var remark = $("#updTestEqDetailRemark").val();
        if(""==remark){
            tipDialog("请输入文字描述",4,'a');
            Loading(false,"","#updateTestingEquipmentDetailModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateTestingEquipmentDetail.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable8").trigger("reloadGrid");
                    $("#updateTestingEquipmentDetailModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#updateTestingEquipmentDetailModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#updateTestingEquipmentDetailModal");
            }
        };
        $('#updateTestingEquipmentDetailForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>
    //删除检测设备详细内容
    <@shiro.hasPermission name="/traceability/deleteTestingEquipmentDetail">
    function deleteTestingEquipmentDetail() {
        var id = GetJqGridRowValue("#gridTable8", "testingEquipmentDetailId");
        if (null == id) {
            tipDialog("请选择您要删除的记录", 4, "warning");
        } else {
            $("#deleteTestingEquipmentDetailModal").modal("show");
        }
    }
    $("#deleteTestingEquipmentDetail").unbind("click");
    $("#deleteTestingEquipmentDetail").click(function () {
        var testingEquipmentDetailId = GetJqGridRowValue("#gridTable8", "testingEquipmentDetailId");
        Loading(true,"正在删除...","#deleteTestingEquipmentDetailModal")
        $.post("${request.contextPath}/traceability/delectTestingEquipmentDetail.json", {testingEquipmentDetailId: testingEquipmentDetailId,resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable8").trigger("reloadGrid");
                tipDialog(res.msg, 4, "warning");
                $("#deleteTestingEquipmentDetailModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, "warning");
                $("#deleteTestingEquipmentDetailModal").modal("hide");
            }
            Loading(false,"","#deleteTestingEquipmentDetailModal");
        }, "json");
    });
    </@shiro.hasPermission>
    //查看检测设备详细内容图片
    <@shiro.hasPermission name="/traceability/testingEquipmentDetailImage">
    function viewTestingEquipmentDetail() {
        var id = GetJqGridRowValue("#gridTable8", "testingEquipmentDetailId");
        let image = GetJqGridRowValue("#gridTable8", "images");
        if(image=="未上传" ){
            tipDialog("请上传图片在查看！",4,"warning");
            return false;
        }
        if(id!=null){
            $("#viewTestingEquipmentDetailModal").modal({
                remote: "${request.contextPath}/traceability/viewTestingEquipmentDetailModal.htm?id="+id
            });}else{
            tipDialog("请选择您要查看图片的列",4,"warning");
        }
    }
    </@shiro.hasPermission>
    //新增检测设备内容
    <@shiro.hasPermission name="/traceability/addTestingEquipmentDetail">
    function addTestingDetail() {
        var id = GetJqGridRowValue("#gridTable8", "testingEquipmentDetailId");
        if(id!=null){
            $("#addTestingEquipmentDetailModal").modal({
                remote: "${request.contextPath}/traceability/addTestingEquipmentDetailModal.htm?id=" +id+"&resourceId="+resourceId
            });
        }else{
            tipDialog("请选择您要新增内容的列",4,"warning");
        }
    }
    $("#addTestingEquipmentDetail").unbind("click");
    $("#addTestingEquipmentDetail").click(function () {
        Loading(true,"正在提交...","#addTestingEquipmentDetailModal");
        var testingEquipmentDetailCname = $("#addTestingEquipmentDetailCname").val();
        if(""==testingEquipmentDetailCname){
            tipDialog("请输入名称",4,'a');
            Loading(false,"","#addTestingEquipmentDetailModal");
            return false;
        }
        var remark = $("#testEqDetailRemark").val();
        if(""==remark){
            tipDialog("请输入内容",4,'a');
            Loading(false,"","#addTestingEquipmentDetailModal");
            return false;
        }
        var options = {
            type: "post",
            cache: false,
            url: '${request.contextPath}/traceability/updateAddTestingEquipmentDetail.json',
            success: function (res) {
                if (res.success) {
                    tipDialog(res.msg, 4, '1');
                    $("#gridTable8").trigger("reloadGrid");
                    $("#addTestingEquipmentDetailModal").modal("hide");
                }
                else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#addTestingEquipmentDetailModal");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                tipDialog("请求失败!", 4, '0');
                Loading(false,"","#addTestingEquipmentDetailModal");
            }
        };
        $('#addTestingEquipmentDetailForm').ajaxSubmit(options);
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/addCertificateManage">
    //    新建证书
        function addCertificateManage() {
            $("#addCertificateManageModal").modal({
                remote: "${request.contextPath}/traceability/addCertificateManageModal.htm?resourceId="+resourceId
            });
        }

        function addCertificateManageJson() {
            Loading(true,"正在提交数据...","#addCertificateManageModal");
            var bigProductId = $("#bigProductTypeId").val();
            if(""==bigProductId){
                $("#bigProductTypeId").focus();
                tipDialog("请选择产品大类",4,"warning");
                Loading(false,"","#addCertificateManageModal");
                return false;
            }
            var smallProductTypeId = $("#smallProductTypeId").val();
            if(""==smallProductTypeId){
                $("#smallProductTypeId").focus();
                tipDialog("请选择产品小类",4,"warning");
                Loading(false,"","#addCertificateManageModal");
                return false;
            }
            var productionLicense = $("#productionLicense").val();
            if(""==productionLicense){
                $("#productionLicense").focus();
                tipDialog("请输入生产许可证",4,"warning");
                Loading(false,"","#addCertificateManageModal");
                return false;
            }
            var productionProcessId = $("#productionProcessId").val();
            if(""==productionProcessId){
                $("#productionProcessId").focus();
                tipDialog("请选择产品生产过程",4,"warning");
                Loading(false,"","#addCertificateManageModal");
                return false;
            }
            var endDate = $("#endDate").val();
            if(""==endDate){
                $("#endDate").focus();
                tipDialog("请选择到期日",4,"warning");
                Loading(false,"","#addCertificateManageModal");
                return false;
            }

            var productStandards = $("#productStandards").val();
            if(""==productStandards){
                $("#productStandards").focus();
                tipDialog("请输入产品标准号",4,"warning");
                Loading(false,"","#addCertificateManageModal");
                return false;
            }

            var options = {
                type: "post",
                cache: false,
                url: '${request.contextPath}/traceability/addCertificateManage.json',
                success: function (res) {
                    if (res.success) {
                        tipDialog(res.msg, 4, '1');
                        $("#gridTable2").trigger("reloadGrid");
                        $("#addCertificateManageModal").modal("hide");
                    }
                    else {
                        tipDialog(res.msg, 4, '0');
                    }
                    Loading(false,"","#addCertificateManageModal");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.error());
                    tipDialog("请求失败!", 4, '0');
                    Loading(false,"","#addCertificateManageModal");
                }
            };
            $('#addCertificateManageForm').ajaxSubmit(options);
        }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/updateCertificateManage">
        //修改证书
        function updateCertificateManageModal() {
            var certificateManageId = GetJqGridRowValue("#gridTable2", "certificateManageId");
            if(null!=certificateManageId){
                $("#updateCertificateManageModal").modal({
                    remote: "${request.contextPath}/traceability/updateCertificateManageModal.htm?certificateManageId=" + certificateManageId+"&resourceId="+resourceId
                });
            }else {
                tipDialog("请选择您要修改的证书",4,"warning");
            }
        }

        function updateCertificateManage() {
            Loading(true,"正在提交数据...","#updateCertificateManageModal");
            var bigProductId = $("#updBigProductTypeId").val();
            if(""==bigProductId){
                $("#updBigProductTypeId").focus();
                tipDialog("请选择产品大类",4,"warning");
                Loading(false,"","#updateCertificateManageModal");
                return false;
            }
            var smallProductTypeId = $("#updSmallProductTypeId").val();
            if(""==smallProductTypeId){
                $("#updSmallProductTypeId").focus();
                tipDialog("请选择产品小类",4,"warning");
                Loading(false,"","#updateCertificateManageModal");
                return false;
            }
            var productionLicense = $("#updProductionLicense").val();
            if(""==productionLicense){
                $("#updProductionLicense").focus();
                tipDialog("请输入生产许可证",4,"warning");
                Loading(false,"","#updateCertificateManageModal");
                return false;
            }
            var productionProcessId = $("#updProductionProcessId").val();
            if(""==productionProcessId){
                $("#updProductionProcessId").focus();
                tipDialog("请选择产品生产过程",4,"warning");
                Loading(false,"","#updateCertificateManageModal");
                return false;
            }
            var endDate = $("#updEndDate").val();
            if(""==endDate){
                $("#updEndDate").focus();
                tipDialog("请选择到期日",4,"warning");
                Loading(false,"","#updateCertificateManageModal");
                return false;
            }

            var productStandards = $("#updProductStandards").val();
            if(""==productStandards){
                $("#updProductStandards").focus();
                tipDialog("请输入产品标准号",4,"warning");
                Loading(false,"","#updateCertificateManageModal");
                return false;
            }

            var options = {
                type: "post",
                cache: false,
                url: '${request.contextPath}/traceability/updateCertificateManage.json',
                success: function (res) {
                    if (res.success) {
                        tipDialog(res.msg, 4, '1');
                        $("#gridTable2").trigger("reloadGrid");
                        $("#updateCertificateManageModal").modal("hide");
                    }
                    else {
                        tipDialog(res.msg, 4, '0');
                    }
                    Loading(false,"","#updateCertificateManageModal");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.error());
                    tipDialog("请求失败!", 4, '0');
                    Loading(false,"","#updateCertificateManageModal");
                }
        };
        $('#updateCertificateManageForm').ajaxSubmit(options);
    }

    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/certificateManage">
        //查看证书
        function certificateManageDetailModal(certificateManageId,isHaveImage) {
//            if("1"==isHaveImage){
                if(""!=certificateManageId){
                    $("#certificateManageDetailModal").modal({
                        remote: "${request.contextPath}/traceability/certificateManageDetailModal.htm?certificateManageId=" + certificateManageId
                    });
                }else {
                    tipDialog("请选择您要查看的列",4,"warning");
                }
//            }else {
//                tipDialog("请先上传图片后再查看",4,"warning");
//            }
        }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/addCertificateManageContent">
    //    增加证书内容
        function addCertificateContent() {
            var certificateManageId = GetJqGridRowValue("#gridTable2", "certificateManageId");
            if(null!=certificateManageId){
                $("#addCertificateContentModal").modal({
                    remote: "${request.contextPath}/traceability/addCertificateContentModal.htm?certificateManageId=" + certificateManageId+"&resourceId="+resourceId
                });
            }else {
                tipDialog("请选择您要增加内容的证书",4,"warning");
            }
        }
        $("#addCertificateContent").unbind("click");
        $("#addCertificateContent").click(function () {
            Loading(true,"正在提交...","#addCertificateContentModal");
            $.post("${request.contextPath}/traceability/addCertificateContent.json", $("#addCertificateContentForm").serialize(), function (result) {
                if(result.success){
                    $("#gridTable").trigger("reloadGrid");
                    tipDialog(result.msg, 4, "1");
                    $("#addCertificateContentModal").modal("hide");
                }else {
                    tipDialog(result.msg, 4, '0');
                }
                Loading(false,"","#addCertificateContentModal");
            },"JSON");
        });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/deleteCertificateManage">
        //删除证书
        function deleteCertificateManage() {
            var certificateManageId = GetJqGridRowValue("#gridTable2", "certificateManageId");
            if(null!=certificateManageId){
                $("#deleteCertificateManageModal").modal("show");
            }else {
                tipDialog("请选择您要删除的列",4,"warning");
            }
        }

        $("#deleteCertificateManage").unbind("click");
        $("#deleteCertificateManage").click(function () {
            Loading(true,"正在提交...","#deleteCertificateManageModal");
            var certificateManageId = GetJqGridRowValue("#gridTable2", "certificateManageId");
            Loading(true,"正在删除...", "#deleteCertificateManageModal");
            $.post("${request.contextPath}/traceability/deleteCertificateManage.json", {certificateManageId: certificateManageId, resourceId: resourceId}, function (res) {
                if (res.success) {
                    $("#gridTable2").trigger("reloadGrid");
                    tipDialog(res.msg, 4, '1');
                    $("#deleteCertificateManageModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#deleteCertificateManageModal");
            }, "json");
        });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/addProductionProcess">
    //新增生产过程
        function addProductionProcess() {
            $("#addProductionProcessModal").modal({
                remote: "${request.contextPath}/traceability/addProductionProcessModal.htm?resourceId=" +resourceId
            });
        }

        $("#addProductionProcess").unbind("click");
        $("#addProductionProcess").click(function () {
            Loading(true,"正在提交...","#addProductionProcessModal");
            var productionProcessName = $("#productionProcessName").val();
            if(""==productionProcessName){
                $("#productionProcessName").focus();
                tipDialog("请输入生产过程名称",4,"warning");
                Loading(false,"","#addProductionProcessModal");
                return false;
            }
            $.post("${request.contextPath}/traceability/addProductionProcess.json", $("#addProductionProcessForm").serialize(), function (res) {
                    if(res.success){
                        $("#gridTable10").trigger("reloadGrid");
                        tipDialog(res.msg, 4, '1');
                        $("#addProductionProcessModal").modal('hide');
                    }else {
                        tipDialog(res.msg, 4, '0');
                    }
                    Loading(false,"","#addProductionProcessModal");
            }, "JSON");
        });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/updateProductionProcess">
    //编辑生产过程
    function updProductionProcess() {
        var productionProcessId = GetJqGridRowValue("#gridTable10", "productionProcessId");
        if(IsChecked(productionProcessId)){
            $("#updProductionProcessModal").modal({
                remote: "${request.contextPath}/traceability/updProductionProcessModal.htm?productionProcessId=" +productionProcessId+"&resourceId="+resourceId
            });
        }
    }

    $("#updProductionProcess").unbind("click");
    $("#updProductionProcess").click(function () {
        Loading(true,"正在提交...","#updProductionProcessModal");
        var productionProcessName = $("#updProductionProcessName").val();
        if(""==productionProcessName){
            $("#updProductionProcessName").focus();
            tipDialog("请输入生产过程名称",4,"warning");
            Loading(false,"","#updProductionProcessModal");
            return false;
        }
        $.post("${request.contextPath}/traceability/updProductionProcess.json", $("#updProductionProcessForm").serialize(), function (res) {
            if(res.success){
                $("#gridTable10").trigger("reloadGrid");
                $("#gridTable11").trigger("reloadGrid");
                tipDialog(res.msg, 4, '1');
                $("#updProductionProcessModal").modal('hide');
            }else {
                tipDialog(res.msg, 4, '0');
            }
            Loading(false,"","#updProductionProcessModal");
        }, "JSON");
    });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/deleteProductionProcess">
        //删除生产过程
        function deleteProductionProcess() {
            var productionProcessId = GetJqGridRowValue("#gridTable10", "productionProcessId");
            if(IsChecked(productionProcessId)){
                $("#deleteProductionProcessModal").modal("show");
            }
        }

        $("#deleteProductionProcess").unbind("click");
        $("#deleteProductionProcess").click(function () {
            var productionProcessId = GetJqGridRowValue("#gridTable10", "productionProcessId");
            Loading(true,"正在删除...", "#deleteProductionProcessModal");
            $.post("${request.contextPath}/traceability/deleteProductionProcess.json", {productionProcessId: productionProcessId,resourceId: resourceId}, function (res) {
                if (res.success) {
                    $("#gridTable10").trigger("reloadGrid");
                    $("#gridTable11").trigger("reloadGrid");
                    tipDialog(res.msg, 4, '1');
                    $("#deleteProductionProcessModal").modal("hide");
                } else {
                    tipDialog(res.msg, 4, '0');
                }
                Loading(false,"","#deleteProductionProcessModal");
            }, "JSON");
        });
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/productionProcessAddProductionProcessDetail">
    //增加详情
        function addProductionProcessDetail() {
            var productionProcessId = GetJqGridRowValue("#gridTable10", "productionProcessId");
            if(IsChecked(productionProcessId)){
                $("#addProductionProcessDetailModal").modal({
                    remote: "${request.contextPath}/traceability/addProductionProcessDetailModal.htm?productionProcessId=" +productionProcessId+"&resourceId="+resourceId
                });
            }
        }
        
        function addProductionProcessDetailJson() {
            Loading(true,"正在提交...","#addProductionProcessDetailModal");
            var productionProcessDetailNumber = $("#productionProcessDetailNumber").val();
            if(""==productionProcessDetailNumber){
                $("#productionProcessDetailNumber").focus();
                tipDialog("请输入编号",4,"warning");
                Loading(false,"","#addProductionProcessDetailModal");
                return false;
            }
            var productionProcessDetailName = $("#productionProcessDetailName").val();
            if(""==productionProcessDetailName){
                $("#productionProcessDetailName").focus();
                tipDialog("请输入过程详情名称",4,"warning");
                Loading(false,"","#addProductionProcessDetailModal");
                return false;
            }
            var options = {
                type: "post",
                cache: false,
                url: '${request.contextPath}/traceability/addProductionProcessDetail.json',
                success: function (res) {
                    if (res.success) {
                        tipDialog(res.msg, 4, '1');
                        $("#gridTable10").trigger("reloadGrid");
                        $("#gridTable11").trigger("reloadGrid");
                        $("#addProductionProcessDetailModal").modal("hide");
                    }
                    else {
                        tipDialog(res.msg, 4, '0');
                    }
                    Loading(false,"","#addProductionProcessDetailModal");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.error());
                    tipDialog("请求失败!", 4, '0');
                    Loading(false,"","#addProductionProcessDetailModal");
                }
            };
            $('#addProductionProcessDetailForm').ajaxSubmit(options);
        }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/updateProductionProcessDetail">
    //编辑生产过程详情
        function updateProductionProcessDetail() {
            var productionProcessDetailedId = GetJqGridRowValue("#gridTable11", "productionProcessDetailedId");
            if(IsChecked(productionProcessDetailedId)){
                $("#updateProductionProcessDetailModal").modal({
                    remote: "${request.contextPath}/traceability/updateProductionProcessDetailModal.htm?productionProcessDetailedId=" +productionProcessDetailedId+"&resourceId="+resourceId
                });
            }
        }
        function updateProductionProcessDetailJson() {
            Loading(true,"正在提交...","#addProductionProcessDetailModal");
            var productionProcessDetailNumber = $("#updProductionProcessDetailNumber").val();
            if(""==productionProcessDetailNumber){
                $("#updProductionProcessDetailNumber").focus();
                tipDialog("请输入编号",4,"warning");
                Loading(false,"","#updateProductionProcessDetailModal");
                return false;
            }
//            else {
//                $("#updProductionProcessDetailNumber").val(productionProcessDetailNumber.replace(/,/gi,''));
//            }
            var productionProcessDetailName = $("#updProductionProcessDetailName").val();
            if(""==productionProcessDetailName){
                $("#updProductionProcessDetailName").focus();
                tipDialog("请输入过程详情名称",4,"warning");
                Loading(false,"","#updateProductionProcessDetailModal");
                return false;
            }
            var options = {
                type: "post",
                cache: false,
                url: '${request.contextPath}/traceability/updateProductionProcessDetail.json',
                success: function (res) {
                    if (res.success) {
                        tipDialog(res.msg, 4, '1');
                        $("#gridTable11").trigger("reloadGrid");
                        $("#updateProductionProcessDetailModal").modal("hide");
                    }
                    else {
                        tipDialog(res.msg, 4, '0');
                    }
                    Loading(false,"","#updateProductionProcessDetailModal");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.error());
                    tipDialog("请求失败!", 4, '0');
                    Loading(false,"","#updateProductionProcessDetailModal");
                }
            };
            $('#updateProductionProcessDetailForm').ajaxSubmit(options);
        }

    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/deleteProductionProcessDetail">
        function deleteProductionProcessDetail() {
                var productionProcessDetailedId = GetJqGridRowValue("#gridTable11", "productionProcessDetailedId");
                if(IsChecked(productionProcessDetailedId)){
                    $("#deleteProductionProcessDetailModal").modal("show");
                }
        }

    $("#deleteProductionProcessDetail").unbind("click");
    $("#deleteProductionProcessDetail").click(function () {
        var productionProcessDetailedId = GetJqGridRowValue("#gridTable11", "productionProcessDetailedId");
        Loading(true,"正在删除...", "#deleteProductionProcessDetailModal");
        $.post("${request.contextPath}/traceability/deleteProductionProcessDetail.json", {productionProcessDetailedId: productionProcessDetailedId, resourceId: resourceId}, function (res) {
            if (res.success) {
                $("#gridTable11").trigger("reloadGrid");
                tipDialog(res.msg, 4, '1');
                $("#deleteProductionProcessDetailModal").modal("hide");
            } else {
                tipDialog(res.msg, 4, '0');
            }
            Loading(false,"","#deleteProductionProcessDetailModal");
        }, "JSON");
    });

    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/productionProcessImage">
    //生产过程详情查看图片
        function viewProductionProcess() {
                var productionProcessDetailedId = GetJqGridRowValue("#gridTable11", "productionProcessDetailedId");
                if(IsChecked(productionProcessDetailedId)){
                    var detailImage = GetJqGridRowValue("#gridTable11", "detailImage");
                    if(detailImage=="已上传"){
                        $("#viewProductionProcessDetailModal").modal({
                            remote: "${request.contextPath}/traceability/viewProductionProcessDetailModal.htm?productionProcessDetailedId=" +productionProcessDetailedId
                        });
                    }else {
                        tipDialog("请先上传图片后再查看",4,"warning");
                    }
                }
        }
    </@shiro.hasPermission>

    <@shiro.hasPermission name="/traceability/addProductionProcessDetail">
        function addProductionProcessDetailTwo() {
            $("#addProductionProcessDetailModalTwo").modal({
                remote: "${request.contextPath}/traceability/addProductionProcessDetailTwoModal.htm?resourceId="+resourceId
            });
        }
        
        function addProductionProcessDetailJsonTwo() {
            Loading(true,"正在提交...","#addProductionProcessDetailModalTwo");
            var productionProcessDetailNumber = $("#addProductionProcessDetailNumber2").val();
            if(""==productionProcessDetailNumber){
                $("#addProductionProcessDetailNumber2").focus();
                tipDialog("请输入编号",4,"warning");
                Loading(false,"","#addProductionProcessDetailModalTwo");
                return false;
            }
            var productionProcessId = $("#addProductionProcessId2").val();
            if(""==productionProcessId){
                tipDialog("请选择生产过程",4,"warning");
                Loading(false,"","#addProductionProcessDetailModalTwo");
                return false;
            }
            var productionProcessDetailName = $("#addProductionProcessDetailName2").val();
            if(""==productionProcessDetailName){
                $("#addProductionProcessDetailName2").focus();
                tipDialog("请输入过程详情名称",4,"warning");
                Loading(false,"","#addProductionProcessDetailModalTwo");
                return false;
            }
            var options = {
                type: "post",
                cache: false,
                url: '${request.contextPath}/traceability/addProductionProcessDetail.json',
                success: function (res) {
                    if (res.success) {
                        tipDialog(res.msg, 4, '1');
                        $("#gridTable10").trigger("reloadGrid");
                        $("#gridTable11").trigger("reloadGrid");
                        $("#addProductionProcessDetailModalTwo").modal("hide");
                    }
                    else {
                        tipDialog(res.msg, 4, '0');
                    }
                    Loading(false,"","#addProductionProcessDetailModalTwo");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.error());
                    tipDialog("请求失败!", 4, '0');
                    Loading(false,"","#addProductionProcessDetailModalTwo");
                }
            };
            $('#addProductionProcessDetailFormTwo').ajaxSubmit(options);
        }
    </@shiro.hasPermission>

</script>