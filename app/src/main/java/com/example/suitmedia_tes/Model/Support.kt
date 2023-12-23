package com.example.suitmedia_tes.Model

import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("url")
    val url: String,
    @SerializedName("text")
    val text: String,

)