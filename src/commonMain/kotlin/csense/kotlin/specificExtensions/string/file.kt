@file:Suppress("NOTHING_TO_INLINE", "unused")

package csense.kotlin.specificExtensions.string

import csense.kotlin.extensions.primitives.string.*
import kotlin.jvm.*

/**
 * Namespace / scoped extensions for string related to "file" operations.
 * @property string [String]
 */
@JvmInline
public value class StringFile(public val string: String)


/**
 * Access file extensions for strings
 */
public inline val String.fileExtensions: StringFile
    inline get() = StringFile(this)


/**
 * Gets the file extension (which is simply the letters following the "last" dot).
 * @receiver [StringFile] the name to extract the filename from
 * @return [String]? null if there are not "." in the string, or the text following the last dot.
 */
public inline fun StringFile.fileExtension(): String? {
    val fileExtension = string.substringAfterLast(".", "")
    return fileExtension.nullOnEmpty()
}

/**
 * Changes the current extension to the given [newExtension] (either replaced or added with a '.' in front)
 * @receiver [StringFile] to change the file extension on
 * @param newExtension [String] the new file extension
 * @return [String]
 */
public inline fun StringFile.withFileExtension(newExtension: String): String {
    return string.substringBeforeLast('.', string) + ".$newExtension"
}

/**
 * Tries to remove any kind of file extensions.
 * @receiver [StringFile] the string to remove the file extension from (if any)
 * @return [String] the resulting string without the ending extension (if any)
 */
public inline fun StringFile.withoutFileExtension(): String = string.substringBeforeLast('.')
