package de.volunteerorganizer.events

import de.volunteerorganizer.events.location.EventLocation
import de.volunteerorganizer.volunteer.VolunteerName
import junit.framework.TestCase
import org.mockito.Mockito
import java.time.Instant
import java.time.Period

class EventTest : TestCase() {
    private val volunteer = EventVolunteer(1, VolunteerName("test", "test"), setOf())
    private var event = Event(1, EventName("t"), EventLocation("t"), EventTimeFrame(Instant.now(), Instant.now()))

    override fun setUp() {
        super.setUp()
        event = Event(1, EventName("testEvent"), EventLocation("testlocation"), EventTimeFrame(Instant.now(), Instant.now()))
    }

    fun testAddVolunteerToTask() {
        val mockEventTask = Mockito.mock(IEventTask::class.java)
        Mockito.`when`(mockEventTask.id).thenReturn(1)
        event.addTask(mockEventTask)

        event.addVolunteerToTask(1, volunteer)

        Mockito.verify(mockEventTask).addVolunteer(volunteer)
    }

    fun testRemoveVolunteerFromTask() {
        val mockEventTask = Mockito.mock(IEventTask::class.java)
        Mockito.`when`(mockEventTask.id).thenReturn(1)
        event.addTask(mockEventTask)

        event.removeVolunteerFromTask(1, volunteer.id)

        Mockito.verify(mockEventTask).removeVolunteer(volunteer.id)
    }

    fun testGetTasks() {
        // see testAddTask and testRemoveTask
    }

    fun testAddTask() {
        val mockEventTask = Mockito.mock(IEventTask::class.java)
        Mockito.`when`(mockEventTask.id).thenReturn(1)
        event.addTask(mockEventTask)

        val tasks = event.getTasks()

        assertEquals(setOf(mockEventTask), tasks)
    }

    fun testRemoveTask() {
        val mockEventTask = Mockito.mock(IEventTask::class.java)
        Mockito.`when`(mockEventTask.id).thenReturn(1)
        event.addTask(mockEventTask)
        event.removeTask(1)
        val tasks = event.getTasks()

        assertEquals(setOf<IEventTask>(), tasks)
    }

    fun testGetBestTimeFrame() {
        val mockEventTask1 = Mockito.mock(IEventTask::class.java)
        val mockEventTask2 = Mockito.mock(IEventTask::class.java)
        val timeEarly1 = Instant.now()
        val timeLate1 = timeEarly1 + Period.ofDays(1)
        val timeLate2 = timeLate1 + Period.ofDays(1)

        Mockito.`when`(mockEventTask1.timeFrame).thenReturn(EventTimeFrame(timeEarly1, timeLate1))
        Mockito.`when`(mockEventTask2.timeFrame).thenReturn(EventTimeFrame(timeLate1, timeLate2))

        event.addTask(mockEventTask1)
        event.addTask(mockEventTask2)

        val timeFrame = event.getBestTimeFrame()

        assertEquals(timeEarly1, timeFrame.startTime)
        assertEquals(timeLate2, timeFrame.endTime)
    }
}
