// 1. Simple Map with Right-to-Left Traversal
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50,
            'C', 100, 'D', 500, 'M', 1000
        );

        int total = map.get(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1)))
                total -= map.get(s.charAt(i));
            else
                total += map.get(s.charAt(i));
        }

        return total;
    }
}

// 2. Left-to-Right with Lookahead
class Solution {
    public int romanToInt(String s) {
        Map<String, Integer> map = Map.of(
            "I", 1, "V", 5, "X", 10, "L", 50,
            "C", 100, "D", 500, "M", 1000
        );

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.substring(i, i + 1));
            int next = 0;
            if (i + 1 < s.length()) {
                next = map.get(s.substring(i + 1, i + 2));
            }

            if (curr < next) {
                total -= curr;
            } else {
                total += curr;
            }
        }

        return total;
    }
}

// 3. Switch-Case Based Implementation
class Solution {
    public int romanToInt(String s) {
        int total = 0, prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = switch (s.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };

            total += (curr < prev) ? -curr : curr;
            prev = curr;
        }

        return total;
    }
}

// 4. Hardcoded Subtraction Cases
class Solution {
    public int romanToInt(String s) {
        int total = 0;
        s = s.replace("IV", "IIII");
        s = s.replace("IX", "VIIII");
        s = s.replace("XL", "XXXX");
        s = s.replace("XC", "LXXXX");
        s = s.replace("CD", "CCCC");
        s = s.replace("CM", "DCCCC");

        for (char c : s.toCharArray()) {
            switch (c) {
                case 'I': total += 1; break;
                case 'V': total += 5; break;
                case 'X': total += 10; break;
                case 'L': total += 50; break;
                case 'C': total += 100; break;
                case 'D': total += 500; break;
                case 'M': total += 1000; break;
            }
        }

        return total;
    }
}

// 5. Array-based Value Mapping
class Solution {
    public int romanToInt(String s) {
        int[] map = new int[26]; // A-Z
        map['I' - 'A'] = 1;
        map['V' - 'A'] = 5;
        map['X' - 'A'] = 10;
        map['L' - 'A'] = 50;
        map['C' - 'A'] = 100;
        map['D' - 'A'] = 500;
        map['M' - 'A'] = 1000;

        int total = map[s.charAt(s.length() - 1) - 'A'];

        for (int i = s.length() - 2; i >= 0; i--) {
            int curr = map[s.charAt(i) - 'A'];
            int next = map[s.charAt(i + 1) - 'A'];

            if (curr < next)
                total -= curr;
            else
                total += curr;
        }

        return total;
    }
}
