package csense.kotlin.extensions.primitives

/**
 * Gets this long negative, if it is already negative, returns that.
 */
inline val Long.negative: Long
    get() = minOf(this, -this)


/**
 *  if this int is not 0 => returns true. false otherwise
 */
inline val Long.isNotZero: Boolean
    get() {
        return !isZero
    }

/**
 *  if this int is 0 => returns true. false otherwise
 */
inline val Long.isZero: Boolean
    get() {
        return this == 0L
    }

