package helpers;

import java.util.HashMap;
import java.util.Map;
import constants.FilePaths;
import driverfactory.DriverFactory;
import pageobjects.BingSearchPage;
import pageobjects.GoogleSearchPage;
import utilities.PropertyFileReader;

public class SearchEngineHelper {
	private Map<String,Map<String,String>>googleSearchResults = new HashMap<>();
	private Map<String,Map<String,String>>bingSearchResults = new HashMap<>();
	
	/* purpose:
	 * this function searches for a text in google search engine and stores url, headline and short Description in a map
	 * @parameters:
	 * searchText : text to search
	 */
	public void searchTextInGoogle(String searchText){
		try{
			System.out.println("----------Test Step: Launch broswer----------");
			DriverFactory.driverSetup();
			GoogleSearchPage googleSearchPage = new GoogleSearchPage();
			System.out.println("----------Test Step: Navigate to google search page----------");
			googleSearchPage.navigateToUrl(PropertyFileReader.getProperty(FilePaths.searchEngineTestsFile,"googleSearchUrl"));
			System.out.println("----------Test Step: Search for search text "+ searchText + " ----------");
			googleSearchPage.searchText(searchText);
			System.out.println("----------Test Step: Parse first " + PropertyFileReader.getProperty(FilePaths.searchEngineTestsFile,"numberOfSearchItemsToCheck") + "search result items (url, headline and short description)----------");
			googleSearchResults = googleSearchPage.getSearchResult();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DriverFactory.closeBrowser();
		}
		
	}

	/* purpose:
	 * this function searches for a text in bing search engine and stores url, headline and short Description in a map
	 * @parameters:
	 * searchText : text to search
	 */
	public void searchTextInBing(String searchText){
		try{
			System.out.println("");
			System.out.println("----------Test Step: Launch broswer----------");
			DriverFactory.driverSetup();
			BingSearchPage bingSearchPage = new BingSearchPage();
			System.out.println("----------Test Step: Navigate to bing search page----------");
			bingSearchPage.navigateToUrl(PropertyFileReader.getProperty(FilePaths.searchEngineTestsFile,"bingSearchUrl"));
			System.out.println("----------Test Step: Search for search text "+ searchText + " ----------");
			bingSearchPage.searchText(searchText);	
			System.out.println("----------Test Step: Parse first " + PropertyFileReader.getProperty(FilePaths.searchEngineTestsFile,"numberOfSearchItemsToCheck") + "search result items (url, headline and short description)----------");
			bingSearchResults = bingSearchPage.getSearchResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DriverFactory.closeBrowser();
		}
		
	}
	
	
	/* purpose:
	 * this function checks if search text is present in url or headline or short description of the search result
	 * @parameters:
	 * searchResults : it's a map having url, headline and short description of each search result item
	 * searhText: String which user searched
	 */
	public void checkIfItemsContainSearchText(Map<String,Map<String,String>>searchResults, String searchText){
		for(String url:searchResults.keySet()){
			if(url.contains(searchText)||url.contains(searchText.toLowerCase())||url.contains(searchText.toUpperCase())){
				System.out.println("search item with url: " + url + " contains search text " + searchText);
			}else{
				System.out.println("search item with url: " + url + " does not contain search text " + searchText);
			}
			if(searchResults.get(url).get("headline").contains(searchText)||searchResults.get(url).get("headline").contains(searchText.toLowerCase())||searchResults.get(url).get("headline").contains(searchText.toUpperCase())){
				System.out.println("headline of search item with url: " + url + " contains search text " + searchText);
			}else{
				System.out.println("headline of search item with url: " + url + " does not contain search text " + searchText);
			}
			if(searchResults.get(url).get("shortDescription").contains(searchText)||searchResults.get(url).get("shortDescription").contains(searchText.toLowerCase())||searchResults.get(url).get("shortDescription").contains(searchText.toUpperCase())){
				System.out.println("Short Description of search item with url: " + url + " contains search text " + searchText);
			}else{
				System.out.println("Short Description of search item with url: " + url + " does not contain search text " + searchText);
			}
		}
	}
	
	
	/* purpose:
	 * this function returns google search result (url, headline and short description) set by searchTextInGoogle(String searchText) as map
	 * @return:
	 * Map: it returns a map 
	 */
	public Map<String,Map<String,String>> getGoogleSearchResult(){
		return this.googleSearchResults;
	}
	
	
	/* purpose:
	 * this function returns bing search result (url, headline and short description) set by searchTextInBing(String searchText) as map
	 * @return:
	 * Map: it returns a map 
	 */
	public Map<String,Map<String,String>> getBingSearchResult(){
		return this.bingSearchResults;
	}
	
	/* purpose:
	 * this function checks for the key existing in two maps
	 * @parameters:
	 * searchEngineOneResult: map
	 * searchEngineeTwoResults:map
	 */
	public void getMostPopularSearchResult(Map<String,Map<String,String>> searchEngineOneResult, Map<String,Map<String,String>> searchEngineeTwoResults){
			for(String url:searchEngineOneResult.keySet()){
			if(searchEngineeTwoResults.containsKey(url)){
				System.out.println("url: " + url + " with headline: " + searchEngineeTwoResults.get(url).get("headline"));
			}
		}
	}
	


}
