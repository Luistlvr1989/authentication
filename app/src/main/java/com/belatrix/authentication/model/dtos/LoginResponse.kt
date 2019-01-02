package com.belatrix.authentication.model.dtos

/**
 * Created by ltalavera on 6/2/17.
 * Handles the JSON response
 */
class LoginResponse(
        val token: String,
        val user: User
)

class User(
        val name: String,
        val email: String,
        val roles: List<Role>
)

class Role(
        val id: Int,
        val name: String,
        val description: String
)