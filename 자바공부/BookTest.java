package 자바공부;
import java.util.*;

public class BookTest {
    public static void main(String[] args) {
    Book book1 = new Book();
		book1.setIsbn("35355");
        book1.setTitle("Java Pro2");
        book1.setAuthor("김싸피");
		book1.setPublisher("jaen.kr");
		book1.setPrice(25000);
        book1.setDesc("Java 응용");

		Book book2 = new Book("21425", "Java Basic", "박싸피", "jaen.kr", 15000, "SW 모델링");
		Magazine m = new Magazine("21429", "Java Basic2", "김싸피2" , "jaen.kr", 16000, "java 응용", "2015", "1");
		
		BookManager bm = new BookManager();
		
		bm.add(book1);
		bm.add(book2);
        bm.add(m);
        

        System.out.println(Arrays.toString(bm.getlist()));
        System.out.println(Arrays.toString(bm.getUsers()));
        System.out.println(Arrays.toString(bm.getMagazine()));

        System.out.println("도서 가격 총합 : " + bm.getTotalPrice());
        System.out.println("도서 가격 평균 : " + bm.getPriceAvg());
        

    }
}
