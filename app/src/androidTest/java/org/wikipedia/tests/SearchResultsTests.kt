package org.wikipedia.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.wikipedia.main.MainActivity
import org.wikipedia.pages.ExplorePage
import org.wikipedia.pages.OnboardingPage
import org.wikipedia.pages.SearchBarPage
import org.wikipedia.pages.SearchResultPage

class SearchResultsTests {

    private val explorePage = ExplorePage()
    private val searchBarPage = SearchBarPage()
    private val onboardingPage = OnboardingPage()
    private val searchResultPage = SearchResultPage()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun beforeTests() {
        onboardingPage.tapOnSkipButton()
    }

    @Test
    fun displayResultsForSearchUsingTypedQuery() {
        val text = "Donald Trump"
        explorePage.tapOnSearchBar()
        searchBarPage.typeTextSearch(text)
        searchResultPage.waitUntilResultsAreLoaded()

        assertNotEquals("List of result for query `$text` is empty.",
                0, searchResultPage.countResults())
    }
}