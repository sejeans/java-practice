package mylab.book.control;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class ManageBook {

    public static void main(String[] args) {
        // 다형성: 부모 타입(Publication) 배열 하나에 자식 객체들을 섞어 담는다
        Publication[] publications = {
            new Magazine("마이크로소프트", "2007-10-01", 328, 9900, "매월"),
            new Magazine("경영과컴퓨터", "2007-10-03", 316, 9000, "매월"),
            new Novel("빠삐용", "2007-07-01", 396, 9800, "베르나르베르베르", "현대소설"),
            new Novel("남한산성", "2007-04-14", 383, 11000, "김훈", "대하소설"),
            new ReferenceBook("실용주의프로그래머", "2007-01-14", 496, 25000, "소프트웨어공학"),
            new Novel("소년이온다", "2014-05-01", 216, 15000, "한강", "장편소설"),
            new Novel("작별하지않는다", "2021-09-09", 332, 15120, "한강", "장편소설")
        };

        System.out.println("==== 도서 정보 출력 ====");
        for (int i = 0; i < publications.length; i++) {
            // 변수 타입은 Publication이지만 실제 객체(Magazine/Novel/...)의 toString()이 불린다
            System.out.println((i + 1) + ". " + publications[i]);
        }

        System.out.println("==== 가격 변경 ====");
        // 문서 설명은 "3번째 도서(인덱스 2)"지만 Sample Run과 통계 결과는 '작별하지않는다'(인덱스 6) 기준이라 6을 썼다
        Publication target = publications[6];
        int oldPrice = target.getPrice();
        System.out.println(target.getTitle() + " 변경 전 가격: " + oldPrice + "원");

        modifyPrice(target);

        int newPrice = target.getPrice();
        System.out.println(target.getTitle() + " 변경 후 가격: " + newPrice + "원");
        System.out.println("차액: " + (oldPrice - newPrice) + "원");

        StatisticsAnalyzer analyzer = new StatisticsAnalyzer();
        analyzer.printStatistics(publications);
    }

    // instanceof로 실제 객체 타입을 확인해서 타입마다 다른 할인율 적용
    public static void modifyPrice(Publication publication) {
        int currentPrice = publication.getPrice();
        if (publication instanceof Magazine) {
            publication.setPrice((int) (currentPrice * 0.6));   // 40% 할인
        } else if (publication instanceof Novel) {
            publication.setPrice((int) (currentPrice * 0.8));   // 20% 할인
        } else if (publication instanceof ReferenceBook) {
            publication.setPrice((int) (currentPrice * 0.9));   // 10% 할인
        }
    }
}
