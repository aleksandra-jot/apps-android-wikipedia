package org.wikipedia.kotlin

import BaseRobot
import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.*
import org.wikipedia.R
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher


class ExploreScreenPage {
    val searchBarExplorePage = withId(R.id.search_container)
    val searchTextInput = withId(R.id.search_src_text)
    val searchResultsList = withId(R.id.search_results_list)
    val pageList = withId(R.id.page_list_item_title)
    val recentSearchesDeleteButton = withId(R.id.recent_searches_delete_button)
    val yesButton = withText("Yes")
    val emptyImage = withId(R.id.search_empty_image)
    val recentSearchesList = withId(R.id.recent_searches)
    val pageToolbarButton = withId(R.id.page_toolbar_button_search)

    fun clickOnSearchBarExplorePage() {
     BaseRobot().doOnView((searchBarExplorePage), click());
 }

    fun typeTextExplorePage(text: String) {
        BaseRobot().doOnView((searchTextInput), typeText(text))
    }

    fun assertRecentSearchItemTitle(text: String) {
        //TestUtil.delay(1)
        BaseRobot().assertOnView(recentSearchesList, matches(hasDescendant(withText(text))))

        // BaseRobot().assertOnView(allOf(withText(text), isDisplayed()), matches(withText(text)))
    }

    fun clickOnSearchItemTitle(text: String) {
        BaseRobot().doOnView((allOf(pageList, withText(text), isDisplayed())), click())
    }

    fun clickOnDeleteRecentSearchesButton() {
        BaseRobot().doOnView(recentSearchesDeleteButton, click())
    }

    fun clickOnYesButton() {
        BaseRobot().doOnView(yesButton, click())
    }

    fun assertDeletedResults() {
        BaseRobot().assertOnView(emptyImage, matches(isDisplayed()))
    }

    fun clickOnSearchToolbarButton() {
        BaseRobot().doOnView(pageToolbarButton, click())
    }

    fun assertSearchNoResult() {
       // TestUtil.delay(1)
        BaseRobot().assertOnView(searchResultsList, matches(hasDescendant(withText("No results"))))
    }

    fun assertSearchResultsList(text: String) {
        //TestUtil.delay(1)
        BaseRobot().assertOnView(searchResultsList, matches(hasDescendant(withText(text))))
    }

    fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
//
//    fun assert() {
//        fun ViewInteraction.isDisplayed(): Boolean {
//            try {
//                check(matches(ViewMatchers.isDisplayed()))
//                return true
//            } catch (e: Exception) {
//                return false
//            }
//        }
//
//        if(onView(withText("Donalsd Trump")).isDisplayed()) {
//           Log.i("OK", "jest super")
//        } else {
//            Log.i("NIE OK", "nie jest super")
//        }
//
//
//    }

}