import java.util.*;
public class Person {

    private List<Card> hand;

    public Person() {
        hand = new ArrayList<>();
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getValueOfHand() {
        Integer sum = hand.stream()
                .map(card -> card.getValue())
                .reduce(0,(a, b) -> a + b);

            if (sum > 21) {
                boolean flag = hand.stream()
                        .map(card -> card.getRank())
                        .anyMatch(s -> s.equals("Ace"));
                if (flag) {
                    sum -= 10;
                }
        }

        return sum;
    }

    public boolean bust() {
        int value = getValueOfHand();

        if (value > 21) {
            return true;
        }
        else {
            return false;
        }
    }


    public void bust(String name) {
        System.out.println(name + " has busted!");
    }


}
