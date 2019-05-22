package com.gustavo.dataStructureAndAlgo.pointOffer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by gustaov on 2019/5/22.
 */
public class HasSubtreeTest {
    @Test
    public void compare() throws Exception {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(15);
        t1.left = t2;
        t1.right = t3;

        TreeNode a1 = new TreeNode(10);
        TreeNode a2 = new TreeNode(5);
        TreeNode a3 = new TreeNode(15);
        a1.left = a2;
        a1.right = a3;

        Assert.assertTrue(HasSubtree.compare(t1,a1));

    }

    @Test
    public void compare1() throws Exception {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(15);
        t1.left = t2;
        t1.right = t3;

        TreeNode a1 = new TreeNode(10);
        TreeNode a2 = new TreeNode(5);
        TreeNode a3 = new TreeNode(15);
        TreeNode a4 = new TreeNode(16);

        a1.left = a2;
        a1.right = a3;
        a3.right = a4;

        Assert.assertFalse(HasSubtree.compare(t1,a1));

    }

    @Test
    public void compare2() throws Exception {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(2);

        t1.left = t2;
        t1.right = t3;
        t2.right=t4;

        TreeNode a1 = new TreeNode(10);
        TreeNode a2 = new TreeNode(5);
        TreeNode a3 = new TreeNode(15);
        TreeNode a4 = new TreeNode(16);

        a1.left = a2;
        a1.right = a3;
        a3.right = a4;

        Assert.assertFalse(HasSubtree.compare(t1,a1));

    }

}