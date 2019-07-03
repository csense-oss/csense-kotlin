@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives

/**
 * is this NOT null or blank, akk its not null nor a "blank" charsequence
 * @receiver CharSequence?
 * @return Boolean
 */
inline fun CharSequence?.isNotNullOrBlank(): Boolean {
    return this != null && this.isNotBlank()
}

/**
 * is this NOT null or empty, akk its not null nor an empty charsequence (length = 0)
 * it can be a single space though (which will yield true)
 * @receiver CharSequence?
 * @return Boolean
 */
inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}