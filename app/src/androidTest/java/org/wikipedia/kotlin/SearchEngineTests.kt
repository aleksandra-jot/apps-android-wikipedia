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

    @Before
    fun beforeTests() {
        BaseRobot().doOnView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed()), click())
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
        searchScreenPage.clickOnSearchNavButton()
        searchScreenPage.clickOnSearchBarSearchPage()
        searchScreenPage.typeTextSearchPage(text)
        searchScreenPage.assertSearchItemTitle(text)
    }

    @Test
    fun searchNoResultsTest() {
        val text = "asdasdad"
        exploreScreenPage.clickOnSearchBarExplorePage();
        exploreScreenPage.typeTextExplorePage(text)
        print("gdzietojest")
        print(exploreScreenPage.getText(exploreScreenPage.getTextId))
        //assertEquals("No results",  exploreScreenPage.getText(exploreScreenPage.getTextId) )
        exploreScreenPage.assertSearchNoResult()
      }

    @Test
    fun addRecentSearchTest() {
        BaseRobot().doOnView((withId(R.id.search_container)), click())
        BaseRobot().doOnView((withId(R.id.search_src_text)), typeText("Donald Trump"))
        BaseRobot().doOnView((allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed())), click())
        BaseRobot().doOnView((withId(R.id.page_toolbar_button_search)), click())
        BaseRobot().assertOnView((allOf(withText("Donald Trump"), isDisplayed())), matches(withText("Donald Trump")))
    }

    @Test
    fun deleteRecentSearchesTest() {
        BaseRobot().doOnView((withId(R.id.search_container)), click())
        BaseRobot().doOnView((withId(R.id.search_src_text)), typeText("Donald Trump"))
        BaseRobot().doOnView((allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed())), click())
        BaseRobot().doOnView((withId(R.id.page_toolbar_button_search)), click())
        BaseRobot().doOnView((withId(R.id.recent_searches_delete_button)), click())
        BaseRobot().doOnView((withText("Yes")), click())
        BaseRobot().assertOnView(withId(R.id.search_empty_image), matches(isDisplayed()))
    }

}

