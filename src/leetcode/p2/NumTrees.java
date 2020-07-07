package leetcode.p2;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *	示例:
 *	输入: 3
 *	输出: 5
 *	解释:
 *	给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *   1         3     3      2      1
 *    \       /     /      / \      \
 *     3     2     1      1   3      2
 *    /     /       \                 \
 *   2     1         2                 3
 * @author ilongli
 * @email 351365415@qq.com
 */
public class NumTrees {

	public static void main(String[] args) {
			
		
	}
	
	/**
	 * 定义：
	 * G(n):长度为n的序列的不同二叉搜索树个数
	 * F(i,n):以i为根的不同二叉搜索树个数(1 <= i <= n)
	 * 
	 * 特别的：G(0)=1	G(1)=1
	 * 
	 * G(n) = ∑(n,i=1)F(i,n)		----- (1)
	 * F(i,n) = G(i-1)*G(n-i)		----- (2)
	 * 将(1)(2)结合得到：
	 * G(n) = ∑(n,i=1)G(i-1)*G(n-i)	----- (3)
	 * 
	 * 实际上，(3)是卡塔兰数的递推关系：https://zh.wikipedia.org/wiki/%E5%8D%A1%E5%A1%94%E5%85%B0%E6%95%B0
	 */
    public static int numTrees(int n) {
    	int[] G = new int[n + 1];
    	G[0] = 1;
    	G[1] = 1;
    	
    	for (int i = 2; i <= n; i++) {
    		for (int j = 1; j <= i; j++) {
    			G[i] += G[j - 1] * G[i - j];
    		}
    	}
    	return G[n];
    }
    
    
    /**
     * 利用卡塔兰数的公式进行计算:
     * C(0)=1 C(n+1)=C(n) * 2(2n+1)/(n+2)
     * 推导见:https://zh.wikipedia.org/wiki/%E5%8D%A1%E5%A1%94%E5%85%B0%E6%95%B0
     */
    public static int numTrees2(int n) {
    	long C = 1;
    	for (int i = 0; i < n; i++) {
    		C = C * 2 * (2 * i + 1) / (i + 2);
    	}
    	
    	return (int) C;
    }
}
