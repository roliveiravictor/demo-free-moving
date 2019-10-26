package com.stonetree.freemoving.feature.pool.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.CarPool

class CarPoolDiffCallback : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(
        oldItem: Car,
        newItem: Car
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Car,
        newItem: Car
    ): Boolean {
        return oldItem.id == newItem.id
    }
}