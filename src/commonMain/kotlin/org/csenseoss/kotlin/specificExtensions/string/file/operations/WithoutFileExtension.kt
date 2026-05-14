@file:Suppress("NOTHING_TO_INLINE", "unused")
package org.csenseoss.kotlin.specificExtensions.string.file.operations

import org.csenseoss.kotlin.specificExtensions.string.file.StringFile


/**
 * Tries to remove any kind of file extensions.
 * @receiver [StringFile] the string to remove the file extension from (if any)
 * @return [String] the resulting string without the ending extension (if any)
 */
public inline fun StringFile.withoutFileExtension(): String = string.substringBeforeLast('.')