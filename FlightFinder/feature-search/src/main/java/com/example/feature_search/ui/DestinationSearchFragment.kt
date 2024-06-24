package com.example.feature_search.ui


import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.feature_search.databinding.FragmentDestinationSearchListDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import com.example.core.utils.SharedPreferencesHelper
import com.example.feature_search.R

@AndroidEntryPoint
class DestinationSearchFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDestinationSearchListDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.example.core.R.style.FullScreenBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDestinationSearchListDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomSheetBehavior()
        loadInputValues()
        setupButtonListeners()
        setupRecommendationClickListeners()
        setupEditorActionListener()
        setupClearButtonListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBottomSheetBehavior() {
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO

            it.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            it.requestLayout()

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        it.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                        it.requestLayout()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    private fun setupButtonListeners() {
        binding.btnComplexRoute.setOnClickListener {
            showPlaceholderFragment()
        }

        binding.btnAnywhere.setOnClickListener {
            binding.etTo.setText(getString(com.example.core.R.string.anywhere))
            saveInputValues()
        }

        binding.btnWeekends.setOnClickListener {
            showPlaceholderFragment()
        }

        binding.btnHotTickets.setOnClickListener {
            showPlaceholderFragment()
        }
    }

    private fun setupRecommendationClickListeners() {
        binding.cardIstanbul.setOnClickListener {
            binding.etTo.setText(com.example.core.R.string.istanbul)
            saveInputValues()
        }

        binding.cardSochi.setOnClickListener {
            binding.etTo.setText(com.example.core.R.string.sochi)
            saveInputValues()
        }

        binding.cardPhuket.setOnClickListener {
            binding.etTo.setText(com.example.core.R.string.phuket)
            saveInputValues()
        }
    }

    private fun setupEditorActionListener() {
        binding.etTo.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event?.action == KeyEvent.ACTION_DOWN) {
                saveInputValues()
                SearchFragment().show(parentFragmentManager, "searchFragment")
                true
            } else {
                false
            }
        }
    }

    private fun setupClearButtonListener() {
        binding.iconClear.setOnClickListener {
            binding.etTo.text.clear()
            saveInputValues()
        }
    }

    private fun showPlaceholderFragment() {
        val placeholderFragment = PlaceholderFragment()
        placeholderFragment.show(parentFragmentManager, "PlaceholderFragment")
        dismiss()
    }

    private fun loadInputValues() {
        val (from, to) = SharedPreferencesHelper.loadInputValues(requireContext())
        binding.etFrom.setText(from)
        binding.etTo.setText(to)
    }

    private fun saveInputValues() {
        SharedPreferencesHelper.saveInputValues(
            requireContext(),
            binding.etFrom.text.toString(),
            binding.etTo.text.toString()
        )
    }

}
