package listeners;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(listeners.TestNGListeners.class) //({_,_})
public class TestNGListenerDemo2 {
	
	@Test(groups = {"sanity"})
	public void test4() {
		System.out.println("I am inside test 4");
	}
	
	@Test(groups = {"sanity", "smoke"})
	public void test5() {
		System.out.println("I am inside test 5");
	}
	
	@Test(groups = {"regression"})
	public void test6() {
		System.out.println("I am inside test 6");
	}
	
	@Test
	public void test7() {
		System.out.println("I am inside test 7");
	}
	
}
