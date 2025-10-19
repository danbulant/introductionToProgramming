package week7;

import common.StdDraw;
import common.StdRandom;

public class PlasmaClouds {
    public static void main(String[] args) {
        var hurst = Double.parseDouble(args[0]);
        var s = Math.pow(2, 2 * hurst);
        var variance = 0.01;

        StdDraw.enableDoubleBuffering();

        draw(.5, .5, 1, 1, variance, s);

        StdDraw.show();
    }

    public static void draw(double x, double y, double halfSize, double color, double var, double s) {
        if (halfSize < 0.01) {
            StdDraw.setPenColor((int) (color * 255), (int) (color * 255), 255);
            StdDraw.filledRectangle(x, y, halfSize, halfSize);
            return;
        }
        var newColor = Math.clamp(StdRandom.gaussian(color, Math.sqrt(var)), 0, 1);
        var newVar = var / s;
        var newHalfSize = halfSize / 2;
        int[][] offsets = { { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };
        for (var offset : offsets) {
            draw(x + newHalfSize * offset[0], y + newHalfSize * offset[1], newHalfSize, newColor, newVar, s);
        }
    }
}
