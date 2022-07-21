package 자바공부;
import java.util.Arrays;

public class BookManager {
    private Book[] books;
    private int max=100;
    private int idx=0;

    // 배열 크기 꽉차면 새로운 배열에 크기를 2배늘려 기존 배열을 옮기기
    public BookManager(){
        books=new Book[max];
    }

    void add(Book b) {
        if(idx < max)
            books[idx++]=b;
        else
            System.out.println("도서가 100권을 넘었습니다");
    };

    void remove(String isbn) {
        for (int i = 0; i < books.length; i++) {
            if(isbn.equals(books[i].getIsbn())) {
                books[i] = new Book();
                break;
            }
        }
    };
    // 일반 사용자만 반환
	public Book[] getUsers() {
		
		int cnt = 0;
		
		for(int i=0; i<this.idx; i++) {
			if(!(books[i] instanceof Magazine)) {
				cnt++;
			}
		}
		
		if (cnt == 0)
			return null;
		
            Book[] res = new Book[cnt];
		
		for(int i=0, index = 0; i<this.idx; i++) {
			if(!(books[i] instanceof Magazine)) {
				res[index++] = books[i];
			}
		}
		
		return res;
		
	}
	
	// Magazine만 반환
	public Magazine[] getMagazine() {
		
		int cnt = 0;
		
		for(int i=0; i<this.idx; i++) {
			if(books[i] instanceof Magazine) {
				cnt++;
			}
		}
		
		if (cnt == 0)
			return null;
		
            Magazine[] res = new Magazine[cnt];
		
		for(int i=0, index = 0; i<this.idx; i++) {
			if(books[i] instanceof Magazine) {
				res[index++] = (Magazine)books[i];
			}
		}
		
		return res;
		
	}

    Book[] getlist() {
        System.out.println(Arrays.toString(books));
        return books;
    }
    
	public Book[] searchByName(String title) {

		int cnt = 0;

		for (int i = 0; i < this.idx-1; i++) {
			// 주어진 이름을 포함하는 사용자인지 검사
			if (books[i].getTitle().contains(title)) {
				cnt++;
			}
		}

		if (cnt == 0)
			return null;

		Book[] res = new Book[cnt];

		for (int i = 0, index = 0; i < this.idx; i++) {
			// 주어진 이름을 포함하는 사용자인지 검사
			if (books[i].getTitle().contains(title)) {
				res[index++] = books[i];
			}
		}

		return res;
	}
    // 사용자의 평균 나이 반환
	public double getTotalPrice() {
		
		int sum = 0;
		
		for(int i=0; i<this.idx; i++) {
			sum += books[i].getPrice();;
		}
		
		return sum;
		
	}
    // 사용자의 평균 나이 반환
	public double getPriceAvg() {
		
		int sum = 0;
		
		for(int i=0; i<this.idx; i++) {
			sum += books[i].getPrice();
            
		}
		
		return sum/idx-1;
		
	}

}
