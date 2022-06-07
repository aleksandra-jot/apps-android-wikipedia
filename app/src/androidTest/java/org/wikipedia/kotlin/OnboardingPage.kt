package org.wikipedia.kotlin

import BaseRobot
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matchers
import org.wikipedia.R

class OnboardingPage {


    val skipButton = ViewMatchers.withId(R.id.fragment_onboarding_skip_button)

    fun clickOnSkipButton() {
        BaseRobot().doOnView(Matchers.allOf(skipButton, isDisplayed()), click())

    }
}