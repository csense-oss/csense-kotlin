package csense.kotlin.logger.extensions//@file:Suppress("unused")
//
//package csense.kotlin.logger
//
//import csense.kotlin.*
//import csense.kotlin.logger.LLoggerExtensions.createPrintLoggerFor
//import csense.kotlin.logger.LLoggerExtensions.createPrintLoggerForAnsiCodes
//import csense.kotlin.logger.LLoggerExtensions.formatMessage
//import csense.kotlin.specificExtensions.string.*
//
//public typealias PrintFunc = (String) -> Unit
//
///**
// * This will add a logger to each category using the stdout (console).
// * @receiver LLogger the logger to add the formatter(s) to
// * @param formatter [Function4]<[LoggingLevel], [String], [String], [Throwable]?, [String]> formatter function
// */
//@Suppress("MissingTestFunction") //is tested in the JVM (so we can capture the standard out) see LLoggerExtensionsKtTest.kt
//public inline fun LLogger.usePrintAsLoggers(
//    crossinline formatter: FunctionLoggerFormatter = ::formatMessage,
//    crossinline printFunc: PrintFunc = ::println
//) {
//    productionLoggers.add(createPrintLoggerFor(formatter, LoggingLevel.Production, printFunc))
//    errorLoggers.add(createPrintLoggerFor(formatter, LoggingLevel.Error, printFunc))
//    warningLoggers.add(createPrintLoggerFor(formatter, LoggingLevel.Warning, printFunc))
//    debugLoggers.add(createPrintLoggerFor(formatter, LoggingLevel.Debug, printFunc))
//}
//
//@Suppress("MissingTestFunction") //is tested in the JVM (so we can capture the standard out) see LLoggerExtensionsKtTest.kt
//public inline fun LLogger.usePrintAsLoggersWithAnsiColor(
//    crossinline formatter: FunctionLoggerFormatter = ::formatMessage,
//    debugColor: String = AnsiConsoleEscapeCodes.purpleTextColor,
//    warningColor: String = AnsiConsoleEscapeCodes.yellowTextColor,
//    ErrorColor: String = AnsiConsoleEscapeCodes.redTextColor,
//    ProductionColor: String = AnsiConsoleEscapeCodes.cyanTextColor,
//    crossinline printFunc: PrintFunc = ::println
//) {
//    productionLoggers.add(
//        createPrintLoggerForAnsiCodes(
//            formatter,
//            LoggingLevel.Production,
//            ProductionColor,
//            printFunc = printFunc
//        )
//    )
//    errorLoggers.add(createPrintLoggerForAnsiCodes(formatter, LoggingLevel.Error, ErrorColor, printFunc = printFunc))
//    warningLoggers.add(
//        createPrintLoggerForAnsiCodes(
//            formatter,
//            LoggingLevel.Warning,
//            warningColor,
//            printFunc = printFunc
//        )
//    )
//    debugLoggers.add(createPrintLoggerForAnsiCodes(formatter, LoggingLevel.Debug, debugColor, printFunc = printFunc))
//}
//
//
//public object LLoggerExtensions {
//    public fun formatMessage(level: LoggingLevel, tag: String, message: String, exception: Throwable?): String {
//        return "$level - [$tag] $message ${exception?.stackTraceToString() ?: ""}"
//    }
//
//    public inline fun createPrintLoggerFor(
//        crossinline formatter: FunctionLoggerFormatter,
//        level: LoggingLevel,
//        crossinline printFunc: PrintFunc
//    ): LoggingFunctionType<Any> {
//        return { tag: String, message: String, exception: Throwable? ->
//            printFunc(formatter(level, tag, message, exception))
//        }
//    }
//
//    public inline fun createPrintLoggerForAnsiCodes(
//        crossinline formatter: FunctionLoggerFormatter,
//        level: LoggingLevel,
//        startCode: String,
//        endCode: String = AnsiConsoleEscapeCodes.resetCode,
//        crossinline printFunc: PrintFunc
//    ): LoggingFunctionType<Any> {
//        return { tag: String, message: String, exception: Throwable? ->
//            printFunc(formatter(level, tag, message, exception).modifications.wrapIn(startCode, endCode))
//        }
//    }
//
//}
//
/**
 * A simple container for ANSI escape codes + some colors (not a complete list)
 * eg see https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 */
public object AnsiConsoleEscapeCodes {
    public const val redTextColor: String = "\u001B[31m"
    public const val greenTextColor: String = "\u001B[32m"
    public const val yellowTextColor: String = "\u001B[33m"
    public const val blueTextColor: String = "\u001B[34m"
    public const val purpleTextColor: String = "\u001B[35m"
    public const val cyanTextColor: String = "\u001B[36m"
    public const val whiteTextColor: String = "\u001B[36m"
    public const val resetCode: String = "\u001B[0m"

}