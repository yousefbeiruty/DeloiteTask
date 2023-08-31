package com.example.deloitetask.screens.main.ui.home

import android.view.View
import com.example.deloitetask.common.BaseViewHolder
import com.example.deloitetask.databinding.ItemMostPopulerBinding
import com.example.deloitetask.extensions.toViewClickListener
import com.example.domain.model.home.MostPopular

class MostPopularViewHolder(private val item: ItemMostPopulerBinding) : BaseViewHolder<MostPopular>(item) {

    override fun bind(
        model: MostPopular, position: Int, clickListener: ((view: View, model: MostPopular, position: Int) -> Unit)?
    ) {
        item.mostPopular = model
        item.clickListener = clickListener.toViewClickListener(model, position)
    }
}