package de.volunteerorganizer.events

import junit.framework.TestCase

class FeatureTypeTest : TestCase() {
    private val testName1 = "VeryCoolName"
    private val testName2 = "VeryUncoolName"

    fun testInit() {
        try {
            FeatureType("", 1)
        } catch (err: IllegalArgumentException) {
            return
        }
        fail()
    }

    fun testTestGetName() {
        val type = FeatureType(testName1, 10)
        assertEquals(type.name, testName1)
    }

    fun testGetId() {
        val type = FeatureType(testName1, 10)
        assertEquals(type.id, 10)
    }

    fun testCopy() {
        val type1 = FeatureType(testName1, 10)
        val typeCopy = type1.copy()
        assertEquals(type1.id, typeCopy.id)
        assertEquals(type1.name, typeCopy.name)
    }

    fun testTestEquals() {
        val type1 = FeatureType(testName1, 10)
        val type2 = FeatureType(testName1, 10)

        val type3 = FeatureType(testName1, 11)
        val type4 = FeatureType(testName2, 10)

        assertTrue(type1 == type2)
        assertFalse(type1 == type3)
        assertFalse(type1 == type4)
    }
}
