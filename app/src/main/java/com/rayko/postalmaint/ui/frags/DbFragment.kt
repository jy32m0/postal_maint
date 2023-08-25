package com.rayko.postalmaint.ui.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.rayko.postalmaint.R
import com.rayko.postalmaint.databinding.FragmentDbBinding


class DbFragment : Fragment() {

    private var _binding: FragmentDbBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDbBinding.inflate(inflater, container, false)

        val cioss = "CIOSS"
        val dbcs = "DBCS"
        val dioss = "DIOSS"

        fun buttonDb(view: View, equipType: String, equipID: String) {
            view.findNavController()
                .navigate(DbFragmentDirections.actionEquipIdFragmentToLogFragment(equipType, equipID))
        }

        binding.buttonDIOSS15.setOnClickListener {
            buttonDb(it, dioss,"15")
        }

        binding.buttonDIOSS16.setOnClickListener {
            buttonDb(it, dioss,"16")
        }

        binding.buttonDB01.setOnClickListener {
            buttonDb(it, dbcs,"01")
        }

        binding.buttonDB02.setOnClickListener {
            buttonDb(it, dbcs,"02")
        }

        binding.buttonDB05.setOnClickListener {
            buttonDb(it, dbcs,"05")
        }

        binding.buttonDB09.setOnClickListener {
            buttonDb(it, dbcs,"09")
        }

        binding.buttonDB10.setOnClickListener {
            buttonDb(it, dbcs,"10")
        }

        binding.buttonDB12.setOnClickListener {
            buttonDb(it, dbcs,"12")
        }

        binding.buttonDB13.setOnClickListener {
            buttonDb(it, dbcs,"13")
        }

        binding.buttonDB17.setOnClickListener {
            buttonDb(it, dbcs,"17")
        }

        binding.buttonDB18.setOnClickListener {
            buttonDb(it, dbcs,"18")
        }

        binding.buttonDB19.setOnClickListener {
            buttonDb(it, dbcs,"19")
        }

        binding.buttonDB20.setOnClickListener {
            buttonDb(it, dbcs,"20")
        }

        binding.buttonCIOSS02.setOnClickListener {
            buttonDb(it, cioss,"2")
        }

        binding.buttonDbCancel.setOnClickListener { view: View ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}