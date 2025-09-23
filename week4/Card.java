public class Card {
  
  public enum Suit {
    Clubs,
    Diamonds,
    Hearts,
    Spades;
    
    public static final String CLUBS = "♣";
    public static final String DIAMONDS = "♦";
    public static final String HEARTS = "♥";
    public static final String SPADES = "♠";

    public String sprint() {
      switch(this) {
        case Clubs: return CLUBS;
        case Diamonds: return DIAMONDS;
        case Hearts: return HEARTS;
        case Spades: return SPADES;
      }
      throw new NullPointerException();
    }
  }

  public Suit suit;
  public int value;

  public Card(Suit suit, int value) {
    this.suit = suit;
    this.value = value;
  }

  // Print a single character for the value
  // Only a single character is returned so that we can format
  // the card output correctly and easily
  public char sprintValue() {
    assert this.value > 0 && this.value < 14;
    if(this.value == 1) {
      return 'A';
    } else if(this.value == 11) {
      return 'J';
    } else if(this.value == 12) {
      return 'Q';
    } else if(this.value == 13) {
      return 'K';
    } else if(this.value == 10) {
      // UTF-8 character that looks like a 10
      // but uses only one character width
      return '⒑';
    }
    // asserted 0 < x < 14, we handled 1, 10-13
    // the only valid values here are 2-9
    return Integer.toString(this.value).charAt(0);
  }

  // Print the card to a string to be later processed (or printed).
  // Approximates 'normal' card deck look
  // Numbered cards have their suit symbol repeated based on their value.
  // Face cards are empty.
  public String sprintCard() {
    var output = "";
    var value = this.sprintValue();
    var suit = this.suit.sprint();
    
    // Generate the top (and bottom) of a card
    // this will show the value of the card on each edge
    var top = "";
    top += value;
    if(this.value > 10) {
      top += "   ";
    } else {
      // and for numbered cards, show the suit characters
      top += this.value >= 4 ? suit : " ";
      top += this.value < 4 && this.value > 1 ? suit : " ";
      top += this.value >= 4 ? suit : " ";
    }
    top += value;
    top += "\n";

    output += top;

    if(this.value > 10) {
      // face cards get suits on the side, right above and under their values
      // numbered cards are empty on their sides
      output += suit + "   " + suit + "\n";
      output += suit + "   " + suit + "\n";
    } else {
      // normal cards have either 3 or 4 rows of suits in 1-3 columns,
      // with the middle one sometimes floating.
      // we have to have a set size and can't have floating characters,
      // so this is a best effort approximation
      // instead of using 3 rows we have a gap in the 3rd row
      output += " ";
      output += this.value >= 6 ? suit : " ";
      // odd or 10
      output += this.value % 2 == 1 || this.value == 10 ? suit : " ";
      output += this.value >= 6 ? suit : " ";
      output += " ";
      output += "\n";
  
      
      output += " ";
      output += this.value >= 8 ? suit : " ";
      output += this.value == 10 ? suit : " ";
      output += this.value >= 8 ? suit : " ";
      output += " ";
      output += "\n";
    }

    output += top;

    return output;
  }

  // Shows cards next to each other (left to right)
  // assumes that sprintCard returns the same width for each card (and each row of a card)
  public static String sprintCards(Card[] cards) {
    String[] output = { "", "", "", "" };

    // split each card into it's 4 rows
    // save the row into relevant output
    for(var i = 0; i < cards.length; i++) {
      var cardstr = cards[i].sprintCard().split("\n");
      for(var x = 0; x < cardstr.length; x++) {
        output[x] += cardstr[x] + " ";
      }
    }

    // and join the rows together with a newline
    var outputstr = "";
    for(var i = 0; i < output.length; i++) {
      outputstr += output[i] + "\n";
    }
    return outputstr;
  }
}
