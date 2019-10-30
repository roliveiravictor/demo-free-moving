import android.widget.GridLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.appham.mockinizer.RequestFilter
import com.appham.mockinizer.mockinize
import com.stonetree.freemoving.NavigatorActivity
import com.stonetree.freemoving.R
import com.stonetree.restclient.core.constants.RestclientConstants.TIMEOUT
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import com.stonetree.restclient.feature.interceptor.RestClientInterceptorImpl
import com.stonetree.restclient.feature.network.NetworkChangeReceiverImpl
import com.stonetree.restclient.feature.network.NetworkReceiver
import junit.framework.TestCase
import kotlinx.android.synthetic.main.view_car_pool.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class CarPoolViewTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(NavigatorActivity::class.java)

    private val rest = module(override = true) {
        factory<RestClientInterceptor> { RestClientInterceptorImpl() }
        factory<CoreHttpClient> { HttpClientStub(get()) }
        single<RestClient> { RestClientImpl() }
        single<NetworkReceiver> { NetworkChangeReceiverImpl() }
    }

    @Before
    fun setup() {
        loadKoinModules(rest)

        rule.activity
            .findNavController(R.id.navigator).apply {
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
    @Ignore
            /* Mock server */
    fun tag_withValue_shouldReturnVisible() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.car_pool),
                ViewMatchers.withTagValue(Matchers.`is`(0))
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun swipe_shouldNotBeEmpty() {
        Espresso.onView(ViewMatchers.withId(R.id.car_pool))
            .perform(ViewActions.swipeUp())

        Assert.assertNotEquals(rule.activity.car_pool.adapter?.itemCount, 0)
    }

    @Test
    fun test() {
        Espresso.onView(ViewMatchers.withId(R.id.car_pool))
            .perform(ViewActions.swipeUp())
    }

    @Test
    fun recyclerView_shouldReturnDefaultValues() {
        rule.activity.car_pool.apply {
            TestCase.assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                TestCase.assertTrue(spanCount == 3)
                TestCase.assertTrue(orientation == GridLayout.VERTICAL)
            }
        }
    }

    class HttpClientStub(interceptor: RestClientInterceptor) : CoreHttpClient {

        private val interceptor: HttpLoggingInterceptor = interceptor.log()

        private val mocks: Map<RequestFilter, MockResponse> = mapOf(
            RequestFilter("/") to MockResponse().apply {
                setResponseCode(404)
                setBody("..")
            }
        )

        override fun create() = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .mockinize(mocks)
            .build()
    }
}