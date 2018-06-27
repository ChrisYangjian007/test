package Utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-08-01.
 */
public class ExcelUtil {
    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel
//
//    /**
//     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
//     * @param in,fileName
//     */
//    public  List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{
//        List<List<Object>> list = null;
//        //创建Excel工作薄
//        Workbook work = this.getWorkbook(in,fileName);
//        if(null == work){
//            throw new Exception("创建Excel工作薄为空！");
//        }
//        Sheet sheet = null;
//        Row row = null;
//        Cell cell = null;
//        list = new ArrayList<List<Object>>();
//        //遍历Excel中所有的sheet
//        for (int i = 0; i < work.getNumberOfSheets(); i++) {
//            sheet = work.getSheetAt(i);
//            if(sheet==null){continue;}
//            //遍历当前sheet中的所有行
//            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {
//                row = sheet.getRow(j);
//                if(row==null||row.getFirstCellNum()==j){continue;}
//                //遍历所有的列
//                List<Object> li = new ArrayList<Object>();
//                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
//                    cell = row.getCell(y);
//                    li.add(this.getCellValue(cell));
//                }
//                list.add(li);
//            }
//        }
////        work.cl();
//        return list;
//    }
//    /**
//     * 描述：根据文件后缀，自适应上传文件的版本
//     */
//    public Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
//        Workbook wb = null;
//        String fileType = fileName.substring(fileName.lastIndexOf("."));
//        if(excel2003L.equals(fileType)){
//            wb = new HSSFWorkbook(inStr);  //2003-
//        }else if(excel2007U.equals(fileType)){
//            wb = new XSSFWorkbook(inStr);  //2007+
//        }else{
//            throw new Exception("解析的文件格式有误！");
//        }
//        return wb;
//    }
//    /**
//     * 描述：对表格中数值进行格式化
//     */
//    public Object getCellValue(Cell cell){
//        Object value = null;
//        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
//        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
//        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_STRING:
//                value = cell.getRichStringCellValue().getString();
//                break;
//            case Cell.CELL_TYPE_NUMERIC:
//                if("General".equals(cell.getCellStyle().getDataFormatString())){
//                value = df.format(cell.getNumericCellValue());
//            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
//                value = sdf.format(cell.getDateCellValue());
//            }else{
//                value = df2.format(cell.getNumericCellValue());
//            }
//            break;
//            case Cell.CELL_TYPE_BOOLEAN:
//                value = cell.getBooleanCellValue();
//                break;
//            case Cell.CELL_TYPE_BLANK:
//                value = "";
//                break;
//            default:
//                break;
//        }
//        return value;
//    }
    private Workbook workbook;
    private OutputStream _OutputStream;
    private InputStream _InputStream;
    private String pattern;// 日期格式

