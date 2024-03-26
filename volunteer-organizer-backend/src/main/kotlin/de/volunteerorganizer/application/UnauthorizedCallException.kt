package de.volunteerorganizer.application

/**
 * Exception that is thrown in case of an unauthorized call.
 * For example if the issuer of the call is not authorized to perform action.
 */
class UnauthorizedCallException : Exception {
    /**
     * Default constructor for unauthorized call.
     */
    constructor() : super()

    /**
     * Constructor taking exception message.
     * @param message message to give to exception
     */
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
