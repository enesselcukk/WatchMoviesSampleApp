package com.enesselcuk.watchmovies.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.enesselcuk.watchmovies.domain.MoviesRepos
import com.enesselcuk.watchmovies.source.remote.response.categorys.ResultMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repos: MoviesRepos) : ViewModel() {

    private val _categoryFlow: MutableStateFlow<PagingData<ResultMovies>> =
        MutableStateFlow(PagingData.empty())
    val categoryFlow: StateFlow<PagingData<ResultMovies>> = _categoryFlow

    fun moviesCategory(categoryName: String, language: String) {
        viewModelScope.launch {
            repos.getMoviesPaging(categoryName, language).collectLatest {
                _categoryFlow.value = it
            }
        }
    }
}