package org.wikipedia.kotlin

import BaseRobot
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.*
import org.wikipedia.R
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.TestUtil


class ExploreScreenPage {
    val searchBarExplorePage = withId(R.id.search_container)
    val searchTextInput = withId(R.id.search_src_text)
    val searchResultsList = withId(R.id.search_results_list)
    val pageList = withId(R.id.page_list_item_title)
    val recentSearchesDeleteButton = withId(R.id.recent_searches_delete_button)
    val yesButton = withText("Yes")
    val emptyImage = withId(R.id.search_empty_image)
    val recentSearchesList = withId(R.id.recent_searches)
    val pageToolbarButton = withId(R.id.page_toolbar_button_search)

    fun clickOnSearchBarExplorePage() {
     BaseRobot().doOnView((searchBarExplorePage), click());
 }

    fun typeTextExplorePage(text: String) {
        BaseRobot().doOnView((searchTextInput), typeText(text))
    }

    fun assertRecentSearchItemTitle(text: String) {
        TestUtil.delay(1)
        BaseRobot().assertOnView(recentSearchesList, matches(hasDescendant(withText(text))))
    }

    fun clickOnSearchItemTitle(text: String) {
        BaseRobot().doOnView((allOf(pageList, withText(text), isDisplayed())), click())
    }

    fun clickOnDeleteRecentSearchesButton() {
        BaseRobot().doOnView(recentSearchesDeleteButton, click())
    }

    fun clickOnYesButton() {
        BaseRobot().doOnView(yesButton, click())
    }

    fun assertDeletedResults() {
        BaseRobot().assertOnView(emptyImage, matches(isDisplayed()))
    }

    fun clickOnSearchToolbarButton() {
        BaseRobot().doOnView(pageToolbarButton, click())
    }

    fun assertSearchNoResult() {
        TestUtil.delay(1)
        BaseRobot().assertOnView(searchResultsList, matches(Utils.atPosition(0, hasDescendant(withText("No results")))))}

    fun assertSearchResultsList(text: String) {
        TestUtil.delay(1)
        BaseRobot().assertOnView(searchResultsList, matches(Utils.atPosition(0, hasDescendant(withText(text)))))
    }
}

