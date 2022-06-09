package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.R


class ArticlePage {

    private val searchBar = withId(R.id.page_toolbar_button_search)

    fun tapOnSearchBar() {
        onView(searchBar).perform(ViewActions.click())
    }
}