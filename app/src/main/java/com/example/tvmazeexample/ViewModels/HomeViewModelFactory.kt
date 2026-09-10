package com.example.tvmazeexample.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvmazeexample.Repository.TvShowRepository

class HomeViewModelFactory(
    private val repository: TvShowRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModels::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModels(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}