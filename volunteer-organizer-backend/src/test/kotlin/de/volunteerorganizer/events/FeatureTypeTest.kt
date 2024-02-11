package de.volunteerorganizer.events

import junit.framework.TestCase

class FeatureTypeTest : TestCase() {
    fun testTestGetName() {
        val type = FeatureType("VeryCoolName", 10)
        assertEquals(type.name, "VeryCoolName")
    }

    fun testGetId() {
        val type = FeatureType("VeryCoolName", 10)
        assertEquals(type.id, 10)
    }

    fun testCopy() {
        val type1 = FeatureType("VeryCoolName", 10)
        val typeCopy = type1.copy()
        assertEquals(type1.id, typeCopy.id)
        assertEquals(type1.name, typeCopy.name)
    }

    fun testTestEquals() {
        val type1 = FeatureType("VeryCoolName", 10)
        val type2 = FeatureType("VeryCoolName", 10)

        val type3 = FeatureType("VeryCoolName", 11)
        val type4 = FeatureType("VeryUncoolName", 10)

        assertTrue(type1 == type2)
        assertFalse(type1 == type3)
        assertFalse(type1 == type4)
    }
}
