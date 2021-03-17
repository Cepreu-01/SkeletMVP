package com.example.skeletmvp.repository.retrofit.model

data class UserRepoPOJOItem(
    val description: String,
    val forks_count: Int,
    val name: String,
    val owner: Owner,
    val stargazers_count: Int
)