package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        String hong = bank.createSavingsAccount("홍길동", 10000, 3.0);   // AC1000
        String kim = bank.createCheckingAccount("김철수", 20000, 5000);  // AC1001
        String lee = bank.createSavingsAccount("이영희", 30000, 2.0);    // AC1002

        bank.printAllAccounts();

        try {
            System.out.println("=== 입금/출금 테스트 ===");
            bank.deposit(hong, 5000);
            bank.withdraw(kim, 3000);

            System.out.println("=== 이자 적용 테스트 ===");
            // findAccount()는 Account 타입을 돌려준다. applyInterest()는 SavingsAccount에만 있어서 형변환이 필요
            SavingsAccount savings = (SavingsAccount) bank.findAccount(hong);
            savings.applyInterest();

            System.out.println("=== 계좌 이체 테스트 ===");
            bank.transfer(lee, kim, 5000);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        bank.printAllAccounts();

        // 1) 한도 초과: 체킹 계좌 한도 5000원보다 큰 6000원 출금
        tryWithdraw(bank, kim, 6000);

        // 2) 잔액 부족을 노린 출금(잔액 22000원 < 30000원).
        //    CheckingAccount는 한도 검사를 먼저 하므로 이것도 한도 초과로 잡힌다 (Sample Run과 같은 결과)
        tryWithdraw(bank, kim, 30000);

        // 3) 없는 계좌
        tryWithdraw(bank, "AC9999", 1000);
    }

    private static void tryWithdraw(Bank bank, String accountNumber, double amount) {
        try {
            bank.withdraw(accountNumber, amount);
        } catch (WithdrawalLimitExceededException e) {
            // 자식 예외를 부모 예외보다 먼저 catch해야 한다. 순서가 반대면 컴파일 에러(도달 불가 코드)
            System.out.println("예외 발생: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
