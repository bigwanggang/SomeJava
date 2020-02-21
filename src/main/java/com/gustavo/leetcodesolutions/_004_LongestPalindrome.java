package com.gustavo.leetcodesolutions;

/**
 * Created by gustaov on 2020/2/18.
 */
public class _004_LongestPalindrome {

    public String longestPalindrome(String s) {
        if(s==null||s.length()==0){
            return s;
        }
        int len = s.length();
        int longestStart = 0, longest = 0;
        for (int i = 0; i < len; i++) {

            int r = i;
            while (r < len-1 && s.charAt(r + 1) == s.charAt(r)) {
                r++;
            }
            int banjing = 0;
            while (i - banjing > 0 && r + banjing < len - 1 && s.charAt(i - banjing - 1) == s.charAt(r + banjing + 1)) {
                banjing++;
            }
            if (2*banjing + r - i > longest) {
                longestStart = i -banjing;
                longest = 2*banjing + r - i;
            }
        }
        return s.substring(longestStart, longestStart + longest + 1);
    }

    public static void main(String[] args) {
        _004_LongestPalindrome solution = new _004_LongestPalindrome();
        System.out.println(solution.longestPalindrome(""));
    }
}
