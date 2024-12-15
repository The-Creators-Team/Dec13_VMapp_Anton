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
import com.creators.dec13_vm_app_anton.data.model.DataModel
import com.creators.dec13_vm_app_anton.data.model.Person
import com.creators.dec13_vm_app_anton.databinding.FragmentPeopleListBinding
import com.creators.dec13_vm_app_anton.ui.adapter.PersonAdapter
import com.creators.dec13_vm_app_anton.ui.view_model.PersonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : Fragment(R.layout.fragment_people_list) {

    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = _binding!!
    private val personListViewModel: PersonListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentPeopleListBinding.bind(view)
        val peopleRv = binding.peopleList
        val personAdapter = PersonAdapter(arrayListOf(
            Person("https://thumbs.dreamstime.com/b/person-line-icon-outline-vector-sign-linear-style-pictogram-isolated-white-user-account-member-symbol-logo-illustration-88294009.jpg",
                "12/21",DataModel("","","","","",""),
                "james","rj@mail.com","rick","blue","0","developer",
                "james")
        ))
        peopleRv.layoutManager = LinearLayoutManager(requireContext())
        peopleRv.adapter = personAdapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(),
            LinearLayoutManager.VERTICAL)
        peopleRv.addItemDecoration(dividerItemDecoration)
        personListViewModel.personListData.observe(viewLifecycleOwner) {
            personAdapter.updateData(it)
        }
        personListViewModel.getPersonListData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}