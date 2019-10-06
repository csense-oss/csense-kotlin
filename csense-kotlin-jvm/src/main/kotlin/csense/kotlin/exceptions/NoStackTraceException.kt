package csense.kotlin.exceptions

import java.lang.Exception

actual class NoStackTraceException actual constructor(message: String) : Exception(message, null, false, false)