package csense.kotlin.specificExtensions.string

import csense.kotlin.annotations.numbers.*
import csense.kotlin.extensions.primitives.*
import kotlin.jvm.*

@JvmInline
public value class StringMpp(public val string: String) {

    /**
     * A class representing an insert to be made into a string
     * @property toInsert String the string to insert
     * @property atIndex Int at what raw index in the string
     */
    public class StringInserts(
        public val toInsert: String,
        @IntLimit(from = 0) public val atIndex: Int
    )
}

public inline val String.mpp: StringMpp
    inline get() = StringMpp(this)

//region Creation and insertion
/**
 * Inserts into the given string, this is only for Kotlin mpp as there is no way to make
 * string inserts in kotlin mpp.
 * @receiver [String] the string to perform the insertion into
 * @param toInsert [Array]<out [StringMpp.StringInserts]> the insets to insert
 * @return [String]? the resulting string,or null if any index is outside of bounds
 */
public fun StringMpp.insertInto(vararg toInsert: StringMpp.StringInserts): String? = with(string) {
    val size = count()

    val sb = StringBuilder()
    toInsert.sortBy { it.atIndex } //make sure its sorted, such that we are never run into any issues.
    val lastInsertIndex = toInsert.lastOrNull()?.atIndex
    if (lastInsertIndex != null && lastInsertIndex > size) {
        return@with null
    }
    val firstInsertIndex = toInsert.firstOrNull()?.atIndex
    if (firstInsertIndex != null && firstInsertIndex.isNegative) {
        return@with null
    }

    //all indexes are guaranteed to be in this string. or either the test or sort does not work.
    var currentToIndex = 0
    var currentLastIndex: Int
    toInsert.forEach {
        currentLastIndex = currentToIndex
        currentToIndex = it.atIndex
        sb.append(
            this.subSequence(currentLastIndex, currentToIndex),
            it.toInsert
        )
    }
    if (currentToIndex < size) {
        sb.append(this.substring(currentToIndex))
    }
    return@with sb.toString()
}
//endregion

