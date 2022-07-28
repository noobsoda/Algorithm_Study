package 자바공부;
/**
 * 도서 정보를 나타내는 클래스
 */
public class Book {
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
    private String desc;
	private int quantity;

	
	public Book() {}

	public Book(String isbn, String title, String author, String publisher, int price, String desc, int quantity) {		
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
        this.desc = desc;
		this.quantity = quantity;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String id) {
		this.isbn = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String password) {
		this.title = password;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String name) {
		this.author = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String email) {
		this.publisher = email;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int age) {
		this.price = age;
	}
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	};

	@Override
	public String toString() {
		return "Book [author=" + author + ", desc=" + desc + ", isbn=" + isbn + ", price=" + price + ", publisher="
				+ publisher + ", quantity=" + quantity + ", title=" + title + "]";
	}
	
}

