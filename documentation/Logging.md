# Logging

By default, this is DISABLED and there are nothing that handles the logging. Csense does use this logger by default in
functions that does log.

There are 4 levels of logging:

- production
- error
- warning
- debug

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
L.error("tag", "message", someException)
L.warning("tag", "message")
L.debugLazy("Tag") { "expensive computation" }
````

### JVM

````kotlin
logClassError("Message")
logClassWarning("warning")
L.debug(X::class, "message")
L.debug(X::class.java, "message")
````



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

## Convenience
since its disabled and unconfigured by default there are some helper methods to setup the logging to console
```kotlin
L.usePrintAsLoggers()
//or for fancy 
L.usePrintAsLoggersWithAnsiColor()

```
