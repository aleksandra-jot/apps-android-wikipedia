package org.wikipedia.pages

import android.app.Activity
import android.app.Instrumentation
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
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

    fun getTextOf(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(TextView::class.java)
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

    fun stubAllExternalIntents() {
        Intents.intending(CoreMatchers.not(IntentMatchers.isInternal()))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }
}