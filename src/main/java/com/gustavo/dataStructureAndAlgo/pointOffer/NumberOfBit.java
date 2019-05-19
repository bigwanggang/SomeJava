package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * 剑指offer
 * 面试题10：二进制中1的个数
 * Created by gustaov on 2019/5/8.
 */
public class NumberOfBit {
    public static int numberOf1(int i){
        int num = 0;
        while (i!=0){
            if((i&1) == 1){
                num++;
            }
            i = i>>>1;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1(-1));
    }
}
