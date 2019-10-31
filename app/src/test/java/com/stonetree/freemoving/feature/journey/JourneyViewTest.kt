package com.stonetree.freemoving.feature.journey

import androidx.test.core.app.ActivityScenario
import com.appham.mockinizer.RequestFilter
import com.appham.mockinizer.mockinize
import com.stonetree.freemoving.NavigatorActivity
import com.stonetree.restclient.core.constants.RestclientConstants
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class JourneyViewTest : AutoCloseKoinTest() {

    private lateinit var activity: NavigatorActivity

    private val rest = module {
        factory<CoreHttpClient>(override = true) {
            HttpClientStub(get()).also { stub ->
                single<RestClient>(override = true) { RestClientImpl(stub) }
            }
        }
    }

    @Before
    fun setup() {
        loadKoinModules(rest)

        ActivityScenario.launch(NavigatorActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                this.activity = activity
            }
        }
    }

    @Test
    fun test() {

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
            .connectTimeout(RestclientConstants.TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .mockinize(mocks)
            .build()
    }
}