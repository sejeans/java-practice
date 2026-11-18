package mylab.bank.exception;

// Exception을 상속 → checked 예외. 호출하는 쪽이 try-catch나 throws를 반드시 써야 한다
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);   // 메시지는 부모(Exception)에 넘겨 두면 getMessage()로 꺼낼 수 있다
    }
}
