package de.volunteerorganizer.domain.event

import de.volunteerorganizer.domain.volunteer.Volunteer

interface IEventTask {
    val timeFrame: EventTimeFrame
    val id: Int
    val name: String

    /**
     * Adds a volunteer to the list of volunteers
     * @param newVolunteer: volunteer to add to task
     */
    fun addVolunteer(newVolunteer: Volunteer)

    /**
     * Removes volunteer from task
     * @param id: ID of volunteer to remove
     */
    fun removeVolunteer(id: Int)

    /**
     * Checks if task meets its requirements
     * @return `true` if volunteers and feature requirements match
     */
    fun meetsRequirements(): Boolean

    /**
     * Get an immutable set of volunteers
     * @return immutable set of volunteers
     */
    fun getVolunteers(): Set<Volunteer>
}
