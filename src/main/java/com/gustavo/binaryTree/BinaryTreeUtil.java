package com.gustavo.binaryTree;

public class BinaryTreeUtil {
    public static int hight(BinaryTree root) {
        if (root == null)
            return 0;
        return Math.max(hight(root.left), hight(root.right)) + 1;
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree('0');
        BinaryTree r1 = new BinaryTree('1');
        BinaryTree r2 = new BinaryTree('2');
        BinaryTree r3 = new BinaryTree('3');
        BinaryTree r4 = new BinaryTree('4');
        BinaryTree r5 = new BinaryTree('5');
        BinaryTree r6 = new BinaryTree('6');
        BinaryTree r7 = new BinaryTree('7');
        root.left = r1;
        root.right = r2;
        r1.left = r3;
        r1.right = r4;
        r2.left = r5;
        r2.right = r6;

        printBinaryTree(root);
        System.out.println("\npreOrder:");
        preOrder(root);
        System.out.println("\ninorder:");
        inOrder(root);
        System.out.println("\npostorder:");
        postOrder(root);
    }

    public static void contructChart(char[][] chart, BinaryTree node, int level, int x, int y) {
        chart[y][x] = (char) node.element;
        if (level == 0) {
            return;
        }
        int lenthOfLine = heightOfLine(level);
        if (node.left != null) {
            for (int i = 1; i <= lenthOfLine; i++) {
                chart[y + i][x - i] = '/';
            }
            contructChart(chart, node.left, level - 1, x - lenthOfLine - 1, y + lenthOfLine + 1);
        }
        if (node.right != null) {
            for (int i = 1; i <= lenthOfLine; i++) {
                chart[y + i][x + i] = '\\';
            }
            contructChart(chart, node.right, level - 1, x + lenthOfLine + 1, y + lenthOfLine + 1);
        }
    }

    /**
     * 控制台打印二叉树
     * @param root
     */
    public static void printBinaryTree(BinaryTree root) {
        int hight = hight(root) - 1;

        int topToLeft = distenceFromLeft(hight);
        char[][] chart = new char[topToLeft + 1][2 * topToLeft + 1];
        contructChart(chart, root, hight, topToLeft, 0);
        for (int i = 0; i < chart.length; i++) {
            for (int j = 0; j < chart[0].length; j++) {
                System.out.print(chart[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * @param level
     * @return
     */
    public static int distenceFromLeft(int level) {
        if (level == 0)
            return 0;
        return (int) Math.pow(2, level - 1) + 1 + distenceFromLeft(level - 1);
    }

    public static int heightOfLine(int level) {
        return (int) Math.pow(2, level - 1);
    }

    /**
     * 前序遍历
     * @param root 跟节点
     */
    public static void preOrder(BinaryTree root) {
        if(root == null)
            return;
        System.out.print(root.element + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * @param root 跟节点
     */
    public static void inOrder(BinaryTree root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.element + " ");
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * @param root 跟节点
     */
    public static void postOrder(BinaryTree root) {
        if(root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.element + " ");
    }
}
