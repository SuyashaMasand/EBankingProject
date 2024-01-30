package PracticePackage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.genericUtility.BaseClass;

@Listeners(Com.genericUtility.ListnerImplimentationClass.class)
public class NewTest extends BaseClass{

	@Test
	public void demo()
	{
		System.out.println("---done ---");
		Assert.fail();
	}
}
