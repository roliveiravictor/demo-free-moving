package com.stonetree.freemoving.feature.pool.view.adapter

import android.view.View
import androidx.navigation.findNavController
import com.stonetree.freemoving.core.extensions.avatar
import com.stonetree.freemoving.core.viewholder.BaseViewHolder
import com.stonetree.freemoving.databinding.ItemPoolBinding
import com.stonetree.freemoving.directions.CarPoolDirections
import com.stonetree.freemoving.feature.pool.model.Car

class CarPoolViewHolder(
    private val bind: ItemPoolBinding
) : BaseViewHolder<Car>(bind) {

    override fun onBind(data: Car) {
        data.apply {
            bind.carId.text = id.toString()
            bind.avatar.setImageResource(fleetType.avatar())
            bind.listener = createOnClickListener(data)
        }
    }

    private fun createOnClickListener(car: Car): View.OnClickListener? {
        return car.let {
            return@let View.OnClickListener { view ->
                val direction = CarPoolDirections.actionLatestToDetails(car)
                view.findNavController().navigate(direction)
            }
        }
    }
}