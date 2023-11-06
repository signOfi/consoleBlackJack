import java.util.concurrent.BlockingDeque;

public class Bet {

    private Balance balance;

    private int betAmount;

    public Bet(Balance balance) {
        this.balance = balance;
        betAmount = 0;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public boolean ableToBet(int userBet) {
        // Want to make sure we can bet an amount
        if (userBet <= 0 || userBet > balance.getBalance()) {
            System.out.print("Please enter a valid amount to bet $ ");
            return false;
        }
        else {
            return true;
        }
    }

    public void standardWin() {
        int amount = balance.getBalance();
        amount += betAmount;
        balance.setBalance(amount);
    }

    public void standardLose() {
        int amount = balance.getBalance();
        amount -= betAmount;
        balance.setBalance(amount);
    }

    public void fold() {
        int amount = balance.getBalance();
        amount -= (betAmount / 2);
    }


    public String toString() {
        return "Your new balance is " + balance.getBalance();
    }






}
