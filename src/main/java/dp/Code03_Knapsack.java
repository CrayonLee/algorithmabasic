package dp;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code03_Knapsack {
    /**
     * @param w   重量数组
     * @param v   价值数组
     * @param bag 重量
     * @return 在不超过最大重量的情况下的最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || v.length == 0 || w.length != v.length) {
            return 0;
        }
        //尝试函数
        return process(w, v, 0, bag);
    }

    /**
     * @param w
     * @param v
     * @param index 从index开始
     * @param rest  包中的剩余容量
     * @return
     */
    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = next + v[index];
        }
        return Math.max(p1,p2);
    }


    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N =w.length;
        int[][] dp = new int[N+1][bag+1];

        for(int index=N-1;index>=0;index--){
            for(int rest=0;rest<=bag;rest++){
                int p1 = dp[index+1][rest];
                int p2 = 0;
                int next = rest-w[index]<0?-1:dp[index+1][rest-w[index]];
                if(next!=-1){
                    p2=next+v[index];
                }
                dp[index][rest]= Math.max(p1,p2);

            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

}
