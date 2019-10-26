package com.stonetree.freemoving.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stonetree.freemoving.databinding.ViewCarPoolBinding
import com.stonetree.view.feature.fragment.CoreFragment

class CarPoolView : CoreFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewCarPoolBinding.inflate(inflater, viewGroup, false)
        return data.root
    }

    override fun onRequestRetry() {
        // Do nothing
    }
}
