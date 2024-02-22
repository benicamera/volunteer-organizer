package de.volunteerorganizer.events

/**
 * Class representing a task at an Event
 * @param name: name of task
 * @param timeFrame: time frame in which the task is scheduled
 * @param featureRequirements: requirements that need to be met in order to execute task
 */
class EventTask(
    override val id: Int,
    val name: String,
    override val timeFrame: EventTimeFrame,
    private val featureRequirements: Set<FeatureRequirement>,
) : IEventTask {
    private val volunteers = mutableSetOf<EventVolunteer>()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true // is the very same instance
        if (other == null || javaClass != other.javaClass) return false // is null or not of same class
        val otherTask: EventTask = other as EventTask // handle other as type of same class
        return id == otherTask.id // ids match
    }

    /**
     * Adds a volunteer to the list of volunteers
     * @param newVolunteer: volunteer to add to task
     */
    override fun addVolunteer(newVolunteer: EventVolunteer) {
        // maybe add feature requirements checking
        volunteers.add(newVolunteer)
    }

    /**
     * Removes volunteer from task
     * @param id: ID of volunteer to remove
     */
    override fun removeVolunteer(id: Int) {
        volunteers.removeIf { v: EventVolunteer -> v.id == id }
    }

    /**
     * Checks if task meets its requirements
     * @return `true` if volunteers and feature requirements match
     */
    override fun meetsRequirements(): Boolean {
        var doesMeet = true
        featureRequirements.forEach { req -> doesMeet = doesMeet && req.meets(volunteers) }
        return doesMeet
    }

    /**
     * Get an immutable set of volunteers
     * @return immutable set of volunteers
     */
    override fun getVolunteers(): Set<EventVolunteer> = volunteers.toSet()

    override fun hashCode(): Int {
        return id
    }
}
