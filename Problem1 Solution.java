import java.util.*;
public class FindMinimumInRotatedArray {

    // ✅ 1. Brute Force using Sorting (O(N log N))
    public static int findMinBySorting(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length); // Don't mutate original
        Arrays.sort(copy);
        return copy[0];
    }

    // ✅ 2. Linear Search (O(N))
    public static int findMinByLinearSearch(int[] nums) {
        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    // ✅ 3. Binary Search (Optimal O(log N))
    public static int findMinByBinarySearch(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }

    // 🧪 Driver code to test all 3 approaches
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input size
        int n = sc.nextInt();
        int[] nums = new int[n];

        // Read input array
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println("🔎 Minimum using Sorting: " + findMinBySorting(nums));
        System.out.println("🔎 Minimum using Linear Search: " + findMinByLinearSearch(nums));
        System.out.println("🚀 Minimum using Binary Search: " + findMinByBinarySearch(nums));
    }
}

/* 
+--------------------------+----------------------+---------------------+---------------------------------------------+
|       Approach           |   Time Complexity     |   Space Complexity  |                  Explanation                |
+--------------------------+----------------------+---------------------+---------------------------------------------+
| Brute Force (Sorting)    | O(N log N)            | O(N)                | Sorts a copy of the array and returns first |
+--------------------------+----------------------+---------------------+---------------------------------------------+
| Linear Search            | O(N)                  | O(1)                | Scans each element to find the minimum      |
+--------------------------+----------------------+---------------------+---------------------------------------------+
| Binary Search (Optimal)  | O(log N)              | O(1)                | Uses binary search on rotated array         |
+--------------------------+----------------------+---------------------+---------------------------------------------+

*/