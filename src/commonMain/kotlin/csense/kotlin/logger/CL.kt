package csense.kotlin.logger

public object CL : CsenseLogger(maxStoredLogMessages = 100) {
    public val debug: CLLogFunction = CLLogFunctionCallToMethod(this::logDebug)
    public val warning: CLLogFunction = CLLogFunctionCallToMethod(this::logWarning)
    public val error: CLLogFunction = CLLogFunctionCallToMethod(this::logError)
}