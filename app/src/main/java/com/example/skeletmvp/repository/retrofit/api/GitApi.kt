package com.example.skeletmvp.repository.retrofit.api

import com.example.skeletmvp.repository.retrofit.model.Repo
import com.example.skeletmvp.repository.retrofit.model.User
import com.example.skeletmvp.repository.retrofit.model.UserRepoPOJOItem
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApi {
    @GET("/users/{user}")
    fun getUserInfo(@Path("user") user:String): Observable<User>
    @GET("/users/{user}/repos")
    fun getUserRepos(@Path("user") user: String):Observable<List<Repo>>
    @GET("/users/{user}/repos")
    fun getUserRepoPOGO(@Path("user") user: String):Observable<List<UserRepoPOJOItem>>

}