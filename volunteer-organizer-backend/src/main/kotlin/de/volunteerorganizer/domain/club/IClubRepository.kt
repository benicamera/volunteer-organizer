package de.volunteerorganizer.domain.club

import de.volunteerorganizer.domain.volunteer.Volunteer

interface IClubRepository {

    /**
     * Saves club to data layer
     * @param club Club to be saved
     */
    fun save(club: Club)

    /**
     * Removes club with given ID
     * @param id ID of club to be deleted
     */
    fun removeById(id: Int)

    /**
     * Can be used to find a club using its ID
     * @param id ID of club to be found
     * @returns Club instance with ID (null if club with ID not found)
     */
    fun findById(id: Int): Club?

    /**
     * Search all clubs the given volunteer is a member in.
     * @param member member instance to find clubs for
     * @returns A set of clubs the volunteer is a member of. Empty if none found
     */
    fun findByMember(member: Volunteer): Set<Club>

    /**
     * Search for all clubs matching the given information
     * @param info club information to search for
     * @returns A set of all clubs that have the given information. Empty if none found.
     */
    fun findByInfo(info: ClubInfo): Set<Club>

    /**
     * Search for all clubs having an event with this ID
     * @param id ID of event
     * @returns A set of all clubs having an event with this ID. Empty if none found.
     */
    fun findByEventId(id: Int): Set<Club>

    /**
     * Search for all clubs a given volunteer has organizer rank.
     * @param organizer volunteer to search for
     * @returns A set of all clubs the given volunteer is an organizer of. Empty if none found.
     */
    fun findByOrganizer(organizer: Volunteer): Set<Club>
}
