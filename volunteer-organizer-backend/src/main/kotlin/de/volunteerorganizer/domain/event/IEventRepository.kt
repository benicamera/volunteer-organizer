package de.volunteerorganizer.domain.event

import de.volunteerorganizer.domain.event.location.EventLocation
import kotlin.jvm.Throws

/**
 * Repository for the Event class
 * @see de.volunteerorganizer.domain.event.Event
 */
interface IEventRepository {
    /**
     * Saves an event to persistence layer
     * @param event event to be saved
     */
    fun saveEvent(event: Event)

    /**
     * Deletes an Event
     * @param id ID of event to be deleted
     * @throws IndexOutOfBoundsException if no event with the given ID found
     */
    @Throws(IndexOutOfBoundsException::class)
    fun deleteEventById(id: Int)

    /**
     * Generates the next event id from persistence layer
     * @returns the ID of the next event to be generated
     */
    fun generateNewEventId(): Int

    /**
     * Searches for an event using its ID.
     * @param id ID of event to be searched
     * @returns found ID (null if not found)
     */
    fun findById(id: Int): Event?

    /**
     * Searches for event at specific location.
     * @param location location where searched event are taking place
     * @returns A set of event at this location (empty set if none found)
     */
    fun findByLocation(location: EventLocation): Set<Event>

    /**
     * Searches for event that take place within the given timeframe.
     * An event is chosen if at least one task starts and ends (inclusive) inside the specified time frame
     * @param timeFrame The time frame where tasks must lie inside of
     * @returns A set of all qualifying event (empty set if none found)
     */
    fun findByTimeFrame(timeFrame: EventTimeFrame): Set<Event>

    /**
     * Searches for event for which a specified volunteer volunteers.
     * @param volunteerId ID of volunteer to search for
     * @returns A set of qualifying event (empty set if none found)
     */
    fun findByVolunteer(volunteerId: Int): Set<Event>
}
