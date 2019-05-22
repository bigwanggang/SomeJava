package com.gustavo.dataStructureAndAlgo.pointOffer;


/**
 * Created by gustaov on 2019/5/22.
 */
public class HasSubtree {
    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        while (root1 != null && root2 != null) {
            if (root1.val > root2.val) {
                root1 = root1.right;
            } else if (root1.val < root2.val) {
                root1 = root1.left;
            } else {
                return compare(root1, root2);
            }
        }
        return true;
    }

    public static boolean compare(TreeNode r1, TreeNode r2) {
        while (r1 != null && r2 != null) {
            if (r1.val != r2.val) {
                return false;
            } else {
                return compare(r1.left, r2.left) && compare(r1.right, r2.right);
            }
        }
        return r1==r2;
    }

    public static void main(String[] args) {

    }
}
