package de.volunteerorganizer.domain.events

import de.volunteerorganizer.domain.volunteer.Volunteer
import de.volunteerorganizer.domain.volunteer.VolunteerFeature
import de.volunteerorganizer.utils.IValidValues

/**
 * Class representing a feature requirement of a task
 * @param feature: the feature covered by this requirement
 * @param validValues: the valid numbers of volunteers having this feature
 */
class FeatureRequirement(private val feature: VolunteerFeature, private val validValues: IValidValues<Int>) {
    /**
     * Checks if given List of volunteers meets requirement
     * @param volunteers: set of volunteers
     * @return: `true` if the list of volunteers meets the requirements
     */
    fun meets(volunteers: Set<Volunteer>): Boolean {
        var num = 0
        volunteers.forEach { vol -> if (vol.hasFeature(feature)) num++ }
        return validValues.isValid(num)
    }
}
