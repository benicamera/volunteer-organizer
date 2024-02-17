package de.volunteerorganizer.events

import junit.framework.TestCase
import java.time.Instant
import java.time.Period

class EventTimeFrameTest : TestCase() {
    fun testInit() {
        var testGood: Boolean
        val before = Instant.now()
        val after = before + Period.ofDays(1)

        try {
            EventTimeFrame(after, before)
            testGood = false
        } catch (err: IllegalArgumentException) {
            testGood = true
        }

        try {
            EventTimeFrame(before, after)
            testGood = testGood && true
        } catch (err: IllegalArgumentException) {
            testGood = false
        }

        if (!testGood) {
            fail()
        }
    }
}
