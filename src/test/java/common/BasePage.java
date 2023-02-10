package common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.Constants;
import driverfactory.DriverFactory;

public class BasePage {
	public  WebDriver driver = DriverFactory.driver.get();
	protected WebDriverWait wait;

	public BasePage(){
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(Constants.MAX_EXPLICIT_TIMEOUT));
	}


	/* purpose:
	 * this function returns WebElement for the By locator
	 * @parameters:
	 * By By locator for WebElement
	 * @return:
	 * It returns WebElement
	 */
	public WebElement getWebElement(By by){
		wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
		return driver.findElement(by);
	}



	/* purpose:
	 * this function returns list of WebElements for the By locator
	 * @parameters:
	 * By By locator for WebElement
	 * @return:
	 * It returns list of WebElement
	 */
	public List<WebElement> getWebElements(By by){
		wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
		return driver.findElements(by);
	}


	/* purpose:
	 * this function gets the WebElement for the By locator and clicks it
	 * @parameters:
	 * By locator for WebElement
	 */
	public void click(By by){
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", getWebElement(by));
	}



	/* purpose:
	 * this function clicks the WebElement
	 * @parameters:
	 * WebElement
	 */
	public void click(WebElement webelement){
		wait.until(ExpectedConditions.elementToBeClickable(webelement));
		webelement.click();
	}


	/* purpose:
	 * this function types text in input field
	 * @parameters:
	 * By: locator for WebElement
	 * String : Types this in text field
	 */
	public void sendKeys(By by, String text){
		if(wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed()){
			driver.findElement(by).sendKeys(text);
		}
	}


	/* purpose:
	 * hit enter key
	 * @parameters:
	 * WebElement
	 */	
	public void pressEnterKey(WebElement webElement){
		webElement.sendKeys(Keys.ENTER);
	}


	/* purpose:
	 * Navigates to an url
	 * @parameters:
	 * String : url
	 */
	public void navigateToUrl(String url){
		driver.get(url);
	}


	/* purpose:
	 * this function waits for page to load for time specified in Constants file
	 */
	public void waitForPageToLoad(){
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.MAX_EXPLICIT_TIMEOUT));
	}


	/* purpose:
	 * this function returns text associated with a WebElement
	 * @parameters:
	 * By By locator for WebElement
	 * @return:
	 * String: It returns text associated with a WebElement
	 */
	public String getText(WebElement webElement){
		wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
		return webElement.getText();
	}
}
