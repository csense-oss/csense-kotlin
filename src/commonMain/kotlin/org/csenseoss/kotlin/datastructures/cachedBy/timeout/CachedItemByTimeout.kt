package org.csenseoss.kotlin.datastructures.cachedBy.timeout

import kotlin.time.*

public data class CachedItemByTimeout<T>(
    val timeout: Duration,
    val start: TimeMark,
    val value: T
) {
    public fun isTimedOut(): Boolean {
        return start.elapsedNow() >= timeout
    }

    public companion object {
        public fun <T> forCurrentTime(
            value: T,
            timeout: Duration
        ): CachedItemByTimeout<T> {
            return CachedItemByTimeout(
                timeout = timeout,
                start = TimeSource.Monotonic.markNow(),
                value = value
            )
        }
    }
}