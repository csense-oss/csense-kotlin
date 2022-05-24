# Logging

By default, this is DISABLED and there are nothing that handles the logging. Csense does use this logger by default in
functions that does log.

There are 3 levels of logging:

- error
- warning
- debug

**Error**
is intended for, errors, bugs, and or other unexpected issues.

**Warning**
is intended for things that might be of interest (say slow network calls etc.) but are not an error per se.

**Debug**
is intended for debugging purpose.

## Setup

For just printing (to the console) there are

```kotlin
CL.printLogsToConsole()
//or if the console supports ansi escape codes / colors
CL.printLogsToConsoleAnsiColored()
```

If using android then you can do this
(NB Logcat does not support ansi colors unfortunately :/)

```kotlin
//Use of globalscope here is "okay", as this is a more "daemon" thread style and is bound by application lifecycle (unless you cancel the job..)
GlobalScope.launch(Dispatchers.Main) {
    CL.debugLoggers.collect {
        Log.d(it.tag, it.message.toString(), it.throwable)
    }
}
GlobalScope.launch(Dispatchers.Main) {
    CL.warningLoggers.collect {
        Log.w(it.tag, it.message.toString(), it.throwable)
    }
}
GlobalScope.launch(Dispatchers.Main) {
    CL.errorLoggers.collect {
        Log.e(it.tag, it.message.toString(), it.throwable)
    }
}
```

## Logs

All logs are by default considered sensitive.
There is a tag, a message with potential placeholders and potentially an exception.
The placeholder is simply written as "{}" and is based on the order.

```kotlin
CL.logDebug("tag", "parameter 1: {}, parameter 2: {}", "first", "second")
```

To simplify usage there is a single instance of an already configured logger called
CL (short for CsenseLogger).

### Log methods

The shortest form is

```kotlin
CL.logDebug("tag", "message")
```

### Sensitive logs

All logs are by default considered sensitive, thus all parameters will not be printed / displayed unless sensitive
logging is enabled.
(and even then it will be stored along side the LogMessage whenever the message still contains sensitive information)

To enable sensitive logging do

```kotlin
CL.enableSensitiveLogging()
```

Eg for android add this in the main application class:

```kotlin
if (BuildConfig.DEBUG) {
    CL.enableSensitiveLogging()
}
```

### Logging helpers

There are some helpers to improve developer experience

```kotlin
logClassDebug("message")
logClassWarning("message")
logClassError("message")
```

### Configuring multiple loggers

Since CL is just an instace of a CsenseLogger you can easily create other instances and then use those to your hearts
desire.
