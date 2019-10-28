package com.stonetree.freemoving.feature.pool.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.stonetree.freemoving.R
import com.stonetree.freemoving.feature.pool.model.Car

class CarPoolAdapter: PagedListAdapter<Car, CarPoolViewHolder>(
    CarPoolDiffCallback()
) {
    override fun onBindViewHolder(holder: CarPoolViewHolder, position: Int) {
        getItem(position)?.let { car ->
            with(holder) {
                itemView.tag = car.id
                onBind(car)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarPoolViewHolder {
        return CarPoolViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_pool, parent, false
            )
        )
    }
}