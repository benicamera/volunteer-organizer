package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerName
import junit.framework.TestCase

class EventVolunteerTest : TestCase() {
    private val feature1 = VolunteerFeature("hello")
    private val features1 = setOf(feature1)
    private val name1 = VolunteerName("test1", "test1")
    private val vol1 = EventVolunteer(1, name1, features1)

    fun testHasFeatureTrue() {
        val shouldBeTrue = vol1.hasFeature(feature1)

        assertTrue(shouldBeTrue)
    }

    fun testHasFeatureFalse() {
        val feature2 = VolunteerFeature("bye")
        val shouldBeFalse = vol1.hasFeature(feature2)

        assertFalse(shouldBeFalse)
    }

    fun testGetId() {
        val shouldBeId1 = vol1.id

        assertEquals(1, shouldBeId1)
    }

    fun testTestGetName() {
        val shouldBeName1 = vol1.name

        assertEquals(name1, shouldBeName1)
    }

    fun testGetFeatures() {
        val shouldBeFeatures1 = vol1.features

        assertEquals(features1, shouldBeFeatures1)
    }
}
