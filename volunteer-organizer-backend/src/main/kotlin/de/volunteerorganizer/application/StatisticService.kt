package de.volunteerorganizer.application

import de.volunteerorganizer.domain.event.IEventRepository

/**
 * Service for all statistic use cases
 * @param eventRepository repository to bes used for events
 */
class StatisticService(private val eventRepository: IEventRepository) {

    /**
     * Method for getting statistics for a specific user.
     * @param issuerId ID of volunteer issuing the use case
     * @param volunteerId ID of the volunteer to get the statistics from
     * @returns the found statistics
     */
    fun getVolunteerStatistic(issuerId: Int, volunteerId: Int): Statistics{

        // check if issuer is entitled to perform this use case

        // collect statistics for volunteer with volunteer id

        // return statistics
        return Statistics(0, 0)
    }

    /**
     * Method for getting statistics for a specific event
     * @param issuerId ID of volunteer issuing the use case
     * @param eventId ID of the event to get the statistics from
     * @returns the found statistics
     */
    fun getEventStatistic(issuerId: Int, eventId: Int): Statistics{

        // check if issuer is entitled to perform this use case

        // collect statistics of event

        // return statistics
        return Statistics(0, 0)
    }
}