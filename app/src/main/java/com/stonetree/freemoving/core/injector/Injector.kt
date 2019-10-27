import com.stonetree.freemoving.feature.journey.view.JourneyViewArgs
import com.stonetree.freemoving.feature.journey.viewmodel.JourneyViewModel
import com.stonetree.freemoving.sources.CarPoolDataSource
import com.stonetree.freemoving.feature.pool.view.adapter.CarPoolAdapter
import com.stonetree.freemoving.feature.pool.viewmodel.CarPoolViewModel
import com.stonetree.freemoving.repositories.CarPoolRepository
import com.stonetree.freemoving.repositories.CarPoolRepositoryImpl
import com.stonetree.freemoving.repositories.JourneyRepository
import com.stonetree.freemoving.repositories.JourneyRepositoryImpl
import com.stonetree.freemoving.sources.factories.CarPoolSourceFactory
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.httpclient.CoreHttpClientImpl
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import com.stonetree.restclient.feature.interceptor.RestClientInterceptorImpl
import com.stonetree.restclient.feature.network.NetworkChangeReceiverImpl
import com.stonetree.restclient.feature.network.NetworkReceiver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class Injector {

    private val journey = module {
        factory<JourneyRepository> { JourneyRepositoryImpl(get()) }
        viewModel { (args: JourneyViewArgs) -> JourneyViewModel(get(), args) }
    }

    private val pool = module {
        factory { CarPoolAdapter() }
        factory { CarPoolDataSource(get()) }
        factory { CarPoolSourceFactory(get()) }

        single<CarPoolRepository> { CarPoolRepositoryImpl(get()) }

        viewModel { CarPoolViewModel(get(), get()) }
    }

    private val rest = module {
        factory<RestClientInterceptor> { RestClientInterceptorImpl() }
        factory<CoreHttpClient> { CoreHttpClientImpl(get()) }
        single<RestClient> { RestClientImpl() }
        single<NetworkReceiver> { NetworkChangeReceiverImpl() }
    }

    fun startModules(): List<Module> {
        return arrayListOf(rest, pool, journey)
    }
}