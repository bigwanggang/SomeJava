package com.gustavo.dataStructureAndAlgo.pointOffer;

/**
 * Created by gustaov on 2019/5/19.
 */
public class RectCover {
    public static int RectCover(int target) {
        if(target >=0 && target<=3)
            return target;
        int a = 2, b =3;
        int i=4;
        while (i++ <=target){
            int tmp = a+b;
            a=b;
            b=tmp;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(RectCover(1));
        System.out.println(RectCover(2));
        System.out.println(RectCover(3));
        System.out.println(RectCover(4));
    }
}
