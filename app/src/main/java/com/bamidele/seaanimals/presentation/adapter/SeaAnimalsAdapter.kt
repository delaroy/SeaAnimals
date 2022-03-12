package com.bamidele.seaanimals.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import com.bamidele.seaanimals.databinding.SeaAnimalsItemsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SeaAnimalsAdapter(private val recyclerViewClickListener: RecyclerViewClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private val TAG: String = "AppDebug"
    var items: List<SeaAnimalsResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeaAnimalListViewHolder {
        return SeaAnimalListViewHolder(
            SeaAnimalsItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SeaAnimalListViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(itemList: List<SeaAnimalsResponse>){
        items = itemList
        notifyDataSetChanged()
    }

    inner class SeaAnimalListViewHolder(private val binding: SeaAnimalsItemsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data: SeaAnimalsResponse?) {

            binding.let {

                it.root.setOnClickListener {
                    recyclerViewClickListener.clickOnItem(data!!)
                }

                Glide.with(it.root)
                    .load(data!!.speciesIllustrationPhoto!!.src)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.productImage)

                binding.speciesName.text = data!!.speciesName
                binding.habitatImpact.text = data.habitatImpacts
            }
        }
    }


    interface RecyclerViewClickListener {
        fun clickOnItem(data: SeaAnimalsResponse)
    }

}