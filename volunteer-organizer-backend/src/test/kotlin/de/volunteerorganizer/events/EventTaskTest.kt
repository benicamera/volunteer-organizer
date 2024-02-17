package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerId
import de.volunteerorganizer.volunteer.VolunteerName
import junit.framework.TestCase
import java.time.Instant

class EventTaskTest : TestCase() {
    private val feature1 = VolunteerFeature("feature1")
    private val featureRequirement1 = FeatureRequirement(VolunteerFeature("feature1"), ValidIntRange(1, 1))
    private val volunteer1 = EventVolunteer(VolunteerId(1), VolunteerName("a", "a"), listOf(feature1))
    private val volunteer2 = EventVolunteer(VolunteerId(2), VolunteerName("b", "b"), listOf(feature1))

    fun testAddVolunteer() {
        // make sure task is fresh and has no one in it
        val task = EventTask("task2", EventTimeFrame(Instant.now(), Instant.now()), listOf(featureRequirement1))

        task.addVolunteer(volunteer1)

        val volunteers = task.getVolunteers()

        assertEquals(setOf(volunteer1), volunteers)
    }

    fun testRemoveVolunteer() {
        // make sure task is fresh and has no one in it
        val task = EventTask("task3", EventTimeFrame(Instant.now(), Instant.now()), listOf(featureRequirement1))
        task.addVolunteer(volunteer1)

        task.removeVolunteer(volunteer1.id)
        val volunteers = task.getVolunteers()

        assertEquals(0, volunteers.size)
    }

    fun testMeetsRequirements() {
        val task = EventTask("task4", EventTimeFrame(Instant.now(), Instant.now()), listOf(featureRequirement1))

        task.addVolunteer(volunteer1)
        val shouldBeTrue = task.meetsRequirements()

        task.addVolunteer(volunteer2)
        val shouldBeFalse = task.meetsRequirements()

        assertTrue(shouldBeTrue)
        assertFalse(shouldBeFalse)
    }
}
