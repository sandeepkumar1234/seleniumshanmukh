package org.wtt.test;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wtt.docker.listener.PropertiesUtility;

public class SampleConnectGrid {
  
	
  WebDriver driver;
  @Test
  public void f() throws MalformedURLException {
	  DesiredCapabilities capabilities=DesiredCapabilities.chrome();
	  capabilities.setPlatform(Platform.LINUX);
	 driver=new RemoteWebDriver(new URL("http://qaopsstagingselenium.walkingtree.tech/wd/hub"), capabilities);
	  System.out.println("driver connected.............");
	  driver.navigate().to(PropertiesUtility.properties.getProperty("google.url"));
	  String title = driver.getTitle();
	  Assert.assertEquals("Google", title);
  }
}
