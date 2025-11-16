import common.StdRandom;

public class RandomSequenceChange {
    public static void main(String[] args) {
        var arrayLength = 5;
        var rounds = 100;
        var data = new int[arrayLength];
        for(var i = 0; i < data.length; i++)
            data[i] = i;
        // number of times any repetition was found after a random shuffle
        var total = 0;
        for(var i = 0; i < rounds; i++) {
            StdRandom.shuffle(data);
            total += (countSequences(data) > 0) ? 1 : 0;
        }
        var chance = 1 - (total / (double) rounds);
        System.out.printf("The chance of no repetition is %s%%\n", chance * 100);
    }

    static int countSequences(int[] data) {
        var output = 0;
        for(var i = 1; i < data.length; i++)
            if(data[i] == data[i - 1] + 1) output++;
        return output;
    }
}
