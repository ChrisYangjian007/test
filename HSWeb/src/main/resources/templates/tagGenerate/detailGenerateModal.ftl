
<table id="gridTable1"></table>
<div id="gridPager1"></div>



<script>
    let endNo = "${sweep.endNo}";
    endNo = parseInt(endNo.replace(/,/g,""));
    let startNo = "${sweep.startNo}";
    startNo = parseInt(startNo.replace(/,/g,""));
    GetGrid1(${sweep.ruleName},startNo,endNo);
    gridPagerStyle1();
</script>