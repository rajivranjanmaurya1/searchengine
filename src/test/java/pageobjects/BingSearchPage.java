package pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.BasePage;
import constants.FilePaths;
import utilities.PropertyFileReader;

public class BingSearchPage extends BasePage{
	private final By searchBox = By.name("q");
	private final By allSearchRecords = By.cssSelector("div#b_content > main ol li.b_algo");
	private final By urls = By.cssSelector("cite");
	private final By headlines = By.cssSelector("h2 a");
	private final By shortDescription = By.cssSelector("p");
	private final By nextPage = By.cssSelector("a[title='Next page']");
	private Map<String,Map<String,String>>bingSearchResults = new HashMap<>();

	/* purpose:
	 * this function enters search text in bing search field and searches for the results
	 */
	public void searchText(String text){
		sendKeys(this.searchBox,text);
	    pressEnterKey(getWebElement(this.searchBox));
	    waitForPageToLoad();
	}
	
	
	/* purpose:
	 * this function searches for a text in bing search engine and stores url, headline and short Description from search result in a map
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
				String url= getText(ele.findElement(this.urls));
				String headline = getText(ele.findElement(this.headlines));
				String shortDescription =getText(ele.findElement(this.shortDescription));
				Map<String,String> headlineShortDescription = new HashMap<>();
				headlineShortDescription.put("headline", headline);
				headlineShortDescription.put("shortDescription", shortDescription);
				bingSearchResults.put(url, headlineShortDescription);
				count++;
			}
			click(this.nextPage);
			waitForPageToLoad();
		}
		return bingSearchResults;
	}
	
	
}
