package com.example.tvmazeexample.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvmazeexample.Repository.TvShowRepository
import com.example.tvmazeexample.ViewModels.HomeViewModels

class HomeViewModelFactory(
    private val repository: TvShowRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == HomeViewModels::class.java) {
            HomeViewModels(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}