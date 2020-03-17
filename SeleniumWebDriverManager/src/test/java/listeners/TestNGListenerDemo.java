package listeners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//To rerun failed test: java -classpath testng.jar;%CLASSPATH% org.testng.TestNG -d test-outputs test-outputs\testng-failed.xml

//@Ignore
@Listeners(listeners.TestNGListeners.class) //({_,_})
public class TestNGListenerDemo {
	
	//dependency >> priority
	@Test(priority = 1, groups = {"window.sanity"}, retryAnalyzer = listeners.RetryAnalyzer.class)
	public void test1() {
		
		System.out.println("I am inside test 1");
		
	}
	
	@Test(priority = 2, groups = {"window.sanity", "window.smoke"}, dependsOnMethods = {"test1"}, retryAnalyzer = listeners.RetryAnalyzer.class) //-1
	public void test2() {
		
		System.out.println("I am inside test 2");
		
		//Assert.assertTrue(false);
		//int i= 1/0;
		
	}
	
	//@Ignore
	@Test(priority = 3, groups = {"window.regression"}, retryAnalyzer = listeners.RetryAnalyzer.class) //0
	public void test3() {
		
		System.out.println("I am inside test 3");
		
		//throw new SkipException("This test case 3 is skipped.");
		Assert.assertTrue(0>1);
		
	}
	
}
