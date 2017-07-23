package com.bigwanggang.permutationCombination;

import java.util.ArrayList;
import java.util.List;

public class PermutationDFS {
    List<List<Integer>> result;
    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        visited = new boolean[nums.length];
        permuteDFS(nums, out);
        return result;
    }

    void permuteDFS(int[] nums, List<Integer> out) {
        if (out.size() == nums.length) {
            List<Integer> temp = new ArrayList<Integer>(out);
            result.add(temp);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (visited[i])
                    continue;
                visited[i] = true;
                out.add(nums[i]);
                permuteDFS(nums, out);
                out.remove(out.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3,4,5};
        PermutationDFS permutation = new PermutationDFS();
        System.out.println(permutation.permute(a));
        List<Integer> temp = new ArrayList<Integer>();
    }
}
