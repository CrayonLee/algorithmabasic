package dp;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class Code01_RobotWalk {

    public static int ways1(int N, int start, int aim, int K) {
        return process1(start, K, aim, N);
    }

    /**
     * @param cur  机器人当前来到的位置是cur
     * @param rest 还有rest步要走
     * @param aim  最终的目标是aim
     * @param N    有哪些位置要走
     * @return 机器人从cur出发，走rest步，最终来到aim的方法数是多少？
     */
    public static int process1(int cur, int rest, int aim, int N) {
        //如果已经不需要走了  走完了
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(cur + 1, rest - 1, aim, N);
        }
        if (cur == N) {
            return process1(cur - 1, rest - 1, aim, N);
        }
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    public static int ways2(int N, int start, int aim, int K) {
        int[][] dp = new int[N+1][K+1];
        for(int i=0;i<=N;i++){
            for(int j=0;j<=K;j++){
                dp[i][j]=-1;
            }

        }
        return process2(start, K, aim, N,dp);
    }

    /**
     * @param cur  机器人当前来到的位置是cur
     * @param rest 还有rest步要走
     * @param aim  最终的目标是aim
     * @param N    有哪些位置要走
     * @param dp   缓存
     * @return 机器人从cur出发，走rest步，最终来到aim的方法数是多少？
     */
    public static int process2(int cur, int rest, int aim, int N,int[][] dp) {
        if(dp[cur][rest]!=-1){
            return dp[cur][rest];
        }
        int ans =0;
        //如果已经不需要走了  走完了
        if (rest == 0) {
            ans=cur == aim ? 1 : 0;
        }else if (cur == 1) {
            ans= process2(cur + 1, rest - 1, aim, N,dp);
        } else if (cur == N) {
            ans= process2(cur - 1, rest - 1, aim, N,dp);
        }else{
            ans = process2(cur - 1, rest - 1, aim, N,dp) + process2(cur + 1, rest - 1, aim, N,dp);
        }
        dp[cur][rest]=ans;
        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {
        int[][] dp = new int[N+1][K+1];
        dp[aim][0]=1;

        for(int rest=1;rest<=K;rest++){
            dp[1][rest]=dp[2][rest-1];
            for(int cur=2;cur<N;cur++){
                dp[cur][rest]=dp[cur-1][rest-1]+dp[cur+1][rest-1];
            }
            dp[N][rest]=dp[N-1][rest-1];
        }

        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(4, 2, 4, 4));
        System.out.println(ways2(4, 2, 4, 4));
        System.out.println(ways3(4, 2, 4, 4));
    }
}
