package de.volunteerorganizer.domain.club

import java.util.Date

/**
 * Data class containing information about the club such as its name and its creation time
 * @param name Name of the club (cannot be empty)
 * @param creationTime Time of club creation
 */
data class ClubInfo(val name: String, val creationTime: Date){
    init {
        require(name.isNotEmpty()){
            "Club name must not be empty."
        }
    }
}