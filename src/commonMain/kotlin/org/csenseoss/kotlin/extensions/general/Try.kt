package org.csenseoss.kotlin.extensions.general

public inline fun <T> tryOrNull(action: () -> T): T? {
    return try {
        action()
    } catch (_: Throwable) {
        null
    }
}