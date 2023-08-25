package com.rayko.postalmaint.ui.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rayko.postalmaint.R
import com.rayko.postalmaint.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        fun buttonMisc(view: View, btnName: String) {
            view.findNavController().navigate(
                HomeFragmentDirections
                    .actionEquipmentFragmentToMiscEquipIdFragment(btnName)
            )
        }

        binding.btnDBCS.setOnClickListener {
            it.findNavController().navigate(
                HomeFragmentDirections
                    .actionEquipmentFragmentToEquipIdFragment()
            )
        }

        binding.btnAFCS.setOnClickListener {
            buttonMisc(it, "AFCS")
        }

        binding.btnAFSM.setOnClickListener {
            buttonMisc(it, "AFSM")
        }

        binding.btnAPBS.setOnClickListener {
            buttonMisc(it, "APBS")
        }

        binding.btnSSM.setOnClickListener {
            buttonMisc(it, "SSM")
        }

        binding.btnOthers.setOnClickListener {
            buttonMisc(it, "AFCS")
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

