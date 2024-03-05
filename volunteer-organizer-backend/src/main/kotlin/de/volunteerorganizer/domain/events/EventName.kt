package de.volunteerorganizer.domain.events

/**
 * Data class containing an event name
 */
data class EventName(val name: String) {
    init {
        require(name.isNotEmpty()) {
            "Event name must not be empty."
        }
    }
}
