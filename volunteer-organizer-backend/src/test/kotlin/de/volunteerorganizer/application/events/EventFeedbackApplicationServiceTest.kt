package de.volunteerorganizer.application.events

import de.volunteerorganizer.domain.club.Club
import de.volunteerorganizer.domain.club.ClubInfo
import de.volunteerorganizer.domain.club.IClubRepository
import de.volunteerorganizer.domain.event.*
import de.volunteerorganizer.domain.event.location.EventLocation
import de.volunteerorganizer.domain.event.location.VirtualAddress
import de.volunteerorganizer.domain.volunteer.Volunteer
import de.volunteerorganizer.domain.volunteer.VolunteerFeature
import de.volunteerorganizer.domain.volunteer.VolunteerName
import io.ktor.http.*
import junit.framework.TestCase
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.kotlin.KArgumentCaptor
import java.time.Instant
import java.util.*

class EventFeedbackApplicationServiceTest : TestCase() {
    private val mockEventRepo = Mockito.mock(IEventRepository::class.java)
    private val mockClubRepo = Mockito.mock(IClubRepository::class.java)
    private val eventFAService = EventFeedbackApplicationService(mockEventRepo, mockClubRepo)
    private val existingVolunteerId = 1
    private val registeredVolunteerId = 2
    private val existingClubId = 0
    private val existingEventId = 0
    private val existingTaskId = 0

    override fun setUp() {
        val club = Club(existingClubId, ClubInfo("test", Date.from(Instant.now())))
        val volunteer1 = Volunteer(existingVolunteerId, VolunteerName("test", "test"), setOf<VolunteerFeature>())
        club.addMember(volunteer1)
        val volunteer2 = Volunteer(registeredVolunteerId, VolunteerName("test", "test"), setOf<VolunteerFeature>())
        club.addMember(volunteer2)

        val timeFrame = EventTimeFrame(Instant.now(), Instant.now())
        val task = EventTask(existingTaskId, "test", timeFrame, setOf<FeatureRequirement>())

        val location = EventLocation("test", VirtualAddress(Url("test")))
        val event = Event(existingEventId, EventName("test"), location, timeFrame)
        event.addTask(task)
        event.addVolunteerToTask(existingTaskId, volunteer2)
        Mockito.`when`(mockEventRepo.findById(existingEventId)).thenReturn(event)
        Mockito.`when`(mockClubRepo.findById(existingClubId)).thenReturn(club)
    }

    fun testRegisterForTask() {
        // Arrange
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)

        // Act
        eventFAService.registerForTask(existingVolunteerId, existingEventId, existingClubId, existingTaskId)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertTrue(capturedEvent.getTasks().find { t -> t.id == existingTaskId }!!.getVolunteers().any { v -> v.id == existingVolunteerId })
    }

    fun testDeregisterFromTask() {
        // Arrange
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)

        // Act
        eventFAService.deregisterFromTask(registeredVolunteerId, existingEventId, existingTaskId)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertFalse(
            capturedEvent.getTasks().find {
                    t ->
                t.id == existingTaskId
            }!!.getVolunteers().any { v -> v.id == registeredVolunteerId },
        )
    }
}
