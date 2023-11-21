@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE") // see https://youtrack.jetbrains.com/issue/KT-60866/Phase-out-usages-of-SuppressINVISIBLEREFERENCE-INVISIBLEMEMBER-in-libraries


package csense.kotlin.extensions.general

import csense.kotlin.*
import kotlin.contracts.*
import kotlin.internal.*

/**
 * Like also, but for empty functions / lambdas.
 * @receiver T
 * @param block [EmptyFunction]
 * @return T
 */

@LowPriorityInOverloadResolution
public inline fun <T> T.also(block: EmptyFunction): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
