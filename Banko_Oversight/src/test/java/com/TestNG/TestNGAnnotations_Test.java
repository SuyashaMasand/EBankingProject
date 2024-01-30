package com.TestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations_Test {
	
	
	@BeforeSuite()
	public void beforeSuite() {
		System.out.println("----LAUNCH DB CONNECTION ------");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("------LAUNCH THE BROWSER-----");
	}
	
	
	@BeforeMethod
	public void beforeMethodA() {
		System.out.println("------LOGIN2-----");
	}
	
	@BeforeMethod
	public void beforeMethodZ() {
		System.out.println("------LOGIN1------");
	}
	
	@Test
	public void test1() {
		System.out.println("=====TEST SCRIPT1========");
	}
	
	@Test
	public void test2() {
		System.out.println("=====TEST SCRIPT2========");
	}
	
	@AfterMethod
	public void afterMethodZ() {
		System.out.println("-----LOGOUT1---------------");
	}
	
	
	@AfterMethod
	public void afterMethodA() {
		System.out.println("-----LOGOUT2---------------");
	}
	
	@AfterClass
	public void afterClas() {
		System.out.println("-----CLOSE THE BROWSER------------");
	}
	
	@AfterSuite
	public void aftersuite() {
		System.out.println("------CLOSE DB CONNECTION-----------------");
	}

}
