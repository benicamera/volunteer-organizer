package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerId
import de.volunteerorganizer.volunteer.VolunteerName
import junit.framework.TestCase
import kotlin.test.assertContentEquals

class EventVolunteerTest : TestCase() {
    private val feature1 = VolunteerFeature("hello")
    private val feature2 = VolunteerFeature("bye")
    private val features1 = listOf(feature1)
    private val id1 = VolunteerId(1)
    private val name1 = VolunteerName("test1", "test1")
    private val vol1 = EventVolunteer(id1, name1, features1)

    fun testHasFeature() {
        val shouldBeTrue = vol1.hasFeature(feature1)
        val shouldBeFalse = vol1.hasFeature(feature2)

        assertTrue(shouldBeTrue)
        assertFalse(shouldBeFalse)
    }

    fun testGetId() {
        val shouldBeId1 = vol1.id

        assertEquals(id1, shouldBeId1)
    }

    fun testTestGetName() {
        val shouldBeName1 = vol1.name

        assertEquals(name1, shouldBeName1)
    }

    fun testGetFeatures() {
        val shouldBeFeatures1 = vol1.features

        assertContentEquals(features1, shouldBeFeatures1)
    }
}
