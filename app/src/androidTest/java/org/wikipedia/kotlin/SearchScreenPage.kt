package org.wikipedia.kotlin

import BaseRobot
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.*
import org.wikipedia.R
import androidx.test.espresso.matcher.ViewMatchers.withId

class SearchScreenPage {
    val searchNavButton = withContentDescription("Search")
    val searchBarSearchPage = withId(R.id.search_card)
    val searchTextInput = withId(R.id.search_src_text)
    val pageList = withId(R.id.page_list_item_title)



    fun clickOnSearchNavButton() {
        BaseRobot().doOnView((searchNavButton), click());
    }

    fun clickOnSearchBarSearchPage() {
        BaseRobot().doOnView((searchBarSearchPage), click())
    }

    fun typeTextSearchPage(text: String) {
        BaseRobot().doOnView((searchTextInput), typeText(text))
    }

    fun assertSearchItemTitle(text: String) {
        BaseRobot().assertOnView((allOf(pageList, withText(text), isDisplayed())), matches(withText(text)))
    }

}