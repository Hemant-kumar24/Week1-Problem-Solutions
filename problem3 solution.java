import java.util.*;

public class PaintHouseII {

    // 🔴 1. Brute Force DFS Approach (TLE for large input)
    public int minCostDFS(int[][] costs) {
        return dfs(costs, 0, -1);
    }

    private int dfs(int[][] costs, int house, int prevColor) {
        if (house == costs.length) return 0;

        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color < costs[0].length; color++) {
            if (color != prevColor) {
                int cost = costs[house][color] + dfs(costs, house + 1, color);
                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }

    // 🟡 2. Memoization (Top-Down DP)
    public int minCostMemo(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        Integer[][] memo = new Integer[n][k];
        int min = Integer.MAX_VALUE;
        for (int color = 0; color < k; color++) {
            min = Math.min(min, helper(costs, 0, color, memo));
        }
        return min;
    }

    private int helper(int[][] costs, int house, int color, Integer[][] memo) {
        if (house == costs.length) return 0;
        if (memo[house][color] != null) return memo[house][color];

        int cost = costs[house][color];
        int minNext = Integer.MAX_VALUE;
        for (int nextColor = 0; nextColor < costs[0].length; nextColor++) {
            if (nextColor != color) {
                minNext = Math.min(minNext, helper(costs, house + 1, nextColor, memo));
            }
        }

        memo[house][color] = cost + minNext;
        return memo[house][color];
    }

    // 🟢 3. Optimized Bottom-Up DP (Best Approach)
    public int minCostDP(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;
        if (k == 0) return 0;

        int min1 = -1, min2 = -1;

        for (int i = 0; i < n; i++) {
            int lastMin1 = min1, lastMin2 = min2;
            min1 = -1;
            min2 = -1;

            for (int j = 0; j < k; j++) {
                if (i > 0) {
                    if (j != lastMin1) {
                        costs[i][j] += costs[i - 1][lastMin1];
                    } else {
                        costs[i][j] += costs[i - 1][lastMin2];
                    }
                }

                if (min1 == -1 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 == -1 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }

        return costs[n - 1][min1];
    }

    // 📌 Main function to test all approaches
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // number of houses
        int k = scanner.nextInt(); // number of colors
        int[][] costs = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                costs[i][j] = scanner.nextInt();
            }
        }

        PaintHouseII solution = new PaintHouseII();

        System.out.println("\n🟢 Optimized DP: " + solution.minCostDP(copy(costs)));
        System.out.println("🟡 Memoization: " + solution.minCostMemo(copy(costs)));
        System.out.println("🔴 Brute Force DFS: " + solution.minCostDFS(copy(costs))); // Warning: TLE for large inputs
    }

    // Utility to deep copy 2D array
    private static int[][] copy(int[][] arr) {
        int[][] newArr = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return newArr;
    }
}
