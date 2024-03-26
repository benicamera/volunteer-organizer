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
import org.mockito.Mockito
import java.time.Instant
import java.util.*

// TODO: randomize strings
class EventApplicationServiceTest : TestCase() {
    private val mockEventRepo = Mockito.mock(IEventRepository::class.java)
    private val mockClubRepo = Mockito.mock(IClubRepository::class.java)
    private val mockTaskRepo = Mockito.mock(ITaskRepository::class.java)
    private val eventApplicationService = EventApplicationService(mockEventRepo, mockClubRepo, mockTaskRepo)

    private val organizerId = 0
    private val clubId = 0
    private val existingEventId = 1
    override fun setUp(){
        val club = Club(clubId, ClubInfo("testClub", Date.from(Instant.now())))
        club.addMember(Volunteer(organizerId, VolunteerName("", ""), setOf<VolunteerFeature>()))
        club.addOrganizer(organizerId)

        Mockito.`when`(mockClubRepo.findById(0)).thenReturn(club)

        val event = Event(1, EventName("test"), )
        Mockito.`when`(mockTaskRepo.generateNewTaskId()).thenReturn(0)
    }

    // TODO: test permission check
    fun testCreateEvent() {
        // Arrange
        val eventName = EventName("test")
        val eventLocation = EventLocation("test", VirtualAddress(Url("test")))
        val timeFrame = EventTimeFrame(Instant.now(), Instant.now())
        val expectedEvent = Event(0, eventName, eventLocation, timeFrame)
        val edit = EventEdit(eventName, eventLocation, timeFrame)

        // Act
        eventApplicationService.createEvent(0, 0, edit)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(expectedEvent)
    }

    fun testDeleteEvent() {
        // Arrange

        // Act
        eventApplicationService.deleteEvent(organizerId, clubId, existingEventId)

        // Assert
        Mockito.verify(mockEventRepo).deleteEventById(existingEventId)
    }

    fun testAddNewTaskToEvent() {
        // Arrange
        val newTaskName = "test"
        val newTaskTimeFrame = EventTimeFrame(Instant.now(), Instant.now())
        val newTaskFeatureRequirement = setOf<FeatureRequirement>()

        // Act
        eventApplicationService.addNewTaskToEvent(organizerId, clubId, existingEventId, newTaskName, newTaskTimeFrame, newTaskFeatureRequirement)

        // Assert
        Mockito.verify(mockTaskRepo.generateNewTaskId())
        Mockito.verify(mockTaskRepo.save(EventTask(0, newTaskName, newTaskTimeFrame, newTaskFeatureRequirement)))
        // TODO: check if task is also saved in Event that is saved
    }

    fun testRemoveTaskFromEvent() {}

    fun testEditEvent() {}

    fun testGetAllEvents() {}
}