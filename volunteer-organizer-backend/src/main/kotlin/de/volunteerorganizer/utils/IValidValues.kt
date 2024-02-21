package de.volunteerorganizer.utils

interface IValidValues<T> {
    /**
     * Checks if given value is among the valid values
     * @param value: value to be checked
     * @return: `true` if value is among values
     */
    fun isValid(value: T): Boolean
}
