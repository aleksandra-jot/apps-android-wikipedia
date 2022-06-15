package org.wikipedia.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.wikipedia.R

class SearchBarPage : BasePage() {

    private var text: String = ""
    //private var searchQuery = ""
    private val searchTextInput = withId(R.id.search_src_text)
    private val pageList = withId(R.id.page_list_item_title)
    private val recentSearchesDeleteButton = withId(R.id.recent_searches_delete_button)
    private val yesButton = withText(R.string.dialog_message_clear_history_yes)
    private val emptyImage = withId(R.id.search_empty_image)
    private val resultText = hasDescendant(withText(text))
    private val searchResultsList = withId(R.id.search_results_list)
    private val noResultText = withId(R.id.results_text)

    fun typeTextSearch(text: String) {
        onView(searchTextInput).perform(typeText(text))
    }

   // fun searchResultForText(): Matcher<View>? = hasDescendant(resultList)

    fun resultElementIsDisplayedOnTheList(): Boolean {
        return viewExists(resultText)
    }

    fun isDisplayedEmptyPage():Boolean {
        return waitForView(emptyImage)
    }

    fun tapOnSearchItemTitle(text: String) {
        waitForView(pageList)
        onView(allOf(pageList, withText(text), isDisplayed())).perform(click())
    }

    fun tapOnDeleteRecentSearchesButton() {
        onView(recentSearchesDeleteButton).perform(click())
    }

    fun tapOnYesButton() {
        onView(yesButton).perform(click())
    }

    fun waitForFor() {
        waitForView(noResultText)
    }

    fun getTextOfNoResultsList(): String {
        waitForView(noResultText)
        return getTextOf(onView(noResultText))
    }
}