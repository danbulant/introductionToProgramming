public class GamblerPlot {
  public static void main(String[] args) {
    // Run trials experiments that start with
    // $stake and terminate on $0 or $goal.
    int stake = Integer.parseInt(args[0]);
    int goal = Integer.parseInt(args[1]);
    double probability = 0.5;
    int bets = 0;
    
    // Run one experiment.
    int cash = stake;
    while (cash > 0 && cash < goal) {
      // Simulate one bet.
      bets++;
      if (Math.random() < probability)
        cash++;
      else
        cash--;

      GamblerPlot.printCash(cash);
    }

    boolean won = cash == goal;
    String wonText = won ? "Won" : "Lost";

    System.out.println();
    System.out.printf("%s after %s bets", wonText, bets);
  }

  public static void printCash(int cash) {
    for(int i = 0; i < cash; i++) {
      System.out.print("*");
    }
    System.out.println();
  }
}