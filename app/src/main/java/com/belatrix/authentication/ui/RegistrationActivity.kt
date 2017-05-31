package com.belatrix.authentication.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.belatrix.authentication.R
import com.belatrix.authentication.utils.ValidationUtils
import kotlinx.android.synthetic.main.activity_registration.*

@Suppress("UNUSED_PARAMETER")
class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    /**
     * Validates the registration, and return all the errors
     * to the views
     */
    fun attemptRegister(view: View) {
        var message: String? = null
        var editText: View? = null

        if (etFirstName.text.isEmpty()) {
            tilFirstName.error = getString(R.string.error_required)
            editText = etFirstName
        }

        if (etLastName.text.isEmpty()) {
            tilLastName.error = getString(R.string.error_required)
            editText = etLastName
        }

        if (etEmail.text.isEmpty()) {
            message = getString(R.string.error_required)
        }
        else if (!ValidationUtils.isEmailValid(etEmail.text)) {
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
        else if (!ValidationUtils.isPasswordValid(etPassword.text)) {
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
     * Returns to the login activity
     */
    fun login(view: View) {
        finish()
    }
}
