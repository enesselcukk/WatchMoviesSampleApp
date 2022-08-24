package com.enesselcuk.watchmovies.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.enesselcuk.watchmovies.domain.MoviesRepos
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repos: MoviesRepos) : ViewModel() {

    private val _flowSearch: MutableStateFlow<PagingData<ResultMovies>> =
        MutableStateFlow(PagingData.empty())
    val flowSearch: StateFlow<PagingData<ResultMovies>> = _flowSearch

    fun searchAll(language: String, name: String) {
        viewModelScope.launch {
            repos.allSearch(language, name).collectLatest {
                _flowSearch.value = it
            }
        }
    }

}