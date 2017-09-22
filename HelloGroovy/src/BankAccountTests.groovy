//Sample groovy test case to test BankAccount class
import groovy.mock.interceptor.*;

class BankAccountTests extends GroovyTestCase {

    private account;

    def void setUp() {
        println 'In Setup';
        account = new BankAccount(10);
    }

    def void tearDown() {
        println 'In teardown'
        account = null;
    }

    def void testCanDepositMoney() {
        account.deposit(5);
        assert 15 == account.balance;
    }

    def void testCanWithdrawMoney() {
        account.withdraw(5);
        assert 5 == account.balance;
    }

    def void testCannotWithdrawMoreMoneyThanBalance() {
        shouldFail(InsufficientFundsException) {
            account.withdraw(15);
        }
    }

    def void testCanAccrueInterest() {
        account.accrueInterest();
        assert 10 == account.balance;
    }

    def void testCanAccrueInterestWithStub() {
        def service = new StubFor(InterestRateService)
        service.demand.getInterestRate {
            return 0.10;
        }
        service.use {
            account.accrueInterest();
            assert 11 == account.balance;
        }
    }

    def void testCanAccrueInterestWithMock() {
        def service = new MockFor(InterestRateService)
        service.demand.getInterestRate {
            return 0.10;
        }
        service.use {
            account.accrueInterest();
            assert 11 == account.balance;
        }
    }

}
