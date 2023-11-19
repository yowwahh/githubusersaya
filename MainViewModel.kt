package com.example.githubapps


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapps.data.remote.ApiClient
import com.example.githubapps.utils.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val resultUser = MutableLiveData<Result>()



    fun getUser() {
        viewModelScope.launch{
                flow {
                    val response = ApiClient
                        .githubService
                        .getUserGithub()

                    emit(response)
                }.onStart {
                    resultUser.value = Result.Loading(true)

                }.onCompletion {
                    resultUser.value = Result.Loading (false)

                }.catch {
                    Log.e("Error", it.message.toString())
                    it.printStackTrace()
                    resultUser.value = Result.Error(it)
                }.collect {
                    resultUser.value = Result.Success(it)
                }
        }
    }
}

