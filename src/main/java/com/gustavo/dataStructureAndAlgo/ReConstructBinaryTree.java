package com.gustavo.dataStructureAndAlgo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gustaov on 2019/5/15.
 */
public class ReConstructBinaryTree {
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode construct(int[] pre, int preL, int preR, int[] in, int inL, int inR) {
        int rootVal = pre[preL];
        System.out.println(rootVal);
        int index = find(in, inL, inR, rootVal);
        if (index == -1 || preL >=preR || inL >= inR) {
            return null;
        }
        TreeNode node = new TreeNode(rootVal);
        node.left = construct(pre, preL + 1, preL + index - inL, in, inL, index - 1);
        node.right = construct(pre, preL + index - inL + 1, preR, in, index + 1, inR);
        return node;
    }

    private static int find(int[] in, int left, int right, int num) {
        for (int i = left; i <= right; i++) {
            if (in[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public static void preOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preOrder(root.left, list);
            preOrder(root.right, list);
        }

    }

    public static void inOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }

    public static void postOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postOrder(root.left, list);
            postOrder(root.right, list);
            list.add(root.val);
        }
    }

    /**
     * 层次遍历
     *
     * @param root
     */
    public static LinkedList<Integer> levelOrder(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.removeFirst();
            if (node != null) {
                list.add(node.val);
                treeNodes.add(node.left);
                treeNodes.add(node.right);
            }
        }
        return list;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
