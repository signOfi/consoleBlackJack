public class Card {

    private String suite;
    private String rank;

    public Card(String suite, String rank) {
        this.suite = suite;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public String getSuite() {
        return suite;
    }

    public String toString() {
        return rank + " of "  + suite;
    }

    public int getValue() {
        if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
            return 10;
        }
        else if (rank.equals("Ace")) {
            return 11;
        }
        else {
            return Integer.valueOf(rank);
        }
    }
}


