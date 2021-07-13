package xc;

import java.util.Scanner;

/**
 * 小A大学毕业后，回家继承了老爸的租车公司。现在小A的店内只剩下一辆汽车可出租，但是一天内有n个订单，每个订单的用车开始时间为第x小时，用车结束时间为第y小时，订单金额为z，请你帮小A安排订单，计算小A可以获得的最大收益。
 * <p>
 * 注意：一辆车在同一个时间段内不能同时安排两个订单。开始时间和结束时间是小时维度的整数，可取1-24小时。
 * <p>
 * 第一行读入一个整数n，表示有n（1<=n<=10）个订单；
 * <p>
 * 第二行读入用空格分隔的整数，表示订单的开始时间。
 * <p>
 * 第三行读入用空格分隔的整数，表示订单的结束时间。
 * <p>
 * 第四行读入用空格分隔的整数，表示订单金额。
 * <p>
 * 输出小A可以获得的最大收益。
 * <p>
 * 样例输入
 * 4
 * 1 2 3 3
 * 3 4 5 6
 * 200 150 180 210
 * 样例输出
 * 410
 */
public class Test2 {
    public static int mostProfit(int[] start, int[] end, int[] amount) {
        if (start == null || end == null || start.length == 0 || end.length == 0 || amount == null || amount.length == 0) {
            return 0;
        }

        return process(start, end, amount, start.length - 1, end.length - 1, amount.length - 1);
    }

    private static int process(int[] start, int[] end, int[] amount, int i, int j, int k) {
//        if (i == 0 && j == 0 && k == 0) {
//            return amount[0];
//        }

        return 0;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        n = Integer.parseInt(sc.nextLine().trim());
        String startStr = "";
        startStr = sc.nextLine().trim();
        String[] s = startStr.split(" ");
        int[] start = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            start[i] = Integer.parseInt(s[i]);
        }

        String endStr = "";
        endStr = sc.nextLine().trim();
        String[] s1 = endStr.split(" ");
        int[] end = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            end[i] = Integer.parseInt(s1[i]);
        }

        String amountStr = "";
        amountStr = sc.nextLine().trim();
        String[] s2 = amountStr.split(" ");
        int[] amount = new int[s2.length];
        for (int i = 0; i < s2.length; i++) {
            amount[i] = Integer.parseInt(s2[i]);
        }

    }
}
