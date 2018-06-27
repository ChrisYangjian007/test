<form id="exportDataForm" style="margin: 1px">
    <table class="form">
        <tr>
            <td class="formValue" style="text-align: center;font-size: 18px;">
                *开始时间
            </td>
            <td class="formValue">
                <input id="exportBeginTime" name="beginTime" type="text" class="txt Wdate" style="width: 100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
        <tr>
            <td class="formValue" style="text-align: center;font-size: 18px;">
                *截止时间
            </td>
            <td class="formValue">
                <input id="exportEndTime" name="endTime" type="text" class="txt Wdate" style="width:100%; "
                       onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </td>
        </tr>
    </table>
</form>
