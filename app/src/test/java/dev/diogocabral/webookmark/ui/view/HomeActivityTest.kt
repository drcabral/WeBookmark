package dev.diogocabral.webookmark.ui.view

import kotlinx.android.synthetic.main.content_home.user_books_list
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeActivityTest {

    private lateinit var homeActivity: HomeActivity

    @Before
    fun setup() {
        homeActivity = Robolectric.buildActivity(HomeActivity::class.java).create().resume().get()
    }

    @Test
    fun `should configure recyclerview when create activity`() {
        assertTrue(homeActivity.user_books_list.hasFixedSize())
        assertNotNull(homeActivity.user_books_list.adapter)
    }

    // TODO: Test livedata observation behavior after it returns value
}
