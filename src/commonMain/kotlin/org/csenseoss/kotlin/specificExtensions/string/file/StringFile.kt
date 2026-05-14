@file:Suppress("NOTHING_TO_INLINE", "unused")

package org.csenseoss.kotlin.specificExtensions.string.file

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
