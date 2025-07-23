// 1: Brute Force (for learning)
public class BruteForceSubarraySum {
    public static int sumOfAllSubarrays(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int subarraySum = 0;
                for (int k = i; k <= j; k++) {
                    subarraySum += arr[k];
                }
                totalSum += subarraySum;
            }
        }

        return totalSum;
    }
}

// Step 2: Better Brute Force (O(n²))
public class BetterSubarraySum {
    public static int sumOfAllSubarrays(int[] arr) {
        int n = arr.length;
        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            int subarraySum = 0;
            for (int j = i; j < n; j++) {
                subarraySum += arr[j];
                totalSum += subarraySum;
            }
        }

        return totalSum;
    }
}

//  Step 3: Optimal Solution (O(n))
class Solution {
    public int subarraySum(int[] arr) {
        int n = arr.length;
        // code here
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += arr[i] * (i+1) * (n-i);
        }
        return sum;
    }
}
