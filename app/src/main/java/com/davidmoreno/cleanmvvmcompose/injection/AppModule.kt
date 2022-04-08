package com.davidmoreno.cleanmvvmcompose.injection

import com.davidmoreno.cleanmvvmcompose.domain.repository.film.BaseFilmRepository
import com.davidmoreno.cleanmvvmcompose.domain.repository.film.FilmRepository
import com.davidmoreno.cleanmvvmcompose.domain.repository.album.BaseAlbumRepository
import com.davidmoreno.cleanmvvmcompose.domain.repository.album.AlbumRepository
import com.davidmoreno.cleanmvvmcompose.domain.usecase.FilmListUseCase
import com.davidmoreno.cleanmvvmcompose.domain.usecase.GetFilmListCase
import com.davidmoreno.cleanmvvmcompose.domain.usecase.GetAlbumListCase
import com.davidmoreno.cleanmvvmcompose.domain.usecase.AlbumListUseCase
import com.davidmoreno.cleanmvvmcompose.domain.util.BASE_URL
import com.davidmoreno.cleanmvvmcompose.service.album.AlbumAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** Application dependency injection */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /** Provide the Retrofit builder instance injection */
    @Provides
    @Singleton
    fun provideRetrofitService(): AlbumAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumAPI::class.java)
    }

    /** Provide the Album repository instance injection */
    @Provides
    @Singleton
    fun provideAlbumRepository(albumAPI: AlbumAPI): BaseAlbumRepository {
        return AlbumRepository(albumAPI = albumAPI)
    }

    /** Provide the AlbumListUseCase instance injection */
    @Provides
    @Singleton
    fun provideAlbumListUseCase(repository: BaseAlbumRepository): AlbumListUseCase {
        return AlbumListUseCase(
            getAlbumList = GetAlbumListCase(repository = repository)
        )
    }

    /** Provide the film repository( instance injection */
    @Provides
    @Singleton
    fun provideFilmRepository(): BaseFilmRepository {
        return FilmRepository()
    }

    /** Provide the FilmListUseCase instance injection */
    @Provides
    @Singleton
    fun provideFilmListUseCase(repository: BaseFilmRepository): FilmListUseCase {
        return FilmListUseCase(
            getFilmListCase = GetFilmListCase(repository = repository)
        )
    }
}