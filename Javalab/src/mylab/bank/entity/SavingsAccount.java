package mylab.bank.entity;

public class SavingsAccount extends Account {
    private double interestRate;  // 이자율(%) 예: 3.0 = 3%

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);  // 공통 필드는 부모 생성자가 초기화
        this.interestRate = interestRate;
    }

    // 이자도 결국 입금이므로 deposit()을 재사용한다 (balance가 private이라 직접 못 바꾸기도 함)
    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("이자 " + interest + "원이 적용되었습니다. 현재 잔액: " + getBalance() + "원");
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return super.toString() + ", 이자율: " + interestRate + "%";
    }
}
