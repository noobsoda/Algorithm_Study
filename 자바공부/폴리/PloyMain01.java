package 자바공부.폴리;

public class PloyMain01 {

	public static void main(String[] args) {
		Father f = new Father();
		Son s = new Son();
		Father f1 = new Son();

		System.out.println(f.job);
		System.out.println(s.job);
		System.out.println(f1.job);
		
		f.work();
		s.work();		
		f1.work();
		
		
	}

}

