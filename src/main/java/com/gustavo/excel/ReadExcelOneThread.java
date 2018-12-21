package com.gustavo.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;

public class ReadExcelOneThread {
    public static void main(String[] args) throws Exception {
        Workbook wb = Utils.getWorkbook(new File(WriteInfo.Path));
        // Excel的页签数量

        Sheet sheet = wb.getSheetAt(0);


        Long start = System.currentTimeMillis();
        System.out.println(sheet.getLastRowNum());

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {

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

//            System.out.println(info);

        }

        System.out.println(System.currentTimeMillis() - start);


    }
}

