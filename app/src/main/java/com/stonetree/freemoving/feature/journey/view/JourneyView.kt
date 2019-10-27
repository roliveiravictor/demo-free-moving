package com.stonetree.freemoving.feature.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.stonetree.freemoving.MainFragment
import com.stonetree.freemoving.databinding.ViewJourneyBinding
import com.stonetree.freemoving.feature.journey.viewmodel.JourneyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.google.android.gms.maps.model.MarkerOptions

class JourneyView : MainFragment() {

    private val args: JourneyViewArgs by navArgs()

    private val vm: JourneyViewModel by viewModel { parametersOf(args) }

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
        markOnMap(arrayListOf(vm.selectedCar()))

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

        data.map.getMapAsync { map ->
            val bounds = vm.camera().bounds()
            map.setLatLngBoundsForCameraTarget(bounds)
        }
    }

    private fun bindObservers(data: ViewJourneyBinding) {
        vm.network.observe(viewLifecycleOwner) { network ->
            data.network = network
        }
    }

    private fun markOnMap(marks: List<MarkerOptions>) {
        data.map.getMapAsync { map ->
            marks.forEach { mark ->
                mark.visible(true)
                map.addMarker(mark)
            }
        }
    }
}