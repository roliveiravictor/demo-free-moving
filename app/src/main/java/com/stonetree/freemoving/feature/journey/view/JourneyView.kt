package com.stonetree.freemoving.feature.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.stonetree.freemoving.MainFragment
import com.stonetree.freemoving.databinding.ViewJourneyBinding
import com.stonetree.freemoving.feature.journey.viewmodel.JourneyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class JourneyView : MainFragment() {

    private val args: JourneyViewArgs by navArgs()

    val vm: JourneyViewModel by viewModel { parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewJourneyBinding
            .inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)

        return data.root
    }

    override fun onRequestRetry() {
        vm.retry()
    }

    private fun bindXml(data: ViewJourneyBinding) {
        data.view = this@JourneyView
    }

    private fun bindObservers(data: ViewJourneyBinding) {

    }
}