package week6;

import java.util.Arrays;

public class Hand {
  enum Type {
    StraightFlush,
    FourOfAKind,
    FullHouse,
    Flush,
    Straight,
    ThreeOfAKind,
    TwoPair,
    Pair,
    HighCard;
  }

  Card[] cards;

  public Hand(Card[] cards) {
    assert cards != null;
    assert cards.length == 5;
    this.cards = cards;
  }

  public Type type() {
    var doubleHistogram = doubleHistogram();
    var hasFlush = hasFlush();
    var hasStraight = hasStraight();
    if(hasFlush && hasStraight) return Type.StraightFlush;
    if(hasFlush) return Type.Flush;
    if(hasStraight) return Type.Straight;
    if(doubleHistogram[4] == 1) return Type.FourOfAKind;
    if(doubleHistogram[3] == 1 && doubleHistogram[2] == 1) return Type.FullHouse;
    if(doubleHistogram[3] == 1) return Type.ThreeOfAKind;
    if(doubleHistogram[2] == 2) return Type.TwoPair;
    if(doubleHistogram[2] == 1) return Type.Pair;
    return Type.HighCard;
  }

  int[] values() {
    var values = new int[cards.length];
    for(var i = 0; i < cards.length; i++) {
      values[i] = cards[i].value;
    }
    return values;
  }

  /**
   * Generates a histogram of card values.
   * Each element in an array contains the number of cards with that value.
   */
  int[] histogram() {
    return Histogram.histogram(values(), 14);
  }
  /**
   * Generates a histogram of the histogram of card values.
   * This shows how many times did any repetitions repeat.
   * For example (1,1,2,2,2,3,3) first histogram results in (0,2,3,2)
   * Second histogram (what this function returns) results in (1,0,2,1).
   * This tells us that there are two pairs and one three of a kind.
   */
  int[] doubleHistogram() {
    // if cards are dealt properly, the max is 5 (cards.length),
    // as a card can't appear more than 4 times
    var histogram = histogram();
    // System.out.println(Arrays.toString(histogram));
    return Histogram.histogram(histogram, cards.length + 1);
  }

  boolean hasFlush() {
    var suit = cards[0].suit;
    for(var i = 1; i < cards.length; i++) {
      if(cards[i].suit != suit) return false;
    }
    return true;
  }

  boolean hasStraight() {
    var values = values();
    Arrays.sort(values);
    var isStraight = true;
    for(var i = 1; i < values.length; i++) {
      if(values[i] != values[i-1] + 1) {
        isStraight = false;
        break;
      }
    }
    if(isStraight) return true;
    return isStraight || Arrays.equals(values, new int[]{
      // A 10 J Q K is valid as well
      1, 10, 11, 12, 13
    });
  }
}
