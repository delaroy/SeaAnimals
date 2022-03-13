package com.bamidele.seaanimals.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bamidele.seaanimals.R
import com.bamidele.seaanimals.presentation.adapter.SeaAnimalsAdapter
import com.bamidele.seaanimals.data.model.SeaAnimalsResponse
import com.bamidele.seaanimals.databinding.SeaAnimalsListBinding
import com.bamidele.seaanimals.presentation.MainActivity
import com.bamidele.seaanimals.presentation.model.UIState
import com.bamidele.seaanimals.presentation.viewmodel.SeaAnimalsViewModel
import com.bamidele.seaanimals.util.SEA_ANIMALS_DATA
import com.bamidele.seaanimals.util.TITLE_NAME
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SeaAnimalsList : Fragment(), SeaAnimalsAdapter.RecyclerViewClickListener {

    private var _binding: SeaAnimalsListBinding? = null
    private val seaAnimalsViewModel: SeaAnimalsViewModel? by viewModels()
    private val seaAnimalsAdapter: SeaAnimalsAdapter by lazy { SeaAnimalsAdapter(this@SeaAnimalsList) }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = SeaAnimalsListBinding.inflate(inflater, container, false)
        seaAnimalsViewModel?.loadSeaAnimals()

        binding.seaAnimalRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = seaAnimalsAdapter
        }

        observeFlowData()
        return binding.root

    }

    private fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            seaAnimalsViewModel?.seaAnimalsFlow?.collect {
                when (it) {
                    is UIState.Loading -> {
                        showProgress()
                    }
                    is UIState.Success -> {
                        hideProgress()
                        it.data?.let {
                            seaAnimalsAdapter.submitList(it)
                        }
                    }
                    is UIState.Error -> {
                        hideProgress()
                        showError(it.error.toString())
                    }
                }
            }
        }
    }

    private fun showError(error: String) {
        if (!error.contains("Conversion error")){
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickOnItem(data: SeaAnimalsResponse) {
        val bundle = Bundle()
        bundle.putParcelable(SEA_ANIMALS_DATA, data)
        bundle.putString(TITLE_NAME, data.speciesName)
        findNavController().navigate(R.id.action_FirstFragment_to_seaAnimalsDetails, bundle)
    }

    override fun onResume() {
        super.onResume()
        seaAnimalsViewModel?.loadSeaAnimals()
        (activity as MainActivity?)!!.setTitle("SeaAnimals")
    }
}