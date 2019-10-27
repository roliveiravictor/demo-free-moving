package com.stonetree.freemoving.feature.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.MapView
import com.stonetree.freemoving.MainFragment
import com.stonetree.freemoving.databinding.ViewJourneyBinding
import com.stonetree.freemoving.feature.journey.viewmodel.JourneyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import com.stonetree.freemoving.R


class JourneyView : MainFragment() {

    private val args: JourneyViewArgs by navArgs()

    val vm: JourneyViewModel by viewModel { parametersOf(args) }

    private lateinit var data: ViewJourneyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        data = ViewJourneyBinding
            .inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)

        data.map.onCreate(savedInstanceState)

        data.map.getMapAsync {
            it.addMarker(MarkerOptions().position(LatLng(51.513259, -0.129147)).title("Marker"));
        }

        return data.root
    }

    override fun onRequestRetry() {
        vm.retry()
    }

    override fun onResume() {
        super.onResume()
        data.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        data.map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        data.map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        data.map.onLowMemory()
    }

    private fun bindXml(data: ViewJourneyBinding) {
        data.view = this@JourneyView
    }

    private fun bindObservers(data: ViewJourneyBinding) {
        vm.network.observe(viewLifecycleOwner) { network ->
            data.network = network
        }
    }
}