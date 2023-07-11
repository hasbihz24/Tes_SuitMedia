package com.example.suitmedia_tes

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: List<User>
)

data class User(
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)