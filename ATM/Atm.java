import java.time.LocalDate;
import java.util.Scanner;

public class Atm {
  private final BankAccount account;
  private final String password;
  private int withdrawalCount = 0;
  private LocalDate lastWithdrawalDate = LocalDate.now();
  private final Scanner scanner = new Scanner(System.in);

  public Atm(BankAccount account, String password) {
    this.account = account;
    this.password = password;
  }

  public void showMenu() {
    System.out.println("Please provide ATM PIN:");
    String enterPassword = scanner.next();
    if (!enterPassword.equals(password)) {
      System.out.println("Incorrect password. Operation cancelled.");
      return;
    }
    int choice;

    do {
      System.out.println("ATM Menu:");
      System.out.println("1. Check Balance");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();

      switch (choice) {
        case 1 -> checkBalance();
        case 2 -> deposit();
        case 3 -> withdraw();
        case 4 -> System.out.println("Thank you for using the ATM.");
        default -> System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 4);
  }

  private void checkBalance() {
    System.out.println("Your current balance is: $" + account.getBalance());
  }

  private void deposit() { // Get deposit amount from the user
    System.out.print("Enter amount to deposit: $");
    double depositAmount = scanner.nextDouble();

    // Perform the deposit
    if (depositAmount > 0) {
      account.deposit(depositAmount);
      System.out.println("Deposited $" + depositAmount + ". New balance is: $" + account.getBalance());
    } else {
      System.out.println("Deposit amount must be positive.");
    }
  }

  private void withdraw() {

    // Check if today is a new day
    if (!LocalDate.now().isEqual(lastWithdrawalDate)) {
      withdrawalCount = 0; // Reset withdrawal count if it's a new day
      lastWithdrawalDate = LocalDate.now();
    }

    int maxWithdrawalsPerDay = 3; // Maximum withdrawals allowed per day

    if (withdrawalCount >= maxWithdrawalsPerDay) {
      System.out.println("You have reached the maximum number of withdrawals for today.");
      return;
    }

    // Get withdrawal amount from the user
    System.out.print("Enter amount to withdraw: $");
    double withdrawalAmount = scanner.nextDouble();

    // Check if the account has enough balance
    if (account.getBalance() >= withdrawalAmount) {
      // Perform the withdrawal
      if (account.withdraw(withdrawalAmount)) {
        System.out.println("Withdraw $" + withdrawalAmount + ". New balance is: $" + account.getBalance());
        withdrawalCount++; // Increment withdrawal count
      } else {
        System.out.println("Withdrawal failed. Please try again.");
      }
    } else {
      System.out.println("Insufficient funds.");
    }
  }

}
