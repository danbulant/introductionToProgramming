
public class UseThree {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Error: Program expects exactly three arguments");
      return;
    }
    System.out.printf("Hi %s, %s and %s. How are you?%n", args[2], args[1], args[0]);
  }
}