### LUR 算法


### quick sort
- 试着写个快速排序，第一个版本由于递归条件没有弄好，产生了StackOverflowError异常，详见栗子：QuickSort.java \
  解决stackoverflowerror的版本：QuickSort1.java，但是还不能完成排序功能
- quick sort 还是按算法导论的来吧，多写几遍就慢慢理解了, 算法导论中的快排算法不算最优，因为要swap的次数太多，网上很多用两个指针，一个指向头一个指向尾，这样的方式更好一些

### 排列组合
- 组合重在“选出”，而排列重在“选出后还再排列”
- next_permutation和pre_permutation, next_permutation分三步：1。 从后往前找到i,使得arr[i] < arr[i+1], 2. 从i往后找到大于arr[i],并且差值最小的，假设位置为k,然后交换i和k的值，3. i位置之后的数组reverse, 详见栗子：_031_NextPermutation， 
pre_permutation的算法流程正好和next_permutation相反，1. 从前往后找到i使得arr[i] > arr[i+1], 2. 从i往后找到小于arr[i]，并且差值最小的，假设找到位置为k,交换i和k的值，3. i位置之后的部分数组reverse，详见栗子：PrePermutation

### 剑指offer的在线编程
https://www.nowcoder.com/ta/coding-interviews?page=1

