package com.gustavo.dataStructureAndAlgo;

import com.gustavo.dataStructureAndAlgo.pointOffer.TreeNode;
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
        List<Integer> list = new ArrayList<Integer>();
        TreeNode root = init();
        ReConstructBinaryTree.preOrder(root, list);
        System.out.println(list);
    }

    @Test
    public void inOrder() {
        List<Integer> list = new ArrayList<Integer>();
        TreeNode root = init();
        ReConstructBinaryTree.inOrder(root, list);
        System.out.println(list);
    }

    @Test
    public void levelOrder() {
        TreeNode root = init();
        LinkedList<Integer> list  = ReConstructBinaryTree.levelOrder(root);
        System.out.println(list);
    }

    private TreeNode init() {
        TreeNode node10 = new TreeNode(10);
        TreeNode node12 = new TreeNode(12);
        TreeNode node1 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
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
        TreeNode node = ReConstructBinaryTree.reConstructBinaryTree(pre,in);

        LinkedList<Integer> list  = ReConstructBinaryTree.levelOrder(node);
        System.out.println(list);
    }

}