package com.example.summerhahaton.Data.Models


import com.google.gson.annotations.SerializedName

data class UsersListDataModelItem(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String
)