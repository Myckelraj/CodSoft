import java.util.Scanner;

public class AtmInterface {
  public static void main(String[] args) {
    // Create a bank account with an initial balance
    BankAccount account = new BankAccount(1000.00);

    // Create an ATM with the bank account
    Atm atm = new Atm(account, "5657");
    try (Scanner scanner = new Scanner(System.in)) {
      boolean running = true;
      while (running) {
        showWelcomeMessage();

        // Simulate card processing
        simulateCardProcessing();

        atm.showMenu();

        // Ask the user if they want to perform another transaction
        System.out.println("Do you want to perform another transaction? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("no")) {
          running = false;
          System.out.println("Thank you for using SBI ATM. Have a nice day!");
        }
      }
    }
  }

  private static void showWelcomeMessage() {
    System.out.println("=============================================");
    System.out.println("Welcome To SBI ATM");
    System.out.println("Please insert your ATM card");
    System.out.println("=============================================");
  }

  private static void simulateCardProcessing() {
    System.out.println("Your ATM card is still processing...");
    try {
      Thread.sleep(4000); // Sleep for 4 seconds (4000 milliseconds)
    } catch (InterruptedException e) {
    }
  }
}
