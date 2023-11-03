@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.general

import kotlin.contracts.*
import kotlin.jvm.*


/**
 * Creates a simpler cast than regular due to the reified type T.
 * @receiver Any the value to cast
 * @return T? the potential cast, if it is unable, null will be returned
 */
public inline fun <reified T> Any.cast(): T? {
    contract {
        returnsNotNull() implies (this@cast is T)
    }
    return this as? T
}

public inline fun <reified Result : Input, Input : Any> Input.castOr(
    action: (input: Input) -> Result
): Result {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    return this.cast<Result>() ?: action(this)
}

@JvmName("castOrReturn")
@OverloadResolutionByLambdaReturnType
public inline fun <reified Result, Input : Any> Input.castOr(
    orAction: (input: Input) -> Nothing
): Result {
    contract {
        callsInPlace(orAction, InvocationKind.AT_MOST_ONCE)
        returns() implies (this@castOr is Result)
    }
    return this.cast<Result>() ?: orAction(this)
}

