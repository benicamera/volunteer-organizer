package de.volunteerorganizer.events

import junit.framework.TestCase

class ValidIntRangeTest : TestCase() {
    private var upperBound = 10
    private var lowerBound = 0
    private var testRange = ValidIntRange(lowerBound, upperBound)

    fun testIsValidSimple() {
        val shouldBeTrue3 = testRange.isValid((upperBound + lowerBound) / 2)
        assertTrue(shouldBeTrue3)
    }

    fun testIsValidEdge() {
        val shouldBeTrue1 = testRange.isValid(upperBound)
        val shouldBeTrue2 = testRange.isValid(lowerBound)
        assertTrue(shouldBeTrue1)
        assertTrue(shouldBeTrue2)
    }

    fun testIsValidFalse() {
        val shouldBeFalse = testRange.isValid(upperBound * 2)
        assertFalse(shouldBeFalse)
    }

    fun testInitError() {
        try {
            ValidIntRange(upperBound, lowerBound)
        } catch (err: IllegalArgumentException) {
            return
        }
        fail()
    }
}
