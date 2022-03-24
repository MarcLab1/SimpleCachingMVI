package com.mvifun.di

import android.app.Application
import androidx.room.Room
import com.mvifun.usecases.DeletePostUseCase
import com.mvifun.usecases.GetPostsFlowUseCase
import com.mvifun.util.Constants
import com.mvifun.repository.PostRepository
import com.mvifun.repository.PostRepository_Impl
import com.mvifun.network.dto.ApiService
import com.mvifun.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideString() = "jello world?"

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService, postDatabase: PostDatabase): PostRepository {
        return PostRepository_Impl(apiService, postDatabase)
    }

    @Provides
    @Singleton
    fun providePostDatabase(application: Application): PostDatabase {
        return Room.databaseBuilder(application, PostDatabase::class.java, "posts")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideGetPostsFlowUseCase(repo: PostRepository): GetPostsFlowUseCase
    {
        return GetPostsFlowUseCase(repo)
    }

    @Provides
    @Singleton
    fun provideDeletePostUseCase(repo: PostRepository): DeletePostUseCase
    {
        return DeletePostUseCase(repo)
    }
}