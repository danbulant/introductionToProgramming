package week7;

import common.StdDraw;

class Squares {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(512 * 4, 512);
        StdDraw.setXscale(0, 4);
        drawSquares(0.5, 0.5, .25, 4, 1);
        drawSquares(1.5, 0.5, .25, 4, 2);
        drawSquares(2.5, 0.5, .25, 4, 3);
        drawSquares(3.5, 0.5, .25, 4, 4);
        StdDraw.show();
    }

    public static void drawSquares(
            double x,
            double y,
            double halfLength,
            int limit,
            int mode) {
        var shouldRecurse = limit > 0;
        limit--;
        var newHalfLength = halfLength / 2.2;

        if (mode == 3 || (mode > 1 && !shouldRecurse))
            drawSingleSquare(x, y, halfLength);

        if (shouldRecurse) {
            int[][] offsets = { { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };
            for (var i = 0; i < 4; i++) {
                if (mode == 2 && i == 3)
                    drawSingleSquare(x, y, halfLength);
                if (mode == 4 && i == 2)
                    drawSingleSquare(x, y, halfLength);
                drawSquares(
                        x + halfLength * offsets[i][0],
                        y + halfLength * offsets[i][1],
                        newHalfLength,
                        limit,
                        mode);
            }
        }

        if (mode == 1)
            drawSingleSquare(x, y, halfLength);
    }

    private static void drawSingleSquare(double x, double y, double halfLength) {
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledSquare(x, y, halfLength);
        StdDraw.setPenColor();
        StdDraw.square(x, y, halfLength);
    }
}