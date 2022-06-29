package org.wikipedia.pages

import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.wikipedia.R


class SettingsPage {

    private val recyclerView = withId(R.id.recycler_view)
   //  private val settingsTitle = withId(R.id.)

    fun tapPrivacyPolicyBtn(text: String) {
        onView(recyclerView).perform(ViewActions.swipeUp())
        onView(withText(text)).perform(click())
    }

    fun isCorrectIntentForPrivacyPolicyPageSend(): Boolean {
        try {
            Intents.intended(hasData(Uri.parse("https://foundation.wikimedia.org/wiki/Privacy_policy")))
        } catch (e: AssertionError) {
            return false
        }
        return true
    }

}
