package de.volunteerorganizer.domain.club

import de.volunteerorganizer.domain.event.Event
import de.volunteerorganizer.domain.volunteer.Volunteer
import kotlin.IndexOutOfBoundsException
import kotlin.jvm.Throws

class Club(val id: Int, val info: ClubInfo) {
    private val members = mutableSetOf<Volunteer>()
    private val organizers = mutableSetOf<Volunteer>()
    private val events = mutableSetOf<Event>()

    /**
     * Adds volunteer to set of club members
     * @param newMember the volunteer instance to be added
     */
    fun addMember(newMember: Volunteer) {
        members.add(newMember)
    }

    /**
     * Removes member with given ID from club
     * @param memberId ID of member to be removed
     */
    fun removeMember(memberId: Int) {
        removeOrganizer(memberId)
        // TODO: also remove from tasks?
        members.removeIf { m -> m.id == memberId }
    }

    /**
     * @returns immutable set of the members
     */
    fun getMembers() = setOf(members)

    /**
     * Add organizer (must be member of the club)
     * @param memberId ID of the member to be promoted to organizer
     * @throws IndexOutOfBoundsException if member cannot be found
     */
    @Throws(IndexOutOfBoundsException::class)
    fun addOrganizer(memberId: Int) {
        val newOrganizer = members.find { m -> m.id == memberId }
            ?: throw IndexOutOfBoundsException("Cannot find member with $memberId.")
        organizers.add(newOrganizer)
    }

    /**
     * Demotes volunteer from organizer position
     * @param organizerId ID of organizer to be demoted
     */
    fun removeOrganizer(organizerId: Int) {
        organizers.removeIf { o -> o.id == organizerId }
    }

    /**
     * @return immutable set of organizers
     */
    fun getOrganizers() = setOf(organizers)

    /**
     * Registers club event
     * @param newEvent event to be added to club
     */
    fun addEvent(newEvent: Event) {
        events.add(newEvent)
    }

    /**
     * Removes event from club
     * @param eventId id of event to be removed
     */
    fun removeEvent(eventId: Int) {
        events.removeIf { e -> e.id == eventId }
    }

    /**
     * @returns immutable set of events of this club
     */
    fun getEvents() = setOf(events)
}
