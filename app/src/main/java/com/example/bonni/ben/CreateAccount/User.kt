package com.example.bonni.ben.CreateAccount

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("email")
    val email: String,

    @SerializedName("firstname")
    val firstname: String,

    @SerializedName("lastname")
    val lastname: String,

    @SerializedName("password")
    val password: String
)
