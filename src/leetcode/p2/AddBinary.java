package leetcode.p2;
/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 
 *	输入为非空字符串且只包含数字 1 和 0。
 *	
 *	示例 1:
 *	
 *	输入: a = "11", b = "1"
 *	输出: "100"
 *	示例 2:
 *	
 *	输入: a = "1010", b = "1011"
 *	输出: "10101"
 * @author ilongli
 * @email 351365415@qq.com
 */

public class AddBinary {

	public static void main(String[] args) {
		String a = "1";
		String b = "1";
		int sum = 2;
		System.out.println(sum & 1);
		System.out.println(Integer.MAX_VALUE << 2);
		System.out.println(addBinary(a, b));
	}
	
    public static String addBinary(String a, String b) {
    	int aL = a.length(), bL = b.length();
    	char[] res = aL > bL ? a.toCharArray() : b.toCharArray();
    	int num = Math.min(aL, bL);
    	int carry = 0;
    	// 先把重叠位置相加起来
    	for (int i = 1; i <= num; i++) {
    		int now = a.charAt(aL - i) + b.charAt(bL - i) + carry - 48;
    		if (now >= 50) {
    			now = now - 2;
    			carry = 1;
    		} else {
    			carry = 0;
    		}
    		res[res.length - i] = (char) now;
    	}
    	// 然后考虑剩余位置的进位
    	for (int j = res.length - num - 1; j >=0; j--) {
    		int now = res[j] + carry;
    		if (now == 50) {
    			now = 48;
    			carry = 1;
    		} else {
    			carry = 0;
    		}
    		res[j] = (char) now;
    		if (carry == 0) break;
    	}
        
    	if (carry == 0) return String.valueOf(res);
    	else return "1" + String.valueOf(res);
    }
    
    public static String addBinary2(String a, String b) {       
        
        int i = a.length() -1;
        int j = b.length() -1;
        
        int carry = 0;// 进位
        
        StringBuilder sb = new StringBuilder();
        
        while (i > -1 || j > -1) {
            
            int abit = i > -1 ? a.charAt(i) - '0' : 0;
            int bbit = j > -1 ? b.charAt(j) - '0' : 0;
            
            int sum = abit + bbit + carry;// 00, 01, 10, 11           
            
            sb.insert(0, sum & 1);
            carry = (sum >>> 1) & 1;
            
            i--;
            j--;
        }
        if (carry == 1)
            sb.insert(0, 1);
        return sb.toString();
    }
}
