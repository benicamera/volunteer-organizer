package de.volunteerorganizer.domain.events

import de.volunteerorganizer.domain.volunteer.VolunteerFeature
import de.volunteerorganizer.domain.volunteer.VolunteerName
import de.volunteerorganizer.utils.ValidIntRange
import junit.framework.TestCase
import java.time.Instant

class EventTaskTest : TestCase() {
    private val feature1 = VolunteerFeature("feature1")
    private val featureRequirement1 = FeatureRequirement(VolunteerFeature("feature1"), ValidIntRange(1, 1))
    private val volunteer1 = EventVolunteer(1, VolunteerName("a", "a"), setOf(feature1))
    private val volunteer2 = EventVolunteer(2, VolunteerName("b", "b"), setOf(feature1))
    private var task = EventTask(0, "", EventTimeFrame(Instant.now(), Instant.now()), setOf(featureRequirement1))

    override fun setUp() {
        super.setUp()
        task = EventTask(1, "task", EventTimeFrame(Instant.now(), Instant.now()), setOf(featureRequirement1))
    }

    fun testEqualsTrue() {
        val task2 = EventTask(1, "task2", EventTimeFrame(Instant.now(), Instant.now()), setOf(featureRequirement1))
        val shouldBeTrue = (task == task2)
        assertTrue(shouldBeTrue)
    }

    fun testEqualsFalse() {
        val task2 = EventTask(2, "task", EventTimeFrame(Instant.now(), Instant.now()), setOf(featureRequirement1))
        val shouldBeFalse = (task == task2)
        assertFalse(shouldBeFalse)
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
