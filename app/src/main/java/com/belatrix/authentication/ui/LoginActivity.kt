package com.belatrix.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.belatrix.authentication.R
import com.belatrix.authentication.utils.ValidationUtils.isEmailValid
import com.belatrix.authentication.utils.ValidationUtils.isPasswordValid
import kotlinx.android.synthetic.main.activity_login.*

@Suppress("UNUSED_PARAMETER")
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    /**
     * Validates the user credentials, and returns the errors
     * to the views
     */
    fun attemptLogin(view: View) {
        var message: String? = null
        var editText: View? = null

        if (etEmail.text.isEmpty()) {
            message = getString(R.string.error_required)
        }
        else if (!isEmailValid(etEmail.text)) {
            message = getString(R.string.error_email)
        }

        tilEmail.error = message
        if (message != null) {
            editText = etEmail
            message = null
        }

        if (etPassword.text.isEmpty()) {
            message = getString(R.string.error_required)
        }
        else if (!isPasswordValid(etPassword.text)) {
            message = getString(R.string.error_password)
        }

        tilPassword.error = message
        if (message != null) {
            editText = tilPassword
        }

        if (editText != null) {
            editText.requestFocus()
        } else {
            finish()
        }
    }

    /**
     * Starts the registration activity
     */
    fun register(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}
