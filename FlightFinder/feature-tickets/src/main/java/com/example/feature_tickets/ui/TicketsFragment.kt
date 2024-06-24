package com.example.feature_tickets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_tickets.adapter.TicketAdapter
import com.example.feature_tickets.databinding.FragmentTicketsBinding
import com.example.feature_tickets.viewmodel.TicketsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TicketsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding get() = _binding!!
    private lateinit var ticketAdapter: TicketAdapter
    private val ticketsViewModel: TicketsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.example.core.R.style.FullScreenBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomSheetBehavior()
        setupUI()
        setupListeners()
        observeViewModel()

        // Получение данных из аргументов
        val direction = arguments?.getString("direction")
        val userInfo = arguments?.getString("userInfo")

        binding.directionTextView.text = direction
        binding.userInfoTextView.text = userInfo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Настройка поведения нижнего диалогового окна.
     */
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

    /**
     * Настройка UI-компонентов.
     */
    private fun setupUI() {
        ticketAdapter = TicketAdapter()
        binding.ticketsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ticketAdapter
        }
    }

    /**
     * Настройка слушателей для UI-компонентов.
     */
    private fun setupListeners() {
        binding.goBackButton.setOnClickListener {
            dismiss()
        }
    }

    /**
     * Наблюдение за изменениями в ViewModel.
     */
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            ticketsViewModel.tickets.collectLatest { tickets ->
                ticketAdapter.items = tickets
            }
        }
    }

}
