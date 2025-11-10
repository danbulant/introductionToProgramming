import common.StdDraw;

class Chaos {
    public static void main(String[] args) {
        var x = 0.01;
        var r = Double.parseDouble(args[0]);
        var buffer = new double[200];
        buffer[0] = x;
        StdDraw.enableDoubleBuffering();

        var t = 0;
        while(true) {
            System.out.format("t = %3d; x = %s\n", t, x);
            x = Math.clamp(nextGeneration(x, r), 0, 1);

            var offset = t % buffer.length;
            if(offset == 0) {
                StdDraw.clear();
                buffer = new double[200];
            }
            buffer[offset] = x;
            for(var toDraw = 0; toDraw < buffer.length; toDraw++) {
                var to = buffer[toDraw];
                StdDraw.line(toDraw / (double)buffer.length, 0, toDraw / (double) buffer.length, to);
            }
            if(offset % 10 == 0) {
                StdDraw.show();
                StdDraw.pause(100);
            }
            if(offset >= 1 && buffer[offset] == buffer[offset - 1]) { // stabilized value
                StdDraw.pause(5000);
            }
            t += 1;
        }
    }

    static double nextGeneration(double x, double r) {
        return r * x * (1 - x);
    }
}