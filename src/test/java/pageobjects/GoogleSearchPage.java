package pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.BasePage;
import constants.FilePaths;
import utilities.PropertyFileReader;

public class GoogleSearchPage extends BasePage{
	
	private final By searchBox = By.name("q");
	private final By allSearchRecords = By.cssSelector("div#rso>div.MjjYud, div.hlcw0c");
	private final By urls = By.cssSelector("cite");
	private final By headlines = By.cssSelector("h3");
	private final By shortDescription = By.cssSelector("div.IsZvec span,div[data-content-feature='1']>div");
	private final By nextPage = By.linkText("Next");
	private Map<String,Map<String,String>>googleSearchResults = new HashMap<>();

	/* purpose:
	 * this function enters search text in google search field and searches for the results
	 */
	public void searchText(String text){
		sendKeys(this.searchBox,text);
	    pressEnterKey(getWebElement(this.searchBox));
	    waitForPageToLoad();
	}
	
	
	/* purpose:
	 * this function searches for a text in google search engine and stores url, headline and short Description from search result in a map
	 *@return:
	 *getSearchResult: Map containing url, headline and short description 
	 *e.g: {"url":{"headline":headline,"shortDescription":shortdescription}}
	 */	
	public Map<String, Map<String, String>> getSearchResult(){
		int count = 0;
		int noOfSearchItemsToVerify = Integer.parseInt(PropertyFileReader.getProperty(FilePaths.searchEngineTestsFile,"numberOfSearchItemsToCheck"));
		while(count<noOfSearchItemsToVerify){
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<WebElement> records = getWebElements(this.allSearchRecords);
			for(WebElement ele:records){
				if(count>(noOfSearchItemsToVerify-1)){
					break;
				}
				String url= getText(ele.findElement(this.urls)).replace(" › ","/");
				String headline = getText(ele.findElement(this.headlines));
				String shortDescription =getText(ele.findElement(this.shortDescription));
				Map<String,String> headlineShortDescription = new HashMap<>();
				headlineShortDescription.put("headline", headline);
				headlineShortDescription.put("shortDescription", shortDescription);
				googleSearchResults.put(url, headlineShortDescription);
				count++;
			}
			click(this.nextPage);
			waitForPageToLoad();
		}
		return googleSearchResults;
	}

}
