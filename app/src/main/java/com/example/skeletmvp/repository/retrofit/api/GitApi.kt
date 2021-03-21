package com.example.skeletmvp.repository.retrofit.api

import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {
    @GET("/users/{user}/repos")
    fun getUserRepoPOGO(@Path("user") user: String):Observable<List<UserRepoPOJOItem>>

}