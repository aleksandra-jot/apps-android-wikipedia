package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.wikipedia.R


class SearchPage {

    private val searchBar = withId(R.id.search_card)

    fun tapOnSearchBar() {
        onView(searchBar).perform(click())
    }
}