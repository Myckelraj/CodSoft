public class BankAccount {
  private double balance;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
    } else {
      System.out.println("Deposit amount must be positive.");
    }
  }

  public boolean withdraw(double amount) {
    if (amount > 0) {
      if (amount <= balance) {
        balance -= amount;
        return true;
      } else {
        System.out.println("Insufficient funds.");
        return false;
      }
    } else {
      System.out.println("Withdrawal amount must be positive.");
      return false;
    }
  }
}
