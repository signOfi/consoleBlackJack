import java.util.*;
public class Deck {

    private List<Card> cards;
    private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    private String[] rank = {"2", "3", "4", "5", "6",
            "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    private Random random;
    private int size;


    public Deck() {
        // Creates new array list and initializes all 52 cards into a deck
        cards = new ArrayList<>();
        random = new Random();
        size = 51;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards.add(new Card(suits[i], rank[j]));
            }
        }
    }

    public Card draw() {
        int randomNum = random.nextInt(size);
        Card randomCard = cards.get(randomNum);
        cards.remove(randomCard);
        size--;
        return randomCard;
    }

    public int compare(int playerValue, int dealerValue) {
        if (playerValue > dealerValue) {
            return 1;
        }
        else if (playerValue == dealerValue) {
            return 0;
        }
        else {
            return -1;
        }
    }


}