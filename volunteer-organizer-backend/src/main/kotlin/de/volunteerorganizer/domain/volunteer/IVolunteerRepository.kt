package de.volunteerorganizer.domain.volunteer

import kotlin.jvm.Throws

/**
 * Repository for volunteers
 * @see de.volunteerorganizer.domain.volunteer.Volunteer
 */
interface IVolunteerRepository {
    /**
     * Saves a volunteer to the storage
     * @param volunteer volunteer to be saved
     */
    fun saveVolunteer(volunteer: Volunteer)

    /**
     * Deletes a volunteer
     * @param id ID of the volunteer to be deleted
     * @throws IndexOutOfBoundsException if no volunteer with the ID is found.
     */
    @Throws(IndexOutOfBoundsException::class)
    fun deleteVolunteerById(id: Int)

    /**
     * Generates the next volunteer id from the storage
     * @returns the ID of the next volunteer
     */
    fun generateNewVolunteerId(): Int

    /**
     * Searches for a volunteer using its ID.
     * @param id ID of volunteer to be searched
     * @return found volunteer instance (null if not found)
     */
    fun findById(id: Int): Volunteer

    /**
     * Searches for volunteers using its name.
     * @param name name of volunteers to be searched
     * @returns a set of qualifying volunteers
     */
    fun findByName(name: VolunteerName): Set<Volunteer>

    /**
     * Searches for volunteers having a given feature.
     * @param featureVolunteerFeature feature of volunteer
     * @returns a set of qualifying volunteers having this feature
     */
    fun findByFeature(featureVolunteerFeature: VolunteerFeature): Set<Volunteer>
}
