package 자바공부.폴리;

public class Father {
	String job = "건설";
	public Father() {
		// TODO Auto-generated constructor stub
		//this.job = job;
	}
	public Father(String job) {
		// TODO Auto-generated constructor stub
		this.job = job;
	}
	void work() {
		System.out.printf("아빠가 %s 일해요\n",job);
	}	
}
