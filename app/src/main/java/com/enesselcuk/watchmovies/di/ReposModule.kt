package com.enesselcuk.watchmovies.di

import com.enesselcuk.watchmovies.domain.MoviesRepos
import com.enesselcuk.watchmovies.source.local.MoviesDatabase
import com.enesselcuk.watchmovies.source.remote.apiService.MoviesService
import com.enesselcuk.watchmovies.source.repos.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object ReposModule {

    @Singleton
    private fun provideRepository(
        api: MoviesService,
        dispatcher: CoroutineDispatcher,
        database: MoviesDatabase
    ) = MoviesRepositoryImpl(api, dispatcher, database)
}

@Module
@InstallIn(ViewModelComponent::class)
interface Repos {
    @Binds
    fun movies(moviesImpl: MoviesRepositoryImpl): MoviesRepos
}
