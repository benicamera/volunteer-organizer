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
    private var task = EventTask("", EventTimeFrame(Instant.now(), Instant.now()), listOf(featureRequirement1))

    override fun setUp() {
        super.setUp()
        task = EventTask("task", EventTimeFrame(Instant.now(), Instant.now()), listOf(featureRequirement1))
    }

    fun testAddVolunteer() {
        task.addVolunteer(volunteer1)

        val volunteers = task.getVolunteers()

        assertEquals(setOf(volunteer1), volunteers)
    }

    fun testRemoveVolunteer() {
        task.addVolunteer(volunteer1)

        task.removeVolunteer(volunteer1.id)
        val volunteers = task.getVolunteers()

        assertEquals(0, volunteers.size)
    }

    fun testMeetsRequirementsTrue() {
        task.addVolunteer(volunteer1)
        val shouldBeTrue = task.meetsRequirements()

        assertTrue(shouldBeTrue)
    }

    fun testMeetsRequirementsFalse() {
        task.addVolunteer(volunteer1)
        task.addVolunteer(volunteer2)

        val shouldBeFalse = task.meetsRequirements()
        assertFalse(shouldBeFalse)
    }
}
