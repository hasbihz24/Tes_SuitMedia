package com.example.suitmedia_tes.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.suitmedia_tes.repos.MainRepository
import com.example.suitmedia_tes.util.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import java.util.ResourceBundle

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getUser(page: Int, perPage: Int) = liveData(Dispatchers.IO) {
        try{
            emit(Resource.success(data = mainRepository.getUser(page, perPage)))
        }catch (ex: Exception){
            emit(Resource.error(data = null, message = ex.message ?: "Error Occured!"))
        }
    }
}