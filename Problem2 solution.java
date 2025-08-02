import java.util.*;

public class SubarraySumEqualsK {

    // 🔴 Approach 1: Brute Force (O(n^2), O(1))
    public int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // 🟡 Approach 2: Prefix Sum Array (O(n^2), O(n))
    public int subarraySumPrefix(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefix[j] - prefix[i] == k) {
                    count++;
                }
            }
        }

        return count;
    }

    // 🟢 Approach 3: Optimal - Prefix Sum + HashMap (O(n), O(n))
    public int subarraySumOptimal(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // base case

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

   
    }
}
