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

// TODO: randomize strings
class EventApplicationServiceTest : TestCase() {
    private val mockEventRepo = Mockito.mock(IEventRepository::class.java)
    private val mockClubRepo = Mockito.mock(IClubRepository::class.java)
    private val mockTaskRepo = Mockito.mock(ITaskRepository::class.java)
    private val eventApplicationService = EventApplicationService(mockEventRepo, mockClubRepo, mockTaskRepo)

    private val organizerId = 0
    private val clubId = 0
    private val existingEventId = 1
    private val existingTaskId = 0

    override fun setUp() {
        val club = Club(clubId, ClubInfo("testClub", Date.from(Instant.now())))
        val organizer = Volunteer(organizerId, VolunteerName("", ""), setOf<VolunteerFeature>())
        club.addMember(organizer)
        club.addOrganizer(organizerId)

        Mockito.`when`(mockClubRepo.findById(0)).thenReturn(club)

        val eventName = EventName("test")
        val eventLocation = EventLocation("test", VirtualAddress(Url("test.de")))
        val eventTimeFrame = EventTimeFrame(Instant.now(), Instant.now())
        val event = Event(existingEventId, eventName, eventLocation, eventTimeFrame)

        val task = EventTask(existingTaskId, "test", eventTimeFrame, setOf<FeatureRequirement>())
        event.addTask(task)
        event.addVolunteerToTask(existingTaskId, organizer)
        Mockito.`when`(mockEventRepo.findById(existingEventId)).thenReturn(event)
        Mockito.`when`(mockTaskRepo.generateNewTaskId()).thenReturn(existingTaskId)
        Mockito.`when`(mockEventRepo.findByVolunteer(organizerId)).thenReturn(setOf(event))
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
        val newTaskName = "addtest"
        val newTaskTimeFrame = EventTimeFrame(Instant.now(), Instant.now())
        val newTaskFeatureRequirement = setOf<FeatureRequirement>()
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)
        Mockito.`when`(mockTaskRepo.generateNewTaskId()).thenReturn(existingTaskId + 1)

        // Act
        eventApplicationService.addNewTaskToEvent(
            organizerId,
            clubId,
            existingEventId,
            newTaskName,
            newTaskTimeFrame,
            newTaskFeatureRequirement,
        )

        // Assert
        Mockito.verify(mockTaskRepo).generateNewTaskId()
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertEquals(newTaskName, capturedEvent.getTasks().find { t -> t.id == existingTaskId + 1 }!!.name)
    }

    fun testRemoveTaskFromEvent() {
        // Arrange
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)

        // Act
        eventApplicationService.removeTaskFromEvent(organizerId, clubId, existingEventId, 0)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertFalse(capturedEvent.getTasks().count { t -> t.id == 0 } > 0)
    }

    fun testEditEventName() {
        // Arrange
        val newName = EventName("notest")
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)
        val edit = EventEdit(newName, null, null)

        // Act
        eventApplicationService.editEvent(organizerId, clubId, existingEventId, edit)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertEquals(EventName("notest"), capturedEvent.name)
    }

    fun testEditEventLocation() {
        // Arrange
        val newLocation = EventLocation("notest", VirtualAddress(Url("notest")))
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)
        val edit = EventEdit(null, newLocation, null)

        // Act
        eventApplicationService.editEvent(organizerId, clubId, existingEventId, edit)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertEquals(newLocation, capturedEvent.location)
    }

    fun testEditEventTimeFrame() {
        // Arrange
        val newTimeFrame = EventTimeFrame(Instant.now(), Instant.now())
        val eventCaptor = KArgumentCaptor<Event>(ArgumentCaptor.forClass(Event::class.java), Event::class)
        val edit = EventEdit(null, null, newTimeFrame)

        // Act
        eventApplicationService.editEvent(organizerId, clubId, existingEventId, edit)

        // Assert
        Mockito.verify(mockEventRepo).saveEvent(eventCaptor.capture())
        val capturedEvent = eventCaptor.firstValue
        assertEquals(newTimeFrame, capturedEvent.timeFrame)
    }

    fun testGetAllEvents() {
        // Arrange

        // Act
        val foundEvents = eventApplicationService.getAllEvents(organizerId)

        // Assert
        assertEquals(1, foundEvents.size)
    }
}
