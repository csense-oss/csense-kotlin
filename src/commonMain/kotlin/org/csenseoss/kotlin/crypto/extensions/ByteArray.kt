package org.csenseoss.kotlin.crypto.extensions

import kotlin.contracts.*
import kotlin.random.*

/**
 * Overwrites entire array with random values to avoid memory inspection
 */
public fun ByteArray.wipeRandom(
    random: Random = Random.Default
) {
    forEachIndexed { index: Int, _: Byte ->
        this[index] = random.nextInt().toByte()
    }
}

/**
 * Will use the bytearray then wipe it.
 */
public inline fun <T> ByteArray.useThenWipe(
    random: Random = Random.Default,
    action: (ByteArray) -> T
): T {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    return try {
        action(this)
    } finally {
        wipeRandom(random)
    }
}