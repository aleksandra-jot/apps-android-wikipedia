package org.wikipedia.pages

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.wikipedia.R

class SearchResultPage: BasePage() {
    private val searchSuggestionsList = withId(R.id.search_results_list)
    private val resultItemTitle = withId(R.id.page_list_item_title)
    private fun itemWithText(text: String): Matcher<View> {
        return hasDescendant(
                allOf(
                        resultItemTitle,
                        withText(text)
                )
        )
    }

    fun tapItemWithText(text: String) {
        waitUntilResultsAreLoaded()
        onView(searchSuggestionsList)
                .perform(actionOnItem<RecyclerView.ViewHolder>(itemWithText(text), click()))
    }

    fun countResults(): Int {
        var itemCount = 0
        fun countItemsMatcher(): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun matchesSafely(item: RecyclerView): Boolean {
                    itemCount = item.adapter!!.itemCount
                    return true
                }

                override fun describeTo(description: Description) {}
            }
        }
        onView(searchSuggestionsList).check(matches(countItemsMatcher()))

        return itemCount
    }

    fun waitUntilResultsAreLoaded() {
        waitForView(allOf(
                hasDescendant(resultItemTitle),
                withParent(searchSuggestionsList)
        ))
    }
}