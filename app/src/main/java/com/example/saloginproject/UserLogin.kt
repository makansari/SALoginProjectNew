package com.example.saloginproject

data class UserLogin(
    val isSuccessful: Boolean,
    val message: String,
    val user: User
) {
    data class User(
        val id: Int,
        val name: String,
        val email: String,
        val email_verified_at: Any,
        val created_at: String,
        val updated_at: String
    )
}