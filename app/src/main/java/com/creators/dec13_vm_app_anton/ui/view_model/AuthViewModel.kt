package com.creators.dec13_vm_app_anton.ui.view_model

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    fun signInWithEmail(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task -> callback(task.isSuccessful) }
    }

    fun signUpWithEmail(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task -> callback(task.isSuccessful) }
    }

    fun resetPassword(email: String, callback: (Boolean) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task -> callback(task.isSuccessful)}
    }

    fun signInWithGitHub(activity: Activity, callback: (Boolean) -> Unit) {
        val provider = OAuthProvider.newBuilder("github.com")
        firebaseAuth.startActivityForSignInWithProvider(activity, provider.build())
            .addOnCompleteListener { task -> callback(task.isSuccessful) }
    }

    fun signInWithYahoo(activity: Activity, callback: (Boolean) -> Unit) {
        val provider = OAuthProvider.newBuilder("yahoo.com")
        firebaseAuth.startActivityForSignInWithProvider(activity, provider.build())
            .addOnCompleteListener { task -> callback(task.isSuccessful) }
    }
}