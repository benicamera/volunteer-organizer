package de.volunteerorganizer.events

import de.volunteerorganizer.domain.events.EventName
import junit.framework.TestCase

class EventNameTest : TestCase() {
    fun testInit() {
        try {
            EventName("")
        } catch (err: IllegalArgumentException) {
            return
        }
        fail()
    }
}
