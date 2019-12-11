package Rida;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.utility.TestUtil;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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

	driver.findElement(By.xpath("//*[text()='BGA']")).click();
	List<WebElement> elementsUnderBGA = driver.findElements(By.xpath("//div[@class='MuiExpansionPanelActions-root sidebarExpansionPanelActions MuiExpansionPanelActions-spacing']/a"));
	for(WebElement e : elementsUnderBGA) {
	  System.out.println(e.getText());
	  Thread.sleep(1000);
	}
	driver.findElement(By.xpath("//span[text() = 'Agent']")).click();
	driver.findElement(By.xpath("//span[text()='Add agents']")).click();
	
}

@DataProvider
public Iterator<Object[]> getTestDataForAgent() {
	ArrayList<Object[]> testDataAgent=TestUtil.getDataFromExcelForAgent();
	return testDataAgent.iterator();
}
@Test(dataProvider = "getTestDataForAgent" , priority = 3)
public void newAgent(String Company, String Currency, String Markuptype, String Markupvalue, String Companystatus, String Companylogo, String Street, String Country , String City, String Postalcode,String Website, String Telephone, String Mobile, String Fax, String Email, String Skypeid) throws InterruptedException
{
driver.findElement(By.name("name")).sendKeys(Company);
Select currency = new Select(driver.findElement(By.name("currency")));
currency.selectByVisibleText(Currency);
Select markUpType = new Select(driver.findElement(By.name("markup_type")));
markUpType.selectByVisibleText(Markuptype);
driver.findElement(By.name("markup_value")).sendKeys(Markupvalue);
Select companyStatus = new Select(driver.findElement(By.name("status")));
companyStatus.selectByVisibleText(Companystatus);
driver.findElement(By.xpath("//input[@name='logo_url']")).sendKeys(Companylogo);
driver.findElement(By.xpath("//*[text()='CONTACT INFORMATION']")).click();
WebElement street = driver.findElement(By.name("street"));
street.sendKeys(Street);

driver.findElement(By.xpath("(//div[@class=' css-1wy0on6'])[1]")).click();
driver.findElement(By.xpath("//div[text()='China']")).click();
driver.findElement(By.xpath("(//div[@class=' css-1wy0on6'])[2]")).click();
driver.findElement(By.xpath("//div[text()='Saigon']")).click();

//WebElement country = driver.findElement(By.xpath("(//div[@class=' css-1wy0on6'])[1]"));
//country.sendKeys(Keys.ARROW_DOWN);

//List method
/*List<WebElement> countrylist = driver.findElements(By.xpath("(//div[@class='css-1g6gooi'])[1]"));
for(WebElement Country1 : countrylist){
    if(Country1.getText().equals(Country)) {
        Country1.click();
        break;
    }
}
List<WebElement> citylist = driver.findElements(By.xpath("(//div[@class='css-1g6gooi'])[2]"));
for(WebElement City1 : citylist){
    if(City1.getText().equals(City)) {
        City1.click();
        break;
    }
}*/

//Select Method
/*Select country = new Select(driver.findElement(By.xpath("//input[@id='react-select-3-input']")));
country.selectByVisibleText(Country);
Select city = new Select(driver.findElement(By.xpath("//div[@class=' css-1hwfws3'][2]")));
city.selectByVisibleText(City);*/

//Javascript executer sendkeys
/*WebElement country = driver.findElement(By.xpath("(//div[@class=' css-1hwfws3'])[1]"));
JavascriptExecutor js = (JavascriptExecutor) driver;  
js.executeScript("arguments[0].value='China';", country);*/
//js.executeScript("document.getElementById('Email').value='SoftwareTestingMaterial.com';");

//Sendkeys
/*driver.findElement(By.xpath("(//div[@class=' css-yk16xz-control'])[1]")).sendKeys(Country);
driver.findElement(By.xpath("(//div[@class=' css-yk16xz-control'])[2]")).sendKeys(City);*/

driver.findElement(By.name("zipcode")).sendKeys(Postalcode);
driver.findElement(By.xpath("//*[text()='ADVANCED INFORMATION']")).click();
driver.findElement(By.name("website")).sendKeys(Website);
driver.findElement(By.name("phone_no")).sendKeys(Telephone);
driver.findElement(By.name("telephone_no")).sendKeys(Mobile);
driver.findElement(By.name("fax")).sendKeys(Fax);
driver.findElement(By.name("email")).sendKeys(Email);
driver.findElement(By.name("skype_id")).sendKeys(Skypeid);
driver.findElement(By.xpath("//span[text()='Save']")).click();

}

@AfterTest
public void endsession() {
	driver.quit();
}
}



