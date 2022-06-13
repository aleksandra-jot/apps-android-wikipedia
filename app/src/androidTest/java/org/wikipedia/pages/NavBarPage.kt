package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.wikipedia.Utils.Companion.getTextOf
import org.wikipedia.R

class NavBarPage {
    val searchNavButton = ViewMatchers.withContentDescription("Search")

    fun tapOnSearchNavButton() {
        onView(searchNavButton).perform(click());
    }

    fun getTextBtn()
    { getTextOf(onView(withId(R.id.navigation_bar_item_small_label_view)))
    }
}