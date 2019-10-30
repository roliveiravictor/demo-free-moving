package com.stonetree.restclient.feature

import android.content.Context
import com.stonetree.restclient.core.extensions.read
import com.stonetree.restclient.core.constants.RestclientConstants.BASE_URL
import com.stonetree.restclient.core.constants.RestclientConstants.API_KEY
import com.stonetree.restclient.core.constants.RestclientConstants.REPOSITORY_PROPS
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class RestClientImpl(httpClient: CoreHttpClient): RestClient {

    private val http: OkHttpClient = httpClient.create()

    private lateinit var retrofit: Retrofit

    private var baseUrl: String = ""

    private var key: String = ""

    override fun key(): String = key

    override fun <T : Any> generate(clazz: KClass<T>): T {
        return this.retrofit.create(clazz.java)
    }

    override fun start(context: Context) {
        baseUrl = REPOSITORY_PROPS.read(context, BASE_URL)
        key = REPOSITORY_PROPS.read(context, API_KEY)
        retrofit = configureRetrofit()
    }

    private fun configureRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(http)
            .build()
    }
}
