package mylab.bank.exception;

// 한도 초과도 "돈을 못 뺀다"는 점에서 잔액 부족의 한 종류로 보고 상속했다.
// 그래서 catch (InsufficientBalanceException e) 하나로 둘 다 잡을 수도 있다.
public class WithdrawalLimitExceededException extends InsufficientBalanceException {
    public WithdrawalLimitExceededException(String message) {
        super(message);
    }
}
