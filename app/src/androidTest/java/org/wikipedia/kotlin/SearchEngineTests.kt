package org.wikipedia.kotlin

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.wikipedia.TestUtil
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
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
    fun searchEngineTest() {

        onView(withId(R.id.search_container)).perform(click())
        TestUtil.delay(3) // brzydkie, nie podoba mi sie, ale Wiki sama tego uzywa
        onView(withId(R.id.search_src_text)).perform(typeText("Donald Trump"))
        TestUtil.delay(3) // same here
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
}
