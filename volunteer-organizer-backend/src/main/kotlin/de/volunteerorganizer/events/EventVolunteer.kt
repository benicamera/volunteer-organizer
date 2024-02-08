package de.volunteerorganizer.events

import de.volunteerorganizer.volunteer.VolunteerFeature
import de.volunteerorganizer.volunteer.VolunteerId
import de.volunteerorganizer.volunteer.VolunteerName

/**
 * Data class for a Volunteer with event participation
 * @param id: Volunteer ID
 * @param name: name of Volunteer
 * @param features: list of features the volunteer has
 */
data class EventVolunteer(val id: VolunteerId, val name: VolunteerName, val features: List<VolunteerFeature>)
