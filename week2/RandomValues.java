import java.util.ArrayList;

class RandomValues {
  public static void main(String[] args) {
    var numbers = new ArrayList<Double>(5);
    for (var i = 0; i < 5; i++)
      numbers.add(Math.random());
    var min = 1.;
    var max = 0.;
    var sum = 0.;
    for (var num : numbers) {
      min = Math.min(num, min);
      max = Math.max(num, max);
      sum += num;
    }

    var avg = sum / numbers.size();

    for (var num : numbers) {
      System.out.printf("%f ", num);
    }
    System.out.println();
    System.out.printf("Average: %f, Minimum: %f, Maximum: %f%n", avg, min, max);
  }
}