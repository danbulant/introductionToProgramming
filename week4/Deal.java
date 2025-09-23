import java.util.Random;

public class Deal {
  public static void main(String[] args) {
    int handCount = Integer.parseInt(args[0]);
    // We have 52 cards, each hand is 5 cards
    if(handCount > 10) {
      System.err.println("Too many hands! Maximum is 10.\njava Deal [hands]");
    }

    // Generate the deck
    Card[] deck = new Card[52];
    for(var i = 0; i < 52; i++) {
      // 13 cards for each suit, take advantage of int division
      var value = (i % 13) + 1;
      var suit = i / 13;
      deck[i] = new Card(Card.Suit.values()[suit], value);
    }
    
    // Randomly shuffle the deck
    Random random = new Random();
    for(var i = 0; i < deck.length; i++) {
      // swap each item with a random item in the array
      // isn't truly random, but we're not using crypto random anyway
      var second = random.nextInt(deck.length);
      var a = deck[i];
      deck[i] = deck[second];
      deck[second] = a;
    }

    for(var handNumber = 0; handNumber < handCount; handNumber++) {
      var offset = handNumber * 5; 
      // since the deck is shuffled, we just take the next 5 cards from the array
      // java could use an array slice so that we didn't need to copy all the objects..
      Card[] hand = {
        deck[offset],
        deck[offset+1],
        deck[offset+2],
        deck[offset+3],
        deck[offset+4]
      };
      System.out.println(Card.sprintCards(hand));
    }
  }
}
