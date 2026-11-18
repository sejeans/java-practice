package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class StatisticsAnalyzer {

    // 타입별 평균 가격 = 타입별 가격 합 / 타입별 개수
    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> totalPrice = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            // getOrDefault: 키가 처음 나오면 0부터 시작
            totalPrice.put(type, totalPrice.getOrDefault(type, 0) + pub.getPrice());
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> average = new HashMap<>();
        for (String type : totalPrice.keySet()) {
            // (double)을 안 붙이면 int / int 라서 소수점이 잘린다
            average.put(type, (double) totalPrice.get(type) / count.get(type));
        }
        return average;
    }

    // 타입별 비율(%) = 타입별 개수 / 전체 개수 * 100
    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> count = new HashMap<>();
        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> distribution = new HashMap<>();
        for (String type : count.keySet()) {
            distribution.put(type, count.get(type) * 100.0 / publications.length);
        }
        return distribution;
    }

    // 출판일 "yyyy-MM-dd"의 앞 4글자가 연도
    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int matched = 0;
        for (Publication pub : publications) {
            if (pub.getPublishDate().substring(0, 4).equals(year)) {
                matched++;
            }
        }
        return matched * 100.0 / publications.length;
    }

    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) {
            return "소설";
        } else if (pub instanceof Magazine) {
            return "잡지";
        } else if (pub instanceof ReferenceBook) {
            return "참고서";
        }
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");  // 천 단위 쉼표, 소수점 최대 2자리

        System.out.println("===== 출판물 통계 분석 =====");

        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> average = calculateAveragePriceByType(publications);
        for (Map.Entry<String, Double> entry : average.entrySet()) {
            System.out.println("   - " + entry.getKey() + ": " + df.format(entry.getValue()) + "원");
        }

        System.out.println("2. 출판물 유형 분포:");
        Map<String, Double> distribution = calculatePublicationDistribution(publications);
        for (Map.Entry<String, Double> entry : distribution.entrySet()) {
            System.out.println("   - " + entry.getKey() + ": " + df.format(entry.getValue()) + "%");
        }

        double ratio = calculatePublicationRatioByYear(publications, "2007");
        System.out.println("3. 2007년에 출판된 출판물 비율: " + df.format(ratio) + "%");
    }
}
