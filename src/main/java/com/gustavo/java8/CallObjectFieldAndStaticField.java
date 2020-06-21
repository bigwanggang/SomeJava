package com.gustavo.java8;

public class CallObjectFieldAndStaticField {
    public static void main(String[] args) {
        CallObjectFieldAndStaticField a = new CallObjectFieldAndStaticField();
        System.out.println(a.outerNum);
        System.out.println(CallObjectFieldAndStaticField.outerStaticNum);
        a.testScopes();
        System.out.println(a.outerNum);
        System.out.println(CallObjectFieldAndStaticField.outerStaticNum);
    }

    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        stringConverter1.convert(1);
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
        stringConverter2.convert(2);
    }
}
