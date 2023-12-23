package com.bangkit.batikdiscover.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.batikdiscover.MainActivity
import com.bangkit.batikdiscover.R
import com.bangkit.batikdiscover.data.DataRepository
import com.bangkit.batikdiscover.data.UserPreference
import com.bangkit.batikdiscover.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    lateinit var dataRepository: DataRepository
    private lateinit var userPreference: UserPreference

    lateinit var emailEditText: EmailValidationEditText
    lateinit var passwordEditText: PasswordValidationEditText
    private lateinit var loginButton: Button
    private lateinit var errorTextViewEmail: TextView
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dataRepository = DataRepository(this)
        userPreference = UserPreference(this)

        emailEditText = findViewById(R.id.edtEmail)
        passwordEditText = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            performLogin()
        }
        val RegisterNowTextView = findViewById<TextView>(R.id.textViewDaftar)
        RegisterNowTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLoginButton() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        loginButton.isEnabled = email.contains('@') && password.length >= 8

        if (!email.contains('@')) {
            errorTextViewEmail.text = "Enter a valid email address"
            errorTextViewEmail.visibility = View.VISIBLE
        } else {
            errorTextViewEmail.text = ""
            errorTextViewEmail.visibility = View.GONE
        }
    }

    fun performLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty() || !email.contains('@')) {
            showToastOnMainThread("Email and Password must be filled correctly")
            return
        }

        progressDialog = ProgressDialog.show(this, "Logging in", "Please wait...", true, false)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = dataRepository.login(email, password)

                runOnUiThread {
                    progressDialog?.dismiss()
                }

                if (response.message == "success" && response.loginResult != null) {
                    userPreference.saveUserToken(response.loginResult.token)
                    showToastOnMainThread("Login successful")
                    navigateToMainActivity()
                } else {
                    showToastOnMainThread("Login failed: Incorrect Password or Email")
                }
            } catch (e: Exception) {
                runOnUiThread {
                    progressDialog?.dismiss()
                    showToastOnMainThread("Login failed: An error occurred")
                    navigateToLoginActivity()
                }
            }
        }
    }

    fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showToastOnMainThread(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
