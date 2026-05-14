@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.csenseoss.kotlin.specificExtensions.string.file.operations

import org.csenseoss.kotlin.extensions.primitives.string.nullOnEmpty
import org.csenseoss.kotlin.specificExtensions.string.file.StringFile


/**
 * Gets the file extension (which is simply the letters following the "last" dot).
 * @receiver [StringFile] the name to extract the filename from
 * @return [String]? null if there are not "." in the string, or the text following the last dot.
 */
public inline fun StringFile.fileExtension(): String? {
    val fileExtension: String = string.substringAfterLast(delimiter = ".", missingDelimiterValue = "")
    return fileExtension.nullOnEmpty()
}
