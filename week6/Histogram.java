package week6;

public class Histogram {
  public static int[] histogram(int[] a, int max) {
    int[] output = new int[max];
    for(var x = 0; x < a.length; x++) {
      output[a[x]]++;
    }
    return output;
  }
}