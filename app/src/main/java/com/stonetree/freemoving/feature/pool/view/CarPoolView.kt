package com.stonetree.freemoving.feature.pool.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stonetree.freemoving.databinding.ViewCarPoolBinding
import com.stonetree.freemoving.feature.pool.view.adapter.CarPoolAdapter
import com.stonetree.freemoving.feature.pool.viewmodel.CarPoolViewModel
import com.stonetree.view.feature.fragment.CoreFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarPoolView : CoreFragment() {

    private val adapter: CarPoolAdapter by inject()

    private val vm: CarPoolViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewCarPoolBinding.inflate(inflater, viewGroup, false)

        bindXml(data, adapter)
        bindObservers(data, adapter)

        return data.root
    }

    override fun onRequestRetry() {
        vm.retry()
    }

    private fun bindXml(
        data: ViewCarPoolBinding,
        adapter: CarPoolAdapter
    ) {
        data.view = this@CarPoolView
        data.carPool.adapter = adapter
    }

    private fun bindObservers(data: ViewCarPoolBinding, adapter: CarPoolAdapter) {
        //Do nothing
    }
}
