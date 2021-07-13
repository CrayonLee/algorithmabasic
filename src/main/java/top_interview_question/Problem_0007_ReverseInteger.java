package top_interview_question;

public class Problem_0007_ReverseInteger {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = res * 10 + tail;
            if ((newResult - tail) / 10 != res) {
                return 0;
            }
            res=newResult;
            x/=10;
        }

        return res;
    }
}
