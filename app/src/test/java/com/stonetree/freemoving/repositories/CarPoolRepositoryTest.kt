package com.stonetree.freemoving.repositories

import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.repository.CoreRepository
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CarPoolRepositoryTest : AutoCloseKoinTest() {

    private val rest = mock(RestClient::class.java)

    private val repository: CarPoolRepository by inject()

    private val journey = module {
        factory<CarPoolRepository> { CarPoolRepositoryImpl(rest) }
    }

    @Before
    fun setup() {
        startKoin {
            loadKoinModules(journey)
        }
    }

    @Test
    fun get_shouldReturnCoreRepositoryInstance() {
        repository.get().apply {
            assertNotNull(this)
            assertThat(
                this,
                `is`(any(CoreRepository::class.java))
            )
        }
    }
}