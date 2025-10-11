package week6;

import java.util.Arrays;

public class HistogramTests {
  public static void main(String[] args) {
    assert Arrays.equals(
      new int[]{ 0,1,1,1 },
      Histogram.histogram(new int[]{ 1,2,3 }, 4)
    );
    assert Arrays.equals(
      new int[]{ 0,3,0,0 },
      Histogram.histogram(new int[]{ 1,1,1 }, 4)
    );
  }
}
