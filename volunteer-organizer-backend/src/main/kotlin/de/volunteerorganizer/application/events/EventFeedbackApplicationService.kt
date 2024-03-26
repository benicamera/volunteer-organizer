package de.volunteerorganizer.application.events

import de.volunteerorganizer.domain.club.IClubRepository
import de.volunteerorganizer.domain.event.IEventRepository
import kotlin.jvm.Throws

/**
 * Service for registering and de-registering to event use case
 * @param eventRepository repository to be used to
 */
class EventFeedbackApplicationService(private val eventRepository: IEventRepository, private val clubRepository: IClubRepository) {
    /**
     * Method for register event use case
     * @param issuerId ID of volunteer issuing register use case
     * @param eventId ID of the event to register to
     * @param clubId ID of the club the event is part of
     * @param taskId ID of the task to add user to
     * @throws NoSuchElementException if event or club is not found
     */
    @Throws(NoSuchElementException::class)
    fun registerForTask(
        issuerId: Int,
        eventId: Int,
        clubId: Int,
        taskId: Int,
    ) {
        // get event with event id
        val event = eventRepository.findById(eventId) ?: throw NoSuchElementException("Event with ID $eventId not found.")

        // get volunteer instance with issuer id
        val club = clubRepository.findById(clubId) ?: throw NoSuchElementException("Club with ID $clubId not found.")
        val volunteer = club.getMembers().first { m -> m.id == issuerId }

        // register volunteer to event
        // TODO: maybe throw exception if task not found?
        event.addVolunteerToTask(taskId, volunteer)

        // save event
        eventRepository.saveEvent(event)
    }

    /**
     * Method for deregistering from event
     * @param issuerId ID of volunteer issuing the use case
     * @param eventId ID of event to deregister from
     */
    fun deregisterFromTask(
        issuerId: Int,
        eventId: Int,
        taskId: Int,
    ) {
        // get event with event id
        val event = eventRepository.findById(eventId) ?: throw NoSuchElementException("Event with ID $eventId not found.")

        // remove volunteer with id from event
        // TODO: maybe throw exception if task not found or issuer is not registered?
        event.removeVolunteerFromTask(taskId, issuerId)

        // save event
        eventRepository.saveEvent(event)
    }
}
