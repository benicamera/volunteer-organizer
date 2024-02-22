package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerName

/**
 * Data class for a Volunteer with event participation
 * @param id: Volunteer ID
 * @param name: name of Volunteer
 * @param features: list of features the volunteer has
 */
class EventVolunteer(val id: Int, val name: VolunteerName, val features: Set<VolunteerFeature>) {
    /**
     * Checks if volunteer has specific feature.
     * @param feature: feature to be checked
     * @return `true` if volunteer has feature
     */
    fun hasFeature(feature: VolunteerFeature): Boolean = features.contains(feature)
}
