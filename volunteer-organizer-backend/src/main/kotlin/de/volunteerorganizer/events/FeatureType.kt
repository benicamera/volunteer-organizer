package de.volunteerorganizer.events

/**
 * Data class representing a feature type
 */
data class FeatureType(val name: String, val id: Int) {
    init {
        require(name.isNotEmpty()) {
            "The name of an feature cannot be empty."
        }
    }
}
