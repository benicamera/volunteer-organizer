package de.volunteerorganizer.events

import java.time.Instant

data class EventTimeFrame(val startTime: Instant, val endTime: Instant) {
    init {
        require(startTime <= endTime) {
            "$startTime is after $endTime => negative time frame."
        }
    }
}
