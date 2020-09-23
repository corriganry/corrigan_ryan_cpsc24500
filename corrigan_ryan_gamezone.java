import java.util.Random;
import java.util.Scanner;
public class corrigan_ryan_gamezone {
    /**
     * This function welcomes the user
     */
    public static void welcome() {
        System.out.println("****************************************");
        System.out.println("*       Welcome to the Game Zone       *");
        System.out.println("****************************************");
    }
    /**
     * This function finds out which game the user would like to play
     * @return the choice of the user
     */
    public static int choice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to play?");
        System.out.println("1. Twenty-one");
        System.out.println("2. Rock Paper Scissors");
        System.out.println("3. Neither - I'm done!");
        System.out.print("Please enter the number of your choice: ");
        int choice = sc.nextInt();
        return choice;
    }
    /**
     * This function finds out the total number for the computers cards
     * @return the total of the cards the computer has
     */
    public static int computerCards() {
        Random rnd = new Random();
        int computerCards = rnd.nextInt(21 - 13) + 13;
        return computerCards;
    }
    /**
     * This function simulates a game of rock paper scissors
     */
    public static void rockPaperScissors() {
        Random rnd = new Random();
        int yourChoice = rnd.nextInt(3);
        int computerChoice = rnd.nextInt(3);
        String yourChoiceString = "";
        String computerChoiceString = "";
        String winMessage = "";
        if (yourChoice == computerChoice) {
            winMessage = ("It was a tie.");
            if (yourChoice == 0) {
                yourChoiceString = "Rock";
                computerChoiceString = "Rock";
            }
            else if (yourChoice == 1) {
                yourChoiceString = "Paper";
                computerChoiceString = "Paper";
            }
            else if (yourChoice == 2) {
                yourChoiceString = "Scissors";
                computerChoiceString = "Scissors";
            }
        }
        else if (yourChoice == 0 && computerChoice == 1) {
            winMessage = "The computer won.";
            yourChoiceString = "Rock";
            computerChoiceString = "Paper";
        }
        else if (yourChoice == 0 && computerChoice == 2) {
            winMessage = "You won.";
            yourChoiceString = "Rock";
            computerChoiceString = "Scissors";
        }
        else if (yourChoice == 1 && computerChoice == 0) {
            winMessage = "You won.";
            yourChoiceString = "Paper";
            computerChoiceString = "Rock";
        }
        else if (yourChoice == 1 && computerChoice == 2) {
            winMessage = "The computer won.";
            yourChoiceString = "Paper";
            computerChoiceString = "Scissors";
        }
        else if (yourChoice == 2 && computerChoice == 1) {
            winMessage = "You won.";
            yourChoiceString = "Scissors";
            computerChoiceString = "Paper";
        }
        else if (yourChoice == 2 && computerChoice == 0) {
            winMessage = "The computer won.";
            yourChoiceString = "Scissors";
            computerChoiceString = "Rock";
        }
        System.out.printf("You played %s, and the computer played %s.\n", yourChoiceString, computerChoiceString);
        System.out.println(winMessage);
    }
    // main
    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        welcome();
        int choice = choice();
        String choiceBJ = "y";
        int cardSum = 0;
        while (choice != 3) {
            if (choice == 1) {
                while (choiceBJ.equalsIgnoreCase("y")) {
                    int card = rnd.nextInt(11) + 1;
                    cardSum += card;
                    System.out.printf("You drew %d\n", (card));
                    System.out.printf("Your current total is %d\n", (cardSum));
                    if (cardSum > 21) {
                        System.out.println("Your total exceeded 21. You lose!");
                        choiceBJ = "n";
                    }
                    else if (cardSum == 21) {
                        System.out.println("Your total is 21. You win!");
                        choiceBJ = "n";
                    }
                    else {
                    System.out.print("Do you want to draw another card? ");
                    choiceBJ = sc.next();
                    }
                    
                }
                if (cardSum >= 21) {
                    System.out.print("");
                }
                else if (cardSum < 21) {
                    int computerCards = computerCards();
                    System.out.printf("The computer drew %d\n", (computerCards));
                    if (computerCards > cardSum) {
                        System.out.println("The computer won!");
                    }
                    else if (computerCards == cardSum) {
                        System.out.println("You tied!");
                    }
                    else if (cardSum > computerCards) {
                        System.out.println("You won!");
                    }
                }
                    
                
            }
            
            else if (choice == 2) {
                rockPaperScissors();
            }
            else if (choice != 1 && choice != 2 && choice != 3) {
                choice = choice();
            }
            choiceBJ = "y";
            cardSum = 0;
            choice = choice();
        }
    System.out.println("Thank you for playing.");
    }
}

