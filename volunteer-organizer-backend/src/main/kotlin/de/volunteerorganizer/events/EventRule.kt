package de.volunteerorganizer.events

/**
 * Enum with possible Event Rules
 * TODO: refine
 */
enum class EventRuleType {
    /**
     * Specify the number of volunteers needed
     * Possible keys: `min` and `max`
     * Possible values: -1 - n with -1 encoding "no limit" on max key
     */
    NUM_VOLUNTEERS,

    /**
     * Specify the number of needed volunteers having a special feature
     * Possible keys: `{feature}_min` and `{feature}_max`
     * Possible values: -1 - n with -1 encoding "no limit" on max key
     */
    NUM_FEATURES
}

/**
 * Data class containing the EventRule
 * @param ruleType: Type of rule
 * @param key: key of rule specifier
 * @param value: value of rule specifier
 */
data class EventRule(val ruleType: EventRuleType, val key: String, val value: Int)
// TODO check if value is valid

