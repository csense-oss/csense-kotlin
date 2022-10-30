package csense.kotlin.patterns.expected.expectedMapCatchingError


public val ExpectedMapCatchingError<Throwable>.throwable: Throwable
    get() = when (this) {
        is ExpectedMapCatchingError.Exception -> exception
        is ExpectedMapCatchingError.Failed -> error
    }