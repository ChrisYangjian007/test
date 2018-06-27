package com.dalian.sea.ExcelUtil;

import com.alibaba.fastjson.JSON;
import com.dalian.sea.json.ImageJson;
import com.dalian.sea.model.ReturnGoods;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xintao on 2017/8/31.
 */
public class ExcelUtil {

    public static void excel(String title, List<String> name, List<List<?>> view, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(new Date());
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();

        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(title);

        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int r = 0; r < name.size(); r++) {
            sheet.setColumnWidth((short) r, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());/*
        f.setColor(IndexedColors.RED.getIndex());*/
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);/*
        f2.setColor(IndexedColors.BLACK.getIndex());
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);*/

        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        // cs.setDataFormat(df.getFormat("#,##0.0"));

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        // cs2.setDataFormat(df.getFormat("text"));
        Cell cell;
        // 创建列名（每行里的单元格）
        for (int r = 0; r < name.size(); r++) {
            cell = row.createCell(r);
            cell.setCellValue(name.get(r));
            cell.setCellStyle(cs);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for (short i = 0; i < view.get(0).size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            row = sheet.createRow((short) i + 1);
            // 在row行上创建一个方格
            for (int v = 0; v < name.size(); v++) {
                cell = row.createCell(v);
                cell.setCellValue(view.get(v).get(i).toString());
                cell.setCellStyle(cs2);
            }
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((title + "-" + date + ".xlsx").getBytes(), "iso-8859-1"));

        ServletOutputStream out = response.getOutputStream();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

    }

    public static void xsl(String title, List<String> name, List<List<?>> view, HttpServletRequest request, HttpServletResponse response) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(new Date());

        // 创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();


        // 创建第一个sheet（页），并命名
        Sheet sheet = workbook.createSheet(title);


        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for (int r = 0; r < name.size(); r++) {
            sheet.setColumnWidth((short) r, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = workbook.createCellStyle();
        CellStyle cs2 = workbook.createCellStyle();

        // 创建两种字体
        Font f = workbook.createFont();
        Font f2 = workbook.createFont();

        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());/*
        f.setColor(IndexedColors.RED.getIndex());*/
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);/*
        f2.setColor(IndexedColors.BLACK.getIndex());
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);*/

        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        // cs.setDataFormat(df.getFormat("#,##0.0"));

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        // cs2.setDataFormat(df.getFormat("text"));
        Cell cell;
        // 创建列名（每行里的单元格）
        for (int r = 0; r < name.size(); r++) {
            cell = row.createCell(r);
            cell.setCellValue(name.get(r));
            cell.setCellStyle(cs);
        }
        for (short i = 0; i < view.get(0).size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            row = sheet.createRow((short) i + 1);
            // 在row行上创建一个方格
            for (int v = 0; v < name.size(); v++) {
                cell = row.createCell(v);
                cell.setCellValue(view.get(v).get(i).toString());
                cell.setCellStyle(cs2);
            }
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((title + "-" + date + ".xls").getBytes(), "iso-8859-1"));

