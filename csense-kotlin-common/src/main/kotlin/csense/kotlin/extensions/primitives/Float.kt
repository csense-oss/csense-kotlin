package csense.kotlin.extensions.primitives

/**
 * Gets this float negative, if it is already negative, returns that.
 */
inline val Float.negative: Float
    get() = minOf(this, -this)

/**
 * Compares two floats with a delta, since floats are not precise.
 *
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Float.equals(otherFloat: Float, delta: Float): Boolean =
        this >= otherFloat - delta && this <= otherFloat + delta

/**
 * Tells if this float value is zero (or more correctly, close to, since floats are not precise)
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Float.isZero(tolerance: Float = 0.1f): Boolean {
    return this < tolerance && this > tolerance.negative
}
