import android.content.Context
import android.widget.GridLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stonetree.freemoving.R
import com.stonetree.freemoving.stubs.HttpClientStub
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.idling.RestClientIdling
import junit.framework.TestCase.assertTrue
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import androidx.test.rule.ActivityTestRule
import com.stonetree.freemoving.NavigatorActivity
import kotlinx.android.synthetic.main.view_car_pool.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class CarPoolViewTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Rule
    @JvmField
    val rule = object : ActivityTestRule<NavigatorActivity>(
        NavigatorActivity::class.java
    ) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            val rest = module {
                factory<CoreHttpClient>(override = true) {
                    HttpClientStub(
                        get(),
                        context
                    )
                }
                single<RestClient>(override = true) { RestClientImpl(get()) }
            }

            loadKoinModules(rest)
        }
    }

    @Before
    fun setup() {
        rule.activity.findNavController(R.id.navigator).apply {
            navigate(R.id.view_carpool)
        }

        IdlingRegistry
            .getInstance()
            .register(RestClientIdling.getResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry
            .getInstance()
            .unregister(RestClientIdling.getResource())
    }

    @Test
    fun tag_withValue_shouldReturnVisible() {
        onView(
            allOf(
                withId(R.id.car_pool_row),
                withTagValue(
                    `is`(692620L)
                )
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun tag_withValue_shouldReturnJourneyView() {
        onView(
            allOf(
                withId(R.id.car_pool_row),
                withTagValue(
                    `is`(692620L)
                )
            )
        ).perform(click())
    }

    @Test
    fun onCreate_shouldNotBeEmpty() {
        assertNotEquals(
            rule.activity.car_pool.adapter?.itemCount,
            0
        )
    }

    @Test
    fun recyclerView_shouldReturnDefaultValues() {
        rule.activity.car_pool.apply {
            assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                assertTrue(spanCount == 3)
                assertTrue(orientation == GridLayout.VERTICAL)
            }
        }
    }
}