package com.creators.dec13_vm_app_anton.ui.view_model

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private val TAG = "AuthViewModel"
    fun signInWithEmail(email: String?, password: String?, callback: (Boolean) -> Unit) {
        val emailParam = if (email.isNullOrBlank()) "aaa" else email
        val passwordParam = if(password.isNullOrBlank()) "aaa" else password
        firebaseAuth.signInWithEmailAndPassword(emailParam, passwordParam)
            .addOnCompleteListener { task -> callback(task.isSuccessful) }
    }

    fun signUpWithEmail(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task -> callback(task.isSuccessful) }
    }

    fun resetPassword(email: String, callback: (Boolean, Exception?) -> Unit) {
        firebaseAuth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val signInMethods = task.result?.signInMethods
                    if (signInMethods?.isNotEmpty() == true) {
                        // Email is registered, send password reset email
                        firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener { resetTask ->
                                callback(resetTask.isSuccessful, resetTask.exception)
                            }
                    } else {
                        // Email is not registered
                        callback(false, Exception("Email is not registered"))
                    }
                } else {
                    // Error fetching sign-in methods
                    callback(false, task.exception)
                }
            }
    }

    fun signInWithGitHub(activity: Activity, callback: (Boolean) -> Unit) {
        val provider = OAuthProvider.newBuilder("github.com")
        firebaseAuth.startActivityForSignInWithProvider(activity, provider.build())
            .addOnCompleteListener { task -> callback(task.isSuccessful) }

    }

    fun signInWithYahoo(activity: Activity, callback: (Boolean) -> Unit) {
        val provider = OAuthProvider.newBuilder("yahoo.com")
        firebaseAuth.startActivityForSignInWithProvider(activity, provider.build())
            .addOnCompleteListener { task ->
                task.exception?.toString()?.let { Log.d(TAG, it) }
                callback(task.isSuccessful) }
    }
}