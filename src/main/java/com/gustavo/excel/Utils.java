package com.gustavo.excel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.gustavo.mysql.Utils.getConn;

public class Utils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    static final String USER = "root";
    static final String PASS = "123456";
    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";

    public static Workbook getWorkbook(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith(EXCEL_XLS)) {     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }


    public static int insert(Info info) {
        Connection conn = getConn(JDBC_DRIVER, DB_URL, USER, PASS);
        int i = 0;
        String sql = "insert into info (Name,age,height, phone, sex,location, job," +
                "hometown, education, major, message) values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
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
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