    // 日期格式
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    public ExcelUtil(Workbook workboook) {
        this.workbook = workboook;
    }
    public ExcelUtil(String p_strFilePath) throws Exception {
        _InputStream=new FileInputStream(new File(p_strFilePath));
        String fileType = p_strFilePath.substring(p_strFilePath.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            workbook = new HSSFWorkbook(_InputStream);
        } else if(excel2007U.equals(fileType)){
            workbook = new XSSFWorkbook(_InputStream);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
    }
    public String toString() {
        return "共有 " + getSheetCount() + "个sheet 页！";
    }
    public String toString(int sheetIx) throws IOException {
        return "第 " + (sheetIx + 1) + "个sheet 页，名称： " + getSheetName(sheetIx) + "，共 " + getRowCount(sheetIx) + "行！";
    }
    /**
     * 根据后缀判断是否为 Excel 文件，后缀匹配xls和xlsx
     */
    public static boolean isExcel(String pathname) {
        if (pathname == null) {
            return false;
        }
        return pathname.endsWith(excel2003L) || pathname.endsWith(excel2007U);
    }
    /**
     * 读取 Excel 第一页所有数据
     */
    public List<List<String>> read() throws Exception {
        return read(0, 0, getRowCount(0) - 1);
    }
    /**
     * 读取指定sheet 页所有数据
     * @param sheetIx 指定 sheet 页，从 0 开始
     */
    public List<List<String>> read(int sheetIx) throws Exception {
        return read(sheetIx, 0, getRowCount(sheetIx) - 1);
    }
    /**
     * 读取指定sheet 页指定行数据
     * @param sheetIx 指定 sheet 页，从 0 开始
     * @param start 指定开始行，从 0 开始
     * @param end 指定结束行，从 0 开始
     */
    public List<List<String>> read(int sheetIx, int start, int end) throws Exception {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        List<List<String>> list = new ArrayList<List<String>>();
        if (end > getRowCount(sheetIx)) {
            end = getRowCount(sheetIx);
        }
        int cols = sheet.getRow(0).getLastCellNum(); // 第一行总列数
        for (int i = start; i <= end; i++) {
            List<String> rowList = new ArrayList<String>();
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                if (row == null) {
                    rowList.add(null);
                    continue;
                }
                rowList.add(getCellValueToString(row.getCell(j)));
            }
            list.add(rowList);
        }
        return list;
    }
    /**
     * 将数据写入到 Excel 默认第一页中，从第1行开始写入
     * @param rowData 数据
     */
    public boolean write(List<List<String>> rowData) throws IOException {
        return write(0, rowData, 0);
    }
    /**
     * 将数据写入到 Excel 新创建的 Sheet 页
     * @param rowData 数据
     * @param sheetName 长度为1-31，不能包含后面任一字符: ：\ / ? * [ ]
     */
    public boolean write(List<List<String>> rowData, String sheetName, boolean isNewSheet) throws IOException {
        Sheet sheet = null;
        if (isNewSheet) {
            sheet = workbook.createSheet(sheetName);
        } else {
            sheet = workbook.createSheet();
        }
        int sheetIx = workbook.getSheetIndex(sheet);
        return write(sheetIx, rowData, 0);
    }
    /**
     * 将数据追加到sheet页最后
     * @param rowData 数据
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param isAppend 是否追加,true 追加，false 重置sheet再添加
     */
    public boolean write(int sheetIx, List<List<String>> rowData, boolean isAppend) throws IOException {
        if (isAppend) {
            return write(sheetIx, rowData, getRowCount(sheetIx));
        } else {// 清空再添加
            clearSheet(sheetIx);
            return write(sheetIx, rowData, 0);
        }
    }
    /**
     * 将数据写入到 Excel 指定 Sheet 页指定开始行中,指定行后面数据向后移动
     * @param rowData 数据
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param startRow 指定开始行，从 0 开始
     */
    public boolean write(int sheetIx, List<List<String>> rowData, int startRow) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        int dataSize = rowData.size();
        if (getRowCount(sheetIx) > 0) {// 如果小于等于0，则一行都不存在
            sheet.shiftRows(startRow, getRowCount(sheetIx), dataSize);
        }
        for (int i = 0; i < dataSize; i++) {
            Row row = sheet.createRow(i + startRow);
            for (int j = 0; j < rowData.get(i).size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowData.get(i).get(j) + "");
            }
        }
        return true;
    }
    /**
     * 设置cell 样式
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param colIndex 指定列，从 0 开始
     */
    public boolean setStyle(int sheetIx, int rowIndex, int colIndex, CellStyle style) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        // sheet.autoSizeColumn(colIndex, true);// 设置列宽度自适应
        sheet.setColumnWidth(colIndex, 4000);
        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
        cell.setCellStyle(style);
        return true;
    }
    /**
     * 设置样式
     * @param type 1：标题 2：第一行
     */
    public CellStyle makeStyle(int type) {
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));// // 内容样式 设置单元格内容格式是文本
        style.setAlignment(CellStyle.ALIGN_CENTER);// 内容居中

        // style.setBorderTop(CellStyle.BORDER_THIN);// 边框样式
        // style.setBorderRight(CellStyle.BORDER_THIN);
        // style.setBorderBottom(CellStyle.BORDER_THIN);
        // style.setBorderLeft(CellStyle.BORDER_THIN);

        Font font = workbook.createFont();// 文字样式
        if (type == 1) {
            // style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);//颜色样式
            // 前景颜色
            // style.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);//背景色
            // style.setFillPattern(CellStyle.ALIGN_FILL);// 填充方式
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeight((short) 500);
        }

        if (type == 2) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeight((short) 300);
        }
        style.setFont(font);
        return style;
    }
    /**
     * 合并单元格
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param firstRow 开始行
     * @param lastRow 结束行
     * @param firstCol 开始列
     * @param lastCol 结束列
     */
    public void region(int sheetIx, int firstRow, int lastRow, int firstCol, int lastCol) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }
    /**
     * 指定行是否为空
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定开始行，从 0 开始
     * @return true 不为空，false 不行为空
     */
    public boolean isRowNull(int sheetIx, int rowIndex) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        return sheet.getRow(rowIndex) == null;
    }
    /**
     * 创建行，若行存在，则清空
     * @param sheetIx 指定 sheet 页，从 0 开始
     * @param rowIndex 指定创建行，从 0 开始
     */
    public boolean createRow(int sheetIx, int rowIndex) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        sheet.createRow(rowIndex);
        return true;
    }
    /**
     * 指定单元格是否为空
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定开始行，从 0 开始
     * @param colIndex 指定开始列，从 0 开始
     * @return true 行不为空，false 行为空
     */
    public boolean isCellNull(int sheetIx, int rowIndex, int colIndex) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        if (!isRowNull(sheetIx, rowIndex)) {
            return false;
        }
        Row row = sheet.getRow(rowIndex);
        return row.getCell(colIndex) == null;
    }
    /**
     * 创建单元格
     * @param sheetIx 指定 sheet 页，从 0 开始
     * @param rowIndex 指定行，从 0 开始
     * @param colIndex 指定创建列，从 0 开始
     * @return true 列为空，false 行不为空
     */
    public boolean createCell(int sheetIx, int rowIndex, int colIndex) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        Row row = sheet.getRow(rowIndex);
        row.createCell(colIndex);
        return true;
    }
    /**
     * 返回sheet 中的行数
     * @param sheetIx 指定 Sheet 页，从 0 开始
     */
    public int getRowCount(int sheetIx) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        if (sheet.getPhysicalNumberOfRows() == 0) {
            return 0;
        }
        return sheet.getLastRowNum() + 1;
    }
    /**
     * 返回所在行的列数
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     * @return 返回-1 表示所在行为空
     */
    public int getColumnCount(int sheetIx, int rowIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        Row row = sheet.getRow(rowIndex);
        return row == null ? -1 : row.getLastCellNum();
    }
    /**
     * 设置row 和 column 位置的单元格值
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     * @param colIndex 指定列，从0开始
     * @param value 值
     */
    public boolean setValueAt(int sheetIx, int rowIndex, int colIndex, String value) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        sheet.getRow(rowIndex).getCell(colIndex).setCellValue(value);
        return true;
    }
    /**
     * 返回 row 和 column 位置的单元格值
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     * @param colIndex 指定列，从0开始
     */
    public String getValueAt(int sheetIx, int rowIndex, int colIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        return getCellValueToString(sheet.getRow(rowIndex).getCell(colIndex));
    }
    /**
     * 重置指定行的值
     * @param rowData 数据
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     */
    public boolean setRowValue(int sheetIx, List<String> rowData, int rowIndex) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        Row row = sheet.getRow(rowIndex);
        for (int i = 0; i < rowData.size(); i++) {
            row.getCell(i).setCellValue(rowData.get(i));
        }
        return true;
    }
    /**
     * 返回指定行的值的集合
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     */
    public List<String> getRowValue(int sheetIx, int rowIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        Row row = sheet.getRow(rowIndex);
        List<String> list = new ArrayList<String>();
        if (row == null) {
            list.add(null);
        } else {
            for (int i = 0; i < row.getLastCellNum(); i++) {
                list.add(getCellValueToString(row.getCell(i)));
            }
        }
        return list;
    }
    /**
     * 返回列的值的集合
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     * @param colIndex 指定列，从0开始
     */
    public List<String> getColumnValue(int sheetIx, int rowIndex, int colIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        List<String> list = new ArrayList<String>();
        for (int i = rowIndex; i < getRowCount(sheetIx); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                list.add(null);
                continue;
            }
            list.add(getCellValueToString(sheet.getRow(i).getCell(colIndex)));
        }
        return list;
    }
    /**
     * 获取excel 中sheet 总页数
     */
    public int getSheetCount() {
        return workbook.getNumberOfSheets();
    }
    /**
     * 新增sheet表
     */
    public void createSheet() {
        workbook.createSheet();
    }
    /**
     * 设置sheet名称，长度为1-31，不能包含后面任一字符: ：\ / ? * [ ]
     * @param sheetIx 指定 Sheet 页，从 0 开始，//
     * @param name
     */
    public boolean setSheetName(int sheetIx, String name) throws IOException {
        workbook.setSheetName(sheetIx, name);
        return true;
    }
    /**
     * 获取 sheet名称
     * @param sheetIx 指定 Sheet 页，从 0 开始
     */
    public String getSheetName(int sheetIx) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        return sheet.getSheetName();
    }
    /**
     * 获取sheet的索引，从0开始
     * @param name sheet 名称
     * @return -1表示该未找到名称对应的sheet
     */
    public int getSheetIndex(String name) {
        return workbook.getSheetIndex(name);
    }
    /**
     * 删除指定sheet
     * @param sheetIx 指定 Sheet 页，从 0 开始
     */
    public boolean removeSheetAt(int sheetIx) throws IOException {
        workbook.removeSheetAt(sheetIx);
        return true;
    }
    /**
     * 删除指定sheet中行，改变该行之后行的索引
     * @param sheetIx 指定 Sheet 页，从 0 开始
     * @param rowIndex 指定行，从0开始
     */
    public boolean removeRow(int sheetIx, int rowIndex) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIx);
        sheet.shiftRows(rowIndex + 1, getRowCount(sheetIx), -1);
        Row row = sheet.getRow(getRowCount(sheetIx) - 1);
        sheet.removeRow(row);
        return true;
    }
    /**
     * 设置sheet 页的索引
     * @param sheetname Sheet 名称
     * @param sheetIx Sheet 索引，从0开始
     */
    public void setSheetOrder(String sheetname, int sheetIx) {
        workbook.setSheetOrder(sheetname, sheetIx);
    }
    /**
     * 清空指定sheet页（先删除后添加并指定sheetIx）
     * @param sheetIx 指定 Sheet 页，从 0 开始
     */
    public boolean clearSheet(int sheetIx) throws IOException {
        String sheetname = getSheetName(sheetIx);
        removeSheetAt(sheetIx);
        workbook.createSheet(sheetname);
        setSheetOrder(sheetname, sheetIx);
        return true;
    }
    public Workbook getWorkbook() {
        return workbook;
    }
    /**
     * 关闭流
     */
    public void close() throws IOException {
        if (_OutputStream != null) {
            _OutputStream.close();
        }
        if (_InputStream != null) {
            _InputStream.close();
        }
    }
    /**
     * 转换单元格的类型为String 默认的 <br>
     * 默认的数据类型：CELL_TYPE_BLANK(3), CELL_TYPE_BOOLEAN(4),
     * CELL_TYPE_ERROR(5),CELL_TYPE_FORMULA(2), CELL_TYPE_NUMERIC(0),
     * CELL_TYPE_STRING(1)
     */
    private String getCellValueToString(Cell cell) {
        String strCell = "";
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (pattern != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        strCell = sdf.format(date);
                    } else {
                        strCell = date.toString();
                    }
                    break;
                }
                // 不是日期格式，则防止当数字过长时以科学计数法显示
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                strCell = cell.toString();
                break;
            case Cell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return strCell;
    }

