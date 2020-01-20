package com.gustavo.leetcodesolutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 10183960 on 2018/9/3.
 */
public class _003_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            for (int j = i; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    if (set.size() > longest) {
                        longest = set.size();
                    }
                    break;
                } else {
                    set.add(s.charAt(j));
                }
            }
            longest = Math.max(longest, set.size());
        }
        return longest;
    }
    /*20200120*/
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int longest = 0;
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (set.contains(cur)) {
                longest = longest > set.size() ? longest : set.size();
                while (cur != s.charAt(begin)) {
                    set.remove(s.charAt(begin));
                    begin++;
                }
                begin++;
            } else {
                set.add(cur);
            }
        }
        return longest > set.size() ? longest : set.size();

    public static void main(String[] args) {
        String s = "helloworld";
        System.out.println(lengthOfLongestSubstring(s));

    }
}
