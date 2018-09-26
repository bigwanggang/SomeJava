package com.bigwanggang.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ReadExcelToMem {
    static LinkedList<Info> list = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // jxl提供的Workbook类
        Workbook wb = Utils.getWorkbook(new File(WriteInfo.Path));
        // Excel的页签数量

        Sheet sheet = wb.getSheetAt(0);


        Long start = System.currentTimeMillis();


        executor.execute(new ReadExcelRunnable(countDownLatch, sheet, 0, 49999));

        countDownLatch.await();
        System.out.print("total time: ");
        System.out.println(System.currentTimeMillis() - start);
        executor.shutdown();

        System.out.println(list.size());

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

                    list.add(info);
                }
            } finally {
                latch.countDown();
            }
        }
    }
}
