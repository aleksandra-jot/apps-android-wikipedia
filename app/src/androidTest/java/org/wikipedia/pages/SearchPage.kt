package org.wikipedia.pages

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.wikipedia.R


class SearchPage {

    private val searchBar = withId(R.id.search_card)
    private val pageList = withId(R.id.page_list_item_title)

    fun tapOnSearchBar() {
        Espresso.onView(searchBar).perform(click())
    }
}