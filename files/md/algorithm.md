### LUR 算法


### quick sort
- 试着写个快速排序，第一个版本由于递归条件没有弄好，产生了StackOverflowError异常，详见栗子：QuickSort.java \
  解决stackoverflowerror的版本：QuickSort1.java，但是还不能完成排序功能

### 排列组合
- 组合重在“选出”，而排列重在“选出后还再排列”
- next_permutation和pre_permutation, next_permutation分三步：1。 从后往前找到i,使得arr[i] < arr[i+1], 2. 从i往后找到大于arr[i],并且差值最小的，假设位置为k,然后交换i和k的值，3. i位置之后的数组reverse, 详见栗子：_031_NextPermutation
