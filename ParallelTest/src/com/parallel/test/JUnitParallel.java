package com.parallel.test;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;

@RunWith(Parallelized.class)
public class JUnitParallel {
	private WebDriver driver,driver1,driver2;
	private String number,model;
	private SelendroidCapabilities capa;
	private WebElement num,menu,accept;
	private boolean acceptNextAlert = true;
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
    LinkedList<String[]> env = new LinkedList<String[]>();
    env.add(new String[]{"9865513655","Nexus 7 (Google)"});
    env.add(new String[]{"8122108691","Nexus 4 (Google)"});
    env.add(new String[]{"9003043638","Nexus 5 (Google)"});
    return env;
  }
  


  public JUnitParallel(String number,String model) throws Exception 
  
  	{
	  this.number=number;
	  this.model=model;
	 
	}

  @Before
  public void setUp() throws Exception {  
   
	  capa = new SelendroidCapabilities("com.example.checking:1.0");
	  capa.setModel(model);
	  driver = new SelendroidDriver(capa);
	  
  }  

  @Test  
  public void testSimple() throws Exception {  

	  if(isElementPresent(By.id("editText1")))
		{
		num= driver.findElement(By.id("editText1"));
		num.sendKeys(number);
		Assert.assertEquals(number, num.getText());
		driver.findElement(By.id("button1")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//menu = driver.findElement(By.xpath("//OverflowMenuButton[@name='More options']"));
		//menu.click();
		Thread.sleep(5000);
		//TouchActions flick = new TouchActions(driver).singleTap(driver.findElement(By.xpath("//ActionMenuItemView[@id='accept']")));
		//flick.perform();
		WebElement element2 = driver.findElement(By.xpath("//ActionMenuItemView[@id='accept']"));
		element2.click();
		}
	  
}

  @After
  public void tearDown() throws Exception {  
    driver.quit();
    
  }
  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}