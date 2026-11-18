package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

// abstract: "그냥 계좌"는 만들 수 없고 저축/체킹 같은 구체적인 계좌만 만들게 한다
public abstract class Account {
    private String accountNumber;  // 계좌번호
    private String ownerName;      // 소유자 이름
    private double balance;        // 잔액

    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + "원이 입금되었습니다. 현재 잔액: " + balance + "원");
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("잔액이 부족합니다. 현재 잔액: " + balance + "원");
        }
        balance -= amount;
        System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + balance + "원");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // balance는 setter를 두지 않았다. 잔액은 deposit/withdraw로만 바뀌어야 하기 때문
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountNumber + ", 소유자: " + ownerName + ", 잔액: " + balance + "원";
    }
}
