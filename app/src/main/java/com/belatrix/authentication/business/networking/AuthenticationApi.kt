package com.belatrix.authentication.business.networking

import com.belatrix.authentication.model.dtos.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by ltalavera on 6/2/17.
 * Handles all the requests
 */
interface AuthenticationApi {
    @FormUrlEncoded
    @POST("/api/users/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<LoginResponse>
}
