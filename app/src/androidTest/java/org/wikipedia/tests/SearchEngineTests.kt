package org.wikipedia.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity
import org.wikipedia.pages.*

@RunWith(AndroidJUnit4::class)
class SearchEngineTests {
    val explorePage = ExplorePage()
    val searchBarPage = SearchBarPage()
    val searchPage = SearchPage()
    val navBarPage = NavBarPage()
    val onboardingPage = OnboardingPage()
    val articlePage = ArticlePage()

    @Before
    fun beforeTests() {
        onboardingPage.clickOnSkipButton()
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun searchBarTest() {
        val text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        assertTrue("No view matched '$text'", searchBarPage.isDisplayed(text))
       }

    @Test
    fun searchPageTest() {
        val text ="Donald Trump"
        navBarPage.clickOnSearchNavButton()
        searchPage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        assertTrue("No view matched '$text'", searchBarPage.isDisplayed(text))
    }

    @Test
    fun searchNoResultsTest() {
        val text = "asdasdad"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        assertTrue("No view matched 'No results'", searchBarPage.isDisplayedNoResults())
    }

    @Test
    fun addRecentSearchTest() {
        val text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        searchBarPage.tapOnSearchItemTitle(text)
        articlePage.tapOnSearchBar()
        assertTrue("No view matched '$text'", searchBarPage.isDisplayed(text))
    }

    @Test
    fun deleteRecentSearchesTest() {
        val text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        searchBarPage.tapOnSearchItemTitle(text)
        articlePage.tapOnSearchBar()
        searchBarPage.tapOnDeleteRecentSearchesButton()
        searchBarPage.tapOnYesButton()
        //assertEquals(searchBarPage.getTextOfElement(), "Yes")
        assertTrue("No view matched '$text'", searchBarPage.isDisplayedEmptyPage())
     }
}
