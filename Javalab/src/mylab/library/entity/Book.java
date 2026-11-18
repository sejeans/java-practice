package mylab.library.entity;

public class Book {
    private String title;        // 제목
    private String author;       // 저자
    private String isbn;         // ISBN
    private int publishYear;     // 출판년도
    private boolean isAvailable; // 대출 가능 여부 (true = 대출 가능)

    public Book() {
        this.isAvailable = true;   // 새로 들어온 책은 대출 가능 상태
    }

    public Book(String title, String author, String isbn, int publishYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.isAvailable = true;
    }

    // 대출: 빌릴 수 있을 때만 상태를 바꾸고 true, 이미 대출 중이면 false
    public boolean checkOut() {
        if (!isAvailable) {
            return false;
        }
        isAvailable = false;
        return true;
    }

    // 반납: 다시 대출 가능 상태로
    public void returnBook() {
        isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    // boolean 필드의 getter는 관례상 getXxx 대신 isXxx (Eclipse 자동 생성도 이렇게 만든다)
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "책 제목: " + title + "\n"
             + "저자: " + author + "\n"
             + "ISBN: " + isbn + "\n"
             + "출판년도: " + publishYear + "\n"
             + "대출 가능 여부: " + (isAvailable ? "가능" : "대출 중");
    }
}
