package week7;

import common.StdDraw;

public class Triangles {
    public static void main(String[] args) {
        int order = Integer.parseInt(args.length > 0 ? args[0] : "1");
        StdDraw.enableDoubleBuffering();

        drawRootTriangle();

        if (order > 1)
            // vertical alignment to center it properly within the root triangle
            recurse(order, 0.5, (height(1)) / 2 - 0.15, 0.5);

        StdDraw.show();
    }

    public static void recurse(int limit, double x, double y, double width) {
        limit--;
        var height = height(width);
        drawTriangle(x, y, width, height);
        var halfWidth = width / 2;
        if (limit > 0) {
            recurse(limit, x, y + height * .75, halfWidth);
            recurse(limit, x - halfWidth, y - height / 4, halfWidth);
            recurse(limit, x + halfWidth, y - height / 4, halfWidth);
        }
    }

    /**
     * Calculates equilateral triangle's height from it's width / side length
     */
    public static double height(double width) {
        return 0.5 * Math.sqrt(3) * width;
    }

    public static void drawTriangle(double x, double y, double width, double height) {
        var topLeft = new double[] { x - width / 2, y + height / 2 };
        var topRight = new double[] { x + width / 2, y + height / 2 };
        var bottom = new double[] { x, y - height / 2 };
        StdDraw.polygon(new double[] { topLeft[0], topRight[0], bottom[0] },
                new double[] { topLeft[1], topRight[1], bottom[1] });
    }

    /**
     * Variant of draw triangle, but draws it upside down (base/water-level side is
     * down)
     */
    public static void drawRootTriangle() {
        var width = 1;
        var height = height(width);
        var offset = (1 - height) / 2;
        StdDraw.polygon(
                new double[] { 0, 1, 0.5 },
                new double[] { offset, offset, height + offset });
    }
}
