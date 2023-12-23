package com.example.suitmedia_tes.Api
import android.telecom.Call
import com.example.suitmedia_tes.Model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int): UserResponse

}