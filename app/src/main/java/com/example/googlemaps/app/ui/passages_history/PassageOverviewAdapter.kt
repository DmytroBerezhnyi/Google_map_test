package com.example.googlemaps.app.ui.passages_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.googlemaps.databinding.ItemPassageOverviewBinding
import com.example.googlemaps.domain.model.Passage

class PassageOverviewAdapter :
    ListAdapter<Passage, PassageOverviewAdapter.PassageOverviewViewHolder>(DiffUtilPassageCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassageOverviewViewHolder {
        return PassageOverviewViewHolder(
            ItemPassageOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PassageOverviewViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class PassageOverviewViewHolder(private val binding: ItemPassageOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(passage: Passage) {
            with(binding) {
                textCarName.text = passage.carName
                textFullLoad.text = passage.generalLoad.toString()
                textTotalLength.text = passage.totalLength.toString()
                textTotalOutlets.text = passage.totalOutlets.toString()
            }
        }
    }

    class DiffUtilPassageCallback : DiffUtil.ItemCallback<Passage>() {
        override fun areItemsTheSame(oldItem: Passage, newItem: Passage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Passage, newItem: Passage): Boolean {
            return oldItem == newItem
        }
    }
}