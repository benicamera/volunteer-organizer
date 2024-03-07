package de.volunteerorganizer.domain.event.location

data class PhysicalAddress(
    private val street: String,
    private val postcode: String,
    private val city: String,
    private val country: String,
) : IEventAddress {
    override fun toString(): String = "$street $postcode $city, $country"
}
