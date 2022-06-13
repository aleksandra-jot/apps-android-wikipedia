package org.wikipedia.pages

import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

open class BasePage {
    private val uiTestsTag = "AUTO_TESTS"

    /**
     * This is a workaround for case where one wants to check if view exists without doing anything to it.
     * If the view is not in view hierarchy (matcher is wrong), a NoMatchingViewException is thrown
     */
    fun viewExists(matcher: Matcher<View>): Boolean {
        return try {
            onView(matcher).perform(noAction())
            true
        }
        catch (e: AmbiguousViewMatcherException) {
            true
        }
        catch (e: NoMatchingViewException) {
            false
        }
    }


    fun waitForView(
            viewMatcher: Matcher<View>,
            timeout: Long = 15,
            errorMessage: String = "View '$viewMatcher' was not found. Waited for: $timeout seconds",
    ): Boolean {
        val intervalMilliseconds: Long = 250
        var remainingTimeMilliseconds = timeout * 1000
        Log.i(uiTestsTag, "Waiting for existence of view '$viewMatcher' started. Timeout used: $timeout seconds")
        while (remainingTimeMilliseconds > 0) {
            SystemClock.sleep(intervalMilliseconds)
            if (viewExists(viewMatcher)) {
                Log.i(uiTestsTag, "View '$viewMatcher' found.")
                return true
            }
            remainingTimeMilliseconds -= intervalMilliseconds
        }
        Log.i(uiTestsTag, errorMessage)
        return false
    }

    /**
     * Move this part to new class that will hold your CustomViewActions.
     * This one here, does nothing - no real interaction with UI is performed
     */
    fun noAction() = object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return CoreMatchers.any(View::class.java)
        }

        override fun getDescription(): String {
            return "Dummy ViewAction performed. No real interaction with UI."
        }

        // No action performed, empty method body
        override fun perform(uiController: UiController?, view: View?) { }
    }
}