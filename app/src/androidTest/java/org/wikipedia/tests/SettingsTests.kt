package org.wikipedia.tests

import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.pages.*
import org.wikipedia.settings.SettingsActivity

@RunWith(AndroidJUnit4::class)
class SettingsTests {
    val navBarPage = NavBarPage()
    val onboardingPage = OnboardingPage()
    val settingsPage = SettingsPage()
    val basePage = BasePage()

    @Before
    fun beforeTests() {
        Intents.init()

    }

    @After
    fun afterTests() {
        Intents.release()
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(SettingsActivity::class.java)

    @Test
    fun checkPrivacyPolicy() {
        basePage.stubAllExternalIntents()
        settingsPage.tapPrivacyPolicyBtn("Privacy policy")
        assertTrue("Incorrect intent was send to open Policy privacy web page",
            settingsPage.isCorrectIntentForPrivacyPolicyPageSend())
    }


}
