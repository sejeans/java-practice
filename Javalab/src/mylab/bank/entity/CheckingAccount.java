package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;  // 1회 출금 한도

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, double withdrawalLimit) {
        super(accountNumber, ownerName, initialBalance);
        this.withdrawalLimit = withdrawalLimit;
    }

    // 오버라이드: 한도 검사를 먼저 하고, 통과하면 잔액 검사와 실제 출금은 부모에게 맡긴다
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다. 한도: " + withdrawalLimit + "원");
        }
        super.withdraw(amount);
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    @Override
    public String toString() {
        return super.toString() + ", 출금 한도: " + withdrawalLimit + "원";
    }
}
