import common.StdDraw;

public class Clock {
    public static void main(String[] args) {
        StdDraw.setScale(-1, 1);
        StdDraw.enableDoubleBuffering();

        while(true) {
            var time = System.currentTimeMillis();
            var ms = (int) (time % 1000);
            // this could be changed to doubles to have smooth movement
            var seconds = (int) (time / 1000 % 60);
            var minutes = (int) (time / 60_000 % 60);
            var hours = (int) (time / 3_600_000 % 12);
            var secondsAngle = (double) seconds / 60 * 2*Math.PI;
            var minutesAngle = (double) minutes / 60 * 2*Math.PI;
            var hoursAngle = (double) hours / 12 * 2*Math.PI;
            
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.004);
            StdDraw.line(0, 0, Math.sin(minutesAngle) * 0.8, Math.cos(minutesAngle) * 0.8);
            StdDraw.line(0, 0, Math.sin(hoursAngle), Math.cos(hoursAngle));
            
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.002);
            StdDraw.line(0, 0, Math.sin(secondsAngle), Math.cos(secondsAngle));

            StdDraw.setPenRadius(0.004);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.circle(0, 0, 1);
            
            StdDraw.show();
            StdDraw.pause(1000 - ms);
        }

    }
}
