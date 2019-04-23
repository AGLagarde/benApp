package com.example.bonni.ben.DataClass

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Json_User(
    @SerializedName("data")
    var data: Data,
    @SerializedName("message")
    var message: String
)

data class Data(
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    var user: User
)

data class User(
    @SerializedName("created")
    var created: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("firstname")
    var firstname: String,
    @SerializedName("house")
    var house: Any,
    @SerializedName("id")
    var id: Int,
    @SerializedName("lastname")
    var lastname: String
)
