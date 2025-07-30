// 1. Brute Force (Triple Loop - TLE for large input)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums[i];
                }
                if (sum == k) count++;
            }
        }
        return count;
    }
}

// 2. Brute Force (Optimized: Remove 1 loop)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, n = nums.length;
        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum += nums[end];
                if (sum == k) count++;
            }
        }
        return count;
    }
}

// 3. Prefix Sum with HashMap (Optimal)
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, prefixSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // base case

        for (int num : nums) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}

// 4. Sliding Window (Only for all-positive arrays)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int i = 0, j = 0, sum = 0, count = 0;

        while (j < nums.length) {
            sum += nums[j];

            while (sum > k && i <= j) {
                sum -= nums[i];
                i++;
            }

            if (sum == k) count++;

            j++;
        }

        return count;
    }
}

// 5. Prefix Sum Array + Brute Count
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length, count = 0;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefix[j] - prefix[i] == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
