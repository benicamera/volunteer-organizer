package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerId
import de.volunteerorganizer.volunteer.VolunteerName
import junit.framework.TestCase

class FeatureRequirementTest : TestCase() {
    private val testFeature = VolunteerFeature("testFeature")
    private val validValues1 = ValidIntRange(0, 1)

    private val feature1 = VolunteerFeature("feature1")
    private val features1 = listOf(testFeature, feature1)
    private val features2 = listOf(feature1, VolunteerFeature("feature2"))
    private val volunteer1 = EventVolunteer(VolunteerId(1), VolunteerName("a", "a"), features1)
    private val volunteer2 = EventVolunteer(VolunteerId(2), VolunteerName("b", "b"), features2)
    private val volunteers = setOf(volunteer1, volunteer2)

    fun testMeetsTrue() {
        val req1 = FeatureRequirement(testFeature, validValues1)

        val shouldBeTrue = req1.meets(volunteers)

        assertTrue(shouldBeTrue)
    }

    fun testMeetsFalse() {
        val req2 = FeatureRequirement(feature1, validValues1)

        val shouldBeFalse = req2.meets(volunteers)

        assertFalse(shouldBeFalse)
    }
}
