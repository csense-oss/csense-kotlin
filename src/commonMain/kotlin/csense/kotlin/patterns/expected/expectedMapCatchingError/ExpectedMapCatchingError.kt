@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.patterns.expected.expectedMapCatchingError

import csense.kotlin.*
import kotlin.contracts.*


public sealed interface ExpectedMapCatchingError<Error> {
    //TODO use inline value class (kotlin 1.8)
    public class Failed<Error>(
        public val error: Error
    ) : ExpectedMapCatchingError<Error>

    //TODO use inline value class (kotlin 1.8)
    public class Exception<Error : Throwable>(
        public val exception: Error
    ) : ExpectedMapCatchingError<Error>
}

public inline fun <Error> ExpectedMapCatchingError<Error>.isFailed(): Boolean {
    contract {
        returns(true) implies (this@isFailed is ExpectedMapCatchingError.Failed)
        returns(false) implies (this@isFailed is ExpectedMapCatchingError.Exception)
    }
    return this is ExpectedMapCatchingError.Failed
}

public inline fun <Error> ExpectedMapCatchingError<Error>.isException(): Boolean {
    contract {
        returns(false) implies (this@isException is ExpectedMapCatchingError.Failed)
        returns(true) implies (this@isException is ExpectedMapCatchingError.Exception)
    }
    return this is ExpectedMapCatchingError.Exception
}