        ServletOutputStream out = response.getOutputStream();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

    }

    public static void xslPro(String titleExcel, List<String> titles, List<List<String>> names, List<List<List<?>>> views, HttpServletRequest request, HttpServletResponse response) throws IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String date = formatter.format(new Date());

        // 创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 创建两种单元格格式
        CellStyle cs = workbook.createCellStyle();
        CellStyle cs2 = workbook.createCellStyle();

        // 创建两种字体
        Font f = workbook.createFont();
        Font f2 = workbook.createFont();
        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);
        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);

        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            List<String> name = names.get(i);
            List<List<?>> view = views.get(i);

            // 创建sheet（页），并命名
            Sheet sheet = workbook.createSheet(title);

            // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
            for (int r = 0; r < name.size(); r++) {
                sheet.setColumnWidth((short) r, (short) (35.7 * 150));
            }
            // 创建第一行
            Row row = sheet.createRow((short) 0);
            Cell cell;
            // 创建列名（每行里的单元格）
            for (int r = 0; r < name.size(); r++) {
                cell = row.createCell(r);
                cell.setCellValue(name.get(r));
                cell.setCellStyle(cs);
            }
            if (view.size() > 0) {
                for (short j = 0; j < view.get(0).size(); j++) {
                    // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                    // 创建一行，在页sheet上
                    row = sheet.createRow((short) j + 1);
                    // 在row行上创建一个方格
                    for (int v = 0; v < name.size(); v++) {
                        cell = row.createCell(v);
                        cell.setCellValue(view.get(v).get(j).toString());
                        cell.setCellStyle(cs2);
                    }
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((titleExcel + "-" + date + ".xls").getBytes(), "iso-8859-1"));

        ServletOutputStream out = response.getOutputStream();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

    }

    public static void returnGoodsExport(ReturnGoods re, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //日期格式化
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormatter.format(new Date());
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet("返货处理表");
        // 设置字体大小
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        //自动换行
        style.setWrapText(true);

        //创建第一行
        Row row = sheet.createRow((short) 0);
        Cell c0 = row.createCell((short) 0);
        c0.setCellStyle(style);
        c0.setCellValue("产品名称");
        Cell c1 = row.createCell((short) 2);
        c1.setCellStyle(style);
        //产品名称
        if (null != re.getProductName()) {
            c1.setCellValue(re.getProductName());
        } else {
            c1.setCellValue("");
        }
        Cell c2 = row.createCell((short) 4);
        c2.setCellStyle(style);
        c2.setCellValue("顾客姓名");
        Cell c3 = row.createCell((short) 6);
        c3.setCellStyle(style);
        //顾客姓名
        if (null != re.getCustomerName()) {
            c3.setCellValue(re.getCustomerName());
        } else {
            c3.setCellValue("");
        }
        Cell c4 = row.createCell((short) 8);
        c4.setCellStyle(style);
        c4.setCellValue("提货日期");
        Cell c5 = row.createCell((short) 10);
        c5.setCellStyle(style);
        if (null != re.getTakeDate()) {
            c5.setCellValue(dateFormatter.format(re.getTakeDate()));
        } else {
            c5.setCellValue("");
        }
        Cell c6 = row.createCell((short) 12);
        c6.setCellStyle(style);
        c6.setCellValue("卡号");
        Cell c7 = row.createCell((short) 14);
        c7.setCellStyle(style);
        if (null != re.getCardNumber()) {
            c7.setCellValue(re.getCardNumber());
        } else {
            c7.setCellValue("");
        }

        //第二行
        Row row1 = sheet.createRow((short) 2);
        Cell c8 = row1.createCell((short) 0);
        c8.setCellStyle(style);
        c8.setCellValue("顾客地址");
        Cell c9 = row1.createCell((short) 2);
        c9.setCellStyle(style);
        if (null != re.getCustomerAddress()) {
            c9.setCellValue(re.getCustomerAddress());
        } else {
            c9.setCellValue("");
        }
        Cell c10 = row1.createCell((short) 8);
        c10.setCellStyle(style);
        c10.setCellValue("返货日期");
        Cell c11 = row1.createCell((short) 10);
        c11.setCellStyle(style);
        if (null != re.getReturnDate()) {
            c11.setCellValue(dateFormatter.format(re.getReturnDate()));
        } else {
            c11.setCellValue("");
        }
        Cell c12 = row1.createCell((short) 12);
        c12.setCellStyle(style);
        c12.setCellValue("快递单号");
        Cell c13 = row1.createCell((short) 14);
        c13.setCellStyle(style);
        if (null != re.getExpressNumber()) {
            c13.setCellValue(re.getExpressNumber());
        } else {
            c13.setCellValue("");
        }

        //第三行
        Row row2 = sheet.createRow((short) 4);
        Cell c14 = row2.createCell((short) 0);
        c14.setCellStyle(style);
        c14.setCellValue("返货种类");
        Cell c15 = row2.createCell((short) 2);
        c15.setCellStyle(style);
        if (null != re.getGoodsType()) {
            c15.setCellValue(re.getGoodsType());
        } else {
            c15.setCellValue("");
        }

        //第四行
        Row row3 = sheet.createRow((short) 6);
        Cell c16 = row3.createCell((short) 0);
        c16.setCellStyle(style);
        c16.setCellValue("不合格(返货)情况描述:");
        Cell cc16 = row3.createCell((short) 2);
        cc16.setCellStyle(style);
        if (null != re.getUnqualifiedDescription()) {
            cc16.setCellValue(re.getUnqualifiedDescription());
        } else {
            cc16.setCellValue("");
        }
        Cell cc17 = row3.createCell((short) 12);
        cc17.setCellStyle(style);
        cc17.setCellValue("检查员");
        Cell cc18 = row3.createCell((short) 14);
        cc18.setCellStyle(style);
        if (null != re.getInspectorOne()) {
            cc18.setCellValue(re.getInspectorOne());
        } else {
            cc18.setCellValue("");
        }

        //第五行
        Row rowb = sheet.createRow((short) 8);
        Cell cc19 = rowb.createCell((short) 12);
        cc19.setCellStyle(style);
        cc19.setCellValue("检查日期");
        Cell cc20 = rowb.createCell((short) 14);
        cc20.setCellStyle(style);
        if (null != re.getCheckDateOne()) {
            cc20.setCellValue(dateFormatter.format(re.getCheckDateOne()));
        } else {
            cc20.setCellValue("");
        }

        //第六行
        Row row4 = sheet.createRow((short) 10);
        Cell c17 = row4.createCell((short) 0);
        c17.setCellStyle(style);
        c17.setCellValue("不合格(返货)处置措施:");
        Cell cell1 = row4.createCell((short) 2);
        cell1.setCellStyle(style);
        if (null != re.getTreatmentMeasures()) {
            cell1.setCellValue(re.getTreatmentMeasures());
        } else {
            cell1.setCellValue("");
        }

        Cell cell2 = row4.createCell((short) 12);
        cell2.setCellStyle(style);
        cell2.setCellValue("检查员");
        Cell cell3 = row4.createCell((short) 14);
        cell3.setCellStyle(style);
        if (null != re.getInspectorTwo()) {
            cell3.setCellValue(re.getInspectorTwo());
        } else {
            cell3.setCellValue("");
        }

        //第七行
        Row rowc = sheet.createRow((short) 12);
        Cell cell4 = rowc.createCell((short) 12);
        cell4.setCellStyle(style);
        cell4.setCellValue("检查日期");
        Cell cell5 = rowc.createCell((short) 14);
        cell5.setCellStyle(style);
        if (null != re.getCheckDateTwo()) {
            cell5.setCellValue(dateFormatter.format(re.getCheckDateTwo()));
        } else {
            cell5.setCellValue("");
        }

        //第八行
        Row row5 = sheet.createRow((short) 14);
        Cell c18 = row5.createCell((short) 0);
        c18.setCellStyle(style);
        c18.setCellValue("不合格评审意见");
        Cell c19 = row5.createCell((short) 2);
        c19.setCellStyle(style);
        c19.setCellValue("不合格性质");
        Cell c20 = row5.createCell((short) 4);
        c20.setCellStyle(style);
        if (null != re.getNonconformance()) {
            c20.setCellValue(re.getNonconformance());
        } else {
            c20.setCellValue("");
        }
        Cell c21 = row5.createCell((short) 10);
        c21.setCellStyle(style);
        c21.setCellValue("主持人");
        Cell c22 = row5.createCell((short) 12);
        c22.setCellStyle(style);
        if (null != re.getHost()) {
            c22.setCellValue(re.getHost());
        } else {
            c22.setCellValue("");
        }

        //第九行
        Row row6 = sheet.createRow((short) 16);
        Cell c23 = row6.createCell((short) 2);
        c23.setCellStyle(style);
        c23.setCellValue("处置方式");
        Cell c24 = row6.createCell((short) 5);
        c24.setCellStyle(style);
        if (null != re.getDisposalWay()) {
            c24.setCellValue(re.getDisposalWay());
        } else {
            c24.setCellValue("");
        }

        //第十行
        Row row7 = sheet.createRow((short) 18);
        Cell c25 = row7.createCell((short) 2);
        c25.setCellStyle(style);
        c25.setCellValue("参加人员");
        Cell c26 = row7.createCell((short) 5);
        c26.setCellStyle(style);
        if (null != re.getParticipants()) {
            c26.setCellValue(re.getParticipants());
        } else {
            c26.setCellValue("");
        }

        CellRangeAddress region1 = new CellRangeAddress(0, 1, 0, 1);
        CellRangeAddress region2 = new CellRangeAddress(0, 1, 2, 3);
        CellRangeAddress region3 = new CellRangeAddress(0, 1, 4, 5);
        CellRangeAddress region4 = new CellRangeAddress(0, 1, 6, 7);
        CellRangeAddress region5 = new CellRangeAddress(0, 1, 8, 9);
        CellRangeAddress region6 = new CellRangeAddress(0, 1, 10, 11);
        CellRangeAddress region7 = new CellRangeAddress(0, 1, 12, 13);
        CellRangeAddress region8 = new CellRangeAddress(0, 1, 14, 15);
        CellRangeAddress region9 = new CellRangeAddress(2, 3, 0, 1);
        CellRangeAddress region10 = new CellRangeAddress(2, 3, 2, 7);
        CellRangeAddress region11 = new CellRangeAddress(2, 3, 8, 9);
        CellRangeAddress region12 = new CellRangeAddress(2, 3, 10, 11);
        CellRangeAddress region13 = new CellRangeAddress(2, 3, 12, 13);
        CellRangeAddress region14 = new CellRangeAddress(2, 3, 14, 15);
        CellRangeAddress region15 = new CellRangeAddress(4, 5, 0, 1);
        CellRangeAddress region16 = new CellRangeAddress(4, 5, 2, 15);
        CellRangeAddress region17 = new CellRangeAddress(6, 9, 0, 1);
        CellRangeAddress a = new CellRangeAddress(6, 9, 2, 11);
        CellRangeAddress b = new CellRangeAddress(6, 7, 12, 13);
        CellRangeAddress c = new CellRangeAddress(6, 7, 14, 15);
        CellRangeAddress d = new CellRangeAddress(8, 9, 12, 13);
        CellRangeAddress e = new CellRangeAddress(8, 9, 14, 15);
        CellRangeAddress region18 = new CellRangeAddress(10, 13, 0, 1);
        CellRangeAddress a1 = new CellRangeAddress(10, 13, 2, 11);
        CellRangeAddress b1 = new CellRangeAddress(10, 11, 12, 13);
        CellRangeAddress f = new CellRangeAddress(10, 11, 14, 15);
        CellRangeAddress d1 = new CellRangeAddress(12, 13, 12, 13);
        CellRangeAddress e1 = new CellRangeAddress(12, 13, 14, 15);
        CellRangeAddress region19 = new CellRangeAddress(14, 21, 0, 1);
        CellRangeAddress region20 = new CellRangeAddress(14, 15, 2, 4);
        CellRangeAddress region21 = new CellRangeAddress(14, 15, 5, 9);
        CellRangeAddress region22 = new CellRangeAddress(14, 15, 10, 11);
        CellRangeAddress region23 = new CellRangeAddress(14, 15, 12, 15);
        CellRangeAddress region24 = new CellRangeAddress(16, 17, 2, 4);
        CellRangeAddress region25 = new CellRangeAddress(16, 17, 5, 15);
        CellRangeAddress region26 = new CellRangeAddress(18, 21, 2, 4);
        CellRangeAddress region27 = new CellRangeAddress(18, 21, 5, 15);

        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        sheet.addMergedRegion(region3);
        sheet.addMergedRegion(region4);
        sheet.addMergedRegion(region5);
        sheet.addMergedRegion(region6);
        sheet.addMergedRegion(region7);
        sheet.addMergedRegion(region8);
        sheet.addMergedRegion(region9);
        sheet.addMergedRegion(region10);
        sheet.addMergedRegion(region11);
        sheet.addMergedRegion(region12);
        sheet.addMergedRegion(region13);
        sheet.addMergedRegion(region14);
        sheet.addMergedRegion(region15);
        sheet.addMergedRegion(region16);
        sheet.addMergedRegion(region17);
        sheet.addMergedRegion(a);
        sheet.addMergedRegion(b);
        sheet.addMergedRegion(c);
        sheet.addMergedRegion(d);
        sheet.addMergedRegion(e);
        sheet.addMergedRegion(region18);
        sheet.addMergedRegion(a1);
        sheet.addMergedRegion(b1);
        sheet.addMergedRegion(f);
        sheet.addMergedRegion(d1);
        sheet.addMergedRegion(e1);
        sheet.addMergedRegion(region19);
        sheet.addMergedRegion(region20);
        sheet.addMergedRegion(region21);
        sheet.addMergedRegion(region22);
        sheet.addMergedRegion(region23);
        sheet.addMergedRegion(region24);
        sheet.addMergedRegion(region25);
        sheet.addMergedRegion(region26);
        sheet.addMergedRegion(region27);


        String images = re.getIamges();
        if(null!=images&&!"".equals(images)){
            List<ImageJson> imageJsonList = JSON.parseArray(images, ImageJson.class);
            //插入图片
            BufferedImage bufferImg;
            try {
                HSSFSheet sheetImg = workbook.createSheet("图片");
                //画图的顶级管理器，一个sheet只能获取一个
                HSSFPatriarch patriarch = sheetImg.createDrawingPatriarch();
                for (int i = 0; i < imageJsonList.size(); i++) {
                    ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                    bufferImg = ImageIO.read(new URL(imageJsonList.get(i).getImageUrl()));
                    ImageIO.write(bufferImg, "png", byteArrayOut);
                    //anchor主要用于设置图片的属性
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 250, (short) 1, 1 + i * 17, (short) 10, 16 + i * 17);
                    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(("返货处理登记表"+ date + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        // 设置response参数，可以打开下载页面
        try {

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (final IOException aa) {
            throw aa;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

   /* public static void exportExcelX(List<Map<String, Object>> list,HttpServletRequest request, HttpServletResponse response, Integer cId) throws Exception {

        // 获得要导出的数据集
        *//*List<Map<String, Object>> list = d_ExchangeCodeService.selectExcelRecord(cId);*//*

        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("NAME").toString());

        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        sheet.setColumnWidth((short) 0, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 1, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 2, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 3, (short) (35.7 * 100));
        sheet.setColumnWidth((short) 4, (short) (35.7 * 250));
        sheet.setColumnWidth((short) 5, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 6, (short) (35.7 * 150));

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();
        // DataFormat df = wb.createDataFormat();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.RED.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        // cs.setDataFormat(df.getFormat("#,##0.0"));

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        // cs2.setDataFormat(df.getFormat("text"));

        // 创建列（每行里的单元格）
        Cell cell = row.createCell(0);
        cell.setCellValue("用户名");
        cell.setCellStyle(cs);

        cell = row.createCell(1);
        cell.setCellValue("订单号");
        cell.setCellStyle(cs);

        cell = row.createCell(2);
        cell.setCellValue("兑换券序列号");
        cell.setCellStyle(cs);

        cell = row.createCell(3);
        cell.setCellValue("兑换券金额");
        cell.setCellStyle(cs);

        cell = row.createCell(4);
        cell.setCellValue("兑换券类型名称");
        cell.setCellStyle(cs);

        cell = row.createCell(5);
        cell.setCellValue("使用时间");
        cell.setCellStyle(cs);

        cell = row.createCell(6);
        cell.setCellValue("使用结束日期");
        cell.setCellStyle(cs);

        cell = row.createCell(7);
        cell.setCellValue("使用结束日期");
        cell.setCellStyle(cs);

        cell = row.createCell(8);
        cell.setCellValue("使用结束日期");
        cell.setCellStyle(cs);

        cell = row.createCell(9);
        cell.setCellValue("使用结束日期");
        cell.setCellStyle(cs);

        cell = row.createCell(10);
        cell.setCellValue("使用结束日期");
        cell.setCellStyle(cs);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        for (short i = 0; i < list.size(); i++) {

            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            row = sheet.createRow((short) i + 1);
            // 在row行上创建一个方格
            cell = row.createCell(0);
            cell.setCellValue(list.get(i).get("usr_UserID") == null ? "未使用" : list.get(i).get("usr_UserID").toString());
            cell.setCellStyle(cs2);

            cell = row.createCell(1);
            cell.setCellValue(list.get(i).get("ord_OrderID") == null ? "未使用" : list.get(i).get("ord_OrderID").toString());
            cell.setCellStyle(cs2);

            cell = row.createCell(2);
            cell.setCellValue(list.get(i).get("Account").toString());
            cell.setCellStyle(cs2);

            cell = row.createCell(3);
            cell.setCellValue(Double.parseDouble(list.get(i).get("Amount").toString()));
            cell.setCellStyle(cs2);

            cell = row.createCell(4);
            cell.setCellValue(list.get(i).get("NAME").toString());
            cell.setCellStyle(cs2);

            cell = row.createCell(5);
            cell.setCellValue(list.get(i).get("UsedTime") == null ? "未使用" : df.format(list.get(i).get("UsedTime")).toString());
            cell.setCellStyle(cs2);

            cell = row.createCell(6);
            cell.setCellValue(df.format(list.get(i).get("BlankOutTime")).toString());
            cell.setCellStyle(cs2);
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((list.get(0).get("NAME").toString() + ".xls").getBytes(), "iso-8859-1"));

        ServletOutputStream out = response.getOutputStream();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {

            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
*/

}
/*

    public static void main(String[] args) throws IOException {
        int count = 100000;
        JSONArray ja = new JSONArray();
        for(int i=0;i<100000;i++){
            Student s = new Student();
            s.setName("POI"+i);
            s.setAge(i);
            s.setBirthday(new Date());
            s.setHeight(i);
            s.setWeight(i);
            s.setSex(i/2==0?false:true);
            ja.add(s);
        }
        Map<String,String> headMap = new LinkedHashMap<String,String>();
        headMap.put("name","姓名");
        headMap.put("age","年龄");
        headMap.put("birthday","生日");
        headMap.put("height","身高");
        headMap.put("weight","体重");
        headMap.put("sex","性别");

        String title = "测试";
        */
/*
        OutputStream outXls = new FileOutputStream("E://a.xls");
        System.out.println("正在导出xls....");
        Date d = new Date();
        ExcelUtil.exportExcel(title,headMap,ja,null,outXls);
        System.out.println("共"+count+"条数据,执行"+(new Date().getTime()-d.getTime())+"ms");
        outXls.close();*//*

        //
        OutputStream outXlsx = new FileOutputStream("D://b.xlsx");
        System.out.println("正在导出xlsx....");
        Date d2 = new Date();
        ExcelUtil.exportExcelX(title,headMap,ja,null,0,outXlsx);
        System.out.println("共"+count+"条数据,执行"+(new Date().getTime()-d2.getTime())+"ms");
        outXlsx.close();

    }
}
class Student {
    private String name;
    private int age;
    private Date birthday;
    private float height;
    private double weight;
    private boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}*/
