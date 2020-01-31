package dev.diogocabral.webookmark.ui

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dev.diogocabral.webookmark.ui.view.HomeActivity
import dev.diogocabral.webookmark.ui.view.SplashScreenActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(SplashScreenActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun cleanup() {
        Intents.release()
    }

    @Test
    fun shouldCallHomeActivityAfterOnCreate() {
        Thread.sleep(2500)
        Intents.intended(hasComponent(HomeActivity::class.java.name))
    }
}
