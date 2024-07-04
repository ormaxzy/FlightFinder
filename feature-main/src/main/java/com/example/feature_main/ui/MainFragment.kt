package com.example.feature_main.ui

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_main.adapter.OfferAdapter
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.feature_main.viewmodel.MainViewModel
import com.example.feature_search.ui.DestinationSearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var offerAdapter: OfferAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInputFilters()
        setupRecyclerView()
        observeViewModel()
        setupListeners()
        mainViewModel.loadSavedInputValues(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.loadSavedInputValues(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.saveInputValues(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupInputFilters() {
        val filter = InputFilter { source, _, _, _, _, _ ->
            if (source != null && !source.toString().matches(Regex("[а-яА-Я]+"))) {
                ""
            } else {
                null
            }
        }
        binding.searchFields.etFrom.filters = arrayOf(filter)
        binding.searchFields.etTo.filters = arrayOf(filter)
    }

    private fun setupRecyclerView() {
        binding.rvOffers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        offerAdapter = OfferAdapter()
        binding.rvOffers.adapter = offerAdapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.offers.collectLatest { offers ->
                offerAdapter.items = offers
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.fromValue.collectLatest { value ->
                if (binding.searchFields.etFrom.text.toString() != value) {
                    binding.searchFields.etFrom.setText(value)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.toValue.collectLatest { value ->
                if (binding.searchFields.etTo.text.toString() != value) {
                    binding.searchFields.etTo.setText(value)
                }
            }
        }
    }

    private fun setupListeners() {
        binding.searchFields.etFrom.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                mainViewModel.updateFromValue(binding.searchFields.etFrom.text.toString())
                mainViewModel.saveInputValues(requireActivity())
            }
        }

        binding.searchFields.etTo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mainViewModel.saveInputValues(requireActivity())
                DestinationSearchFragment().show(parentFragmentManager, "destinationSearch")
            }
        }
    }
}
