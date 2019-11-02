import android.content.Context
import android.util.Log
import android.widget.GridLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.freemoving.NavigatorActivity
import com.stonetree.freemoving.R
import com.stonetree.freemoving.stubs.HttpClientStub
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.idling.RestClientIdling
import junit.framework.TestCase.*
import kotlinx.android.synthetic.main.view_car_pool.*
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

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
        onData(withId(R.id.car_pool))
            .inAdapterView(
                allOf(
                    withId(R.id.car_pool_row),
                    withTagValue(
                        `is`(692026)
                    )
                )
            ).perform(click())
    }

    @Test
    fun swipe_shouldNotBeEmpty() {
        onView(withId(R.id.car_pool))
            .perform(swipeUp())

        Assert.assertNotEquals(rule.activity.car_pool.adapter?.itemCount, 0)
    }

    @Test
    fun test() {
        onView(withId(R.id.car_pool))
            .perform(swipeUp())
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