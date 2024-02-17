package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature

/**
 * Class representing a feature requirement of a task
 * @param feature: the feature covered by this requirement
 * @param validAmounts: the valid numbers of volunteers having this feature
 */
class FeatureRequirement(private val feature: VolunteerFeature, private val validAmounts: IValidValues<Int>) {
    /**
     * Checks if given List of volunteers meets requirement
     * @param volunteers: set of volunteers
     * @return: `true` if the list of volunteers meets the requirements
     */
    fun meets(volunteers: Set<EventVolunteer>): Boolean {
        var num = 0
        volunteers.forEach { vol -> if (vol.hasFeature(feature)) num++ }
        return validAmounts.isValid(num)
    }
}
