package kmp;

public class Code03_IsRotation {
    public static boolean isRotation(String a, String b) {
        if (a.length() == 0 || b.length() == 0 || a.length() < b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndex(b2, a) != -1;
    }

    public static int getIndex(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] next = getNextArray(ms);
        int mi = 0;
        int si = 0;
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                mi++;
                si++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < str2.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
//        String str1 = "yunzuocheng";
//        String str2 = "zuochengyun";
        String str1 = "aa";
        String str2 = "a";
        System.out.println(isRotation(str1, str2));

    }
}
