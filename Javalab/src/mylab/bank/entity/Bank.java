package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
    private List<Account> accounts;  // 은행이 관리하는 계좌 목록
    private int nextAccountNumber;   // 다음에 줄 계좌번호

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountNumber = 1000;
    }

    // 계좌는 Bank 안에서만 만든다 → 계좌번호 중복을 Bank가 책임진다 (합성 관계)
    public String createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        String accountNumber = "AC" + nextAccountNumber++;  // 쓰고 나서 1 증가
        Account account = new SavingsAccount(accountNumber, ownerName, initialBalance, interestRate);
        accounts.add(account);
        System.out.println("저축 계좌가 생성되었습니다: " + account);
        return accountNumber;
    }

    public String createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String accountNumber = "AC" + nextAccountNumber++;
        Account account = new CheckingAccount(accountNumber, ownerName, initialBalance, withdrawalLimit);
        accounts.add(account);
        System.out.println("체킹 계좌가 생성되었습니다: " + account);
        return accountNumber;
    }

    // 못 찾으면 null을 돌려주는 대신 예외를 던진다 → 호출하는 쪽이 null 체크를 잊을 수 없다
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    // CheckingAccount면 오버라이드된 withdraw()가 불린다 (다형성)
    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        // 두 계좌를 먼저 다 찾아 둔다. 받는 계좌가 없는데 돈부터 빠져나가면 안 되니까
        Account from = findAccount(fromAccountNumber);
        Account to = findAccount(toAccountNumber);

        from.withdraw(amount);  // 여기서 예외가 나면 아래 입금은 실행되지 않는다
        to.deposit(amount);
        System.out.println(amount + "원이 " + fromAccountNumber + "에서 " + toAccountNumber + "로 송금되었습니다.");
    }

    public void printAllAccounts() {
        System.out.println("=== 모든 계좌 목록 ===");
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("===================");
    }
}
