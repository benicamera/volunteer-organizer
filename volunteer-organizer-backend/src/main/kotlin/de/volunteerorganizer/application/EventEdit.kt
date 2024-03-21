package de.volunteerorganizer.application

import de.volunteerorganizer.domain.event.EventName
import de.volunteerorganizer.domain.event.EventTimeFrame
import de.volunteerorganizer.domain.event.location.EventLocation

data class EventEdit(val name: EventName?, val location: EventLocation?, val timeFrame: EventTimeFrame?)