package mylab.student.exception;

public class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message);   // 메시지를 부모(Exception)에게 넘김 → 나중에 e.getMessage()로 꺼냄
    }
}
