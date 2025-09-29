package week5;

import common.StdDraw;

public class CircleLines {
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    double p = Double.parseDouble(args[1]);
    assert p >= 0. && p <= 1.;
    StdDraw.setXscale(-1.1, 1.1);
    StdDraw.setYscale(-1.1, 1.1);
    StdDraw.clear();
    double[][] points = new double[n][2];

    for(var i = 0; i < n; i++) {
      // explicitly convert to double
      double fi = i;
      // divide by total to get 0-1
      fi /= n;
      // and convert to radians
      fi *= 2*Math.PI;
      // save calculations
      points[i][0] = Math.sin(fi);
      points[i][1] = Math.cos(fi);
      StdDraw.filledCircle(points[i][0], points[i][1], 0.01);
    }

    StdDraw.setPenColor(StdDraw.GRAY);
    
    for(var i = 0; i < n; i++) {
      for(var x = i; x < n; x++) {
        if(Math.random() > p) continue;

        StdDraw.line(points[i][0], points[i][1], points[x][0], points[x][1]);
      }
    }
  }
}
