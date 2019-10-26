package com.stonetree.freemoving.feature.pool.view.adapter

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.stonetree.freemoving.core.viewholder.BaseViewHolder
import com.stonetree.freemoving.databinding.ItemPoolBinding
import com.stonetree.freemoving.feature.pool.model.Car
import com.stonetree.freemoving.feature.pool.model.CarPool

class CarPoolViewHolder(
    private val bind: ItemPoolBinding
) : BaseViewHolder<Car>(bind) {

    override fun onBind(data: Car) {
        data.id.apply {
            bind.carId.text = "Driver: ${toString()}"
            bind.listener = createOnClickListener(this)
        }
    }

    private fun createOnClickListener(id: Long?): View.OnClickListener? {
        return id?.let {
            return@let View.OnClickListener { view ->
                Log.w(javaClass.name, "Id:${id}")
            }
        }
    }
}