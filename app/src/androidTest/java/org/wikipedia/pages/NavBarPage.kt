package org.wikipedia.pages

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

class NavBarPage {
    val searchNavButton = ViewMatchers.withContentDescription("Search")

    fun clickOnSearchNavButton() {
        BaseRobot().doOnView((searchNavButton), ViewActions.click());
    }
}