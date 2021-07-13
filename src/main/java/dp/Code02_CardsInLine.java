package dp;

import com.sun.pisces.GradientColorMap;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 */
public class Code02_CardsInLine {
    //返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    //后手获得的最大分数
    private static int g1(int[] arr, int L, int R) {
        //如果只剩一张牌的话 那么这张牌一定会被先手拿走
        if (L == R) {
            return 0;
        }
        //对手拿走了L位置的牌   题目中说明了每个人都是绝顶聪明的
        int p1 = f1(arr,L+1,R);
        int p2 = f1(arr,L,R-1);
        return Math.min(p1,p2);
    }

    //先手获得的最大分数
    private static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }


    //傻缓存
    public static int win2(int[] arr){
        if (arr == null || arr.length==0) {
            return 0;
        }
        int N =arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j]=-1;
                gmap[i][j]=-1;
            }
        }
        int first = f2(arr, 0, arr.length - 1,fmap,gmap);
        int second = g2(arr, 0, arr.length - 1,fmap,gmap);
        return Math.max(first, second);
    }

    //后手获得的最大分数
    private static int g2(int[] arr, int L, int R,int[][] fmap,int[][] gmap) {
        if(gmap[L][R]!=-1){
            return gmap[L][R];
        }
        int ans =0;
        if (L == R) {
            ans= 0;
        }else {
            int p1 = f1(arr, L + 1, R);
            int p2 = f1(arr, L, R - 1);
            ans= Math.min(p1, p2);
        }
        gmap[L][R]=ans;
        return ans;
    }

    //先手获得的最大分数
    private static int f2(int[] arr, int L, int R,int[][] fmap,int[][] gmap) {
        if(fmap[L][R]!=-1){
            return fmap[L][R];
        }

        int ans=0;
        if (L == R) {
            ans= arr[L];
        }else {
            int p1 = arr[L] + g1(arr, L + 1, R);
            int p2 = arr[R] + g1(arr, L, R - 1);
            ans= Math.max(p1, p2);
        }
        fmap[L][R]=ans;
        return ans;
    }

    public static int win3(int[] arr){
        if (arr == null || arr.length==0) {
            return 0;
        }
        int N =arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i]=arr[i];
            //这句话不写也是一样的效果
//            gmap[i][i]=0;
        }

        for(int startCol=1;startCol<N;startCol++){
            int L=0;
            int R=startCol;
            while(R<N){
                fmap[L][R]=Math.max(arr[L]+gmap[L+1][R],arr[R]+gmap[L][R-1]);
                gmap[L][R]=Math.min(fmap[L+1][R],fmap[L][R-1]);
                L++;
                R++;
            }
        }
        //这个返回值是可以通过暴力递归看出来的
        return Math.max(fmap[0][N-1],gmap[0][N-1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
