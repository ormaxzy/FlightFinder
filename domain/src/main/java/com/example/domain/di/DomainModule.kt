package com.example.domain.di

import com.example.domain.repository.OfferRepository
import com.example.domain.repository.TicketOfferRepository
import com.example.domain.repository.TicketRepository
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetTicketOffersUseCase
import com.example.domain.usecase.GetTicketsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetOffersUseCase(offerRepository: OfferRepository): GetOffersUseCase {
        return GetOffersUseCase(offerRepository)
    }

    @Provides
    @Singleton
    fun provideGetTicketOffersUseCase(ticketOfferRepository: TicketOfferRepository): GetTicketOffersUseCase {
        return GetTicketOffersUseCase(ticketOfferRepository)
    }

    @Provides
    @Singleton
    fun provideGetTicketsUseCase(ticketRepository: TicketRepository): GetTicketsUseCase {
        return GetTicketsUseCase(ticketRepository)
    }
}
