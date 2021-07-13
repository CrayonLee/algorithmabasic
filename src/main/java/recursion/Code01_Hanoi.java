package recursion;

public class Code01_Hanoi {
    public static void hanoi(int n){
        if(n>0){
            func(3,"left","right","mid");
        }
    }

    public static void func(int n,String from,String to,String other){
      if(n==1){
          System.out.println("Move 1 from " + from + " to " + to);
      }else{
          func(n-1,from,other,to);
          System.out.println("Move " + n + " from " + from + " to " + to);
          func(n-1,other,to,from);
      }
    }

    public static void main(String[] args) {
        hanoi(3);
        System.out.println("==========");
    }
}
