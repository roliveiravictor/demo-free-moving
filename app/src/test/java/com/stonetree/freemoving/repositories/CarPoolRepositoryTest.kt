package com.stonetree.freemoving.repositories

import com.stonetree.restclient.feature.repository.CoreRepository
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.any
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CarPoolRepositoryTest : AutoCloseKoinTest() {

    private val repository: CarPoolRepository by inject()

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