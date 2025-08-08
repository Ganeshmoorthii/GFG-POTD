// Sort & Compare Adjacent (Classic)
import java.util.*;

class Solution {
    public int minTimeDiff(String[] arr) {
        int n = arr.length;
        int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            String[] parts = arr[i].split(":");
            int h = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            int s = Integer.parseInt(parts[2]);
            times[i] = h * 3600 + m * 60 + s;
        }

        Arrays.sort(times);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, times[i] - times[i - 1]);
        }

        // wrap-around case
        minDiff = Math.min(minDiff, 86400 - times[n - 1] + times[0]);

        return minDiff;
    }
}

// Boolean Array Marking (Bucket Sort Style)
import java.util.*;

class Solution {
    public int minTimeDiff(String[] arr) {
        boolean[] seen = new boolean[86400];
        
        for (String t : arr) {
            String[] p = t.split(":");
            int sec = Integer.parseInt(p[0]) * 3600 + Integer.parseInt(p[1]) * 60 + Integer.parseInt(p[2]);
            if (seen[sec]) return 0; // duplicate time
            seen[sec] = true;
        }

        int first = -1, prev = -1, minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < 86400; i++) {
            if (seen[i]) {
                if (first == -1) first = i;
                if (prev != -1) minDiff = Math.min(minDiff, i - prev);
                prev = i;
            }
        }

        minDiff = Math.min(minDiff, 86400 - prev + first);
        return minDiff;
    }
}

// Convert to Seconds & Use PriorityQueue
import java.util.*;

class Solution {
    public int minTimeDiff(String[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String t : arr) {
            String[] p = t.split(":");
            int sec = Integer.parseInt(p[0]) * 3600 + Integer.parseInt(p[1]) * 60 + Integer.parseInt(p[2]);
            pq.offer(sec);
        }

        int first = pq.peek();
        int prev = pq.poll();
        int minDiff = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            minDiff = Math.min(minDiff, curr - prev);
            prev = curr;
        }

        minDiff = Math.min(minDiff, 86400 - prev + first);
        return minDiff;
    }
}

// Pairwise Min (Brute Force)
class Solution {
    public int minTimeDiff(String[] arr) {
        int n = arr.length;
        int[] secArr = new int[n];
        
        for (int i = 0; i < n; i++) {
            String[] p = arr[i].split(":");
            secArr[i] = Integer.parseInt(p[0]) * 3600 + Integer.parseInt(p[1]) * 60 + Integer.parseInt(p[2]);
        }

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = Math.abs(secArr[i] - secArr[j]);
                minDiff = Math.min(minDiff, Math.min(diff, 86400 - diff));
            }
        }
        return minDiff;
    }
}

// Streaming Approach (Insertion Sort Style)
import java.util.*;

class Solution {
    public int minTimeDiff(String[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (String t : arr) {
            String[] p = t.split(":");
            int sec = Integer.parseInt(p[0]) * 3600 + Integer.parseInt(p[1]) * 60 + Integer.parseInt(p[2]);

            Integer floor = set.floor(sec);
            Integer ceil = set.ceiling(sec);

            if (floor != null) minTime = Math.min(minTime, sec - floor);
            if (ceil != null) minTime = Math.min(minTime, ceil - sec);
            if (!set.isEmpty()) minTime = Math.min(minTime, 86400 - Math.abs(set.first() - sec));

            set.add(sec);
        }
        return minTime;
    }
    
    private int minTime = Integer.MAX_VALUE;
}

//
