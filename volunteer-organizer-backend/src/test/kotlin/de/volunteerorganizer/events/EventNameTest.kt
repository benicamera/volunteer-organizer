package de.volunteerorganizer.events

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
