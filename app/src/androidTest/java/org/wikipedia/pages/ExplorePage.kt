package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import org.wikipedia.R
import androidx.test.espresso.matcher.ViewMatchers.withId


class ExplorePage {

    private val searchBar = withId(R.id.search_container)

    fun tapOnSearchBar() {
       onView(searchBar).perform(click())
   }

}

