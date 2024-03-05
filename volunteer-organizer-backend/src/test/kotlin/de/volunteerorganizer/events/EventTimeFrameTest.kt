package de.volunteerorganizer.events

import de.volunteerorganizer.domain.events.EventTimeFrame
import junit.framework.TestCase
import java.time.Instant
import java.time.Period

class EventTimeFrameTest : TestCase() {
    fun testInitError() {
        val before = Instant.now()
        val after = before + Period.ofDays(1)

        try {
            EventTimeFrame(after, before)
        } catch (err: IllegalArgumentException) {
            return
        }
        fail()
    }
}
