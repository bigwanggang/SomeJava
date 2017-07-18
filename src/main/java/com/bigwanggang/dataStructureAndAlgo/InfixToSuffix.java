package com.bigwanggang.dataStructureAndAlgo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by gustaov on 2017/7/18.
 */
public class InfixToSuffix {
    public static Map<Character, Integer> PRIORITY = new HashMap<>();

    static {
        PRIORITY.put('(', 0);
        PRIORITY.put('*', 1);
        PRIORITY.put('+', 2);
    }

    public static String infixToSuffix(char[] chars) {
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == ')') {
                while (stack.peek() != '(')
                    sb.append(stack.pop());
                stack.pop();  //pop'('
            } else if (c == '(')
                stack.push(c);
            else if (c == '*' || c == '+') {
                while(!stack.isEmpty() && stack.peek()!='(' && PRIORITY.get(stack.peek()) <= PRIORITY.get(c))
                    sb.append(stack.pop());
                stack.push(c);
            }
            else
                sb.append(c);
        }
        while(!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "a+b*c+(d*e+f)*g";
        System.out.println(infixToSuffix(s.toCharArray()));
    }
}
