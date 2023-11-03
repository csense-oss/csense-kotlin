@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.string

import csense.kotlin.extensions.collections.generic.collection.*
import csense.kotlin.extensions.collections.generic.collection.operations.*
import csense.kotlin.extensions.primitives.char.*
import csense.kotlin.extensions.primitives.int.*
import csense.kotlin.extensions.primitives.string.*
import kotlin.jvm.*

@JvmInline
public value class StringConversion(public val string: String)

public inline val String.conversion: StringConversion
    get() = StringConversion(this)


/**
 * The opposite of [csense.kotlin.extensions.collections.array.typed.byte.toHexString] , so takes a hex string (eg "0x20") and converts it to a byte array of that
 * if any error is found during the "deserialization, null will be returned.
 * @receiver [String]
 * @return [ShortArray]? since [UByte]s are still experimental, [Short]s are used to make sure the size "is ok"
 */
public inline fun StringConversion.fromHexStringToByteArray(): ShortArray? = with(string) {
    //strip prefix iff asked to
    if (length.isOdd || isEmpty()) {
        return@with null
    }
    //we have the hex prefix iff it starts with "0x". strip that iff necessary
    val string: String = skipStartsWith("0x", true)
    val result = ShortArray(size = string.length / 2)
    GenericCollections.forEach2Indexed(length = string.length, getter = string::get) { index: Int, first: Char, second: Char ->
        val shortValue: Short = hexCharsToValue(first, second) ?: return@with null
        result[index / 2] = shortValue
    }
    return@with result
}
