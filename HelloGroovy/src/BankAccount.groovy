//Sample BankAccount class to demo unit testing
class BankAccount {
    private balance;

    BankAccount(openingBalance) {
        balance = openingBalance;
    }

    def void deposit(amount) {
        balance += amount;
    }

    def void withdraw(amount) {
        if (balance > amount ) {
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException();
        }
    }

    def accrueInterest() {
        def service = new InterestRateService();
        def rate = service.getInterestRate();

        def accruedInterest = balance * rate;

        deposit(accruedInterest);
    }

    def getBalance() {
        balance;
    }
}
