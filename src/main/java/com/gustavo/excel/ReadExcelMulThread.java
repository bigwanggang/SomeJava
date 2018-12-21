package com.gustavo.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadExcelMulThread {

    public static void main(String[] args) throws IOException {
        ConcurrentHashMap<String, Info> map = new ConcurrentHashMap<>();
        int sheetNum = 10;
        Workbook wb = Utils.getWorkbook(new File(WriteExcelMultiSheet.Path));

        CountDownLatch latch = new CountDownLatch(sheetNum);

        ExecutorService service = Executors.newFixedThreadPool(4);

        for (int i = 0; i < sheetNum; i++) {
            service.execute(new ReadExcelRunnable(wb.getSheet(i + ""), latch, map));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());
    }

    private static class ReadExcelRunnable implements Runnable {

        Sheet sheet;
        CountDownLatch latch;
        ConcurrentHashMap map;

        public ReadExcelRunnable(Sheet sheet, CountDownLatch latch, ConcurrentHashMap map) {
            this.sheet = sheet;
            this.latch = latch;
            this.map = map;
        }

        @Override
        public void run() {
            try {
                int lastrow = sheet.getLastRowNum();
                for (int i = 0; i <= lastrow; i++) {
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

                    map.put(name, info);

                }
            } finally {
                latch.countDown();
            }
        }
    }
}
