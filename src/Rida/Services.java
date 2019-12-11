package Rida;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.utility.TestUtil;




public class Services  {
	
	public class CompanyAgent {
		public String baseUrl = "http://139.59.21.242/";
		public WebDriver driver;
		SoftAssert softassert = new SoftAssert();
		
	
		

		


@BeforeTest
public void setUp(){
	System.setProperty("webdriver.chrome.driver", "/home/sangeetha/Desktop/Selenium/chromedriver_linux64/chromedriver");
	driver=new ChromeDriver();
	driver.get(baseUrl);
	 driver.manage().window().maximize();	
	 driver.manage().deleteAllCookies();
	 driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
     driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

}

@Test(priority = 0)
public void verifyTitle(){
	String expectedtitle = "Rida International";
	String actualtitle = driver.getTitle();
	assertEquals(actualtitle, expectedtitle,"Title does not match");
}

@DataProvider
public Iterator<Object[]> getTestDataForLogin() {
ArrayList<Object[]> testDataLogin=TestUtil.getDataFromExcelForLogin();
return testDataLogin.iterator();
}
@Test(dataProvider = "getTestDataForLogin" , priority = 1)
public void login(String Username, String Password) {
WebElement Email = driver.findElement(By.name("email"));
Email.clear();
Email.sendKeys(Username);
WebElement pwd =  driver.findElement(By.name("password"));
pwd.clear();
pwd.sendKeys(Password);
driver.findElement(By.xpath("//*[@class='common-button loginBtn']")).click();
WebElement logggedInUser = driver.findElement(By.xpath("//span[contains(text(),'Dashboard')]"));
softassert.assertEquals(logggedInUser.getText(),"Dashboard","Unauthorized user");
softassert.assertAll();
}

@Test(priority = 2)

public void AddAgent() throws InterruptedException
{

driver.findElement(By.xpath("//*[text()='AGA']")).click();
List<WebElement> elementsUnderAGA = driver.findElements(By.xpath("//div[@class='MuiExpansionPanelActions-root sidebarExpansionPanelActions MuiExpansionPanelActions-spacing']/a"));
for(WebElement e : elementsUnderAGA) {
  System.out.println(e.getText());
  Thread.sleep(1000);
}
driver.findElement(By.xpath("//span[text() = 'Service']")).click();
driver.findElement(By.xpath("//span[text()='Add Service']")).click();

}




}
}
