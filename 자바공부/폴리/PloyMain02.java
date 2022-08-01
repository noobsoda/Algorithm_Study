package 자바공부.폴리;

public class PloyMain02 {

	public static void main(String[] args) {
		Father f = new Father();
		Son s = new Son();
		Father f1 = new Son();
		
		Father[] arr = new Father[3];
		arr[0] = f;
		arr[1] = s;
		arr[2] = f1;
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] instanceof Son) {
				Son ss = (Son) arr[i];
				System.out.println(ss.job);
			}
			//System.out.println(arr[i].job);
		}
		
		System.out.println("---------------------");
		for (int i = 0; i < arr.length; i++) {
			arr[i].work();
		}
		
		System.out.println("---------------------");
		
		toWork(s);
		
		
	}
	
//	public static void toWork(Son s) {
//		s.work();
//	}
	
	public static void toWork(Father f) {
		f.work();
	}

}
