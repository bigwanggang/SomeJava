package com.bigwanggang.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcel {
    public static void main(String[] args) {
        try {

            // jxl提供的Workbook类
            Workbook wb = Utils.getWorkbook(new File(WriteInfo.Path));
            // Excel的页签数量

            Sheet sheet = wb.getSheetAt(0);

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            Long start = System.currentTimeMillis();
            for (int i = firstRow; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                String name = row.getCell(0).getStringCellValue();
                String age = row.getCell(1).getStringCellValue();
                String height = row.getCell(2).getStringCellValue();
                String phone = row.getCell(3).getStringCellValue();
                String sex = row.getCell(4).getStringCellValue();
                String location = row.getCell(5).getStringCellValue();
                String job = row.getCell(6).getStringCellValue();
                String hometown = row.getCell(7).getStringCellValue();
                String education = row.getCell(8).getStringCellValue();
                String major = row.getCell(9).getStringCellValue();
                String message = row.getCell(10).getStringCellValue();
                Info info = new Info(name, age, height, phone, sex, location,
                        job, hometown, education, major, message);

                Utils.insert(info);
            }

            Long end = System.currentTimeMillis();
            System.out.println("total time: " + String.valueOf(end - start));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
