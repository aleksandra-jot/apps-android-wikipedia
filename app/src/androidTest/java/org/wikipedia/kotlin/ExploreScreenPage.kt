package org.wikipedia.kotlin

import BaseRobot
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.*
import org.wikipedia.R
import androidx.test.espresso.matcher.ViewMatchers.withId
import junit.framework.Assert
import org.hamcrest.Matcher
import org.jsoup.Connection

class ExploreScreenPage {
    val searchBarExplorePage = withId(R.id.search_container)
    val searchTextInput = withId(R.id.search_src_text)
    val resultsText = withId(R.id.results_text)
    val pageList = withId(R.id.page_list_item_title)
    val getTextId = onView(allOf(resultsText))


    fun clickOnSearchBarExplorePage() {
     BaseRobot().doOnView((searchBarExplorePage), click());
 }

    fun typeTextExplorePage(text: String) {
        BaseRobot().doOnView((searchTextInput), typeText(text))
    }

    fun assertSearchItemTitle(text: String) {
        BaseRobot().assertOnView((allOf(pageList, withText(text), isDisplayed())), matches(withText(text)))
    }

    fun assertSearchNoResult() {
        BaseRobot().assertOnView((allOf(resultsText, withText("No results"), isDisplayed())), matches(withText("No results")))
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

    }