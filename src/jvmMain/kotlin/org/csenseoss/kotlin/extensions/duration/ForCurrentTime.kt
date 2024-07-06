package org.csenseoss.kotlin.extensions.duration

import kotlin.time.*

public fun Duration.Companion.forCurrentTime(): Duration {
    return System.currentTimeMillis().toDuration(DurationUnit.MILLISECONDS)
}