package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerId

/**
 * Class representing a task at an Event
 * @param name: name of task
 * @param timeFrame: time frame in which the task is scheduled
 * @param featureRequirements: requirements that need to be met in order to execute task
 */
class EventTask(val name: String, private val timeFrame: EventTimeFrame, private val featureRequirements: List<FeatureRequirement>) {
    private val volunteers = mutableSetOf<EventVolunteer>()

    /**
     * Adds a volunteer to the list of volunteers
     * @param newVolunteer: volunteer to add to task
     */
    fun addVolunteer(newVolunteer: EventVolunteer) {
        // maybe add feature requirements checking
        volunteers.add(newVolunteer)
    }

    /**
     * Removes volunteer from task
     * @param id: ID of volunteer to remove
     */
    fun removeVolunteer(id: VolunteerId) {
        volunteers.removeIf { v: EventVolunteer -> v.id == id }
    }

    /**
     * Checks if task meets its requirements
     * @return `true` if volunteers and feature requirements match
     */
    fun meetsRequirements(): Boolean {
        var doesMeet = true
        featureRequirements.forEach { req -> doesMeet = doesMeet && req.meets(volunteers) }
        return doesMeet
    }

    /**
     * Get an immutable set of volunteers
     * @return immutable set of volunteers
     */
    fun getVolunteers(): Set<EventVolunteer> = volunteers.toSet()
}
