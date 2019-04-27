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

    public static void main(String[] args) {
        String s = "helloworld";
        System.out.println(lengthOfLongestSubstring(s));

    }
}
