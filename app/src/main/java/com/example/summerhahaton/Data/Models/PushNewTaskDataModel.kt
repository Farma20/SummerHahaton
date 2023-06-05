package com.example.summerhahaton.Data.Models


import com.google.gson.annotations.SerializedName

data class PushNewTaskDataModel(
    @SerializedName("begin")
    var begin: String = "",
    @SerializedName("end")
    var end: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("user_id")
    var userId: Int = -1,
    @SerializedName("value")
    var value: String = ""
)