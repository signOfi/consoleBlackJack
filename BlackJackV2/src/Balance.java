public class Balance {

    private int balance;

    public Balance() {
        // All users will start with 100 dollars to bet
        balance = 100;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void newBalance(int balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Your balance is " + balance;
    }

}
