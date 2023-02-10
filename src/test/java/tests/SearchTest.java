package tests;

import constants.FilePaths;
import helpers.SearchEngineHelper;
import utilities.PropertyFileReader;
public class SearchTest {
	public static SearchEngineHelper SearchEngineHelper = new SearchEngineHelper();

	public static void main(String[] args) {
		searchScenario();
	}
	
	/*
	 * This is the test case for searching a text in google and bing and checking the common items from both searches
	 */
	public static void searchScenario(){
		String searchText = PropertyFileReader.getProperty(FilePaths.searchEngineTestsFile,"searchText");
		SearchEngineHelper.searchTextInGoogle(searchText);
		System.out.println("");
		System.out.println("----------Test Step: Check if google serached items  contain search text in url, headline or short description----------");
		SearchEngineHelper.checkIfItemsContainSearchText(SearchEngineHelper.getGoogleSearchResult(), searchText);
		SearchEngineHelper.searchTextInBing(searchText);
		System.out.println("");
		System.out.println("----------Test Step: Check if bing serached items  contain search text in url, headline or short description----------");
		SearchEngineHelper.checkIfItemsContainSearchText(SearchEngineHelper.getBingSearchResult(), searchText);
		System.out.println("");
		System.out.println("----------Test Step: Check most popular items (Items which are found in both the search engines)----------");
		SearchEngineHelper.getMostPopularSearchResult(SearchEngineHelper.getGoogleSearchResult(), SearchEngineHelper.getBingSearchResult());
	}
	
	}
