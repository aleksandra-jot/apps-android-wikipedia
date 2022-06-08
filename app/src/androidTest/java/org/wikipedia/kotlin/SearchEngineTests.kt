package org.wikipedia.kotlin

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.main.MainActivity
import org.wikipedia.R
import org.wikipedia.TestUtil
import org.wikipedia.kotlin.Utils.Companion.atPosition

@RunWith(AndroidJUnit4::class)
class SearchEngineTests {
    val exploreScreenPage = ExploreScreenPage()
    val searchScreenPage = SearchScreenPage()
    val navBarScreenPage = NavBarPage()
    val onboardingPage = OnboardingPage()

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
        exploreScreenPage.clickOnSearchBarExplorePage()
        exploreScreenPage.typeTextExplorePage(text)
        exploreScreenPage.assertSearchResultsList(text)
    }

    @Test
    fun searchPageTest() {
        val text ="Donald Trump"
        navBarScreenPage.clickOnSearchNavButton()
        searchScreenPage.clickOnSearchBarSearchPage()
        searchScreenPage.typeTextSearchPage(text)
        searchScreenPage.assertSearchItemTitle(text)
    }

    @Test
    fun searchNoResultsTest() {
        val text = "asdasdad"
        exploreScreenPage.clickOnSearchBarExplorePage();
        exploreScreenPage.typeTextExplorePage(text)
        exploreScreenPage.assertSearchNoResult()
    }

    @Test
    fun addRecentSearchTest() {
        val text = "Donald Trump"
        exploreScreenPage.clickOnSearchBarExplorePage();
        exploreScreenPage.typeTextExplorePage(text)
        exploreScreenPage.clickOnSearchItemTitle(text)
        exploreScreenPage.clickOnSearchToolbarButton()
        exploreScreenPage.assertRecentSearchItemTitle(text)
    }

    @Test
    fun deleteRecentSearchesTest() {
        val text = "Donald Trump"
        exploreScreenPage.clickOnSearchBarExplorePage();
        exploreScreenPage.typeTextExplorePage(text)
        exploreScreenPage.clickOnSearchItemTitle(text)
        exploreScreenPage.clickOnSearchToolbarButton()
        exploreScreenPage.clickOnDeleteRecentSearchesButton()
        exploreScreenPage.clickOnYesButton()
        exploreScreenPage.assertDeletedResults()
     }
}
