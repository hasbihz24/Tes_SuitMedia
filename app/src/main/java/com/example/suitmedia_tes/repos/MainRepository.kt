package com.example.suitmedia_tes.repos

import com.example.suitmedia_tes.Api.ApiClient
import com.example.suitmedia_tes.Api.ApiService

class MainRepository() {
    private val apiService: ApiService = ApiClient.instance
    suspend fun getUser(page: Int,perPage: Int) = apiService.getUsers(page,perPage)
}