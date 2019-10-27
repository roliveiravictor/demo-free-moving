package com.stonetree.freemoving.feature.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.stonetree.freemoving.MainFragment
import com.stonetree.freemoving.databinding.ViewJourneyBinding
import com.stonetree.freemoving.feature.journey.viewmodel.JourneyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE
import com.stonetree.freemoving.R
import com.stonetree.freemoving.core.constants.Constants.Map.Camera.ZOOM_DISTANCE

class JourneyView : MainFragment(), OnCameraMoveStartedListener, OnCameraIdleListener {

    private val args: JourneyViewArgs by navArgs()

    private val vm: JourneyViewModel by viewModel { parametersOf(args) }

    private lateinit var map: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewJourneyBinding
            .inflate(inflater, viewGroup, false)

        bindMap()
        bindXml(data)
        bindObservers()

        map.onCreate(savedInstanceState)
        markOnMap(arrayListOf(vm.selectedCar()))

        return data.root
    }

    override fun onRequestRetry() {
        vm.retry()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map.onLowMemory()
    }

    private fun bindXml(data: ViewJourneyBinding) {
        data.view = this@JourneyView
    }

    private fun bindMap() {
        map = childFragmentManager
            .findFragmentById(R.id.journey_map) as SupportMapFragment

        map.getMapAsync { map ->
            map.setOnCameraMoveStartedListener(this)
            map.setOnCameraIdleListener(this)

            val pos = vm.camera().position()
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, ZOOM_DISTANCE))
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        when (p0) {
            REASON_GESTURE,
            REASON_DEVELOPER_ANIMATION -> map.getMapAsync { map ->
                vm.saveLastPosition(map.cameraPosition)
            }
        }
    }

    override fun onCameraIdle() {
        map.getMapAsync { map ->
            vm.load(map.cameraPosition)
        }
    }

    private fun bindObservers() {
        vm.marks.observe(viewLifecycleOwner) { marks ->
            markOnMap(marks)
        }
    }

    private fun markOnMap(marks: List<MarkerOptions>) {
        map.getMapAsync { map ->
            marks.forEach { mark ->
                map.addMarker(mark)
            }
        }
    }
}