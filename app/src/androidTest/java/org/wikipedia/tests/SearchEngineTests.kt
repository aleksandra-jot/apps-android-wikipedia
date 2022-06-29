package org.wikipedia.tests

import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
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
    val settingsPage = SettingsPage()

    @Before
    fun beforeTests() {
        Intents.init()
        onboardingPage.tapOnSkipButton()

    }

    @After
    fun afterTests() {
        Intents.release()
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun searchFromExplorePage() {
        var text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        assertTrue("No view matched '$text'", searchBarPage.resultElementIsDisplayedOnTheList())
       }

    @Test
    fun searchFromSearchPage() {
        val text ="Donald Trump"
        navBarPage.tapOnSearchNavButton()
        searchPage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        assertTrue("No view matched '$text'", searchBarPage.resultElementIsDisplayedOnTheList())
    }

    @Test
    fun searchNoResults() {
        val textTyped = "asasdasdad"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(textTyped)
        assertEquals("No results", searchBarPage.getTextOfNoResultsList()  )
    }

    @Test
    fun addRecentSearch() {
        val text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        searchBarPage.tapOnSearchItemTitle(text)
        articlePage.tapOnSearchBar()
        assertTrue("No view matched '$text'", searchBarPage.resultElementIsDisplayedOnTheList())
    }

    @Test
    fun deleteRecentSearches() {
        val text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        searchBarPage.tapOnSearchItemTitle(text)
        articlePage.tapOnSearchBar()
        searchBarPage.tapOnDeleteRecentSearchesButton()
        searchBarPage.tapOnYesButton()
        assertTrue("No view matched '$text'", searchBarPage.isDisplayedEmptyPage())
     }

    @Test
    fun checkSettingsPage() {
        navBarPage.tapOnMoreNavButton()
        navBarPage.tapOnSettingsButton()
        //assertTrue("No view matched Settings page", settingsPage.isDisplayedSettingsTitle())
    }
}
