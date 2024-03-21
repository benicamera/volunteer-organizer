package de.volunteerorganizer.application.events

import de.volunteerorganizer.domain.event.EventName
import de.volunteerorganizer.domain.event.EventTimeFrame
import de.volunteerorganizer.domain.event.location.EventLocation

/**
 * @param name new name
 * @param location new location
 * @param timeFrame new timeframe
 */
data class EventEdit(val name: EventName?, val location: EventLocation?, val timeFrame: EventTimeFrame?)
