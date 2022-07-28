package 자바공부;

/**
 * 도서리스트를 관리하기 위한 클래스를 위한 명세역할의 인터페이스
 */
public interface IBookManager {
	//코드를 작성해주세요. 
	void add(Book book);
	
	void remove(String isbn);

	Book[] getList();

	Book searchByIsbn(String isbn);

	Book[] searchByTitle(String title);

	Magazine[] getMagazines();

	Book[] getBooks();

	int getTotalPrice();

	double getPriceAvg();

	void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException;

	void buy(String isbn, int quantity) throws ISBNNotFoundException;

}
