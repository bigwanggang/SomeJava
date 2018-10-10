package com.bigwanggang.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.bigwanggang.excel.Utils.*;
import static com.bigwanggang.mysql.Utils.getConn;

public class ReadExcel {
    static String sql = "insert into info (Name,age,height, phone, sex,location, job," +
            "hometown, education, major, message) values(?,?,?,?,?,?,?,?,?,?,?)";

    public static void main(String[] args) throws Exception {


        // jxl提供的Workbook类
        Workbook wb = Utils.getWorkbook(new File(WriteInfo.Path));
        // Excel的页签数量

        Sheet sheet = wb.getSheetAt(0);

        int firstRow = sheet.getFirstRowNum();
        int lastRow = sheet.getLastRowNum();

        Long start = System.currentTimeMillis();
        Connection conn = getConn(JDBC_DRIVER, DB_URL, USER, PASS);
        for (int i = firstRow; i <= lastRow; i++) {
            System.out.println("row" + i);
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


            PreparedStatement pstmt = null;

            pstmt = (PreparedStatement) conn.prepareStatement(sql);


            pstmt.setString(1, info.getName());
            pstmt.setString(2, info.getAge());
            pstmt.setString(3, info.getHeight());
            pstmt.setString(4, info.getPhone());
            pstmt.setString(5, info.getSex());
            pstmt.setString(6, info.getLocation());
            pstmt.setString(7, info.getJob());
            pstmt.setString(8, info.getHometown());
            pstmt.setString(9, info.getEducation());
            pstmt.setString(10, info.getMajor());
            pstmt.setString(11, info.getMessage());
            pstmt.executeUpdate();
        }
        Long end = System.currentTimeMillis();
        System.out.println("total time: " + String.valueOf(end - start));
    }
}
