package com.rayko.postalmaint.ui.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.rayko.postalmaint.R
import com.rayko.postalmaint.data.CallDataApplication
import com.rayko.postalmaint.databinding.FragmentDetailBinding
import com.rayko.postalmaint.viewmodels.LogViewModel
import com.rayko.postalmaint.viewmodels.LogViewModelFactory

class DetailFragment : Fragment() {

    companion object {
        var EQUIP_TYPE = "equipType"
        var EQUIP_NUM = "equipID"
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var equipType: String

    private lateinit var equipID: String

    private val viewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as CallDataApplication).database.callDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            equipType = it.getString(EQUIP_TYPE).toString()
            equipID = it.getString(EQUIP_NUM).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

