package com.TestNG;

import org.testng.annotations.Test;

public class GroupExe2_Test {
	
	
	@Test(groups="smoke")
	public void test4() {
		System.out.println("----TEST4-----");
	}
	
	@Test(groups="regression")
	public void test5() {
		System.out.println("-----TEST5-------");
	}
	
	@Test(groups= {"smoke","regression"})
	public void test6() {
		System.out.println("-----TEST6---------");
	}

}
