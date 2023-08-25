package com.rayko.postalmaint.ui.frags


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rayko.postalmaint.R
import com.rayko.postalmaint.data.CallDataApplication
import com.rayko.postalmaint.databinding.FragmentLogBinding
import com.rayko.postalmaint.viewmodels.LogViewModel
import com.rayko.postalmaint.viewmodels.LogViewModelFactory
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.rayko.postalmaint.ui.LogListAdapter
import kotlinx.coroutines.launch

class LogFragment : Fragment() {

    private var _binding: FragmentLogBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as CallDataApplication).database.callDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogBinding.inflate(inflater, container, false)

        val args = LogFragmentArgs.fromBundle(requireArguments())
        val equipType = args.equipType
        val equipId = args.machId

        viewModel.onStartLogging(equipType, equipId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val logListAdapter = LogListAdapter {
            val action = LogFragmentDirections
                .actionLogFragmentToDetailFragment(
                    equipType = it.equipType,
                    equipID = it.equipNum.toString()
                )
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = logListAdapter

        viewModel.getFullList()

        lifecycle.coroutineScope.launch {
            viewModel.getFullList().collect() {
                logListAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
