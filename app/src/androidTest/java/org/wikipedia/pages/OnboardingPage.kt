package org.wikipedia.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import org.wikipedia.R

class OnboardingPage {

    val skipButton = ViewMatchers.withId(R.id.fragment_onboarding_skip_button)

    fun tapOnSkipButton() {
        onView(skipButton).perform(click())
    }
}