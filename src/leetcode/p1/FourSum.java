package leetcode.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourSum {

	public static void main(String[] args) {
//		System.out.println(fourSum(new int[] {-3,-2,-1,0,0,1,2,3}, 0));
		System.out.println(fourSum(new int[] {1, 1, 1, -1, 1}, 4));
	}
	
    public static List<List<Integer>> fourSum(int[] nums, int target) {
    	// length
        int n = nums.length;
        
        // check
        if (n < 3) return Collections.emptyList();
        
        // sort
        Arrays.sort(nums);
        
        // result
        List<List<Integer>> result = new ArrayList<>();
        
        // loop 4->3
        for (int i = 0; i < n - 3; i++) {
        	// skip if unnecessary
        	if (i > 0 && nums[i] == nums[i - 1]) continue;
        	// init new target
        	int _target = target - nums[i];
        	
        	// loop 3->2
        	for (int j = i + 1; j < n - 2; j++) {
        		// skip if unnecessary
        		if (j > i + 1 && nums[j] == nums[j - 1]) continue;
    			int l = j + 1;
    			int r = n - 1;
    			while (l < r) {
    				int sum = nums[j] + nums[l] + nums[r];
    				if (sum == _target) {
    					result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
    					l += 1;
    					r -= 1;
    					while (l < r && nums[l] == nums[l - 1]) l += 1;
    					while (r > l && nums[r] == nums[r + 1]) r -= 1;
    				} else if (sum > _target) {
    					r -= 1;
    				} else {
    					l += 1;
    				}
    			}
        	}
        }
        
    	return result;
    }
    
}
