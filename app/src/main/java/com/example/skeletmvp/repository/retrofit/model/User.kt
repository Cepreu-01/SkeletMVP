package com.example.skeletmvp.repository.retrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class User(
    val avatar_url: String,
    val created_at: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val public_repos: Int,
    val repos_url: String
)