package com.creators.dec13_vm_app_anton.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.creators.dec13_vm_app_anton.R
import com.creators.dec13_vm_app_anton.data.model.Room
import com.creators.dec13_vm_app_anton.databinding.FragmentPeopleListBinding
import com.creators.dec13_vm_app_anton.databinding.FragmentRoomsBinding
import com.creators.dec13_vm_app_anton.ui.adapter.RoomAdapter
import com.creators.dec13_vm_app_anton.ui.view_model.RoomsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment(R.layout.fragment_rooms) {

    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!
    private val roomsViewModel: RoomsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRoomsBinding.bind(view)
        val roomsRv = binding.roomsRv
        val roomsAdapter = RoomAdapter(arrayListOf())
        roomsRv.layoutManager = LinearLayoutManager(requireContext())
        roomsRv.adapter = roomsAdapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(),
            LinearLayoutManager.VERTICAL)
        roomsRv.addItemDecoration(dividerItemDecoration)
        roomsViewModel.roomsData.observe(viewLifecycleOwner) {
            roomsAdapter.updateRooms(it)
        }
        roomsViewModel.getRoomsData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rooms, container, false)
    }
}