public class Dealer extends Person{

    public Dealer() {
        super();
    }

    public void printDealerWholeHand() {
            System.out.println("Here is the value of the dealer's hand -> " + getValueOfHand() + " <-");
        }

    public String toString() {
        return "The dealer hand value is currently -> " + getHand().get(0).getValue() + " <-";
    }

    public boolean lessThan17() {
        if (getValueOfHand() < 17) {
            return true;
        }
        else {
            return false;
        }
    }

}
