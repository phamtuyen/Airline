package mobivi.com.vn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class hello {
	final static Logger logger = Logger.getLogger(hello.class);
	private static WebDriver driver = new HtmlUnitDriver(true);
	
	
//	private static  WebDriver driver = new FirefoxDriver();
	public static void main(String[] args) throws IOException {		
		System.out.println("AAA");
		String userAgent = "mozilla/5.0 (X11; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0";
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("general.useragent.override", userAgent);
		DesiredCapabilities cap = DesiredCapabilities.firefox();	
		cap.setCapability(FirefoxDriver.PROFILE, profile);
		cap.setVersion("24.0");
		driver = new HtmlUnitDriver(cap);	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		driver.get("http://www.agoda.com/vi-vn/city/ninh-binh-vn.html");
		System.out.println("AAA");
		beginHtmlUnit();
//		example(); // GUI
	}
	
	private static void beginHtmlUnit() throws IOException{
		driver.findElement(By.name("ctl00$ctl00$MainContent$area_promo$CitySearchBox1$SearchButton")).click();
		System.out.println("BBB");
		driver.findElement(By.linkText("Emeralda Resort Ninh Binh")).click();
		System.out.println("CCC");
		for (String handle : driver.getWindowHandles()) {		
			driver = (HtmlUnitDriver) driver.switchTo().window(handle);
			if ( driver.getCurrentUrl().contains("emeralda-resort")) 
				break;
			else 
				continue;
		}	
		
		System.out.println("CHOOSE ROOM");
		
		WebElement select = driver.findElement(By.id("room0"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if("1".equals(option.getText()))
				option.click();
		}	
		
		driver.findElement(By.id("button_room0")).click();
		System.out.println("DDD");

		// BEGIN CARD	
		System.out.println("BEGIN CARD");
		
		WebElement firtName = driver.findElement(By.id("txtCntFirstName"));				
		firtName.sendKeys("Tuyen");			
		new Actions(driver).moveToElement(firtName).perform();
		

		WebElement lastName = driver.findElement(By.id("txtCntLastName"));
		lastName.sendKeys("Pham Van");		
		new Actions(driver).moveToElement(lastName).perform();


//		WebElement email = driver.findElement(By.id("txtEmail"));
//		email.sendKeys("vantuyen.pham@mobivi.com");		
//		new Actions(driver).moveToElement(email).perform();
		
		WebElement email = driver.findElement(By.id("txtEmail"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		
		executor.executeScript("document.getElementById('txtEmail').value = 'vantuyen.pham@mobivi.com';", email);
//		email.sendKeys(Keys.TAB); 
		new Actions(driver).moveToElement(email).perform();

//		WebElement comfirmEmail = driver.findElement(By.id("txtConfirmEmail"));
//		comfirmEmail.sendKeys("vantuyen.pham@mobivi.com");		
//		new Actions(driver).moveToElement(comfirmEmail).perform();
			
		WebElement element = driver.findElement(By.id("txtConfirmEmail"));		
		executor.executeScript("document.getElementById('txtConfirmEmail').value = 'vantuyen.pham@mobivi.com';", element);
//		element.sendKeys(Keys.TAB); 
		new Actions(driver).moveToElement(element).perform();

		WebElement phoneNo = driver.findElement(By.id("txtPhoneNo"));
		phoneNo.sendKeys("0965292512");
		new Actions(driver).moveToElement(phoneNo).perform();		
		
		WebElement selectCountry = driver.findElement(By.id("ddlCntctCountry"));
//		List<WebElement> optionsCountry = selectCountry.findElements(By.tagName("option"));
//		for (WebElement option : optionsCountry) {
//			if("Singapore".equals(option.getText()))
//				option.click();
//		}	
		
		executor.executeScript("document.getElementById('ddlCntctCountry').value = '114';", selectCountry);
		new Actions(driver).moveToElement(selectCountry).perform();
		
		
		
		File file = new File("/home/phamtuyen/parse.txt");
		if (!file.exists()) {
			file.createNewFile();
		}	
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(driver.getPageSource());
		bw.close();	

		driver.findElement(By.id("btnContinue")).click();					
		
		
		System.out.println("SECOND STEP");	
		// step-success
		String istrue = driver.findElement(By.id("secondstep")).getAttribute("class");	
		System.out.println(istrue);  
		
		// 
		paymentHtmlUnit(); 	
	}

	private static void paymentHtmlUnit() {
		WebElement femaleSex = driver.findElement(By.id("payOpt2"));
		femaleSex.isSelected();   

		WebElement select = driver.findElement(By.id("ddlPaymentMethod"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if("MasterCard".equals(option.getText()))
				option.click();
		}

		WebElement txtCard_0 = driver.findElement(By.id("txtCard_0"));
		txtCard_0.clear();
		txtCard_0.sendKeys("1111");

		WebElement txtCard_1 = driver.findElement(By.id("txtCard_1"));
		txtCard_1.clear();
		txtCard_1.sendKeys("2222");

		WebElement txtCard_2 = driver.findElement(By.id("txtCard_1"));
		txtCard_2.clear();
		txtCard_2.sendKeys("3333");

		WebElement txtCard_3 = driver.findElement(By.id("txtCard_3"));
		txtCard_3.clear();
		txtCard_3.sendKeys("4444");

		// mm
		select = driver.findElement(By.id("ddlExpiryMonth"));
		options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if("01".equals(option.getText()))
				option.click();
		}
		// yyyy
		select = driver.findElement(By.id("ddlExpiryYear"));
		options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if("2020".equals(option.getText()))
				option.click();
		}

		//
		WebElement txtCvc = driver.findElement(By.id("txtCvc"));
		txtCvc.clear();
		txtCvc.sendKeys("168");

		// btnSubmit
		driver.findElement(By.id("btnSubmit")).click();	

	}
	
	private static void example() throws IOException{
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.findElement(By.name("ctl00$ctl00$MainContent$area_promo$CitySearchBox1$SearchButton")).click();
		driver.findElement(By.linkText("Emeralda Resort Ninh Binh")).click();
		for (String handle : driver.getWindowHandles()) {		
			driver.switchTo().window(handle);
		}	
		Select dropdownDestAP = new Select(driver.findElement(By.id("room0")));			
		dropdownDestAP.selectByIndex(1);
		driver.findElement(By.id("button_room0")).click();

		// TEST TEST TEST	
		WebElement firtName = driver.findElement(By.id("txtCntFirstName"));				
		firtName.sendKeys("Cuong");		
		new Actions(driver).moveToElement(firtName).perform();
		

		WebElement lastName = driver.findElement(By.id("txtCntLastName"));
		lastName.sendKeys("Truong Viet");
		new Actions(driver).moveToElement(lastName).perform();


		WebElement email = driver.findElement(By.id("txtEmail"));
		email.sendKeys("cuong.truong@mobivi.vn");
		new Actions(driver).moveToElement(email).perform();


		WebElement comfirmEmail = driver.findElement(By.id("txtConfirmEmail"));
		comfirmEmail.sendKeys("cuong.truong@mobivi.vn");
		new Actions(driver).moveToElement(comfirmEmail).perform();


		WebElement phoneNo = driver.findElement(By.id("txtPhoneNo"));
		phoneNo.sendKeys("0965292512");
		new Actions(driver).moveToElement(phoneNo).perform();


		Select country = new Select(driver.findElement(By.id("ddlCntctCountry")));			
		country.selectByValue("38");

		driver.findElement(By.id("btnContinue")).click();
		
		String istrue = driver.findElement(By.id("secondstep")).getAttribute("class");	
		System.out.println(istrue); 

//		payment();

	}

}





















