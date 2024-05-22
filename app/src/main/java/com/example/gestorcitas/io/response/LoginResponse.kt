package com.example.gestorcitas.io.response

import com.example.gestorcitas.model.User

data class LoginResponse(

    val success: Boolean,
    val user: User,
    val jwt: String


)
