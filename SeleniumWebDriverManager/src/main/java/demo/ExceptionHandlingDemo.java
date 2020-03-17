package demo;

public class ExceptionHandlingDemo {
	
	//try, catch, finally, throws, throw
	public static void main(String[] args) {
		try {
			demo();
		} 
		catch(Exception e) {
			System.out.println("I am inside catch block");

			//Check Error
			System.out.println("Error is: " + e.getMessage());
			System.out.println("Cause is: " + e.getCause());
			e.printStackTrace();
		}
		finally {
			System.out.println("I am inside finally block");
		}
	}

	public static void demo() throws Exception {
		
			System.out.println("Hello World....!");
			
			//throw new ArithmeticException("not valid operation");
			int i = 1/0; //Error hence execution stop	
			System.out.println("Completed");
		
	}

}
