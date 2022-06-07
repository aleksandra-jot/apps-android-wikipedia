package org.wikipedia.kotlin
import android.util.Log

import BaseRobot
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.R
import org.wikipedia.main.MainActivity
import androidx.test.espresso.matcher.ViewMatchers.withId
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue



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
        exploreScreenPage.assertSearchItemTitle(text)
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

