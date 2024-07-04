package com.example.data.di

import com.example.data.remote.ApiService
import com.example.data.remote.RemoteDataSource
import com.example.data.repository.OfferRepositoryImpl
import com.example.data.repository.TicketOfferRepositoryImpl
import com.example.data.repository.TicketRepositoryImpl
import com.example.domain.repository.OfferRepository
import com.example.domain.repository.TicketOfferRepository
import com.example.domain.repository.TicketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideOfferRepository(remoteDataSource: RemoteDataSource): OfferRepository {
        return OfferRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTicketOfferRepository(remoteDataSource: RemoteDataSource): TicketOfferRepository {
        return TicketOfferRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideTicketRepository(remoteDataSource: RemoteDataSource): TicketRepository {
        return TicketRepositoryImpl(remoteDataSource)
    }
}
