package de.volunteerorganizer.application.events

import de.volunteerorganizer.domain.club.IClubRepository
import de.volunteerorganizer.domain.event.Event
import de.volunteerorganizer.domain.event.EventTask
import de.volunteerorganizer.domain.event.EventTimeFrame
import de.volunteerorganizer.domain.event.FeatureRequirement
import de.volunteerorganizer.domain.event.IEventRepository
import de.volunteerorganizer.domain.event.ITaskRepository
import kotlin.jvm.Throws

/**
 * Service covering event creation, event edit and event listing use cases.
 * TODO: refine to not make it dependant on domain?
 * TODO: maybe get clubIds from event?
 * @param eventRepository repository for events to be used
 */
class EventApplicationService(
    private val eventRepository: IEventRepository,
    private val clubRepository: IClubRepository,
    private val taskRepository: ITaskRepository,
) {
    /**
     * Method for creating event use case.
     * @param issuerId ID of volunteer issuing event creation
     * @param clubId ID of club to perform the action on
     * @param eventInformation Information for event creation. No member of instance can be null
     * @throws IllegalArgumentException if club is not found or eventInformation field is null
     * @throws IllegalAccessException if issuer is no organizer of club
     */
    @Throws(IllegalArgumentException::class, IllegalAccessException::class)
    fun createEvent(
        issuerId: Int,
        clubId: Int,
        eventInformation: EventEdit,
    ) {
        // check if volunteer with issuer id is allowed to create event
        checkOrganizerPermission(issuerId, clubId)

        // create event instance
        val errorMessage = "No event information can be null."
        val newEventName = eventInformation.name ?: throw IllegalArgumentException(errorMessage)
        val newEventLocation = eventInformation.location ?: throw IllegalArgumentException(errorMessage)
        val newEventTimeFrame = eventInformation.timeFrame ?: throw IllegalArgumentException(errorMessage)
        val newEventId = eventRepository.generateNewEventId()

        val newEvent = Event(newEventId, newEventName, newEventLocation, newEventTimeFrame)

        // save event instance
        val club = clubRepository.findById(clubId) ?: throw IllegalArgumentException("No club with ID $clubId found.")
        club.addEvent(newEvent)
        eventRepository.saveEvent(newEvent)
        clubRepository.save(club)
    }

    /**
     * Delete event from repository.
     * @param issuerId ID of volunteer issuing event deletion
     * @param clubId ID of the club to perform the action on
     * @param eventId ID of event to be deleted
     * @throws IllegalAccessException if issuer is not allowed to perform action
     * @throws IllegalArgumentException if club not found
     */
    @Throws(IllegalArgumentException::class, IllegalAccessException::class)
    fun deleteEvent(
        issuerId: Int,
        clubId: Int,
        eventId: Int,
    ) {
        // check if volunteer with issuer id is allowed to delete event
        checkOrganizerPermission(issuerId, clubId)

        // delete event from repository
        val club = clubRepository.findById(clubId) ?: throw IllegalArgumentException("Club with ID $clubId not found.")
        club.removeEvent(eventId)
        clubRepository.save(club)
        eventRepository.deleteEventById(eventId)
    }

    /**
     * Method for adding task to event use case.
     * @param issuerId ID of volunteer issuing the use case
     * @param clubId ID of the club to perform action on
     * @param eventId ID of event to which the task is added
     * @param taskName name of the task to be created
     * @param timeFrame time frame of the task
     * @param featureRequirements requirements for the task
     * @throws IllegalArgumentException if club or event not found
     * @throws IllegalAccessException if issuer is not allowed to perform action
     */
    @Throws(IllegalArgumentException::class, IllegalAccessException::class)
    fun addNewTaskToEvent(
        issuerId: Int,
        clubId: Int,
        eventId: Int,
        taskName: String,
        timeFrame: EventTimeFrame,
        featureRequirements: Set<FeatureRequirement>,
    ) {
        // check if volunteer with issuer id is allowed to add task to event
        checkOrganizerPermission(issuerId, clubId)

        // create task
        val newTaskId = taskRepository.generateNewTaskId()
        val newTask = EventTask(newTaskId, taskName, timeFrame, featureRequirements)

        // add task to event
        val event = eventRepository.findById(eventId) ?: throw IllegalArgumentException("Event with ID $eventId not found.")
        event.addTask(newTask)

        // save event
        taskRepository.save(newTask)
        eventRepository.saveEvent(event)
    }

    /**
     * Method for removing tasks from event.
     * @param issuerId ID of volunteer issuing the removing of the task
     * @param clubId ID of club to perform action in
     * @param eventId ID of event from which task is removed
     * @param taskId ID of task to be removed
     * @throws IllegalAccessException if issuer is not entitled to perform action
     * @throws IllegalArgumentException if club or event not found
     */
    @Throws(IllegalArgumentException::class, IllegalAccessException::class)
    fun removeTaskFromEvent(
        issuerId: Int,
        clubId: Int,
        eventId: Int,
        taskId: Int,
    ) {
        // check if volunteer with issuer id is allowed to remove task from event
        checkOrganizerPermission(issuerId, clubId)

        // get event instance
        val event = eventRepository.findById(eventId) ?: throw IllegalArgumentException("Could not find event with ID $eventId.")

        // remove task with taskId from event
        event.removeTask(taskId)

        // save event to repository
        eventRepository.saveEvent(event)
    }

    /**
     * Method for edit event use case.
     * Null fields of eventEdit are not changed.
     * @param issuerId ID of volunteer issuing the edit use case
     * @param clubId ID of club to perform action in
     * @param eventId ID of event to be edited
     * @param eventEdit edits to be made
     * @throws IllegalAccessException if issuer is not allowed to perform action
     * @throws IllegalArgumentException if club or event is not found
     */
    @Throws(IllegalArgumentException::class, IllegalAccessException::class)
    fun editEvent(
        issuerId: Int,
        clubId: Int,
        eventId: Int,
        eventEdit: EventEdit,
    ) {
        // check if volunteer with issuerId is allowed to edit the event
        checkOrganizerPermission(issuerId, clubId)

        // get event instance
        val oldEvent = eventRepository.findById(eventId) ?: throw IllegalArgumentException("Could not find event with ID $eventId")

        // edit event
        val newName = eventEdit.name ?: oldEvent.name
        val newLocation = eventEdit.location ?: oldEvent.location
        val newTimeFrame = eventEdit.timeFrame ?: oldEvent.timeFrame
        val newEvent = Event(oldEvent.id, newName, newLocation, newTimeFrame)

        // save event to repository
        eventRepository.saveEvent(newEvent)
    }

    /**
     * Method for event listing use case
     * @param issuerId ID of volunteer issuing the use case
     * @returns set of event for user TODO: maybe refine output to make it not dependant on domain?
     */
    fun getAllEvents(issuerId: Int): Set<Event> = eventRepository.findByVolunteer(issuerId)

    /**
     * Checks if given issuer is organizer in the given club.
     * Throws errors if check failed
     * @param issuerId ID of volunteer to check
     * @param clubId ID of club to check
     * @throws IllegalArgumentException if club could not be found
     * @throws IllegalAccessException if issuer is not organizer in the club
     */
    @Throws(IllegalArgumentException::class, IllegalAccessException::class)
    fun checkOrganizerPermission(
        issuerId: Int,
        clubId: Int,
    ) {
        // TODO: refine exceptions
        val club = clubRepository.findById(clubId) ?: throw IllegalArgumentException("Club with ID $clubId not found.")
        if (!club.isOrganizer(issuerId)) {
            throw IllegalAccessException("Volunteer with ID $issuerId is not an organizer.")
        }
    }
}
