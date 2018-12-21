package com.gustavo.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadExcelMultiThread {
    static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // jxl提供的Workbook类
        Workbook wb = Utils.getWorkbook(new File(WriteInfo.Path));
        // Excel的页签数量

        Sheet sheet = wb.getSheetAt(0);

        Long start = System.currentTimeMillis();

        for (int i = 0; i < 3; i++) {
            executor.execute(new ReadExcelRunnable(countDownLatch, sheet, 10000 * i, 10000 * i + 9999));
        }

        countDownLatch.await();
        System.out.print("total time: ");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(integer.get());
        executor.shutdown();

    }

    private static class ReadExcelRunnable implements Runnable {
        private CountDownLatch latch;
        private Sheet sheet;

        private int startline;
        private int endline;

        public ReadExcelRunnable(CountDownLatch latch, Sheet sheet, int startline, int endline) {
            this.latch = latch;
            this.sheet = sheet;
            this.startline = startline;
            this.endline = endline;
        }

        @Override
        public void run() {
            try {
                for (int i = startline; i <= endline; i++) {
//                    integer.incrementAndGet();
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
//                    System.out.println(info);

                }
            } finally {
                latch.countDown();
            }
        }
    }
}
