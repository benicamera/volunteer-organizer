package de.volunteerorganizer.domain.event.location

import io.ktor.http.Url

data class VirtualAddress(private val url: Url) : IEventAddress {
    override fun toString(): String = url.toString()
}
