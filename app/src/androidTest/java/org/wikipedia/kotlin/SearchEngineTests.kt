package org.wikipedia.kotlin

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.wikipedia.TestUtil
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.R
import org.wikipedia.main.MainActivity


@RunWith(AndroidJUnit4::class)
class SearchEngineTests {

    @Before
    // sama zrobilam ! mam nadzieje, ze bedzie dzialalo w nastepnych testach :D
    fun beforeTests() {
        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed()))
                .perform(click())
    }

    @Rule
    @JvmField // nie wiedzialam co to, skopiowalam z testow z Wiki, bez tego Kotlin sie nie zalacza?
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun searchBarTest() {

        onView(withId(R.id.search_container)).perform(click())
        TestUtil.delay(2) // brzydkie, nie podoba mi sie, ale Wiki sama tego uzywa
        onView(withId(R.id.search_src_text)).perform(typeText("Donald Trump"))
        TestUtil.delay(2) // same here
        onView(allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed()))
                .check(matches(withText("Donald Trump")))

        // to rozwiazanie  skopiowalam :(
        // nie podoba mi sie to rozwiazanie Wiki, bo robi asercje na displayed tego konkretnie elementu,
        // a potem sprawdza ten tekst, ktory teoretycznie sie sprawdza w tej pierwszej asercji ?_?
        // Kiedy probowalam sie dostac do elementu z listy zwracalo mi , ze jest ich wiele.
        // Probowalam z first (nie widzialo importu ?_?), atPosition, z marnym skutkiem
        // jak probowalam zrobic assercje na to, ze w jakims procencie sie pojawia  id Displayed  - lista, to mialam tez  blad
        // podobnie jak chcialam zrobic onData.
        // Yoda teach me plesa
    }

    @Test
    fun searchPageTest() {

        TestUtil.delay(2)
        onView(withContentDescription("Search")).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.search_card)).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.search_src_text)).perform(typeText("Donald Trump"))
        TestUtil.delay(2)
        onView(allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed()))
                .check(matches(withText("Donald Trump")))
    }

    @Test
    fun searchNoResultsTest() {

        onView(withId(R.id.search_container)).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.search_src_text)).perform(typeText("sadfasdf1"))
        TestUtil.delay(2)
        onView(allOf(withId(R.id.results_text), withText("No results"), isDisplayed()))
                .check(matches(withText("No results")))
    }

    @Test
    fun addRecentSearchTest() {

        onView(withId(R.id.search_container)).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.search_src_text)).perform(typeText("Donald Trump"))
        TestUtil.delay(2)
        onView(allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.page_toolbar_button_search)).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText("Donald Trump"), isDisplayed())).check(matches(withText("Donald Trump")))

        // jak to zrobic inaczej :(
        //onData(withId(R.id.recent_searches_recycler)).onChildView(withText("Donald Trump")).perform(click())
        //onView(withId(R.id.recent_searches_recycler)).check(matches(withText("Donald Trump")))
    }

    @Test
    fun deleteRecentSearchesTest() {

        onView(withId(R.id.search_container)).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.search_src_text)).perform(typeText("Donald Trump"))
        TestUtil.delay(2)
        onView(allOf(withId(R.id.page_list_item_title), withText("Donald Trump"), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.page_toolbar_button_search)).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.recent_searches_delete_button)).perform(click())
        TestUtil.delay(2)
        onView(withText("Yes")).perform(click())
        TestUtil.delay(2)
        onView(withId(R.id.page_list_item_title)).check(doesNotExist())
    }
}

