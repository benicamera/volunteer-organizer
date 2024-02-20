package de.volunteerorganizer.events

/**
 * Class representing an integer range
 * @param minValue: lower bound of interval (inclusive)
 * @param maxValue: upper bound of interval (inclusive)
 */
class ValidIntRange(private val minValue: Int, private val maxValue: Int) : IValidValues<Int> {
    init {
        require(minValue <= maxValue) {
            "$minValue is bigger than $maxValue => ${::minValue.name} is bigger than ${::maxValue.name}"
        }
    }

    override fun isValid(value: Int): Boolean = value in minValue..maxValue
}
