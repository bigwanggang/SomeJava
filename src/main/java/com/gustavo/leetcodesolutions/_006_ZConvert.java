package com.gustavo.leetcodesolutions;

/**
 * Created by gustaov on 2020/2/21.
 */
public class _006_ZConvert {
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        int len = s.length();
        char[] newChar = new char[len];
        int index = 0;
        int diff = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            if (isInRange(i, len)) {
                newChar[index++] = s.charAt(i);
            }
            if ((i == 0 || i == numRows - 1)) {
                int j = 1;
                while (isInRange(j * diff + i, len)) {
                    newChar[index++] = s.charAt(j * diff + i);
                    j++;
                }
            } else {
                int j = 1;
                while (isInRange(j * diff - i, len)) {
                    newChar[index++] = s.charAt(j * diff - i);
                    if (isInRange(j * diff + i, len)) {
                        newChar[index++] = s.charAt(j * diff + i);
                    }
                    j++;
                }
            }
        }
        return new String(newChar);
    }

    private boolean isInRange(int index, int len) {
        return index >= 0 && index < len;
    }

    public static void main(String[] args) {
        _006_ZConvert convert = new _006_ZConvert();
        System.out.println(convert.convert("abcdefghijklmn", 3));
    }
}
