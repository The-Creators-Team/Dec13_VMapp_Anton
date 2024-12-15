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
import com.creators.dec13_vm_app_anton.databinding.FragmentLoginBinding
import com.creators.dec13_vm_app_anton.ui.view_model.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val authViewModel: AuthViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passwordEt.text.toString()
            authViewModel.signInWithEmail(email, pass) { success ->
                if (success) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.forgotPasswordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.githubBtn.setOnClickListener {
            authViewModel.signInWithGitHub(requireActivity()) { success ->
                if (success) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    Toast.makeText(requireContext(), "GitHub sign-in failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.yahooBtn.setOnClickListener {
            authViewModel.signInWithYahoo(requireActivity()) { success ->
                if (success) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    Toast.makeText(requireContext(), "Yahoo sign-in failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
