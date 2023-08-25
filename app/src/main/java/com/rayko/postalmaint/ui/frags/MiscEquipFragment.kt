package com.rayko.postalmaint.ui.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rayko.postalmaint.R
import com.rayko.postalmaint.databinding.FragmentMiscEquipBinding


class MiscEquipFragment : Fragment() {

    private var _binding: FragmentMiscEquipBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val binding: FragmentMiscEquipBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_misc_equip, container, false
//        )
        _binding = FragmentMiscEquipBinding.inflate(inflater, container, false)

        // argument from EquipmentFragment (equipment type: AFCS, DBCS...)
        val equipType = MiscEquipFragmentArgs.fromBundle(requireArguments()).equipType

        fun buttonToDo(view: View, machID: String) {
            view.findNavController()
                .navigate(MiscEquipFragmentDirections.actionMiscEquipIdFragmentToLogFragment(equipType, machID))
        }

        binding.buttonMisc1.setOnClickListener {
            buttonToDo(it, "1")
        }

        binding.buttonMisc2.setOnClickListener {
            buttonToDo(it, "2")
        }

        binding.buttonMisc3.setOnClickListener {
            buttonToDo(it, "3")
        }

        binding.buttonMisc4.setOnClickListener {
            buttonToDo(it, "4")
        }

        binding.buttonMiscCancel.setOnClickListener { view: View ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}