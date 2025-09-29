package week5;

import common.StdIn;

public class MissingInt {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    boolean[] nums = new boolean[n];
    for(var i = 0; i < n - 1; i++) {
      int integer = StdIn.readInt() - 1;
      nums[integer] = true;
    }
    for(var i = 0; i < nums.length; i++) {
      if(!nums[i]) {
        System.err.println(i + 1);
      }
    }
  }
}
