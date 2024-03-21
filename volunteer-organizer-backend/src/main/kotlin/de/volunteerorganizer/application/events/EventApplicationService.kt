package de.volunteerorganizer.application.events

import de.volunteerorganizer.domain.club.IClubRepository
import de.volunteerorganizer.domain.event.*

/**
 * Service covering event creation, event edit and event listing use cases.
 * TODO: refine to not make it dependant on domain?
 * @param eventRepository repository for events to be used
 * @param clubRepository repository for clubs to be used
 */
class EventApplicationService(private val eventRepository: IEventRepository, private val clubRepository: IClubRepository) {
    /**
     * Method for creating event use case
     * @param issuerId ID of volunteer issuing event creation
     * @param eventInformation Information for event creation. No member of instance can be null
     */
    fun createEvent(
        issuerId: Int,
        clubId: Int,
        eventInformation: EventEdit,
    ) {
        // check if volunteer with issuer id is allowed to create event
        val club = clubRepository.findById(clubId) ?: throw IllegalArgumentException("club with id not found")

        if (!club.isOrganizer(issuerId))
            {
                throw IllegalArgumentException("issuer is not allowed") // TODO: refine error
            }

        // create event instance
        val eventName = eventInformation.name ?: throw IllegalArgumentException("event information must not contain null fields")
        val eventTimeFrame = eventInformation.timeFrame ?: throw IllegalArgumentException("event information must not contain null fields")
        val eventLocation = eventInformation.location ?: throw IllegalArgumentException("event information must not contain null fields")
        val eventId = eventRepository.generateNewEventId()

        val event = Event(eventId, eventName, eventLocation, eventTimeFrame)

        // save event instance
        eventRepository.saveEvent(event)
    }

    /**
     * Delete event from repository
     * @param issuerId ID of volunteer issuing event deletion
     * @param eventId ID of event to be deleted
     */
    fun deleteEvent(
        issuerId: Int,
        eventId: Int,
    ) {
        // check if volunteer with issuer id is allowed to delete event

        // delete event from repository
    }

    /**
     * Method for adding task to event use case
     * @param issuerId ID of volunteer issuing the use case
     * @param eventId ID of event to which the task is added
     * @param taskName name of the task to be created
     * @param timeFrame time frame of the task
     * @param featureRequirements requirements for the task
     */
    fun addNewTaskToEvent(
        issuerId: Int,
        eventId: Int,
        taskName: String,
        timeFrame: EventTimeFrame,
        featureRequirements: Set<FeatureRequirement>,
    ) {
        // check if volunteer with issuer id is allowed to add task to event

        // create task

        // add task to event

        // save event
    }

    /**
     * Method for removing tasks from event.
     * @param issuerId ID of volunteer issuing the removing of the task
     * @param eventId ID of event from which task is removed
     * @param taskId ID of task to be removed
     */
    fun removeTaskFromEvent(
        issuerId: Int,
        eventId: Int,
        taskId: Int,
    ) {
        // check if volunteer with issuer id is allowed to remove task from event

        // get event instance

        // remove task with taskId from event

        // save event to repository
    }

    /**
     * Method for edit event use case.
     * Null fields of eventEdit are not changed.
     * @param issuerId ID of volunteer issuing the edit use case
     * @param eventId ID of event to be edited
     * @param eventEdit edits to be made
     */
    fun editEvent(
        issuerId: Int,
        eventId: Int,
        eventEdit: EventEdit,
    ) {
        // check if volunteer with issuerId is allowed to edit the event

        // get event instance

        // edit event

        // save event to repository
    }

    /**
     * Method for event listing use case
     * @param issuerId ID of volunteer issuing the use case
     * @returns set of event for user TODO: maybe refine output to make it not dependant on domain?
     */
    fun getAllEvents(issuerId: Int): Set<Event> {
        // get all events from repository for issuerId
        // return them
        return setOf<Event>()
    }
}
