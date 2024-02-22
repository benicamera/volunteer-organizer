package de.volunteerorganizer.events

import de.volunteerorganizer.events.location.EventLocation

class Event(val id: Int, val name: EventName, val location: EventLocation, val timeFrame: EventTimeFrame) {
    private val tasks = mutableSetOf<IEventTask>()
    // TODO: private id setter

    /**
     * Adds volunteer to task
     * @param taskId: ID of task to add the volunteer to
     * @param volunteer: volunteer to add to the task
     */
    fun addVolunteerToTask(
        taskId: Int,
        volunteer: EventVolunteer,
    ) {
        tasks.find { t -> t.id == taskId }?.addVolunteer(volunteer)
    }

    /**
     * Removes a volunteer from a task
     * @param taskId: ID of task to remove the volunteer from
     * @param volunteerId: ID of volunteer to remove
     */
    fun removeVolunteerFromTask(
        taskId: Int,
        volunteerId: Int,
    ) {
        tasks.find { t -> t.id == taskId }?.removeVolunteer(volunteerId)
    }

    /**
     * @return immutable set of Event tasks
     */
    fun getTasks(): Set<IEventTask> = tasks.toSet()

    /**
     * Adds task to event
     * @param newTask: task to add
     */
    fun addTask(newTask: IEventTask) {
        tasks.add(newTask)
    }

    /**
     * Removes task from event
     * @param taskId: ID of task to remove
     */
    fun removeTask(taskId: Int) {
        tasks.removeIf { t -> t.id == taskId }
    }

    /**
     * Gets the tightest timeframe considering the tasks
     * @return best time frame
     */
    fun getBestTimeFrame(): EventTimeFrame {
        val minFrame =
            tasks.minBy { t ->
                t.timeFrame.startTime
            }
        val maxFrame = tasks.maxBy { t -> t.timeFrame.endTime }

        return EventTimeFrame(minFrame.timeFrame.startTime, maxFrame.timeFrame.endTime)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}
