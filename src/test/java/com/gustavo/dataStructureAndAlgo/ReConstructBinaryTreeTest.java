package com.gustavo.dataStructureAndAlgo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gustaov on 2019/5/15.
 */
public class ReConstructBinaryTreeTest {
    @Test
    public void preOrder() throws Exception {
        List<Integer> list = new ArrayList<>();
        ReConstructBinaryTree.TreeNode root = init();
        ReConstructBinaryTree.preOrder(root, list);
        System.out.println(list);
    }

    @Test
    public void inOrder() {
        List<Integer> list = new ArrayList<>();
        ReConstructBinaryTree.TreeNode root = init();
        ReConstructBinaryTree.inOrder(root, list);
        System.out.println(list);
    }

    @Test
    public void levelOrder() {
        ReConstructBinaryTree.TreeNode root = init();
        LinkedList<Integer> list  = ReConstructBinaryTree.levelOrder(root);
        System.out.println(list);
    }

    private ReConstructBinaryTree.TreeNode init() {
        ReConstructBinaryTree.TreeNode node10 = new ReConstructBinaryTree.TreeNode(10);
        ReConstructBinaryTree.TreeNode node12 = new ReConstructBinaryTree.TreeNode(12);
        ReConstructBinaryTree.TreeNode node1 = new ReConstructBinaryTree.TreeNode(1);
        ReConstructBinaryTree.TreeNode node5 = new ReConstructBinaryTree.TreeNode(5);
        ReConstructBinaryTree.TreeNode node8 = new ReConstructBinaryTree.TreeNode(8);
        ReConstructBinaryTree.TreeNode node7 = new ReConstructBinaryTree.TreeNode(7);
        ReConstructBinaryTree.TreeNode node6 = new ReConstructBinaryTree.TreeNode(6);
        ReConstructBinaryTree.TreeNode node9 = new ReConstructBinaryTree.TreeNode(9);
        ReConstructBinaryTree.TreeNode node3 = new ReConstructBinaryTree.TreeNode(3);
        ReConstructBinaryTree.TreeNode node4 = new ReConstructBinaryTree.TreeNode(4);
        node10.left = node12;
        node10.right = node1;
        node12.left = node5;
        node12.right = node8;
        node5.left = node9;
        node5.right = node3;
        node1.left = node7;
        node1.right = node6;
        node7.left = node4;
        return node10;
    }

    @Test
    public void reConstructBinaryTree(){
        int[]pre = {10,12,5,9,3,8,1,7,4,6};
        int[] in={9,5,3,12,8,10,4,7,1,6};
        ReConstructBinaryTree.TreeNode node = ReConstructBinaryTree.reConstructBinaryTree(pre,in);

        LinkedList<Integer> list  = ReConstructBinaryTree.levelOrder(node);
        System.out.println(list);
    }

}