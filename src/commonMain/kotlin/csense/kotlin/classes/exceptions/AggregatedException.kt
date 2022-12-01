package csense.kotlin.classes.exceptions

import csense.kotlin.extensions.collections.array.generic.*
import csense.kotlin.extensions.collections.iterable.*

public data class AggregatedException(
    override val message: String?,
    override val cause: Throwable,
    public val relatedExceptions: List<Throwable>
) : Exception(
    /*message = */ message,
    /*message = */ cause
) {

    public constructor(
        message: String? = null,
        vararg exceptions: Exception,
    ) : this(
        message = message,
        cause = exceptions.firstOr(missingException),
        relatedExceptions = exceptions.drop(1)
    )

    public constructor (
        message: String? = null,
        exceptions: Iterable<Exception>
    ) : this(
        message = message,
        cause = exceptions.firstOr(missingException),
        relatedExceptions = exceptions.drop(1)
    )


    public constructor (
        message: String? = null,
        cause: Exception,
        vararg relatedExceptions: Exception
    ) : this(
        message = message,
        cause = cause,
        relatedExceptions = relatedExceptions.toList()
    )

    public companion object {
        private val missingException = Exception(
            "No exceptions provided for this ${AggregatedException::class.simpleName}"
        )
    }
}
