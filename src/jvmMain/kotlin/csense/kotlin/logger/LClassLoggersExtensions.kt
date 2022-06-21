@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.logger

import csense.kotlin.*


@Deprecated("Use CL. CL does not have any production levels.", level = DeprecationLevel.ERROR)
public inline fun <reified T : Any> T.logClassProduction(message: String, exception: Throwable? = null): Nothing =
    unexpected()

@Deprecated("Use CL. CL does not have any production levels.", level = DeprecationLevel.ERROR)
public inline fun logCurrentStackTraceProd(
    tag: String = "stack",
    limit: Int = 10,
    skipFirstFunctions: Int = 0
): Nothing = unexpected()
