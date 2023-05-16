package csense.kotlin.extensions.duration

import kotlin.time.*

public suspend fun Duration.delay() {
    kotlinx.coroutines.delay(this)
}
