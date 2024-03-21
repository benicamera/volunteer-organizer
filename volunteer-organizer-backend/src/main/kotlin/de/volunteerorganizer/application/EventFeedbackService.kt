package de.volunteerorganizer.application

import de.volunteerorganizer.domain.event.IEventRepository

/**
 * Service for registering and de-registering to event use case
 * @param eventRepository repository to be used to
 */
class EventFeedbackService(private val eventRepository: IEventRepository) {

    /**
     * Method for register event use case
     * @param issuerId ID of volunteer issuing register use case
     * @param eventId ID of the event to register to
     */
    fun registerToEvent(issuerId: Int, eventId: Int) {
        // get event with event id

        // get volunteer instance with issuer id

        // register volunteer to event

        // save event
    }

    /**
     * Method for deregistering from event
     * @param issuerId ID of volunteer issuing the use case
     * @param eventId ID of event to deregister from
     */
    fun deregisterFromEvent(issuerId: Int, eventId: Int) {
        // get event with event id

        // remove volunteer with id from event

        // save event
    }
}
