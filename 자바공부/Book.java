package 자바공부;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
    private String desc;
	
	public Book() {}

	public Book(String isbn, String title, String author, String publisher, int price, String desc) {		
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
        this.desc = desc;
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

	@Override
	public String toString() {
		return isbn + "    |" + title + "    |" + author + "    |" + publisher + "    |" + price + "    |" + desc;
	}
	
}
