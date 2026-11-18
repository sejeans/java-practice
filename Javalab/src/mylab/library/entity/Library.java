package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;       // 도서관 이름
    private List<Book> books;  // 보유 도서 목록

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();  // 배열과 달리 크기를 미리 정하지 않아도 된다
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    // 제목은 하나만 찾으면 되므로 Book 하나를 반환. 없으면 null
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {   // 문자열 비교는 == 가 아니라 equals
                return book;
            }
        }
        return null;
    }

    // 한 저자가 여러 권을 썼을 수 있으므로 List로 반환
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // 실제 상태 변경은 Book에게 맡긴다 (책임 분리: Library는 찾기, Book은 자기 상태 관리)
    public boolean checkOutBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book == null) {
            return false;
        }
        return book.checkOut();
    }

    public boolean returnBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book == null || book.isAvailable()) {  // 없는 책이거나 대출 중이 아니면 반납 실패
            return false;
        }
        book.returnBook();
        return true;
    }

    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    // 내부 리스트를 그대로 넘기면 밖에서 add/remove로 망가뜨릴 수 있어 복사본을 준다
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooksCount() {
        return getAvailableBooks().size();
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
