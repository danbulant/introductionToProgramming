public class Card {
  public static final String CLUBS = "♣";
  public static final String DIAMONDS = "♦";
  public static final String HEARTS = "♥";
  public static final String SPADES = "♠";

  public enum Suit {
    Clubs,
    Diamonds,
    Hearts,
    Spades;

    public String sprint() {
      switch(this) {
        case Clubs: return Card.CLUBS;
        case Diamonds: return Card.DIAMONDS;
        case Hearts: return Card.HEARTS;
        case Spades: return Card.SPADES;
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

  public static Card fromNumbers(int suit, int value) {
    return new Card(Suit.values()[suit], value);
  }

  public String sprintValue() {
    if(this.value == 1) {
      return "A";
    } else if(this.value == 11) {
      return "J";
    } else if(this.value == 12) {
      return "Q";
    } else if(this.value == 13) {
      return "K";
    } else if(this.value == 10) {
      return "⒑";
    }
    return Integer.toString(this.value);
  }

  public String sprintCard() {
    var output = "";
    var value = this.sprintValue();
    var suit = this.suit.sprint();
    
    output += value;
    
    if(this.value > 10) {
      output += "   ";
    } else {
      output += this.value >= 4 ? suit : " ";
      output += this.value < 4 && this.value > 1 ? suit : " ";
      output += this.value >= 4 ? suit : " ";
    }
    output += value;
    output += "\n";

    if(this.value > 10) {
      output += suit + "   " + suit + "\n";
      output += suit + "   " + suit + "\n";
    } else {
      output += " ";
      output += this.value >= 6 ? suit : " ";
      output += this.value % 2 == 1 || this.value >= 8 ? suit : " ";
      output += this.value >= 6 ? suit : " ";
      output += " ";
      output += "\n";
  
      
      output += " ";
      output += this.value >= 9 ? suit : " ";
      output += this.value == 8 || this.value == 10 ? suit : " ";
      output += this.value >= 9 ? suit : " ";
      output += " ";
      output += "\n";
    }

    
    output += value;
    
    if(this.value > 10) {
      output += "   ";
    } else {
      output += this.value >= 4 ? suit : " ";
      output += this.value < 4 && this.value > 1 ? suit : " ";
      output += this.value >= 4 ? suit : " ";
    }
    output += value;
    output += "\n";

    return output;
  }

  public static String sprintCards(Card[] cards) {
    String[] output = { "", "", "", "" };

    for(var i = 0; i < cards.length; i++) {
      var cardstr = cards[i].sprintCard().split("\n");
      for(var x = 0; x < cardstr.length; x++) {
        output[x] += cardstr[x] + " ";
      }
    }

    var outputstr = "";
    for(var i = 0; i < output.length; i++) {
      outputstr += output[i] + "\n";
    }
    return outputstr;
  }
}
