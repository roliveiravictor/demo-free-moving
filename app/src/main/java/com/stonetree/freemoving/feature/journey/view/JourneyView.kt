package com.stonetree.freemoving.feature.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stonetree.freemoving.MainFragment
import com.stonetree.freemoving.databinding.ViewJourneyBinding

class JourneyView : MainFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewJourneyBinding
            .inflate(inflater, viewGroup, false)

        return data.root
    }

    override fun onRequestRetry() {
        //Do nothing
    }
}