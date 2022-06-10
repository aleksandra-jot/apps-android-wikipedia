package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf
import org.wikipedia.R
import org.wikipedia.Utils


class SearchBarPage {

    private val searchTextInput = withId(R.id.search_src_text)
    private val pageList = withId(R.id.page_list_item_title)
    private val recentSearchesDeleteButton = withId(R.id.recent_searches_delete_button)
    private val yesButton = withText(R.string.dialog_message_clear_history_yes)
    private val emptyImage = withId(R.id.search_empty_image)


    fun typeTextSearch(text: String) {
        onView(searchTextInput).perform(typeText(text))
    }

    fun isDisplayed(text: String): Boolean {
        return BasePage().viewExists(hasDescendant(withText(text)))
    }

    fun isDisplayedNoResults():Boolean {
        return BasePage().waitForView(hasDescendant(withText("No results")))
    }

    fun isDisplayedEmptyPage():Boolean {
        return BasePage().waitForView(emptyImage)
    }

    fun tapOnSearchItemTitle(text: String) {
        BasePage().waitForView(pageList)
        onView(allOf(pageList, withText(text), isDisplayed())).perform(click())
    }

    fun tapOnDeleteRecentSearchesButton() {
        onView(recentSearchesDeleteButton).perform(click())
    }

    fun tapOnYesButton() {
        onView(yesButton).perform(click())
    }

    fun getTextOfElement() {
        Utils.getTextOf(onView(yesButton))
    }
}