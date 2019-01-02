package com.belatrix.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.belatrix.authentication.AuthenticationApplication
import com.belatrix.authentication.R
import com.belatrix.authentication.business.networking.NetworkManager
import com.belatrix.authentication.utils.ValidationUtils.isEmailValid
import com.belatrix.authentication.utils.ValidationUtils.isPasswordValid
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AuthenticationApplication.component.inject(this)
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
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()*/
            networkManager.login("mobilelab@belatrixsf.com", "q1!q1!")
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
