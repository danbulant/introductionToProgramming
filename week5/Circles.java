package week5;

import common.StdDraw;

public class Circles {
  public static void main(String[] args) {
    var circleCount = Integer.parseInt(args[0]);
    var pBlack = Double.parseDouble(args[1]);
    var minRadius = Double.parseDouble(args[2]);
    var maxRadius = Double.parseDouble(args[3]);

    StdDraw.setScale();
    StdDraw.clear();

    for(var i = 0; i < circleCount; i++) {
      if(Math.random() < pBlack) {
        StdDraw.setPenColor(StdDraw.BLACK);
      } else {
        StdDraw.setPenColor(StdDraw.WHITE);
      }
      var radii = Math.random() * (maxRadius - minRadius) + minRadius;
      var posX = Math.random();
      var posY = Math.random();
      StdDraw.filledCircle(posX, posY, radii);
    }
  }
}
