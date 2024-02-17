package de.volunteerorganizer.events

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
     * @param: newTimeFrame: new time frame of event
     * TODO: maybe change to setter
     */
    fun changeTimeFrame(newTimeFrame: EventTimeFrame)

    /**
     * Changes location of Event
     * @param newLocation: new EventLocation of Event
     * TODO: maybe change to setter
     */
    fun changeLocation(newLocation: EventLocation)

    /**
     * Adds new task to event
     * @param newTask: new event task to add
     */
    fun addTask(newTask: EventTask)

    /**
     * Removes task with given ID
     * @param taskId: id of task to be deleted
     */
    fun removeTask(taskId: Int)
}
