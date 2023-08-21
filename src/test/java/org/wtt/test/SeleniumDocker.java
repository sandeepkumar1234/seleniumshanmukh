package org.wtt.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.wtt.docker.listener.PropertiesUtility;

public class SeleniumDocker{  

	
	private static WebDriver driver;
	

	@BeforeSuite
	public void setupBeforeSuite(ITestContext context) {
  		PropertiesUtility.loadApplicationProperties();
		
	}	


	@BeforeTest
	public void setUp() throws MalformedURLException {
 		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL("http://qaopsstagingselenium.walkingtree.tech/wd/hub"), capabilities);
  		System.out.println("driver loaded.................ra worst");
		System.out.println(driver);
	}
	
	
	@AfterTest
	public void afterTest() throws MalformedURLException {
	}

	@Test(priority=1)
	public void simpleTest() throws MalformedURLException {
		// Launch website  
		driver.navigate().to(PropertiesUtility.properties.getProperty("application.url")); 
		String tittle=driver.getTitle();
		System.out.println("tittle"+tittle);
		Assert.assertEquals("Tutorials List - Javatpoint", driver.getTitle());
	}


	@Test(priority=2)
	public void testCheckBox() throws MalformedURLException {

		WebElement element=driver.findElement(By.xpath("//*[@id=\"link\"]/div/ul/li[3]/a"));
		element.click();
		WebElement element2=driver.findElement(By.xpath("//*[@id=\"city\"]/table/tbody/tr/td/h1"));
		String expectedText = element2.getText();
		System.out.println("checkBox():"+expectedText);
		Assert.assertEquals("Java Tutorial", expectedText);
		driver.navigate().refresh();
	}



}  

