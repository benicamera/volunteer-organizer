package de.volunteerorganizer.domain.event.location

/**
 * Data class holding a location that can be used for Events
 */
data class EventLocation(val name: String, val address: IEventAddress)
