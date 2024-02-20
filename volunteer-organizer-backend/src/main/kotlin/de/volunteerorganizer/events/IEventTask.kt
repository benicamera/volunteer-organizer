package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerId

interface IEventTask {
    /**
     * Adds a volunteer to the list of volunteers
     * @param newVolunteer: volunteer to add to task
     */
    fun addVolunteer(newVolunteer: EventVolunteer)

    /**
     * Removes volunteer from task
     * @param id: ID of volunteer to remove
     */
    fun removeVolunteer(id: VolunteerId)

    /**
     * Checks if task meets its requirements
     * @return `true` if volunteers and feature requirements match
     */
    fun meetsRequirements(): Boolean

    /**
     * Get an immutable set of volunteers
     * @return immutable set of volunteers
     */
    fun getVolunteers(): Set<EventVolunteer>
}
