import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10; 
    private static int totalRounds = 0; 
    private static int roundsWon = 0; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            int targetNumber = random.nextInt(100) + 1;
            displayNewRoundBanner();
            System.out.println("The number has been generated between 1 and 100. You have " + MAX_ATTEMPTS
                    + " attempts to guess it!");
            playRound(scanner, targetNumber);
            totalRounds++;
            playAgain = askPlayAgain(scanner);
        } while (playAgain);

        displayScore();
        scanner.close();
    }

    private static void displayNewRoundBanner() {
        System.out.println("======================================");
        System.out.println("|          NEW ROUND STARTED         |");
        System.out.println("======================================");
    }

    private static void displaySuccessArt() {
        System.out.println("*******************************");
        System.out.println("*     YOU GUESSED IT!         *");
        System.out.println("*        CONGRATULATIONS!     *");
        System.out.println("*******************************");
    }

    private static void displayFailureArt(int targetNumber) {
        System.out.println("#################################");
        System.out.println("#       OUT OF ATTEMPTS!        #");
        System.out.println("#  The correct number was: " + targetNumber + "    #");
        System.out.println("#################################");
    }

    private static void playRound(Scanner scanner, int targetNumber) {
        int attempts = 0;
        boolean guessedCorrectly = false;

        while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Please enter a number between 1 and 100.");
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                displaySuccessArt();
                guessedCorrectly = true;
                roundsWon++;
            }
        }

        if (!guessedCorrectly) {
            displayFailureArt(targetNumber);
        }
    }

    private static boolean askPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().toLowerCase();
        return response.equals("yes");
    }

    private static void displayScore() {
        System.out.println("===================================");
        System.out.println("Game Over! You've played " + totalRounds + " rounds and won " + roundsWon + " times.");
        System.out.println("===================================");
    }
}
