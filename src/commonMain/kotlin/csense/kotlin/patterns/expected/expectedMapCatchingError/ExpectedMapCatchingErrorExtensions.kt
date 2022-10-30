package csense.kotlin.patterns.expected.expectedMapCatchingError


public val <T : Throwable> ExpectedMapCatchingError<T>.throwable: T
    get() = when (this) {
        is ExpectedMapCatchingError.Exception -> exception
        is ExpectedMapCatchingError.Failed -> error
    }