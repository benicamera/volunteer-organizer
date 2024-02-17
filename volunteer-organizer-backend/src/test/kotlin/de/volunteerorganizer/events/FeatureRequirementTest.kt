package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerId
import de.volunteerorganizer.volunteer.VolunteerName
import junit.framework.TestCase

class FeatureRequirementTest : TestCase() {
    private val testFeature = VolunteerFeature("testFeature")
    private val validValues1 = ValidIntRange(0, 1)
    private val validValues2 = ValidIntRange(2, 20)
    private val req1 = FeatureRequirement(testFeature, validValues1)
    private val feature1 = VolunteerFeature("feature1")
    private val req2 = FeatureRequirement(feature1, validValues1)
    private val req3 = FeatureRequirement(testFeature, validValues2)

    fun testMeets() {
        val features1 = listOf(testFeature, feature1)
        val features2 = listOf(feature1, VolunteerFeature("feature2"))
        val volunteer1 = EventVolunteer(VolunteerId(1), VolunteerName("a", "a"), features1)
        val volunteer2 = EventVolunteer(VolunteerId(2), VolunteerName("b", "b"), features2)
        val volunteers = setOf(volunteer1, volunteer2)

        val shouldBeTrue1 = req1.meets(volunteers)
        val shouldBeTrue2 = req2.meets(volunteers)
        val shouldBeFalse = req3.meets(volunteers)

        assertTrue(shouldBeTrue1)
        assertTrue(shouldBeTrue2)
        assertFalse(shouldBeFalse)
    }
}
