@file:Suppress("NOTHING_TO_INLINE", "unused")

package org.csenseoss.kotlin.specificExtensions.string.file.operations

import org.csenseoss.kotlin.specificExtensions.string.file.StringFile


/**
 * Changes the current extension to the given [newExtension] (either replaced or added with a '.' in front)
 * @receiver [StringFile] to change the file extension on
 * @param newExtension [String] the new file extension
 * @return [String]
 */
public inline fun StringFile.withFileExtension(newExtension: String): String {
    return string.substringBeforeLast(delimiter = '.', missingDelimiterValue = string) + ".$newExtension"
}
