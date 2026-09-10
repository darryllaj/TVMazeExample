package com.example.tvmazeexample.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmazeexample.UiState
import com.example.tvmazeexample.Repository.TvShowRepositoryInterface
import com.example.tvmazeexample.Response.TvShowResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModels(
    private val repository: TvShowRepositoryInterface
) : ViewModel() {

    private  val _uiState = MutableStateFlow<UiState<List<TvShowResponse>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<TvShowResponse>>> = _uiState

    init {
        getShows()
    }

    fun getShows() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                val result = repository.getShow()

                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}