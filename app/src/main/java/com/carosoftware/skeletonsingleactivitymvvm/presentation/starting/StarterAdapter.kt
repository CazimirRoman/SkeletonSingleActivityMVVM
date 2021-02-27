package com.carosoftware.skeletonsingleactivitymvvm.presentation.starting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.carosoftware.skeletonsingleactivitymvvm.R
import com.carosoftware.skeletonsingleactivitymvvm.databinding.ItemStartingBinding
import com.carosoftware.skeletonsingleactivitymvvm.domain.StarterModel
import timber.log.Timber

class StartingViewHolder(val itemStartingBinding: ItemStartingBinding) : RecyclerView.ViewHolder(itemStartingBinding.root)

class StartingAdapter(val data: MutableList<StarterModel>): RecyclerView.Adapter<StartingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartingViewHolder {
        val itemStartingBinding: ItemStartingBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_starting,
            parent,
            false
        )

        return StartingViewHolder(itemStartingBinding)
    }

    override fun onBindViewHolder(holder: StartingViewHolder, position: Int) {
        holder.itemStartingBinding.data = data[position]
    }

    override fun getItemCount(): Int = data.size

    fun setUpData(starter: List<StarterModel>) {
        Timber.d("setUpData called with $starter")
        data.clear()
        data.addAll(starter)
        notifyDataSetChanged()
    }
}