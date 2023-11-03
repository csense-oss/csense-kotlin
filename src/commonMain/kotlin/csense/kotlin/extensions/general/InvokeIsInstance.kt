@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.general

import csense.kotlin.*
import kotlin.contracts.*
import kotlin.jvm.*


/**
 * invokes the given action if this is the specific type [T] (and returns the result) or null if this is
 * not the specific type
 * @receiver [Any] the unknown type
 * @param action [FunctionUnit]<T> the action to call if this is actually a T
 */
public inline fun <reified Result : Input, Input : Any> Input.invokeIsInstance(action: FunctionUnit<Result>) {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    cast<Result>()?.let(action)
}

public inline fun <reified Result : Input, Input : Any> Input.asInstanceOr(onNotInstance: () -> Result): Result {
    contract {
        callsInPlace(onNotInstance, InvocationKind.AT_MOST_ONCE)
    }
    return this.cast<Result>() ?: onNotInstance()
}


@JvmName("asInstanceOrReturn")
@OverloadResolutionByLambdaReturnType
public inline fun <reified Result : Input, Input : Any> Input.asInstanceOr(onNotInstance: () -> Nothing): Result {
    contract {
        callsInPlace(onNotInstance, InvocationKind.AT_MOST_ONCE)
        returns() implies (this@asInstanceOr is Result)
    }
    return this.cast<Result>() ?: onNotInstance()
}
