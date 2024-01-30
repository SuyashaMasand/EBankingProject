package com.TestNG;

import org.testng.annotations.Test;

public class DataProvider_Execution_Test {
	
	
	
	//way 2 - execute data provider method is different class
	@Test(dataProviderClass = DataProviderTest.class, dataProvider = "excelData1")
	public void executeTest(String name, String value ) {
		
		System.out.println("name---->"+name+"to----->"+value);
	}
	
}
