package component.com.zbm.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

public class ExcelUtils {
    /**
     * @param fileName      文件名称
     * @param headers       表头
     * @param dataset       数据集
     * @param isSortDataSet 是否对数据排序
     * @param response      HttpServletResponse
     * @param mergeBasis    合并基准列 可选
     * @param mergeCells    要合并的列 可选
     * @param timeCells     时间列 可选
     * @throws IOException
     */
    public static void exportExelMerge(String fileName, final String[] headers, List<String[]> dataset,
                                       boolean isSortDataSet, HttpServletResponse response, final Integer[] mergeBasis, final Integer[] mergeCells,
                                       final Integer[] sumCells, final Integer[] timeCells) throws IOException {
        String title = "Sheet1";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        createExcelMerge(title, headers, dataset, isSortDataSet, response.getOutputStream(), mergeBasis, mergeCells,
                sumCells, timeCells);
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
    }

    public static void createExcelMerge_simple(String fileName, String title, final String[] headers, List<String[]> dataset, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        createExcelMerge_simple(title, headers, dataset, response.getOutputStream());
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();
    }


    /**
     * 简单表头 简单数据整合
     *
     * @param title   文件名称
     * @param headers 表头
     * @param dataset 数据组
     * @param out     输出流对象
     */
    public static void createExcelMerge_simple(String title, final String[] headers, List<String[]> dataset,
                                               OutputStream out) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(title);
            sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节
            HSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式
            HSSFCellStyle commonDataStyle;
            if (headers == null || headers.length <= 0) {
                return;
            }
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(headStyle);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
                sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
            }
            row = sheet.createRow(1);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(headStyle);
            }
            // 遍历集合数据，产生数据行
            //commonDataStyle1
            for (int i = 0; i < dataset.size(); i++) {
                String[] it = dataset.get(i);
                row = sheet.createRow(i + 2);
                if (i % 2 == 0) {
                    commonDataStyle = createCommonDataStyle(workbook);
                } else {
                    commonDataStyle = createCommonDataStyle1(workbook);
                }
                for (int j = 0; j < it.length; j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(commonDataStyle);
                    cell.setCellValue(it[j]);
                }
            }
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单表头 复杂数据整合
     *
     * @param title         文件名称
     * @param headers       表头
     * @param dataset       数据集
     * @param isSortDataSet 是否对数据排序
     * @param out           OutputStream
     * @param mergeBasis    合并基准列 可选
     * @param mergeCells    要合并的列
     * @param sumCells      要求和的列
     * @param timeCells     时间列 可选
     */
    public static void createExcelMerge(String title, final String[] headers, List<String[]> dataset,
                                        boolean isSortDataSet, OutputStream out, final Integer[] mergeBasis, final Integer[] mergeCells,
                                        final Integer[] sumCells, final Integer[] timeCells) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);

        sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节

        HSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式
        HSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式
        HSSFCellStyle numStyle = createNumStyle(workbook); // 生成数字类型保留两位小数样式
        HSSFCellStyle sumRowStyle = createSumRowStyle(workbook); // 生成合计行样式

        if (headers == null || headers.length <= 0) {
            return;
        }

        HSSFRow row = sheet.createRow(0); // 产生表格标题行
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        if (isSortDataSet && mergeBasis != null && mergeBasis.length > 0) {
            // 是否排序数据
            Collections.sort(dataset, new Comparator<String[]>() {
                public int compare(String[] o1, String[] o2) {
                    String s1 = "";
                    String s2 = "";
                    for (int i = 0; i < mergeBasis.length; i++) {
                        s1 += (o1[mergeBasis[i].intValue()] + Character.valueOf((char) 127).toString());
                        s2 += (o2[mergeBasis[i].intValue()] + Character.valueOf((char) 127).toString());
                    }
                    if (timeCells != null && timeCells.length > 0) {
                        for (int i = 0; i < timeCells.length; i++) {
                            s1 += o1[timeCells[i].intValue()];
                            s2 += o2[timeCells[i].intValue()];
                        }
                    }
                    if (s1.compareTo(s2) < 0) {
                        return -1;
                    } else if (s1.compareTo(s2) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        // 遍历集合数据，产生数据行
        Iterator<String[]> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            String[] dataSources = it.next();
            for (int i = 0; i < dataSources.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(commonDataStyle);
                cell.setCellValue(dataSources[i]);
            }
        }
        try {
            if (mergeBasis != null && mergeBasis.length > 0 && mergeCells != null && mergeCells.length > 0) {
                for (int i = 0; i < mergeCells.length; i++) {
                    mergedRegion(sheet, mergeCells[i], 1, sheet.getLastRowNum(), workbook, mergeBasis);
                }
            }

            if (sumCells != null && sumCells.length > 0) {
                createSumRow(sheet, row, headers, sumCells, sumRowStyle, numStyle);
            }

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复杂表头 复杂数据整合
     *
     * @param title         文件名称
     * @param headers       表头
     * @param dataset       数据集
     * @param isSortDataSet 是否对数据排序
     * @param out           OutputStream
     * @param mergeBasis    合并基准列 可选
     * @param mergeCells    要合并的列
     * @param sumCells      要求和的列
     * @param timeCells     时间列 可选
     */
    public static void createExcelMerge_complex(String title, final List<String[]> headers, List<String[]> dataset,
                                                boolean isSortDataSet, OutputStream out, final Integer[] mergeBasis, final Integer[] mergeCells,
                                                final Integer[] sumCells, final Integer[] timeCells) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);

        sheet.setDefaultColumnWidth(15); // 设置表格默认列宽度为15个字节

        HSSFCellStyle headStyle = createHeadStyle(workbook); // 生成头部样式
        HSSFCellStyle commonDataStyle = createCommonDataStyle(workbook); // 生成一般数据样式
        HSSFCellStyle numStyle = createNumStyle(workbook); // 生成数字类型保留两位小数样式
        HSSFCellStyle sumRowStyle = createSumRowStyle(workbook); // 生成合计行样式

        if (headers == null || headers.size() <= 0) {
            return;
        }
        HSSFFont headFont = workbook.createFont();
        headFont.setColor(HSSFColor.BROWN.index);
        headFont.setFontHeightInPoints((short) 10);
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        CellStyle style = workbook.createCellStyle();
        // style.setFont(fontHead);
        style.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // style.setWrapText(true);// 设置自动换行
        style.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
        style.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
        style.setBorderTop(CellStyle.BORDER_THIN);// 上边框
        style.setBorderRight(CellStyle.BORDER_THIN);// 右边框
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 把字体应用到当前的样式
        style.setFont(headFont);
        // 这个就是合并单元格
        // 横行竖列
        // 参数说明：1：开始行 2：结束行 3：开始列 4：结束列
        // 比如我要合并 第二行到第四行的 第六列到第八列 sheet.addMergedRegion(new CellRangeAddress(1,3,5,7));
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 1, (short) 0, (short) 0));// 第一行到第二行 第1列
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 1, (short) 1, (short) 1));
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 1, (short) 2, (short) 2));
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 1, (short) 3, (short) 3));
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 0, (short) 4, (short) 10));
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 1, (short) 11, (short) 11));
        sheet.addMergedRegion(new CellRangeAddress((short) 0, (short) 1, (short) 12, (short) 12));
        // sheet.addMergedRegion(new CellRangeAddress(0,2,1,2));
        HSSFRow row = null;
        for (int i = 0; i < headers.size(); i++) {
            row = sheet.createRow(i);
            for (int j = 0; j < headers.get(i).length; j++) {
                HSSFCell cell = row.createCell(j);
                if (headers.get(i)[j].equals("")) {
                    cell.setCellStyle(headStyle);
                } else {
                    cell.setCellValue(headers.get(i)[j]);
                    cell.setCellStyle(headStyle);
                }
            }
        }
        if (isSortDataSet && mergeBasis != null && mergeBasis.length > 0) {
            // 是否排序数据
            Collections.sort(dataset, new Comparator<String[]>() {
                public int compare(String[] o1, String[] o2) {
                    String s1 = "";
                    String s2 = "";
                    for (int i = 0; i < mergeBasis.length; i++) {
                        s1 += (o1[mergeBasis[i].intValue()] + Character.valueOf((char) 127).toString());
                        s2 += (o2[mergeBasis[i].intValue()] + Character.valueOf((char) 127).toString());
                    }
                    if (timeCells != null && timeCells.length > 0) {
                        for (int i = 0; i < timeCells.length; i++) {
                            s1 += o1[timeCells[i].intValue()];
                            s2 += o2[timeCells[i].intValue()];
                        }
                    }
                    if (s1.compareTo(s2) < 0) {
                        return -1;
                    } else if (s1.compareTo(s2) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        // 遍历集合数据，产生数据行
        Iterator<String[]> it = dataset.iterator();
        int index = headers.size() - 1;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            String[] dataSources = it.next();
            for (int i = 0; i < dataSources.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(commonDataStyle);
                cell.setCellValue(dataSources[i]);
            }
        }
        try {
            if (mergeBasis != null && mergeBasis.length > 0 && mergeCells != null && mergeCells.length > 0) {
                for (int i = 0; i < mergeCells.length; i++) {
                    mergedRegion(sheet, mergeCells[i], 1, sheet.getLastRowNum(), workbook, mergeBasis);
                }
            }
            /*
             * if (sumCells != null && sumCells.length > 0) { createSumRow(sheet, row,
             * headers, sumCells, sumRowStyle, numStyle); }
             */

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建合计行
     *
     * @param sheet
     * @param row
     * @param headers
     * @param sumCells
     * @param sumRowStyle
     * @param numStyle
     */
    private static void createSumRow(HSSFSheet sheet, HSSFRow row, final String[] headers, final Integer[] sumCells,
                                     HSSFCellStyle sumRowStyle, HSSFCellStyle numStyle) {
        row = sheet.createRow(sheet.getLastRowNum() + 1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(sumRowStyle);
        }
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < sumCells.length; j++) {
                sheet.getRow(i).getCell(sumCells[j])
                        .setCellValue(Double.parseDouble(sheet.getRow(i).getCell(sumCells[j]).getStringCellValue()));
                sheet.getRow(i).getCell(sumCells[j]).setCellStyle(numStyle);
            }
        }
        HSSFCell sumCell = row.getCell(0);
        sumCell.setCellValue("合计：");
        String sumFunctionStr = null;
        for (int i = 0; i < sumCells.length; i++) {
            sumFunctionStr = "SUM(" + CellReference.convertNumToColString(sumCells[i]) + "2:"
                    + CellReference.convertNumToColString(sumCells[i]) + sheet.getLastRowNum() + ")";
            row.getCell(sumCells[i]).setCellFormula(sumFunctionStr);
        }
    }

    /**
     * 合并单元格
     *
     * @param sheet
     * @param cellLine
     * @param startRow
     * @param endRow
     * @param workbook
     * @param mergeBasis
     */
    private static void mergedRegion(HSSFSheet sheet, int cellLine, int startRow, int endRow, HSSFWorkbook workbook,
                                     Integer[] mergeBasis) {
        HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平
        String s_will = sheet.getRow(startRow).getCell(cellLine).getStringCellValue(); // 获取第一行的数据,以便后面进行比较
        int count = 0;
        Set<Integer> set = new HashSet<Integer>();
        CollectionUtils.addAll(set, mergeBasis);
        for (int i = 2; i <= endRow; i++) {
            String s_current = sheet.getRow(i).getCell(cellLine).getStringCellValue();
            if (s_will.equals(s_current)) {
                boolean isMerge = true;
                if (!set.contains(cellLine)) {// 如果不是作为基准列的列 需要所有基准列都相同
                    for (int j = 0; j < mergeBasis.length; j++) {
                        if (!sheet.getRow(i).getCell(mergeBasis[j]).getStringCellValue()
                                .equals(sheet.getRow(i - 1).getCell(mergeBasis[j]).getStringCellValue())) {
                            isMerge = false;
                        }
                    }
                } else {// 如果作为基准列的列 只需要比较列号比本列号小的列相同
                    for (int j = 0; j < mergeBasis.length && mergeBasis[j] < cellLine; j++) {
                        if (!sheet.getRow(i).getCell(mergeBasis[j]).getStringCellValue()
                                .equals(sheet.getRow(i - 1).getCell(mergeBasis[j]).getStringCellValue())) {
                            isMerge = false;
                        }
                    }
                }
                if (isMerge) {
                    count++;
                } else {
                    sheet.addMergedRegion(new CellRangeAddress(startRow, startRow + count, cellLine, cellLine));
                    startRow = i;
                    s_will = s_current;
                    count = 0;
                }
            } else {
                sheet.addMergedRegion(new CellRangeAddress(startRow, startRow + count, cellLine, cellLine));
                startRow = i;
                s_will = s_current;
                count = 0;
            }
            if (i == endRow && count > 0) {
                sheet.addMergedRegion(new CellRangeAddress(startRow, startRow + count, cellLine, cellLine));
            }
        }
    }

    /**
     * 标题单元格样式
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createHeadStyle(HSSFWorkbook workbook) {
        HSSFFont headFont = workbook.createFont();
        headFont.setColor(HSSFColor.LEMON_CHIFFON.index);
        headFont.setFontHeightInPoints((short) 10);
        //headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workbook.createCellStyle();
        // style.setFont(fontHead);
        style.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.DARK_TEAL.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillBackgroundColor(HSSFColor.DARK_TEAL.index);
        // style.setWrapText(true);// 设置自动换行
        style.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
        style.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
        style.setBorderTop(CellStyle.BORDER_THIN);// 上边框
        style.setBorderRight(CellStyle.BORDER_THIN);// 右边框
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 把字体应用到当前的样式
        style.setFont(headFont);
        return style;
    }


    /**
     * 合计行单元格样式
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createSumRowStyle(HSSFWorkbook workbook) {
        // 合计行单元格样式
        HSSFCellStyle sumRowStyle = workbook.createCellStyle();
        sumRowStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        sumRowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        sumRowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        sumRowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        sumRowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        sumRowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        sumRowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        sumRowStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        // 合计行单元格字体
        HSSFFont sumRowFont = workbook.createFont();
        sumRowFont.setColor(HSSFColor.VIOLET.index);
        sumRowFont.setFontHeightInPoints((short) 12);
        sumRowFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        sumRowStyle.setFont(sumRowFont);
        return sumRowStyle;
    }

    /**
     * 普通数据单元格样式
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createCommonDataStyle(HSSFWorkbook workbook) {
        // 普通数据单元格样式
        HSSFCellStyle commonDataStyle = workbook.createCellStyle();
        commonDataStyle.setFillForegroundColor(HSSFColor.MAROON.index);
        commonDataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        commonDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        commonDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 普通数据单元格字体
        HSSFFont commonDataFont = workbook.createFont();
        commonDataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        commonDataStyle.setFont(commonDataFont);
        return commonDataStyle;
    }


    /**
     * 普通数据单元格样式
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createCommonDataStyle1(HSSFWorkbook workbook) {
        // 普通数据单元格样式
        HSSFCellStyle commonDataStyle = workbook.createCellStyle();
        commonDataStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
        commonDataStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        commonDataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        commonDataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        commonDataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 普通数据单元格字体
        HSSFFont commonDataFont = workbook.createFont();
        commonDataFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        commonDataStyle.setFont(commonDataFont);
        return commonDataStyle;
    }

    /**
     * 自定义保留两位小数数字单元格格式
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle createNumStyle(HSSFWorkbook workbook) {
        // 自定义保留两位小数数字单元格格式
        HSSFCellStyle numStyle = workbook.createCellStyle();
        numStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        numStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        numStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        numStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        numStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        numStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        numStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        numStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        numStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        // 自定义保留两位小数数字单元格字体
        HSSFFont numFont = workbook.createFont();
        numFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        numStyle.setFont(numFont);
        return numStyle;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            OutputStream out = new FileOutputStream("C:/Users/xshel/Desktop/" + System.currentTimeMillis() + ".xls");
            // createExcelMerge 方法数据
            String[] headers = {"大区", "部门", "金额", "数量", "日期"};
            List<String[]> dataset = new ArrayList<String[]>();
            dataset.add(new String[]{"华东", "部门3", "35", "1", "2015-01-01"});
            dataset.add(new String[]{"华北", "部门1", "20", "1", "2015-01-02"});
            dataset.add(new String[]{"华北", "部门2", "25", "1", "2015-01-03"});
            dataset.add(new String[]{"华北", "部门5", "25", "1", "2015-01-04"});
            dataset.add(new String[]{"华南", "部门1", "15", "1", "2015-01-05"});
            ExcelUtils.createExcelMerge_simple("測試.xls", headers, dataset, out);

            // =================================================================================================
            // createExcelMerge_complex 方法数据
            List<String[]> headerList = new ArrayList<>();
            String[] str1 = {"标题1", "标题2", "标题3", "标题4", "标题5", "", "", "", "", "", "", "标题12", "标题14"};
            String[] str2 = {"", "", "", "", "标题5", "标题6", "标题7", "标题8", "标题9", "标题10", "标题11", "", ""};
            headerList.add(str1);
            headerList.add(str2);
            List<String[]> datasets = new ArrayList<String[]>();
            datasets.add(new String[]{"华南", "部门3", "30", "1", "2015-01-18", "123", "123", "123", "123", "123", "123",
                    "123", "123"});
            datasets.add(new String[]{"华南", "部门3", "30", "1", "2015-01-18", "123", "123", "123", "123", "123", "123",
                    "123", "123"});
            datasets.add(new String[]{"华北", "部门5", "30", "1", "2015-01-18", "123", "123", "123", "123", "123", "123",
                    "123", "123"});
            datasets.add(new String[]{"华北", "部门5", "30", "1", "2015-01-18", "123", "123", "123", "123", "123", "123",
                    "123", "123"});
		/*	ExcelUtils.createExcelMerge_complex("测试.xls", headerList, datasets, true,
			out, new Integer[] { 0, 1 },new Integer[] { 0, 1 }, new Integer[] { 2 }, new
			Integer[] { 4 });*/
            out.close();
            JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
