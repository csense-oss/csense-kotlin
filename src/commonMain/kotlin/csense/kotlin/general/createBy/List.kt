package csense.kotlin.general.createBy

import kotlin.contracts.*

public inline fun <T> createListBy(builder: (result: MutableList<T>) -> Unit): List<T> {
    contract {
        callsInPlace(builder, InvocationKind.EXACTLY_ONCE)
    }
    val result: MutableList<T> = mutableListOf()
    builder(result)
    return result
}