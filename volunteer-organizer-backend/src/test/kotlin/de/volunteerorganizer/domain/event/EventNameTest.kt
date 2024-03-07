package de.volunteerorganizer.domain.event

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
