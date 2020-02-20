package leetcode.p3.q121_130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 127.单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *	每次转换只能改变一个字母。
 *	转换过程中的中间单词必须是字典中的单词。
 *	说明:
 *	
 *	如果不存在这样的转换序列，返回 0。
 *	所有单词具有相同的长度。
 *	所有单词只由小写字母组成。
 *	字典中不存在重复的单词。
 *	你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *	示例 1:
 *	
 *	输入:
 *	beginWord = "hit",
 *	endWord = "cog",
 *	wordList = ["hot","dot","dog","lot","log","cog"]
 *	
 *	输出: 5
 *	
 *	解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *	     返回它的长度 5。
 *	示例 2:
 *	
 *	输入:
 *	beginWord = "hit"
 *	endWord = "cog"
 *	wordList = ["hot","dot","dog","lot","log"]
 *	
 *	输出: 0
 *	
 *	解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 * 
 * @author ilongli
 * @email 351365415@qq.com
 */
public class LadderLength {

	public static void main(String[] args) {
		System.out.println(new LadderLength().ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
	}
	

	/**
	 * 1.BFS
	 * 算法
	 *	1.对给定的 wordList 做预处理，找出所有的通用状态。将通用状态记录在字典中，键是通用状态，值是所有具有通用状态的单词。
	 *	
	 *	2.将包含 beginWord 和 1 的元组放入队列中，1 代表节点的层次。我们需要返回 endWord 的层次也就是从 beginWord 出发的最短距离。
	 *	
	 *	3.为了防止出现环，使用访问数组记录。
	 *	
	 *	4.当队列中有元素的时候，取出第一个元素，记为 current_word。
	 *	
	 *	5.找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
	 *	
	 *	6.从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
	 *	
	 *	7.对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
	 *	
	 *	8.最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
	 */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
    	int L = beginWord.length();
    	
    	HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
    	
    	// 1.预处理
    	wordList.forEach(word -> {
    		for (int i = 0; i < L; i++) {
    			String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
    			ArrayList<String> transformations = 
    					allComboDict.getOrDefault(newWord, new ArrayList<String>());
    			transformations.add(word);
    			allComboDict.put(newWord, transformations);
    		}
    	});
    	
    	System.out.println(allComboDict);
    	
    	// 2.
    	Queue<Pair<String, Integer>> Q = new LinkedList<>();
    	Q.add(new Pair<>(beginWord, 1));
    	
    	// 3.
    	HashMap<String, Boolean> visited = new HashMap<>();
    	visited.put(beginWord, true);
    	
    	while (!Q.isEmpty()) {
    		// 4.
    		Pair<String, Integer> node = Q.remove();
    		String word = node.getKey();
    		int level = node.getValue();
    		
    		// 5.
    		for (int i = 0; i < L; i++) {
    			String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
    			
    			// 6.
    			for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
    				
    				// 8.
    				// 注：因为最外面的循环都是先处理完当前level再处理下一个level的，
    				// 所以当达到期望的单词的时候，这时的level肯定是最短变换序列长度
    				if (adjacentWord.equals(endWord)) {
    					return level + 1;
    				}
    				
    				if (!visited.containsKey(adjacentWord)) {
    					visited.put(adjacentWord, true);
    					// 7.
    					Q.add(new Pair<>(adjacentWord, level + 1));
    				}
    			}
    		}
    	}
    	return 0;
    }
    
    /**
     * 2.双向BFS
     * 算法与之前描述的标准广搜方法相类似。
	 *	唯一的不同是我们从两个节点同时开始搜索，同时搜索的结束条件也有所变化。
	 *	
	 *	我们现在有两个访问数组，分别记录从对应的起点是否已经访问了该节点。
	 *	
	 *	如果我们发现一个节点被两个搜索同时访问，就结束搜索过程。因为我们找到了双向搜索的交点。过程如同从中间相遇而不是沿着搜索路径一直走。
	 *	
	 *	双向搜索的结束条件是找到一个单词被两边搜索都访问过了。
	 *	
	 *	最短变换序列的长度就是中间节点在两边的层次之和。因此，我们可以在访问数组中记录节点的层次。
     */
    private int L;
    private HashMap<String, ArrayList<String>> allComboDict;
    
    LadderLength() {
    	this.L = 0;
    	this.allComboDict = new HashMap<String, ArrayList<String>>();
    }
    
    private int visitWordNode(
    		Queue<Pair<String, Integer>> Q,
    		HashMap<String, Integer> visited,
    		HashMap<String, Integer> othersVisited) {
    	Pair<String, Integer> node = Q.remove();
    	String word = node.getKey();
    	int level = node.getValue();
    	
    	for (int i = 0; i < this.L; i++) {
    		String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
    		
    		for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
    			if (othersVisited.containsKey(adjacentWord)) {
    				return level + othersVisited.get(adjacentWord);
    			}
    			
    			if (!visited.containsKey(adjacentWord)) {
    				visited.put(adjacentWord, level + 1);
    				Q.add(new Pair<>(adjacentWord, level + 1));
    			}
    		}
    	}
    	return -1;
    }
    
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
    	
    	if (!wordList.contains(endWord)) return 0;
    	
    	this.L = beginWord.length();
    	
    	wordList.forEach(word -> {
    		for (int i = 0; i < L; i++) {
    			String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
    			ArrayList<String> transformations = 
    					this.allComboDict.getOrDefault(newWord, new ArrayList<String>());
    			transformations.add(word);
    			this.allComboDict.put(newWord, transformations);
    		}
    	});
    	
    	
    	Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
    	
    	Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
    	
    	Q_begin.add(new Pair<>(beginWord, 1));
    	Q_end.add(new Pair<>(endWord, 1));
    	
    	HashMap<String, Integer> visitedBegin = new HashMap<>();
    	HashMap<String, Integer> visitedEnd = new HashMap<>();
    	visitedBegin.put(beginWord, 1);
    	visitedEnd.put(endWord, 1);
    	
    	while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
    		int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
    		if (ans > -1) return ans;
    		
    		ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
    		if (ans > -1) return ans;
    	}
    	
    	return 0;
    }
    
    class Pair<K,V> {

        private K key;

        public K getKey() { return key; }

        private V value;
   
        public V getValue() { return value; }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
