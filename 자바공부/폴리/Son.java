package 자바공부.폴리;

public class Son extends Father{
	String job = "IT";
	public Son() {
		
	}
	public Son(String job) {
		super();
		this.job = job;
	}

	void work() {
		System.out.printf("아들이 %s 일해요\n",job);
	}
}
