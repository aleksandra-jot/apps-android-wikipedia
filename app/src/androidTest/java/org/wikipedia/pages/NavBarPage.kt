package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

import org.wikipedia.R

class NavBarPage: BasePage() {
    val searchNavButton = ViewMatchers.withContentDescription("Search")
    val moreNavButton = ViewMatchers.withContentDescription("More")
    val donateButton = withId(R.id.main_drawer_donate_container)
    val settingsButton = withId(R.id.main_drawer_settings_container)

    fun tapOnSearchNavButton() {
        onView(searchNavButton).perform(click());
    }

    fun tapOnMoreNavButton() {
        onView(moreNavButton).perform(click());
    }

    fun getTextBtn() {
        getTextOf(onView(withId(R.id.navigation_bar_item_small_label_view)))
    }

    fun tapDonateButton() {
        onView(donateButton).perform(click());
    }

    fun tapOnSettingsButton() {
        onView(settingsButton).perform(click());
    }
}