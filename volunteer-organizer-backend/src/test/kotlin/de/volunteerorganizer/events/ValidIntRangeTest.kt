package de.volunteerorganizer.events

import junit.framework.TestCase

class ValidIntRangeTest : TestCase() {
    fun testIsValid() {
        val upper = 10
        val lower = 0
        val testRange = ValidIntRange(lower, upper)

        val shouldBeTrue1 = testRange.isValid(lower)
        val shouldBeTrue2 = testRange.isValid(upper)
        val shouldBeTrue3 = testRange.isValid((lower + upper) / 2)
        val shouldBeFalse = testRange.isValid(upper * 2)

        assertTrue(shouldBeTrue1)
        assertTrue(shouldBeTrue2)
        assertTrue(shouldBeTrue3)
        assertFalse(shouldBeFalse)
    }
}
