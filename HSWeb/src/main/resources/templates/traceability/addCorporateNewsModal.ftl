<#--新增企业新闻-->
<form id="addCorporateNewsForm" style="margin: 1px">
    <input type="hidden" name="resourceId" value="${resourceId}">
    <table class="form">
        <tr>
            <th class="formTitle">标题：
            </th>
            <td class="formValue">
                <input class="txt required" id="newsTitle" type="text" name="title"/>
            </td>
        </tr>
        <tr>
            <th class="formTitle">链接：
            </th>
            <td class="formValue">
                <input class="txt required" id="newsLink" type="text" name="link" />
            </td>
        </tr>
        <tr>
            <th class="formTitle">时间：
            </th>
            <td class="formValue">
                <input id="corporateNewsDate"  name="corporateNewsDate" type="text" class="txt Wdate" style="width: 100%; "
                        onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
    </table>
    <br/>
    <div class="col-md-12">
        <p><span class="line"></span>上传图片<span class="line"></span></p>
        <div class="image">
            <div class="fileinput fileinput-new fileinputDiv" data-provides="fileinput">
                <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 170px; height: 160px;">
                <img src=""/>
                </div>
                <div>
                        <span class="btn green-jungle btn-outline btn-file">
                                <span class="fileinput-new"> 添加 </span>
                                <span class="fileinput-exists"> 修改 </span>
                                <input type="file" name="images"/>
                        </span>
                    <a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> 删除 </a>
                </div>
            </div>
        </div>
    </div>
</form>
