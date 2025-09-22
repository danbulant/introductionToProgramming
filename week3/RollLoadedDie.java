public class RollLoadedDie {
  public static void main(String[] args) {
    double rand8 = Math.random() * 8.;
    // floor returns double. Round gives long for double input, so conversion needed
    int random = (int)(Math.round(Math.floor(rand8)) + 1);
    // 1 - 8 to 1 - 6, with 6 receiving the probabilities of 7 - 8
    random = Math.min(random, 6);
    System.out.println(random);
  }
}
