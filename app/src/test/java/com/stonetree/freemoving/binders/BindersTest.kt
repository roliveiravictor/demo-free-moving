package com.stonetree.freemoving.binders

import android.view.View
import android.widget.ImageView
import androidx.test.core.app.ApplicationProvider
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.model.NetworkState.Companion.LOADED
import com.stonetree.restclient.core.model.NetworkState.Companion.LOADING
import junit.framework.TestCase.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BindersTest: AutoCloseKoinTest() {

    private val view: ImageView = ImageView(ApplicationProvider.getApplicationContext())

    @Test
    fun test_bindIsIdleOnSuccess_shouldReturnVisible() {
        bindIsIdle(view, LOADED)
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun test_bindIsIdleOnFailed_shouldReturnVisible() {
        bindIsIdle(view, NetworkState.error("mError"))
        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun test_bindIsIdleOnRunning_shouldReturnGone() {
        bindIsIdle(view, LOADING)
        assertEquals(View.GONE, view.visibility)
    }
}