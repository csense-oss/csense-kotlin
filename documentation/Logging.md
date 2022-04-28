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


```kotlin

```

On Android you could instead hook into the various levels and direct to `Log.x`

```kotlin
L.errorLogger
```

Or you could save data to a file / database etc.

## Enabling / disabling

Controlling individual levels

```kotlin
L.isErrorLoggingAllowed = false
```

To control all logging

```kotlin
L.isLoggingAllowed(true)
```

## Ways to log

The general form to log is:

````kotlin
//the fully fledged 
L.error("tag", "message", someException)
//a more regular log
L.warning("tag", "message")
````

To modify the loggers, (clearing them and or appending listeners), just access it like so

````kotlin
L.warningLoggers
````

When in the JVM, there are quite a lot of further help for logging; some examples hereof:

To set up logging to use the console there are the following function

```kotlin
L.usePrintAsLoggers() //potentially taking a formatter function to format the logs.

```