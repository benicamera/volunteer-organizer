package de.volunteerorganizer.domain.volunteer

/**
 * Name of Volunteer
 * @param firstName: first name(s) of volunteer as string
 * @param lastName: last name(s) of volunteer as string
 */
data class VolunteerName(val firstName: String, val lastName: String) {
    /**
     * @return name of volunteer in shape "firstname lastname"
     */
    override fun toString(): String = "$firstName $lastName"
}
