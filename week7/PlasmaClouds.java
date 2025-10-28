package week7;

import common.StdDraw;
import common.StdRandom;

public class PlasmaClouds {
    public static void main(String[] args) {
        var hurst = Double.parseDouble(args[0]);
        var s = Math.pow(2, 2 * hurst);
        var variance = 0.01;

        StdDraw.enableDoubleBuffering();

        draw(.5, .5, 1, StdRandom.uniformDouble(), StdRandom.uniformDouble(), StdRandom.uniformDouble(), StdRandom.uniformDouble(), variance, s);

        StdDraw.show();
    }

    // c = color ("color vertical horizontal")
    // t = top
    // r = right
    // l = left
    // m = middle (horizontal)
    // c = center (vertical)
    public static void draw(double x, double y, double halfSize, double ctl, double ctr, double cbl, double cbr, double var, double s) {
        var color = (ctl+ctr+cbl+cbr) / 4;
        if (halfSize < 1/512.) {
            var clamped = Math.clamp(color, 0, 1);
            StdDraw.setPenColor((int) (clamped * 255), (int) (clamped * 255), 255);
            StdDraw.filledRectangle(x, y, halfSize, halfSize);
            return;
        }
        var newColor = StdRandom.gaussian(color, Math.sqrt(var));
        var newVar = var / s;
        var newHalfSize = halfSize / 2;

        var ctm = (ctl + ctr) / 2;
        var cbm = (cbl + cbr) / 2;
        var ccl = (cbl + ctl) / 2;
        var ccr = (cbr + ctr) / 2;

        // tl
        draw(x - newHalfSize, y - newHalfSize, newHalfSize, ctl, ctm, ccl, newColor, newVar, s);
        // tr
        draw(x + newHalfSize, y - newHalfSize, newHalfSize, ctm, ctr, newColor, ccr, newVar, s);
        // bl
        draw(x - newHalfSize, y + newHalfSize, newHalfSize, ccl, newColor, cbl, cbm, newVar, s);
        // br
        draw(x + newHalfSize, y + newHalfSize, newHalfSize, newColor, ccr, cbm, cbr, newVar, s);
    }
}
