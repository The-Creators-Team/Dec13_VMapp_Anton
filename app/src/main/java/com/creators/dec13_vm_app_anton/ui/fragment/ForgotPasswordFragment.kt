package com.creators.dec13_vm_app_anton.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creators.dec13_vm_app_anton.R
import com.creators.dec13_vm_app_anton.databinding.FragmentForgotPasswordBinding
import com.creators.dec13_vm_app_anton.ui.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val authViewModel: AuthViewModel by viewModels()

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentForgotPasswordBinding.bind(view)

        binding.resetBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            authViewModel.resetPassword(email) { isSuccess, exception ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "Reset link sent to your email", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack() // Navigate back to the LoginFragment
                } else {
                    Toast.makeText(requireContext(), "Failed to send reset email: ${exception.toString()}", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.cancelBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
