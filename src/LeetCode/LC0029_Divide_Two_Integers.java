package LeetCode;
/*
<<  left shift
左移 = 乘2
左移并不改变x值，只是给个结果去evaluate，想改变x值需要左等 <<=

e.g. 多少个9加起来是45
1. 想知道的是，结果的商中，是1的比特位有哪些
2. 先找一个power of 2，是乘以9比45小的最大的 power of two，找的方法是反向使用bs, 比45小就一直乘，乘到比45大就往回移一个。这样知道bit最高位的1在哪里
3. 乘2的操作就是左移右移
4. 然后用45减这个power of 2 x 9的结果，差值再重复上面的步骤，找下一个power of 2
5. 一直到45减完
6. 如果有余数，判断方法是减完差值小于9

 */

public class LC0029_Divide_Two_Integers {
    public int divide(int dividend, int divisor){
        if (dividend == 0) throw new IllegalArgumentException();
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // overflow

        long dividendL = Math.abs(dividend);
        long divisorL = Math.abs(divisor);

        int res = 0;
        while (dividendL >= divisorL){
            int shift = 0;
            while (dividendL >= (divisorL << shift)){
                shift++;
            }
            res += 1 << (shift - 1);
            dividendL -= divisor << (shift - 1);
        }
        
        if  (dividendL >= 0 && divisorL > 0 || dividendL <= 0 && divisorL < 0) return res;
        else return -res;
    }
}
