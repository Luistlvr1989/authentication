package com.belatrix.authentication.utils

/**
 * Created by ltalavera on 5/31/17.
 * Main validation utils for the app
 */
object ValidationUtils {
    /**
     * Validates the email
     * @param email The string containing the email
     * @return Boolean If the email is correct
     */
    fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Validates the size of the password
     * @param password The string containing the password
     * @return Boolean If the password is correct
     */
    fun isPasswordValid(password: CharSequence): Boolean {
        if (password.length < 6) {
            return false
        }
        return true
    }
}