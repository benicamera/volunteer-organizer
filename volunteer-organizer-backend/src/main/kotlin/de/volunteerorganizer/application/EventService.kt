package de.volunteerorganizer.application

import de.volunteerorganizer.domain.event.*
import de.volunteerorganizer.domain.event.location.EventLocation

/**
 * Service covering event creation, event edit and event listing use cases.
 * @param eventRepository repository for events to be used
 */
class EventService (private val eventRepository: IEventRepository) {

    /**
     * Method for creating event use case
     * @param issuerId ID of volunteer issuing event creation
     * @param eventName name of event to be created
     * @param timeFrame time frame when the event takes place
     * @param location location where the event takes place
     */
    fun createEvent(issuerId: Int, eventName: EventName, timeFrame: EventTimeFrame, location: EventLocation){
        // check if volunteer with issuer id is allowed to create event

        // create event instance

        // save event instance
    }

    /**
     * Delete event from repository
     * @param issuerId ID of volunteer issuing event deletion
     * @param eventId ID of event to be deleted
     */
    fun deleteEvent(issuerId: Int, eventId: Int){
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
    fun addNewTaskToEvent(issuerId: Int, eventId: Int, taskName: String, timeFrame: EventTimeFrame, featureRequirements: Set<FeatureRequirement>){
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
    fun removeTaskFromEvent(issuerId: Int, eventId: Int, taskId: Int){
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
    fun editEvent(issuerId: Int, eventId: Int, eventEdit: EventEdit){
        // check if volunteer with issuerId is allowed to edit the event

        // get event instance

        // edit event

        // save event to repository
    }

    /**
     * Method for event listing use case
     * @param issuerId ID of volunteer issuing the use case
     * @returns set of event for user
     */
    fun getAllEvents(issuerId: Int): Set<Event>{
        // get all events from repository for issuerId
        // return them
        return setOf<Event>()
    }
}