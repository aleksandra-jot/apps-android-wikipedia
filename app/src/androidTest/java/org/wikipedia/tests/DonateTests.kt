package org.wikipedia.tests

import android.app.Activity
import android.app.Instrumentation
import android.os.SystemClock
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.BundleMatchers
import androidx.test.espresso.intent.matcher.BundleMatchers.hasValue
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.matcher.UriMatchers.hasHost
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.wikipedia.main.MainActivity
import org.wikipedia.pages.NavBarPage
import org.wikipedia.pages.OnboardingPage
import org.wikipedia.settings.SettingsActivity

class DonateTests {
    private val navBarPage = NavBarPage()
    private val onboardingPage = OnboardingPage()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SettingsActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
        onboardingPage.tapOnSkipButton()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun openDonateWebPage() {
        val expectedDonatePageHost = "donate.wikimedia.org"

        stubAllExternalIntents()
        navBarPage.tapMoreOption()
        navBarPage.tapDonateOption()
        SystemClock.sleep(2000)

        assertTrue("Incorrect intent was send to open Donate web page",
                isCorrectIntentForDonatePageSend(expectedDonatePageHost))
    }

    private fun stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
    }

    private fun isCorrectIntentForDonatePageSend(donatePageHost: String): Boolean {
        //val intentMatcher = IntentMatchers.hasAction("android.intent.action.VIEW")
        val openWebWithUrlMatcher = allOf(
                hasAction("android.intent.action.VIEW"),
                hasData(hasHost(donatePageHost))
        )
        val intentMatcher = IntentMatchers.hasExtras(BundleMatchers.hasValue(openWebWithUrlMatcher))

        try {
            Intents.intended(intentMatcher)
        } catch (e: AssertionError) {
            return false
        }
        return true
    }
}