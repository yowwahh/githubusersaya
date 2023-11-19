package com.example.githubapps.data.remote



import com.example.githubapps.data.model.ResponseUserGithub
import retrofit2.http.GET


interface GithubService {

    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub() : MutableList<ResponseUserGithub.ItemsItem>
}