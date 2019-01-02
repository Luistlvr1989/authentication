package com.belatrix.authentication.business.networking

import com.belatrix.authentication.business.injection.scope.ApplicationScope
import com.belatrix.authentication.model.dtos.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ltalavera on 6/1/17.
 * Handles the basic communication
 */
@ApplicationScope
class NetworkManager @Inject constructor(private val api: AuthenticationApi) {
    fun login(email: String, password: String) {
        val call = api.login(email, password)
        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                Timber.i(response?.body().toString())
            }

            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {

            }
        })
    }
}