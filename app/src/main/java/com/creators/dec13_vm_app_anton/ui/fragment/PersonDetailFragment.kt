package com.creators.dec13_vm_app_anton.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.creators.dec13_vm_app_anton.R
import com.creators.dec13_vm_app_anton.databinding.FragmentPersonDetailBinding
import com.creators.dec13_vm_app_anton.ui.view_model.PersonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonDetailFragment : Fragment(R.layout.fragment_person_detail) {
    private var _binding: FragmentPersonDetailBinding? = null
    private val binding get() = _binding!!
    private val personDetailsViewModel: PersonDetailsViewModel by viewModels()

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPersonDetailBinding.bind(view)
        val personId = arguments?.getString("personId")

        personDetailsViewModel.personDetailsData.observe(viewLifecycleOwner) {
            binding.textViewCreatedAt.text = String.format(getString(R.string.created_at_label), it.createdAt)
            binding.textViewName.text = String.format(getString(R.string.first_name_last_name), it.firstName, it.lastName)
            binding.textViewEmail.text = String.format(getString(R.string.email_label), it.email)
            binding.textViewJobTitle.text = String.format(getString(R.string.job_title_label), it.jobtitle)
            binding.textViewFavoriteColor.text = String.format(getString(R.string.favorite_color_label), it.favouriteColor)

            Glide.with(binding.imageViewAvatar.context)
                .load(it.avatar)
                .placeholder(R.drawable.person)
                .into(binding.imageViewAvatar)
        }

        if (personId != null) {
            personDetailsViewModel.getPersonDetailsData(personId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_detail, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}