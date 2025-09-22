
class WindChill {
  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Usage: [temperature] [velocity]");
      return;
    }
    var temperature = Double.parseDouble(args[0]);
    var velocity = Double.parseDouble(args[1]);
    if (Math.abs(temperature) > 50. || velocity > 120. || velocity < 3.) {
      System.err.println("Values outside of allowed range.");
      return;
    }
    var wind_chill = WindChill.calculateWindChill(temperature, velocity);
    System.out.printf("The effective temperature is %fF.%n", wind_chill);
  }

  public static double calculateWindChill(double T, double v) {
    return 35.74 + (0.6215 * T) + (0.4275 * T - 35.75) * Math.pow(v, 0.16);
  }
}