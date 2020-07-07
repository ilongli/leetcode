package leetcode.p2;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 
 *	你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *	
 *	要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *	
 *	文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *	
 *	说明:
 *	
 *	单词是指由非空格字符组成的字符序列。
 *	每个单词的长度大于 0，小于等于 maxWidth。
 *	输入单词数组 words 至少包含一个单词。
 *	示例:
 *	
 *	输入:
 *	words = ["This", "is", "an", "example", "of", "text", "justification."]
 *	maxWidth = 16
 *	输出:
 *	[
 *	   "This    is    an",
 *	   "example  of text",
 *	   "justification.  "
 *	]
 *	示例 2:
 *	
 *	输入:
 *	words = ["What","must","be","acknowledgment","shall","be"]
 *	maxWidth = 16
 *	输出:
 *	[
 *	  "What   must   be",
 *	  "acknowledgment  ",
 *	  "shall be        "
 *	]
 *	解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *	     因为最后一行应为左对齐，而不是左右两端对齐。       
 *	     第二行同样为左对齐，这是因为这行只包含一个单词。
 *	示例 3:
 *	
 *	输入:
 *	words = ["Science","is","what","we","understand","well","enough","to","explain",
 *	         "to","a","computer.","Art","is","everything","else","we","do"]
 *	maxWidth = 20
 *	输出:
 *	[
 *	  "Science  is  what we",
 *	  "understand      well",
 *	  "enough to explain to",
 *	  "a  computer.  Art is",
 *	  "everything  else  we",
 *	  "do                  "
 *	]
 * @author ilongli
 * @email 351365415@qq.com
 */
public class FullJustify {
	
	public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth = 16;
		List<String> fullJustify = fullJustify(words, maxWidth);
		fullJustify.stream().forEach(s -> {
			System.out.println("[" + s + "]");
		});
	}
	
    public static List<String> fullJustify(String[] words, int maxWidth) {
    
    	List<String> res = new ArrayList<>();
    	int start = 0, cur = 0, curSum = 0;
    	
    	while (cur < words.length) {
    		curSum += words[cur].length();
    		int num = cur - start;
    		// 当前字符总数加上N个单词之间至少有N-1个空格
    		if (curSum + num > maxWidth) {
    			int _curSum = curSum - words[cur].length();
    			// 满一行了
    			StringBuilder sb = new StringBuilder();
    			if (num == 1) {
    				// 这行只有一个单词
    				sb.append(words[start]);
    				for (int i = 0; i < maxWidth - _curSum; i++) {
    					sb.append(" ");
    				}
    				start = cur;
    			} else {
    				// 这行有多个单词
    				// 算出每个单词之间平均要放入的空格数量
    				int perBlank = (maxWidth - _curSum) / (num - 1);
    				// 算出多出来的空格数量
    				int overflowBlank = (maxWidth - _curSum) % (num - 1);
    				while (start < cur) {
    					sb.append(words[start]);
    					if (start != (cur - 1)) {
    						for (int i = 0; i < perBlank; i++) {
    							sb.append(" ");
    						}
    					}
    					if (overflowBlank-- > 0) sb.append(" ");
    					start++;
    				}
    			}
    			
    			res.add(sb.toString());
    			curSum = words[cur].length();
    		}
    		cur++;
    		// 最后一行
    		if (cur == words.length) {
    			StringBuilder sb = new StringBuilder();
    			while (start < cur - 1) {
    				sb.append(words[start]);
    				sb.append(" ");
    				start++;
    			}
    			sb.append(words[cur - 1]);
    			for (int i = sb.length(); i < maxWidth; i++) {
    				sb.append(" ");
    			}
    			res.add(sb.toString());
    			break;
    		}
    	}
    	
    	return res;
    }
}
