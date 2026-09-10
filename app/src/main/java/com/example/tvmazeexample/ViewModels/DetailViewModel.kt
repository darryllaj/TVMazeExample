package com.example.tvmazeexample.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmazeexample.Repository.TvShowRepository
import com.example.tvmazeexample.Response.TvShowResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: TvShowRepository
) : ViewModel() {

    private val _showDetail = MutableStateFlow<TvShowResponse?>(null)
    val showDetail: StateFlow<TvShowResponse?> = _showDetail

    fun getShowDetail(id: Int) {
        viewModelScope.launch {
            try {
                _showDetail.value = repository.getShowDetail(id)
                val result = repository.getShowDetail(id)

                println("ID       = ${result.id}")
                println("NAME     = ${result.name}")
                println("PREMIERED = ${result.premiered}")
                println("SUMMARY  = ${result.summary}")

                _showDetail.value = result


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}