package com.example.tvmazeexample.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmazeexample.Repository.TvShowRepository
import com.example.tvmazeexample.Response.TvShowResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModels : ViewModel() {
    private val repository = TvShowRepository()

    private val _shows = MutableStateFlow<List<TvShowResponse>>(emptyList())
    val shows: StateFlow<List<TvShowResponse>> = _shows

    init {
        getShows()
    }

    private fun getShows() {
        viewModelScope.launch {
            try {
                _shows.value = repository.getShow()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}