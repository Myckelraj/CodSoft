package NumberGame;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      Random random = new Random();
      int maxAttempts = 5;
      boolean playAgain = true;
      System.out.println("Welcome to the Number Guessing Game!");
      while (playAgain) {
        int numberToGuess = random.nextInt(100) + 1;
        int numberOfAttempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("I have generated a number between 1 and 100. Can you guess it?");
        System.out.println("You have a maximum of " + maxAttempts + " attempts.");

        while (numberOfAttempts < maxAttempts && !hasGuessedCorrectly) {
          System.out.print("Enter your guess: ");
          int userGuess = scanner.nextInt();
          numberOfAttempts++;
          if (userGuess == numberToGuess) {
            System.out.println("Congratulations! You guessed the correct number.");
            hasGuessedCorrectly = true;
          } else if (userGuess > numberToGuess) {
            System.out.println("Too high! Try again.");
          } else {
            System.out.println("Too low! Try again.");
          }
        }
        if (!hasGuessedCorrectly) {
          System.out.println("Sorry, you've used all your attempts. The number was: " + numberToGuess);
        }

        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next();
        playAgain = response.equalsIgnoreCase("yes");
      }
      System.out.println("Thank you for playing! Goodbye buddy.");
    }
  }
}
