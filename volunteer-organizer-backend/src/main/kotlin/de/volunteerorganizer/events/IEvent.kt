package de.volunteerorganizer.events

import java.util.Date

/**
 * Interface for Events that need volunteers
 */
interface IEvent {
    /**
     * Changes name of Event
     * @param newName: new EventName of Event
     * TODO: maybe change to setter
     */
    fun changeName(newName: EventName)

    /**
     * Changes date of Event
     * @param: newDate: new Date of Event
     * TODO: maybe change to setter
     */
    fun changeDate(newDate: Date)

    /**
     * Changes location of Event
     * @param newLocation: new EventLocation of Event
     * TODO: maybe change to setter
     */
    fun changeLocation(newLocation: EventLocation)

    /**
     * Changes one rule of the Event.
     * @param newRule: new EventRule to change
     */
    fun changeRule(newRule: EventRule)

    /**
     * Adds new volunteer to Event.
     * @param newVolunteer: new EventVolunteer to add
     */
    fun addVolunteer(newVolunteer: EventVolunteer)

    /**
     * Removes volunteer from Event.
     * @param volunteer: EventVolunteer
     */
    fun removeVolunteer(volunteer: EventVolunteer)
}