package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf
import org.wikipedia.R



class SearchBarPage {

    private val text: String = "Donald Trump"
    private val searchTextInput = withId(R.id.search_src_text)
    private val pageList = withId(R.id.page_list_item_title)
    private val recentSearchesDeleteButton = withId(R.id.recent_searches_delete_button)
    private val yesButton = withText(R.string.dialog_message_clear_history_yes)
    private val emptyImage = withId(R.id.search_empty_image)
    private val resultText = hasDescendant(withText(text))


    fun typeTextSearch(text: String) {
        onView(searchTextInput).perform(typeText(text))
    }

    fun isDisplayedElement(): Boolean {
        return BasePage().viewExists(resultText)
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
        //Utils.getTextOf(onView(yesButton))
//        actionOnItemAtPosition(1, Utils.getTextOf(this))
//        Utils.getTextOf(onView(actionOnItemAtPosition(1, BasePage().waitForView(onView(yesButton)))))
        //onView(hasDescendant(withId(R.id.main_nav_tab_layout)(Utils.atPosition(0, getText))))

    }
}