package com.bigwanggang.binaryTree;

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
        root.left = r1;
        root.right = r2;
        r1.left = r3;
        r1.right = r4;
        r4.left = r5;

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

}
