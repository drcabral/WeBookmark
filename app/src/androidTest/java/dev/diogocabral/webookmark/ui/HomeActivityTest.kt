package dev.diogocabral.webookmark.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dev.diogocabral.webookmark.R
import dev.diogocabral.webookmark.ui.view.HomeActivity
import dev.diogocabral.webookmark.ui.view.SearchActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun cleanup() {
        Intents.release()
    }

    @Test
    fun shouldStartSearchBookActivityWhenClickingOnPlusFloatingButton() {
        onView(withId(R.id.add_book_button)).perform(click())

        Intents.intended(hasComponent(SearchActivity::class.java.name))
    }
}
