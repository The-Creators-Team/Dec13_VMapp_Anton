package com.creators.dec13_vm_app_anton.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creators.dec13_vm_app_anton.R
import com.creators.dec13_vm_app_anton.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMainBinding.bind(view)

        binding.viewPeopleBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_peopleListFragment)
        }

        binding.viewRoomsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_roomsListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
