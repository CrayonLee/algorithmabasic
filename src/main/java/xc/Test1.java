package xc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * “周游世界”是携程举办的一个大型旅游类节目，节目规则如下：
 *
 * 1. 节目组给出一组有序的全球旅游景点，到达该景点则将会获取不同的分数奖励。
 *
 * 2. 每位参赛者都位于旅游景点的第1站，默认获取该景点的分数奖励。
 *
 * 3. 每位参赛者会得到一组数值一样的卡片，每张卡片上有一个数字，1，2，3或4，不同的数字代表可以从当前站点向后行进的步数。
 *
 * 例如，当前处于第一站，若使用一张数字1的卡片，则到达第二站，若使用一张数字2的卡片，则到达第三站。
 *
 * 很显然，这个游戏获胜的条件和卡片的使用顺序相关。
 *
 * 那么，应该如何合理的安排这些卡片的使用顺序获取比赛的胜利呢？游游陷入了沉思。。。
 *
 *
 *
 * 输入描述
 * scores数组代表当前给出的各个景点的分数。
 *
 * cards数组代表当前给出的卡片。
 *
 * 求当前这个输入下，游游可以获得的最高分。
 *
 * 例如样例中所示，共四个景点，分值分别为1，2，3，4。共2张卡片，分值分别为1，2。很显然先使用2再使用1可以使获得的分数最高。1+3+4=8分。
 *
 * 提示：
 *
 * 1. scores.length <= 100
 *
 * 2. cards中每一种卡片的数值不超过40，就是说卡片数值为1的总数不超过40，2/3/4同理。
 *
 * 3. sum(cards[i]) < scores.length
 *
 * 输出描述
 * 当前数据下可以得到的最大分数。
 *
 *
 * 样例输入
 * 4
 * 1
 * 2
 * 3
 * 4
 * 2
 * 1
 * 2
 * 样例输出
 * 8
 */
public class Test1 {
    /*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
scores数组代表当前给出的各个景点的分数。

cards数组代表当前给出的卡片。

求当前这个输入下，游游可以获得的最高分。

例如样例中所示，共四个景点，分值分别为1，2，3，4。共2张卡片，分值分别为1，2。很显然先使用2再使用1可以使获得的分数最高。1+3+4=8分。

提示：

1. scores.length <= 100

2. cards中每一种卡片的数值不超过40，就是说卡片数值为1的总数不超过40，2/3/4同理。

3. sum(cards[i]) < scores.length
******************************开始写代码******************************/
    static int procee(int[] scores, int[] cards) {
        int res =0;
        if(scores==null||cards==null||scores.length==0||cards.length==0){
            return 0;
        }
        Arrays.sort(scores);
        Arrays.sort(cards);
        int size = cards.length;
        if(size==1){
            res+=scores[scores.length-1];
        }else{

        }
        return res;

    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _scores_size = 0;
        _scores_size = Integer.parseInt(in.nextLine().trim());
        int[] _scores = new int[_scores_size];
        int _scores_item;
        for(int _scores_i = 0; _scores_i < _scores_size; _scores_i++) {
            _scores_item = Integer.parseInt(in.nextLine().trim());
            _scores[_scores_i] = _scores_item;
        }

        int _cards_size = 0;
        _cards_size = Integer.parseInt(in.nextLine().trim());
        int[] _cards = new int[_cards_size];
        int _cards_item;
        for(int _cards_i = 0; _cards_i < _cards_size; _cards_i++) {
            _cards_item = Integer.parseInt(in.nextLine().trim());
            _cards[_cards_i] = _cards_item;
        }

        res = procee(_scores, _cards);
        System.out.println(String.valueOf(res));




    }
}
