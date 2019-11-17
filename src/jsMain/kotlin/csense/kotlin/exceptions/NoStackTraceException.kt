package csense.kotlin.exceptions

/**
 *
 * @constructor
 */
actual class NoStackTraceException actual constructor(message: String) : Exception(message, null) {
    init {
        val us = this.asDynamic()
        if (us.stack !== undefined || us.stack !== null) {
            us.stack = undefined
        }
    }
}