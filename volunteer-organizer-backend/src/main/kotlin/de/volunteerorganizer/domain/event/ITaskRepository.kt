package de.volunteerorganizer.domain.event

interface ITaskRepository {
    /**
     * Generates a new ID for a new task.
     * @returns new ID
     */
    fun generateNewTaskId(): Int
}
