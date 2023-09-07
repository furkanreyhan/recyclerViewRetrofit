package com.furkanreyhan.amphibians.remote.model


import com.google.gson.annotations.SerializedName

data class AmphibiansItem(
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)