//package org.wikipedia.tests
//
//import android.app.Activity
//import android.app.Instrumentation
//import android.content.Intent
//import androidx.test.espresso.intent.Intents.intended
//import androidx.test.espresso.intent.Intents.intending
//import androidx.test.espresso.intent.matcher.IntentMatchers.*
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import org.hamcrest.Matchers.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.wikipedia.TestUtil
//import org.wikipedia.main.MainActivity
//import org.wikipedia.pages.*
//
//class IntentsTests {
//    val explorePage = ExplorePage()
//    val searchBarPage = SearchBarPage()
//    val searchPage = SearchPage()
//    val navBarPage = NavBarPage()
//    val onboardingPage = OnboardingPage()
//    val articlePage = ArticlePage()
//
//
//    @Before
//    fun beforeTests() {
//        onboardingPage.tapOnSkipButton()
//
////        fun stubAllExternalIntents() {
////            // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
////            // every test run. In this case all external Intents will be blocked.
////            intending(not(isInternal()))
////                    .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, null))
////        }
////    }
//
//
////    @get:Rule
////    @JvmField
////    val intentsTestRule = IntentsTestRule(MyActivity::class.java)
//
//        @Rule
//        @JvmField
//        var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)
//
//        @Test
//        fun checkDonatePage() {
//
//            val expectedIntent =
//                hasData("https://donate.wikimedia.org/w/index.php?title=Special:LandingPage&country=PL&uselang=en&utm_medium=WikipediaApp&utm_source=2.7.50401-r-2022-06-20&utm_campaign=Android")
//            navBarPage.tapOnMoreNavButton()
//            TestUtil.delay(2)
//            navBarPage.tapOnDonateButton()
//            TestUtil.delay(2)
//            //assertThat(Matcher<Intent>).hasData(Uri.parse("www.google.com"))
//            intended(
//                allOf(
//                    hasAction(Intent.ACTION_CHOOSER),
//                    hasExtra(
//                        `is`(Intent.EXTRA_INTENT),
//                        allOf(
//                            hasAction(Intent.ACTION_SEND),
//                            hasExtra(Intent.EXTRA_TEXT, expectedIntent)
//                        )
//                    )
//                )
//            )
//            // Using a canned RecordedIntentMatcher to validate that an intent resolving
//            // to the "phone" activity has been sent.
//            intending(expectedIntent)
//
////        Intent resultData = new Intent();
////        resultData.putExtra("resultData", "fancyData");
////        ActivityResult result = new ActivityResult(Activity.RESULT_OK, resultData);
////
////        intending(toPackage(HomeScreenActivity.class.getName())).respondWith(result));
//        }
//    }
//}