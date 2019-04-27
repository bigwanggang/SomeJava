package com.gustavo.dataStructureAndAlgo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SuffixToNodeTree {
    public static Set<Character> SYMBOL = new HashSet<Character>(Arrays.asList('*', '+'));

    public static BinaryNode suffixToBinaryNode(char[] chars) {
        Stack<BinaryNode> nodeStack = new Stack<BinaryNode>();
        for (char c : chars) {
            if (SYMBOL.contains(c)) {
                BinaryNode n1 = nodeStack.pop();
                BinaryNode n2 = nodeStack.pop();
                BinaryNode theNode = new BinaryNode(c);
                theNode.left = n2;
                theNode.right = n1;
                nodeStack.push(theNode);
            } else {
                nodeStack.push(new BinaryNode(c));
            }
        }
        return nodeStack.pop();
    }

    private static class BinaryNode {
        public BinaryNode(Object element) {
            this.element = element;
        }

        Object element;
        BinaryNode left;
        BinaryNode right;
    }

    public static void main(String[] args) {
        String s = "abc*+";
        BinaryNode node = suffixToBinaryNode(s.toCharArray());

    }
}
