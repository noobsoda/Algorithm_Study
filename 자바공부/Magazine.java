package 자바공부;

public class Magazine extends Book {
	
	// 새로 추가된 필드 접근 제한자 설정
	private String year;
	private String month;

	public Magazine() {
		// 부모 클래스의 생성자 호출
		super();
	}

	public Magazine(String isbn, String title, String author, String publisher, int price, String desc, String year, String month) {
		// 부모 클래스의 생성자 호출
		super(isbn, title, author, publisher, price, desc);
		this.year = year;
		this.month = month;

	}

	/*
	 * 새로 추가된 필드들의 getter / setter 메소드를 추가해주세요.
	 */
	 public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
    public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Magazine [isbn=" + this.getIsbn() + ", title=" + this.getTitle() + ", author=" + this.getAuthor()
				+ ", publisher=" + this.getPublisher() + ", price=" + this.getPrice() + ", year=" + year + ", month=" + month
				+ "]";
	}

}
