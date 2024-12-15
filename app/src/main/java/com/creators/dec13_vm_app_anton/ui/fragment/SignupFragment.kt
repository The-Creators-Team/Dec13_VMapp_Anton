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
import com.creators.dec13_vm_app_anton.databinding.FragmentSignupBinding
import com.creators.dec13_vm_app_anton.ui.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [SignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SignupFragment : Fragment(R.layout.fragment_signup) {

    private val authViewModel: AuthViewModel by viewModels()

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSignupBinding.bind(view)

        binding.signupBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEt.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            authViewModel.signUpWithEmail(email, password) { success ->
                if (success) {
                    Toast.makeText(requireContext(), "Sign-up successful", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack() // Navigate back to LoginFragment or another screen
                } else {
                    Toast.makeText(requireContext(), "Sign-up failed", Toast.LENGTH_SHORT).show()
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
