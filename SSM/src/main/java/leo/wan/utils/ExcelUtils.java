package leo.wan.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author leo
 * @Title
 * @Description
 * @date 2017年10月5日  下午11:30:21
 */
@Slf4j
public class ExcelUtils {

    public static List<List<String>> parserExcel(InputStream is) throws IOException {
        Workbook workbook = getWorkBook(is);

        return parserWorkBook(workbook);
    }

    private static List<List<String>> parserWorkBook(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        return parserSheet(sheet);
    }

    private static List<List<String>> parserSheet(Sheet sheet) {
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        int beginIndex = sheet.getRow(0).getFirstCellNum();
        int endIndex = sheet.getRow(0).getLastCellNum();
        List<List<String>> result = new ArrayList<>();
        for (int index = firstRowNum; index <= lastRowNum; index++) {
            List<String> rowValue = null;
            Row row = sheet.getRow(index);
            if (row != null) {
                rowValue = parserRow(row, beginIndex, endIndex);
            }
            result.add(rowValue);
        }
        return result;
    }

    private static List<String> parserRow(Row row, int beginIndex, int endIndex) {
        List<String> rowValue = new ArrayList<>();
        for (int index = beginIndex; index < endIndex; index++) {
            String cellValue = "";
            Cell cell = row.getCell(index);
            if (isMergedRegion(row.getSheet(), row, cell)) {
                cellValue = getMergedRegionValue(row.getSheet(), row, cell);
            } else {
                cellValue = getCellValue(cell);
            }
            rowValue.add(cellValue);
        }
        return rowValue;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            // 数字
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            // 字符串
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            // Boolean
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            // 错误
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    private static Workbook getWorkBook(InputStream is) throws IOException {
        Workbook workbook = new XSSFWorkbook(is);
        return workbook;
    }

    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param currentRow
     * @param cell
     * @return
     */
    private static String getMergedRegionValue(Sheet sheet, Row currentRow, Cell cell) {
        if (cell == null) {
            return "";
        }
        int sheetMergeCount = sheet.getNumMergedRegions();
        int row = currentRow.getRowNum();
        int column = cell.getColumnIndex();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {

                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);

                    return getCellValue(fCell);
                }
            }
        }
        return null;
    }

    /**
     * 判断指定的单元格是否是合并单元格
     *
     * @param sheet
     * @param
     * @param
     * @return
     */
    private static boolean isMergedRegion(Sheet sheet, Row currentRow, Cell cell) {
        if (cell == null) {
            return false;
        }
        int sheetMergeCount = sheet.getNumMergedRegions();
        int row = currentRow.getRowNum();
        int column = cell.getColumnIndex();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <T> Workbook createWorkBook(Map<String, List<T>> sheetDatas, Map<String, String> heads) {
        Workbook wb = new XSSFWorkbook();
        //
        for (String sheetName : sheetDatas.keySet()) {
            Sheet sheet = wb.createSheet(sheetName);
            List<T> datas = sheetDatas.get(sheetName);
            sheet.autoSizeColumn(1,true);
            fillSheet(sheet, datas, heads);
        }

        return wb;
    }

    public static <T> void fillSheet(Sheet sheet, List<T> datas, Map<String, String> heads) {
        //填充标题栏
        Row headRow = sheet.createRow(0);
        int indexCel = 0;
        for (String filedName : heads.keySet()) {
            String value = heads.get(filedName);
            Cell cell = headRow.createCell(indexCel++);
            CellStyle cellStyle = cell.getRow().getSheet().getWorkbook().createCellStyle();
            Font font = cell.getRow().getSheet().getWorkbook().createFont();
            //粗体显示
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            cellStyle.setFont(font);
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            fillCell(cell, value,cellStyle);
        }
        for (int index = 0; index < datas.size(); index++) {
            //从第二行开始
            Row row = sheet.createRow(index + 1);
            fillRow(row, datas.get(index), heads);
        }
    }

    private static void fillRow(Row row, Object object, Map<String, String> heads) {
        int index = 0;
        for (String filedName : heads.keySet()) {
            Cell cell = row.createCell(index++);
            try {
                String value = BeanUtils.getProperty(object, filedName);
                fillCell(cell, value,null);
            } catch (Exception e) {
                log.error("获取对象属性值出错", e);
            }
        }
    }

    private static void fillCell(Cell cell, String value,CellStyle cellStyle) {
        cell.setCellValue(value);
        if (cellStyle!=null){
            cell.setCellStyle(cellStyle);
        }
    }
}
