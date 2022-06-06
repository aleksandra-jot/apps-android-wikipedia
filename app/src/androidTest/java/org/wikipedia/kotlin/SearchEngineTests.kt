package org.wikipedia.kotlin

import BaseRobot
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
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


@RunWith(AndroidJUnit4::class)
class SearchEngineTests {

    @Before
    fun beforeTests() {
        BaseRobot().doOnView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed()), click())
    }

    @Rule
    @JvmField // nie wiedzialam co to, skopiowalam z testow z Wiki, bez tego Kotlin sie nie zalacza?
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun searchBarTest() {
        BaseRobot().doOnView((withId(R.id.search_container)), click());
        BaseRobot().doOnView((withId(R.id.search_src_text)), typeText("Donald Trump"));
        BaseRobot().assertOnView((allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed())), matches(withText("Donald Trump")))
    }

    @Test
    fun searchPageTest() {
        BaseRobot().doOnView((withContentDescription("Search")), click());
        BaseRobot().doOnView((withId(R.id.search_card)), click());
        BaseRobot().doOnView((withId(R.id.search_src_text)), typeText("Donald Trump"));
        BaseRobot().assertOnView((allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed())), matches(withText("Donald Trump")))
    }

    @Test
    fun searchNoResultsTest() {
        val text = "asdasdasd"
        BaseRobot().doOnView((withId(R.id.search_container)), click());
        BaseRobot().doOnView((withId(R.id.search_src_text)), typeText("sadfasdf1"));
        //BaseRobot().assertOnView((allOf(withId(R.id.results_text), withText("No results"), isDisplayed())), matches(withText("No results")))
        onView(withId(R.id.results_text))
                .check(matches(Utils.atPosition(0, withText("No results"))))

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

