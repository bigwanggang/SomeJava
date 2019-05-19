package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * Created by gustaov on 2019/5/19.
 */
public class Power {
    public static double Power(double base, int exponent) {
        boolean flag = exponent<0?true:false;
        exponent = exponent<0?-exponent:exponent;
        double result = 1;
        while (exponent-- >0){
            result = result*base;
        }
        return flag?1/result:result;
    }

    public static void main(String[] args) {
        System.out.println(Power(2, -3));
    }
}
