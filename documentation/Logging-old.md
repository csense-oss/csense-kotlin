# Logging

By default, this is DISABLED and there are nothing that handles the logging. Csense does use this logger by default in
functions that does log.

There are 4 levels of logging:

- production
- error
- warning
- debug

**Production**
is intended for, production use. that is, if something is unexpected, or not an error but requires logging.

**Error**
is intended for, errors, bugs, and or other unexpected issues.

**Warning**
is intended for things that might be of interest (say slow network calls etc.) but are not an error per se.

**Debug**
is intended for debugging purpose.

## Setup

Can be as follows:

```kotlin
L.isLoggingAllowed(true)
L.usePrintAsLoggers()  
```

On Android you could instead hook into the various levels and direct to `Log.x`

```kotlin
L.productionLoggers += { tag, message, error -> Log.e(tag, message, error) }
L.errorLoggers += { tag, message, error -> Log.e(tag, message, error) }
L.warningLoggers += { tag, message, error -> Log.w(tag, message, error) }
L.debugLoggers += { tag, message, error -> Log.d(tag, message, error) }
```

Or you could save data to a file / database etc.

## Ways to log

The general logging is as follows:

````kotlin
L.logProd("Transaction", "Successful for id 1234")
L.error("Exception", "bad network?", someException)
L.warning("Network", "slow")
L.debug("info", "shotgun debugging")
````

### JVM

````kotlin
logClassError("Message")
logClassWarning("warning")
L.debug(X::class, "message")
L.debug(X::class.java, "message")
````

There is also lazy logging, in case the message is expensive to compute (since kotlin is eagerly evaluating parameters).
They take the form of a lambda instead of a message.

```kotlin
L.logProdLazy("Tag") { "MyVeryExpensiveMessage" }
L.errorLazy(tag = "tag", message = { "message" }, exception = Exception())
L.warningLazy("tag", message = { "message" })
L.debugLazy("tag") { "expensive message" }
```

## Enabling / disabling

Controlling each level

```kotlin
L.isProductionLoggingAllowed = true
L.isErrorLoggingAllowed = true
L.isWarningLoggingAllowed = true
L.isDebugLoggingAllowed = true
```

To control all logging

```kotlin
L.isLoggingAllowed(true)
```

## Controlling loggers

To modify the loggers, (clearing them and or appending listeners), just access it like so

````kotlin
L.warningLoggers
````

## Stacktrace logging (JVM)

Feature for logging the stacktrace.

```kotlin
logCurrentStackTraceProd(tag = "Unexpected call")
logCurrentStackTraceError(tag = "Bug")
logCurrentStackTraceWarning()
logCurrentStackTraceDebug(tag = "Debug", limit = 100)
```

## Convenience

since its disabled and unconfigured by default there are some helper methods to set up the logging to console

```kotlin
L.usePrintAsLoggers()
//or for fancy prints
L.usePrintAsLoggersWithAnsiColor()
```
