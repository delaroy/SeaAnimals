package com.bamidele.seaanimals.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bamidele.seaanimals.data.model.ImageGallery
import com.bamidele.seaanimals.databinding.SeaAnimalDetailsItemsBinding
import com.bamidele.seaanimals.databinding.SeaAnimalsDetailsBinding
import com.bamidele.seaanimals.databinding.SeaAnimalsListBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SeaAnimalsDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private val TAG: String = "AppDebug"
    var items: List<ImageGallery> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeaAnimalsDetailsViewHolder {
        return SeaAnimalsDetailsViewHolder(
            SeaAnimalDetailsItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SeaAnimalsDetailsViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(itemList: List<ImageGallery>){
        items = itemList
        notifyDataSetChanged()
    }

    inner class SeaAnimalsDetailsViewHolder(private val binding: SeaAnimalDetailsItemsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: ImageGallery?) {

            binding.let {

                Glide.with(it.root)
                    .load(data!!.src)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivGridImage)

                binding.title.text = data.title
            }
        }
    }
}