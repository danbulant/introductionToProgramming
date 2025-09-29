public class Transpose {
  public static void main(String[] args) {
    int[][] input = {
      {99, 85, 98},
      {98, 57, 78},
      {92, 77, 76},
    };

    for(var i = 0; i < input.length; i++) {
      for(var j = i; j < input.length; j++) {
        var og = input[i][j];
        input[i][j] = input[j][i];
        input[j][i] = og;
      }
    }

    for(var i = 0; i < input.length; i++) {
      for(var j = 0; j < input.length; j++) {
        System.out.print(input[i][j] + " ");
      }
      System.out.println();
    }
  }
}
