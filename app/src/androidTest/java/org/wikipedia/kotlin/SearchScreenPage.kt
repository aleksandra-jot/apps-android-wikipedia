package org.wikipedia.kotlin

import BaseRobot
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.wikipedia.R
import org.wikipedia.TestUtil


class SearchScreenPage {

    val searchBarSearchPage = withId(R.id.search_card)
    val searchTextInput = withId(R.id.search_src_text)
    val pageList = withId(R.id.page_list_item_title)
    val searchResultsList = withId(R.id.search_results_list)

    fun clickOnSearchBarSearchPage() {
        BaseRobot().doOnView((searchBarSearchPage), click())
    }

    fun typeTextSearchPage(text: String) {
        BaseRobot().doOnView((searchTextInput), typeText(text))
    }

    fun assertSearchItemTitle(text: String) {
        TestUtil.delay(1)
        BaseRobot().assertOnView(searchResultsList, matches(hasDescendant(withText(text))))
    }

}