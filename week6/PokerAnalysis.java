package week6;

import common.StdDraw;
import common.StdRandom;
import common.StdStats;

public class PokerAnalysis {
  /**
   * Example program using PokerAnalysis
   * 
   * Renders a plot of each type of hand
   * The bars represent types in Hand.Type, in the same order
   */
  public static void main(String[] args) {
    var decks = 1000;
    // var hands = decks * 10;
    var types = analyzeShuffledDecks(decks);
    var typeFloats = new double[types.length];
    var max = StdStats.max(types);
    for(var i = 0; i < types.length; i++) {
      // max or hands can be used here
      typeFloats[i] = (double)types[i] / max;
    }
    
    StdDraw.setPenColor();
    StdStats.plotBars(typeFloats);
  }

  /**
   * Gets nth (5-card) hand of a given deck.
   * Up to 10 hands can be dealt from a given deck
   */
  public static Hand getHand(Card[] deck, int handOffset) {
    var offset = handOffset * 5; 
    return new Hand(new Card[]{
      deck[offset],
      deck[offset+1],
      deck[offset+2],
      deck[offset+3],
      deck[offset+4]
    });
  }

  /**
   * Gets the first hand of a given deck.
   */
  public static Hand getHand(Card[] deck) {
    return getHand(deck, 0);
  }

  public static Card[] getRandomDeck() {
    Card[] deck = new Card[52];
    for(var i = 0; i < 52; i++) {
      var value = (i % 13) + 1;
      var suit = i / 13;
      deck[i] = new Card(Card.Suit.values()[suit], value);
    }
    StdRandom.shuffle(deck);
    return deck;
  }

  /**
   * Shuffles n decks, draws 10 hands from each, and saves the number of hands of each type
   * Returns an array mapping type (using their ordinals) to number of hands found
   */
  public static int[] analyzeShuffledDecks(int decks) {
    var types = new int[Hand.Type.values().length];
    for(var i = 0; i < decks; i++) {
      var deck = getRandomDeck();
      for(var offset = 0; offset < 10; offset++) {
        var hand = getHand(deck, offset);
        types[hand.type().ordinal()]++;
      }
    }
    return types;
  }

  public static int numberOfHandsOfType(int[] analyzedShuffledDecks, Hand.Type type) {
    return analyzedShuffledDecks[type.ordinal()];
  }
}
