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
    book1.setQuantity(1);

	Book book2 = new Book("21425", "Java Basic", "박싸피", "jaen.kr", 15000, "SW 모델링", 2);
	Magazine m = new Magazine("21429", "Java Basic2", "김싸피2" , "jaen.kr", 16000, "java 응용", "2015", "1", 3);
		
	BookManagerImpl bm = BookManagerImpl.getInstance();
		
	bm.add(book1);
	bm.add(book2);
    bm.add(m);
        

    System.out.println(Arrays.toString(bm.getList()));
    System.out.println(Arrays.toString(bm.getBooks()));
    System.out.println(Arrays.toString(bm.getMagazines()));
    //System.out.println(Arrays.toString(bm.searchByTitle("Java")));
    try{
        bm.buy("21422", 2);              
    }
    catch(ISBNNotFoundException e){
        e.printStackTrace();
    }
    try{
        bm.sell("21425", 5);   
    }
    catch(ISBNNotFoundException e){
        e.printStackTrace();
    }
    catch(QuantityException e){
        e.printStackTrace();
    }

    System.out.println("도서 가격 총합 : " + bm.getTotalPrice());
    System.out.println("도서 가격 평균 : " + bm.getPriceAvg());
        

    }
}
