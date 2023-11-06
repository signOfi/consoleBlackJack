import java.util.*;
public class UserInterface {

    private Scanner scanner;
    private Player playerHand;
    private Dealer dealerHand;
    private Deck deck;
    private Bet bet;
    private Balance balance;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        playerHand = new Player();
        dealerHand = new Dealer();
        deck = new Deck();
        balance = new Balance();
        bet = new Bet(balance);
    }

    public void start() {
        startPhase();
        while (true) {
            boolean flag = initialBettingPhase();
            if (flag == false) {
                break;
            }
            initialHand();
            actionPhase();
            dealerPhase();
            // Restarts the deck and player and dealer hands
            playerHand = new Player();
            dealerHand = new Dealer();
            deck = new Deck();
        }
    }

    public void startPhase() {
        // Welcome player. all players will start with $100 dollars
        System.out.println("Your current balance is $" + balance.getBalance());
    }

    public boolean initialBettingPhase() {
        System.out.print("(Enter \"quit\" to quit) Please enter an amount you would like to bet $ ");
        while(true) {
            int userBet = validInput();
            if (userBet == -1) {
                return false;
            }
            boolean flag = bet.ableToBet(userBet);
            if (flag == true) {
                bet.setBetAmount(userBet);
                break;
            }
        }
        printEmptyLine();
        return true;
    }

    public void initialHand() {
        // This grabs 2 cards for the player and for the dealer
        // This also takes 4 cards out of the deck now we only can choose from 48 cards.
        for (int i = 0; i < 2; i++) {
            Card drawnCard1 = deck.draw();
            playerHand.addToHand(drawnCard1);
            Card drawnCard2 = deck.draw();
            dealerHand.addToHand(drawnCard2);
        }
        // Prints the players hand
        printPlayerHand();

        // Prints the dealers hand
        printDealerHand();

    }

    public void printValue(Person person) {
        System.out.println(person);
    }

    public void actionPhase() {
        printEmptyLine();
        while (true) {
            System.out.print("Would you like to hit or stand ");
            String userInput = scanner.nextLine();
            System.out.print("\n");
            if (userInput.equals("hit")) {
                Card drawnCard1 = deck.draw();
                playerHand.addToHand(drawnCard1);
                printPlayerHand();
            }
            else if (userInput.equals("stand")) {
                printVisibleDealerHand();
                break;
            }
            else {
                System.out.println("Not a valid command");
            }
            if (playerHand.bust()) {
                break;
            }
        }
    }

    public void printPlayerHand() {
        System.out.println("Player Hand Contains:");
        System.out.println("+-------------+");
        playerHand.getHand().stream()
                .forEach(card -> System.out.print(" " + card.getRank() + " of "
                + card.getSuite() + "\n"));
        System.out.println("+-------------+");
        printValue(playerHand);
        printEmptyLine();
    }

    public void printDealerHand() {
        System.out.println("Dealer Hand Contains: \n" + "+-------------+\n "
                + dealerHand.getHand().get(0) + "\n" + " [???] Card\n" + "+-------------+");
        printValue(dealerHand);
    }

    public void printVisibleDealerHand() {
        System.out.println("Dealer Hand Contains:");
        System.out.println("+-------------+");
        dealerHand.getHand().stream()
                .forEach(card -> System.out.print(" " + card.getRank() + " of "
                        + card.getSuite() + "\n"));
        System.out.println("+-------------+");
        dealerHand.printDealerWholeHand();
        printEmptyLine();
    }

    public void dealerPhase() {
        // Case I if dealer bust
        if (playerHand.bust()) {
            printBust("player");

            while (dealerHand.lessThan17()) {
                Card drawnCard = deck.draw();
                dealerHand.addToHand(drawnCard);
            }

        }
        // If dealer is < 17 must hit, dealer 17 < x <= 21 hold, else > 21 bust
        else {
            while (dealerHand.lessThan17()) {
                Card drawnCard = deck.draw();
                dealerHand.addToHand(drawnCard);
            }

            if (dealerHand.bust()) {
                printBust("dealer");
            } else {
                printWinner();
            }
        }
    }
    public void printWinner() {
        System.out.println("Lets see who wins...");
        int value = deck.compare(playerHand.getValueOfHand(), dealerHand.getValueOfHand());
        if (value == 1) {
            printInformation("wins","Player");
        }
        else if (value == 0) {
            printInformation("tie","There is a ");
        }
        else {
            printInformation("lose","Player");
        }
    }

    public void printInformation(String winsOrLose, String playerOrDealer) {
        printVisibleDealerHand();
        System.out.println(playerOrDealer + " " + winsOrLose + "!");
        if (winsOrLose.equals("wins")) {
            bet.standardWin();
        }
        else if (winsOrLose.equals("lose")) {
            bet.standardLose();
        }
        else if(winsOrLose.equals("tie")) {

        }
        System.out.println("Your hand value is " + playerHand.getValueOfHand());
        System.out.println(bet);
        printEmptyLine();
    }

    public void printEmptyLine() {
        System.out.println("");
    }

    public void printBust(String name) {
        printVisibleDealerHand();

        if (name.equals("player")) {
            playerHand.bust(name);
            bet.standardLose();
        }
        else {
            dealerHand.bust(name);
            bet.standardWin();
        }
        System.out.println("Your hand value is " + playerHand.getValueOfHand());
        System.out.println(bet);
        printEmptyLine();
    }

    public int validInput() {
        while (true) {
            try {
                String str = scanner.nextLine();
                if(str.equals("quit")) {
                    return -1;
                }
                int intValue = Integer.valueOf(str);
                return intValue;
            } catch (Exception e) {
                System.out.println("Not a number");
                System.out.print("Enter a valid Bet: ");
            }
        }
    }

}

