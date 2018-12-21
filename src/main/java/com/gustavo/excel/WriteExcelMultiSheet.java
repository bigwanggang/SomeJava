package com.gustavo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static com.gustavo.excel.Utils.getWorkbook;

public class WriteExcelMultiSheet {
    public static final String Path = "D:/writeExcel.xlsx";

    public static void main(String[] args) {
        Map<String, String> dataMap = new HashMap<String, String>();

        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList("Name", "age", "height", "phone", "sex", "location", "job", "hometown", "education",
                "major", "desc"));
        writeExcel(list, 30, Path);
    }

    public static void writeExcel(List<String> dataList, int cloumnCount, String finalXlsxPath) {
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbook(finalXlsxFile);

            // sheet 对应一个工作页
            for (int j = 0; j < 10; j++) {
                Sheet sheet = workBook.getSheet(j+"");
                /**
                 * 删除原有数据，除了属性列
                 */
                int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算

                for (int i = 1; i <= rowNumber; i++) {
                    Row row = sheet.getRow(i);
                    sheet.removeRow(row);
                }
                // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
                out = new FileOutputStream(finalXlsxPath);
                workBook.write(out);
                /**
                 * 往Excel中写新数据
                 */

                // 创建一行：从第二行开始，跳过属性列
                for (int i = 0; i < 500; i++) {
                    Row row = sheet.createRow(i);

                    for (int k = 0; k < dataList.size(); k++) {
                        // 在一行内循环
                        Cell first = row.createCell(k);
                        first.setCellValue(dataList.get(k) + j + "-" + i);

                    }
                }
            }


            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }
}
