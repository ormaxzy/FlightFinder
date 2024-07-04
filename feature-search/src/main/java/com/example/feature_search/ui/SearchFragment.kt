package com.example.feature_search.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_search.adapter.TicketAdapter
import com.example.feature_search.databinding.FragmentSearchBinding
import com.example.feature_search.viewmodel.SearchViewModel
import com.example.feature_tickets.ui.TicketsFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import com.example.core.utils.SharedPreferencesHelper

@AndroidEntryPoint
class SearchFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var ticketAdapter: TicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.example.core.R.style.FullScreenBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomSheetBehavior()
        setupUI()
        observeViewModel()
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBottomSheetBehavior() {
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
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

    private fun setupUI() {
        ticketAdapter = TicketAdapter()
        binding.flightRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ticketAdapter
        }

        binding.etFrom.addTextChangedListener {
            searchViewModel.updateFromValue(it.toString())
        }

        binding.etTo.addTextChangedListener {
            searchViewModel.updateToValue(it.toString())
        }

        loadInputValues()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.tickets.collectLatest { tickets ->
                ticketAdapter.items = tickets
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.fromValue.collectLatest { value ->
                if (binding.etFrom.text.toString() != value) {
                    binding.etFrom.setText(value)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.toValue.collectLatest { value ->
                if (binding.etTo.text.toString() != value) {
                    binding.etTo.setText(value)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.departureDate.collectLatest { date ->
                val (dateText, dayOfWeekText) = date.toFormattedString()
                binding.flightDateText.text = dateText
                val dayWeek = ", $dayOfWeekText"
                binding.flightDayWeekText.text = dayWeek
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.returnDate.collectLatest { date ->
                val (dateText) = date?.toFormattedString() ?: Pair("обратно", "")
                binding.etReturnbuttonText.text = dateText
            }
        }
    }

    private fun setupListeners() {
        binding.iconBack.setOnClickListener {
            dismiss()
        }

        binding.iconClear.setOnClickListener {
            binding.etTo.text.clear()
            searchViewModel.updateToValue("")
        }

        binding.iconSwap.setOnClickListener {
            searchViewModel.swapLocations()
        }

        binding.etReturnDateBtn.setOnClickListener {
            showDatePicker { date -> searchViewModel.updateReturnDate(date) }
        }

        binding.flightDateBtn.setOnClickListener {
            showDatePicker { date -> searchViewModel.updateDepartureDate(date) }
        }

        binding.viewAllTicketsButton.setOnClickListener {
            saveInputValues()
            val direction = "${binding.etFrom.text} - ${binding.etTo.text}"
            val userInfo = "${binding.flightDateText.text}, ${binding.passengersButtonText.text}"

            val fragment = TicketsFragment().apply {
                arguments = Bundle().apply {
                    putString("direction", direction)
                    putString("userInfo", userInfo)
                }
            }

            fragment.show(parentFragmentManager, TicketsFragment::class.java.simpleName)
        }

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

    private fun showDatePicker(onDateSet: (date: Date) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                onDateSet(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun Date.toFormattedString(): Pair<String, String> {
        val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        val dayOfWeekFormat = SimpleDateFormat("E", Locale.getDefault())
        val dateText = dateFormat.format(this)
        val dayOfWeekText = dayOfWeekFormat.format(this)
        return Pair(dateText, dayOfWeekText)
    }

    private fun loadInputValues() {
        val (from, to) = SharedPreferencesHelper.loadInputValues(requireContext())
        searchViewModel.updateFromValue(from)
        searchViewModel.updateToValue(to)
    }

    private fun saveInputValues() {
        SharedPreferencesHelper.saveInputValues(
            requireContext(),
            searchViewModel.fromValue.value,
            searchViewModel.toValue.value
        )
    }

}
