package com.stonetree.freemoving.feature.carpool

import android.widget.GridLayout.VERTICAL
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.freemoving.NavigatorActivity
import com.stonetree.freemoving.R
import com.stonetree.restclient.feature.idling.RestClientIdling
import junit.framework.TestCase.assertTrue
import kotlinx.android.synthetic.main.view_car_pool.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers.`is`
import org.junit.*
import org.junit.Assert.assertNotEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CarPoolViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<NavigatorActivity>(NavigatorActivity::class.java)

    private lateinit var controller: NavController

    @Before
    fun setup() {
        controller = rule.activity
            .findNavController(R.id.navigator).apply {
                navigate(R.id.view_carpool)
            }

        IdlingRegistry
            .getInstance()
            .register(RestClientIdling.getResource())

        while(RestClientIdling.getResource().isIdleNow) { }
    }

    @After
    fun tearDown() {
        IdlingRegistry
            .getInstance()
            .unregister(RestClientIdling.getResource())
    }

    @Test
    @Ignore
    /* Mock server */
    fun tag_withValue_shouldReturnVisible() {
        onView(
            allOf(
                withId(R.id.car_pool),
                withTagValue(`is`(0))
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun swipe_shouldNotBeEmpty() {
        onView(withId(R.id.car_pool))
            .perform(swipeUp())

        assertNotEquals(rule.activity.car_pool.adapter?.itemCount, 0)
    }

    @Test
    fun recyclerView_shouldReturnDefaultValues() {
        rule.activity.car_pool.apply {
            assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                assertTrue(spanCount == 3)
                assertTrue(orientation == VERTICAL)
            }
        }
    }
}