package de.volunteerorganizer.domain.event

interface ITaskRepository {

    /**
     * Saves given task to persistence layer.
     * @param task task to be saved
     */
    fun save(task: IEventTask)

    /**
     * Finds task by ID.
     * @param id ID to search for
     * @returns task found (null if none found)
     */
    fun findById(id: Int): IEventTask?

    /**
     * Finds tasks a volunteer is registered.
     * @param volunteerId ID of volunteer to find
     * @returns Set of found tasks (empty if none found)
     */
    fun findByVolunteer(volunteerId: Int): Set<IEventTask>

    /**
     * Deletes the task.
     * @param id ID of task to delete
     */
    fun deleteTask(id: Int)

    /**
     * Generates a new ID for a new task.
     * @returns new ID
     */
    fun generateNewTaskId(): Int
}