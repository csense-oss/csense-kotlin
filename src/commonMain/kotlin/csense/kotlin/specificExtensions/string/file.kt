@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.specificExtensions.string

import csense.kotlin.extensions.*

/**
 * Namespace / scoped extensions for string related to "file" operations.
 * @property string [String]
 */
inline class StringFile(val string: String)


/**
 *
 */
inline val String.fileExtensions: StringFile
    inline get() = StringFile(this)


/**
 * Gets the file extension (which is simply the letters following the "last" dot).
 * @receiver [StringFile] the name to extract the filename from
 * @return [String]? null if there are not "." in the string, or the text following the last dot.
 */
inline fun StringFile.fileExtension(): String? {
    val fileExtension = string.substringAfterLast(".", "")
    return fileExtension.isEmpty().map(null, fileExtension) //map empty to null
}


/**
 * Tries to remove any kind of file extensions.
 * @receiver [StringFile] the string to remove the file extension from (if any)
 * @return [String] the resulting string if there were any dot's or this string if not.
 */
inline fun StringFile.removeFileExtension(): String = string.substringBeforeLast(".")
