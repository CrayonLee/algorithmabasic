package _58;

import com.sun.scenario.effect.Merge;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Test3 {
    public static int[] mergePrice (int[] a, int[] b) {
        // write code here
        int[] c = new int[a.length+b.length];
        Arrays.sort(a);
        Arrays.sort(b);
      int i=0,j=0,k=0;
      while(i<a.length&&j<b.length&&k<c.length){
          c[k++]=a[i]>=b[j]?b[j++]:a[i++];
      }
      if(a[a.length-1]>b[b.length-1]){
          c[c.length-1]=a[a.length-1];
      }else{
          c[c.length-1]=b[b.length-1];
      }
      return c;
    }

    public static void main(String[] args) {
//        [38,65,67,125],[79,89,129]
        int[] a = new int[]{38,65,67,125};
        int[] b= new int[]{79,89,129};
        int[] ints = mergePrice(a, b);
        System.out.println(Arrays.toString(ints));
    }
}
