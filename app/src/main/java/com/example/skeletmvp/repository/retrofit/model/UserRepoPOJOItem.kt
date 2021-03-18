package com.example.skeletmvp.repository.retrofit.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserRepoPOJOItem(
    val description: String?,
    val forks_count: Int,
    @PrimaryKey
    val name: String,
    @Embedded(prefix = "Owner")
    val owner: Owner,
    val stargazers_count: Int,
    val created_at:String
)