//    // 显示的导出表的标题
//    private String title;
//    // 导出表的列名
//    private String[] rowName;
//    private List<object[]> dataList = new ArrayList<object[]>();
//    HttpServletResponse response;
//    // 构造方法，传入要导出的数据
//    public ExportExcel(String title, String[] rowName,
//                       List<object[]> dataList) {
//        this.dataList = dataList;
//        this.rowName = rowName;
//        this.title = title;
//    }
//    /*
//     * 导出数据
//     */
//    public void export(OutputStream out) throws Exception {
//        try {
//            HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
//            HSSFSheet sheet = workbook.createSheet(title); // 创建工作表
//            // 产生表格标题行
//            HSSFRow rowm = sheet.createRow(0);
//            HSSFCell cellTiltle = rowm.createCell(0);
//            // sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面 - 可扩展】
//            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
//            HSSFCellStyle style = this.getStyle(workbook); // 单元格样式对象
//            /*
//             * sheet.addMergedRegion(new
//             * CellRangeAddress(0,dataList.get(0).length-1 , 0,
//             * (rowName.length-1)));
//             */// 合并单元格
//            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0,
//                    dataList.get(0).length - 1));// 列行
//            cellTiltle.setCellStyle(style);
//            cellTiltle.setCellValue(title);
//
//            // 定义所需列数
//            int columnNum = rowName.length;
//            HSSFRow rowRowName = sheet.createRow(0); // 在索引2的位置创建行(最顶端的行开始的第二行)
//
//            // 将列头设置到sheet的单元格中
//            for (int n = 0; n < columnNum; n++) {
//                HSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
//                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
//                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
//                cellRowName.setCellValue(text); // 设置列头单元格的值
//                cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
//            }
//
//            // 将查询出的数据设置到sheet对应的单元格中
//            for (int i = 0; i < dataList.size(); i++) {
//
//                Object[] obj = dataList.get(i);// 遍历每个对象
//                HSSFRow row = sheet.createRow(i + 2);// 创建所需的行数（从第三行开始写数据）
//
//                for (int j = 0; j < obj.length; j++) {
//                    HSSFCell cell = null; // 设置单元格的数据类型
//                    if (j == 0) {
//                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
//                        cell.setCellValue(i);
//                    } else {
//                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
//                        if (obj[j] != null) {
//                            cell.setCellValue(obj[j].toString());
//                        }
//                    }
//                    cell.setCellStyle(style); // 设置单元格样式
//                }
//            }
//            // 让列宽随着导出的列长自动适应
//            for (int colNum = 0; colNum < dataList.get(1).length; colNum++) {
//                int columnWidth = sheet.getColumnWidth(colNum) / 256;
//                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
//                    HSSFRow currentRow;
//                    // 当前行未被使用过
//                    if (sheet.getRow(rowNum) == null) {
//                        currentRow = sheet.createRow(rowNum);
//                    } else {
//                        currentRow = sheet.getRow(rowNum);
//                    }
//                    /*
//                     * if (currentRow.getCell(colNum) != null) { HSSFCell
//                     * currentCell = currentRow.getCell(colNum); if
//                     * (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING)
//                     * { int length =
//                     * currentCell.getStringCellValue().getBytes().length; if
//                     * (columnWidth < length) { columnWidth = length; } } }
//                     */
//                    if (currentRow.getCell(colNum) != null) {
//                        HSSFCell currentCell = currentRow.getCell(colNum);
//                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                            int length = 0;
//                            try {
//                                length = currentCell.getStringCellValue()
//                                        .getBytes().length;
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            if (columnWidth < length) {
//                                columnWidth = length;
//                            }
//                        }
//                    }
//
//                }
//                if (colNum == 0) {
//                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
//                } else {
//                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
//                }
//            }
//            if (workbook != null) {
//                try {
//                    workbook.write(out);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            out.close();
//        }
//
//    }
//
//    /*
//     * 列头单元格样式
//     */
//    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
//
//        // 设置字体
//        HSSFFont font = workbook.createFont();
//        // 设置字体大小
//        font.setFontHeightInPoints((short) 11);
//        // 字体加粗
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 设置字体名字
//        font.setFontName("Courier New");
//        // 设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置底边框;
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        // 设置底边框颜色;
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        // 设置左边框;
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        // 设置左边框颜色;
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        // 设置右边框;
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        // 设置右边框颜色;
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        // 设置顶边框;
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        // 设置顶边框颜色;
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        // 在样式用应用设置的字体;
//        style.setFont(font);
//        // 设置自动换行;
//        style.setWrapText(false);
//        // 设置水平对齐的样式为居中对齐;
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 设置垂直对齐的样式为居中对齐;
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        return style;
//
//    }
//
//    /*
//     * 列数据信息单元格样式
//     */
//    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
//        // 设置字体
//        HSSFFont font = workbook.createFont();
//        // 设置字体大小
//        // font.setFontHeightInPoints((short)10);
//        // 字体加粗
//        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 设置字体名字
//        font.setFontName("Courier New");
//        // 设置样式;
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置底边框;
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        // 设置底边框颜色;
//        style.setBottomBorderColor(HSSFColor.BLACK.index);
//        // 设置左边框;
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        // 设置左边框颜色;
//        style.setLeftBorderColor(HSSFColor.BLACK.index);
//        // 设置右边框;
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        // 设置右边框颜色;
//        style.setRightBorderColor(HSSFColor.BLACK.index);
//        // 设置顶边框;
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        // 设置顶边框颜色;
//        style.setTopBorderColor(HSSFColor.BLACK.index);
//        // 在样式用应用设置的字体;
//        style.setFont(font);
//        // 设置自动换行;
//        style.setWrapText(false);
//        // 设置水平对齐的样式为居中对齐;
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 设置垂直对齐的样式为居中对齐;
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        return style;
//    }
        /*// 使用流将数据导出
        OutputStream out = null;
            try {
        // 防止中文乱码
                Parament findByName = paramentDao.findByName("export_name");

                String headStr = "attachment; filename=\""
                        + new String((findByName.getParament_value()+*//*下载后的文件名*//*".xls").getBytes("gb2312"), "ISO8859-1") + "\"";
                response.setContentType("octets/stream");
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", headStr);
                out = response.getOutputStream();
                ExportExcelSeedBack ex = new ExportExcelSeedBack(findByName.getParament_value(),*//*表的标题*//* new String[]{findByName.getParament_value()*//*内容标题*//*},
                        dataList*//*数据准备  ---数据类型为List<object[]>*//*);
                ex.export(out);
            } catch (Exception e) {
                e.printStackTrace();
            }*/

   /* *//**
     * 将Excel文档转换为一个html页面
     *
     * @param inputPath
     *            本地文件读取的路径加文件名，如：C:\\1.xls
     * @param outputPath
     *            本地文件输出的路径加文件名，如：C:\\1.xls
     * @throws Exception
     *//*
    public static void excel2Html(final String inputPath,
                                  final String outputPath) throws Exception {

        ExcelToHtmlConverter converter = new ExcelToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());

        @SuppressWarnings("static-access")
        Document htmlDocument = converter.process(new File(inputPath));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "iso-8859-1");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();

        String content = new String(outStream.toByteArray());

        FileOutputStream outputStream = new FileOutputStream(outputPath);
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write(content);
        pw.close();
    }

    *//**
     * 将一个Word文档转换为一个html页面
     *
     * @param inputFilePath
     *            想要读取的文件所在文件夹的路径，如：C:\\
     * @param inputFileName
     *            想要读取的文件名字，如：1.xls
     * @param outputFilePath
     *            准备写出的文件所在文件夹的路径，如：C:\\
     * @param outputFileName
     *            准备写出的文件名字，如：1.xls
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     *//*
    public static void word2Html(final String inputFilePath,
                                 final String inputFileName, final String outputFilePath,
                                 final String outputFileName) throws IOException,
            ParserConfigurationException, TransformerException {

        InputStream input = new FileInputStream(inputFilePath + inputFileName);
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());

        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            @Override
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(inputFilePath
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();

        String content = new String(outStream.toByteArray());

        FileOutputStream outputStream = new FileOutputStream(outputFilePath
                + outputFileName);
        PrintWriter pw = new PrintWriter(outputStream);
        pw.write(content);
        pw.close();
    }*/
//    // 将值拼成sql语句
//    public static void writeSql(String rowValue,BufferedWriter bw) throws IOException{
//        String[] sqlValue = rowValue.split("#");
//        String sql = "";
//        sql="INSERT INTO table_name (列名1) VALUES("+ sqlValue[0].trim() + ");"+"\n";
//        System.out.print(sql);
//        try {
//            bw.write(sql);
//            bw.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
