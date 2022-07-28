package 자바공부;

import java.util.*;
/**
 * 도서리스트를 배열로 유지하며 관리하는 클래스
 */

public class BookManagerImpl implements IBookManager {
    private ArrayList<Book> books = new ArrayList<>();

    private final int MAX_SIZE = 100;

    private static BookManagerImpl bm = new BookManagerImpl();
    
    private BookManagerImpl() {};
    
    public static BookManagerImpl getInstance(){
        return bm;
    }

    public void add(Book b) {
        if(books.size() < MAX_SIZE){
            books.add(b);
        }
        else{
            System.out.println("유저의 수가 100을 넘었습니다. 등록 불가.");
        }
    };

    public void remove(String isbn) {
        Book book;
        for(Iterator<Book> it = books.iterator(); it.hasNext();){
            book = it.next();
            if(isbn.equals(book.getIsbn())) {
                it.remove();
                break;
            }
        }        
    };

    @Override
    public Book[] getList() {  
        Book res[] = new Book[books.size()];
        
        return this.books.toArray(res);
    }
    

	public Book searchByIsbn(String isbn) {

		Book res = new Book();

        boolean flag = false;
        Book book;
        for(Iterator<Book> it = books.iterator(); it.hasNext();){
            book = it.next();
            if(book.getIsbn().contains(isbn)) {
                res = book;
                flag = true;
            }
        }  		
        if(!flag)       
			return null;

		return res;
	}

    public Book[] searchByTitle(String title) {

        ArrayList<Book> list = new ArrayList<>();
        
        Book book;
        for(Iterator<Book> it = books.iterator(); it.hasNext();){
            book = it.next();
            if(book.getTitle().contains(title)) {
                list.add(book);
            }
        }		

		if (list.size() == 0)
			return null;

		Book[] res = new Book[list.size()];
        
        return list.toArray(res);
        
	}
    // Magazine만 반환
	public Magazine[] getMagazines() {
		
		ArrayList<Magazine> list = new ArrayList<>();

		for(int i=0; i<books.size(); i++) {
			if(books.get(i) instanceof Magazine) {
                list.add((Magazine)books.get(i));
			}
		}
	
        Magazine[] res = new Magazine[list.size()];		
		
		return list.toArray(res);
		
	}
    
    public Book[] getBooks() {
	
        ArrayList<Book> list = new ArrayList<>();

		for (int i = 0; i < books.size(); i++) {
			if (!(books.get(i) instanceof Magazine)) {
				list.add(books.get(i));
			}
		}

        

		Book[] res = new Book[list.size()];

        return list.toArray(res);		
	}


    // 사용자의 총 가격 반환
    @Override
	public int getTotalPrice() {
		
		int sum = 0;
		
		for(int i=0; i<books.size(); i++) {
			sum += books.get(i).getPrice();;
		}
		
		return sum;
		
	}
    // 사용자의 평균 가격 반환
    @Override
	public double getPriceAvg() {
		
		int sum = 0;
		
		for(int i=0; i<books.size(); i++) {
			sum += books.get(i).getPrice();
            
		}
		
		return sum/books.size();
		
	}

    @Override
    public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException { 
        Book res = new Book();

        boolean flag = false;
        Book book;
        for(Iterator<Book> it = books.iterator(); it.hasNext();){
            book = it.next();
            if(book.getIsbn().contains(isbn)) {
                res = book;
                flag = true;
            }
        }  		
        if(!flag)       
			throw new ISBNNotFoundException(isbn); 
        
        if(res.getQuantity()-quantity < 1)
            throw new QuantityException();
        else
            res.setQuantity(res.getQuantity()-1);
        
        
        
    }

    @Override
    public void buy(String isbn, int quantity) throws ISBNNotFoundException {
        Book res = new Book();

        boolean flag = false;
        Book book;
        for(Iterator<Book> it = books.iterator(); it.hasNext();){
            book = it.next();
            if(book.getIsbn().contains(isbn)) {
                res = book;
                flag = true;
            }
        }  		
        if(!flag)       
			throw new ISBNNotFoundException(isbn);
        
        res.setQuantity(res.getQuantity()+1);
        
    }

}
