package com.stonetree.freemoving.feature.pool.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.freemoving.feature.pool.model.Pool

class CarPoolDiffCallback : DiffUtil.ItemCallback<Pool>() {

    override fun areItemsTheSame(
        oldItem: Pool,
        newItem: Pool
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Pool,
        newItem: Pool
    ): Boolean {
        return oldItem.id == newItem.id
    }
}