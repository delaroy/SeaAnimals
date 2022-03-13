package com.bamidele.seaanimals.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import com.bamidele.seaanimals.databinding.SeaAnimalsDetailsBinding
import com.bamidele.seaanimals.presentation.MainActivity
import com.bamidele.seaanimals.presentation.adapter.SeaAnimalsDetailsAdapter
import com.bamidele.seaanimals.util.SEA_ANIMALS_DATA
import com.bamidele.seaanimals.util.TITLE_NAME

class SeaAnimalsDetails: Fragment() {
    private var _binding: SeaAnimalsDetailsBinding? = null
    private val binding get() = _binding!!
    private var title_name = ""
    private val seaAnimalsDetailsAdapter: SeaAnimalsDetailsAdapter by lazy { SeaAnimalsDetailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SeaAnimalsDetailsBinding.inflate(inflater, container, false)

        binding.seaAnimalDetailsRecyclerview.apply {
            adapter = seaAnimalsDetailsAdapter
        }

        if (arguments != null) {
            val value = arguments?.getParcelable<SeaAnimalsResponse>(SEA_ANIMALS_DATA)
            title_name = arguments?.getString(TITLE_NAME)!!
            if (value!!.imageGallery != null) {
                binding.errorMessage.visibility = GONE
                val image = value.imageGallery!!.toList()
                seaAnimalsDetailsAdapter.submitList(image)
            } else {
                binding.errorMessage.visibility = VISIBLE
            }
        } else {
            binding.errorMessage.visibility = VISIBLE
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)!!.setTitle(title_name)
    }
}