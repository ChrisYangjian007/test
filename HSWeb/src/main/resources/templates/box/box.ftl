<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>晓芹食品追溯箱码信息查询</title>
    <style>
        body{
            font-size: 16px;
            width: 100%;
            height: 100%;
            margin: 0 auto;
            padding: 0;
            background-color: #f4f4f4;
        }
        #xmk{
            margin: 2rem auto;
            width: 80%;
            border-radius: 20px;
            padding: 2rem 4rem;
            background-color: #ffffff;
        }
        #xmTitle{
            font-size: 32px;
            font-weight: 700;
            border-left: 5px solid red;
            padding-left: 1rem;
            margin-left:-1.5rem;
        }
        #xmInput{
            border: 0px;
            border-radius: 5rem;
            background-color: #f4f4f4;
            width: 92%;
            height: 5rem;
            outline: none;
            -webkit-appearance: none;
            margin: 3rem auto;
            font-size: 38px;
            padding: 0 2rem;
        }
        #xmTable{
            background-color: #faf1f1;
            width: 100%;
            margin-bottom: 2rem;
        }
        .cp>td{
            padding: 1rem 0 1rem 2rem;
        }
        .cpName{
            color: #a39797;
            font-size: 32px;
            width: 85%;
        }
        .cpNum{
            font-size: 32px;
            width: 15%;
            color: #ff4b58;
        }
        #inputBox{
            position: relative;
        }
        #chahao{
            float: right;
            right: 2%;
            top:35%;
            position: absolute;
        }

    </style>
</head>
<body>
<div id="xmk">
    <p id="xmTitle">箱码</p>
    <div id="inputBox">
        <input id="xmInput" type="text" disabled <#if boxOrder??> value="${saOrderPDA.boxOrder.boxCode}"</#if>>
    </div>
    <table id="xmTable">
        <#if saOrderPDA.orderDetailPDAList??>
            <#list saOrderPDA.orderDetailPDAList as detail>
                <tr class="cp">
                    <td class="cpName">${detail.productName}</td>
                    <td class="cpNum">${detail.number}${detail.orderDetailDw}</td>
                </tr>
            </#list>
        </#if>
    </table>
</div>
</body>
</html>