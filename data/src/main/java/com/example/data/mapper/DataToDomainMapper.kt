package com.example.data.mapper

import com.example.data.model.*
import com.example.domain.model.*

object DataToDomainMapper {

    fun mapOffer(data: OfferData): Offer {
        return Offer(
            id = data.id,
            title = data.title,
            town = data.town,
            price = mapPrice(data.price)
        )
    }

    fun mapTicketOffer(data: TicketOfferData): TicketOffer {
        return TicketOffer(
            id = data.id,
            title = data.title,
            time_range = data.timeRange,
            price = mapPrice(data.price)
        )
    }

    fun mapTicket(data: TicketData): Ticket {
        return Ticket(
            id = data.id,
            badge = data.badge,
            price = mapPrice(data.price),
            provider_name = data.providerName,
            company = data.company,
            departure = mapDeparture(data.departure),
            arrival = mapArrival(data.arrival),
            has_transfer = data.hasTransfer,
            has_visa_transfer = data.hasVisaTransfer,
            luggage = mapLuggage(data.luggage),
            hand_luggage = mapHandLuggage(data.handLuggage),
            is_returnable = data.isReturnable,
            is_exchangable = data.isExchangable
        )
    }

    private fun mapPrice(data: PriceData): Price {
        return Price(value = data.value)
    }

    private fun mapDeparture(data: DepartureData): Departure {
        return Departure(town = data.town, date = data.date, airport = data.airport)
    }

    private fun mapArrival(data: ArrivalData): Arrival {
        return Arrival(town = data.town, date = data.date, airport = data.airport)
    }

    private fun mapLuggage(data: LuggageData): Luggage {
        return Luggage(has_luggage = data.hasLuggage, price = data.price?.let { mapPrice(it) })
    }

    private fun mapHandLuggage(data: HandLuggageData): HandLuggage {
        return HandLuggage(has_hand_luggage = data.hasHandLuggage, size = data.size)
    }
}
