import java.util.Random;

public class Deal {
  public static void main(String[] args) {
    int handCount = Integer.parseInt(args[0]);
    if(handCount > 10) {
      System.err.println("Too many hands! Maximum is 10.\njava Deal [hands]");
    }

    Card[] deck = new Card[52];
    for(var i = 0; i < 52; i++) {
      var value = (i % 13) + 1;
      var suit = i / 13;
      deck[i] = Card.fromNumbers(suit, value);
    }
    
    Random random = new Random();
    for(var i = 0; i < deck.length; i++) {
      var second = random.nextInt(deck.length);
      var a = deck[i];
      deck[i] = deck[second];
      deck[second] = a;
    }

    for(var handNumber = 0; handNumber < handCount; handNumber++) {
      var offset = handNumber * 5; 
      Card[] hand = { deck[offset], deck[offset+1], deck[offset+2], deck[offset+3], deck[offset+4] };
      System.out.println(Card.sprintCards(hand));
    }
  }
}
