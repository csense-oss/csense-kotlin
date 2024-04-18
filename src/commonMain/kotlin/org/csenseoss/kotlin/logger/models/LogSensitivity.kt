@file:Suppress("NOTHING_TO_INLINE")

package org.csenseoss.kotlin.logger.models

public enum class LogSensitivity {
    Sensitive,
    Insensitive
}

public inline fun LogSensitivity.isSensitive(): Boolean =
    this == LogSensitivity.Sensitive

public inline fun LogSensitivity.isNotSensitive(): Boolean =
    !isSensitive()