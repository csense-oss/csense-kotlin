# Csense kotlin

This project contains very basic kotlin only related extensions and functionality. It is the building block for most
csense projects.

Be aware that the use of experimental features, to support a more correct implementation and usage is applied, since
correctness is one of the highest concerns, along the "right" usage; e.g. the design principle is that the right way to
use something should be emphasized more than convenience; however with kotlin there are very few things that seems
inconvenient that are also "wrong";

## Installation

add this maven repo (into your repositories, either in the root build.gradle og the application's build.gradle)

```groovy
repositories {
    maven {
        url 'https://pkgs.dev.azure.com/csense-oss/csense-oss/_packaging/csense-oss/maven/v1'
        name 'csense-oss'
    }
}
```

Then add the dependency

```groovy
dependencies {
    implementation 'csense.kotlin:csense-kotlin:0.0.52'
}
```

## Changelog

can be found in [changelog.md](changelog.md)

## Roadmap

- more extensions
- general algorithms that would be needed across multiple platforms
- general functionality that is very common to share (e.g. multiple subprojects; for example preferences for an
  application ect)

## High level explanations

In overall terms this library contains extensions building on top of the kotlin Stdlib and kotlin coroutines lib. The
intention is that while the stdlib's provide quite a lot of functionality, there are always something that seems to be
missing and as such this library contains a lot of very stdlib looking alike code.

However, there are also more "general" purpose things, such as Logging, some algorithms implemented with more "
flexibility"
and of course some other concepts as well.

### Logging

The logging is an all-purpose general simple logging framework, that exposes a few levels (including a special channel
meant for production)
along the ability to change, hook into, and generally do what you want with how it does it and whereto;

There are also controls to set whenever a channel should "produce" anything, akk you could have a logger printing to the
console, but a channel, say debug, is set to not print anything. For example disabling error logging:

```kotlin
L.isErrorLoggingAllowed = false
```

To control all logging the following function is available

```kotlin
L.isLoggingAllowed(true)
```

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

````kotlin

class X {
    fun doWork() {
        logClassError("some message") //will use the classname as the tag

        logCurrentStackTraceDebug() //will log the current stack to debug (the tag will be "stack") but can be changed


    }
}
L.debug(x::class, "message") // uses the name of X via the KClass 
````

To set up logging to use the console there are the following function

```kotlin
L.usePrintAsLoggers() //potentially taking a formatter function to format the logs.

```

### Extensions

Check out the type of extensions you are curious about; they are arranged in terms of their type;

### Patterns

### Very general helpers

There are some very convenient methods / properties; a few examples / mentions:

typeK() is basically a function that gives you the required type(class) of an object. The java equivalent  (which gives
a java class ) is called "type", and is only in the JVM before:

````kotlin
class AClassWithALongName

fun usesType(type: KClass<AClassWithALongName>) {}
usesType(AClassWithALongName::class)
````

csense:

````kotlin
class AClassWithALongName

fun usesType(type: KClass<AClassWithALongName>) {}
usesType(typeK()) //extension
//there is also type(), which is only for java (java Class)   
````

toUnit() is a simple extension to make the receiver return Unit.

before:

````kotlin

fun some() {
    coroutineScope.launch {
        doWork()
    }
}

````

csense:

````kotlin
fun some(): Unit = coroutineScope.launch {
    doWork()
}.toUnit()
````

map() convert a boolean to a value; In regular terms you can convert a bool to a value in a very boilerplate way;
before:

````kotlin
val myValue = if (someBool) {
    42
} else {
    40
}

````

csense:

````kotlin
val myValue = someBool.map(42, 40)
````

Of course if that is "too condensed" you could always write it like so:

````kotlin
val myValue = someBool.map(ifTrue = 42, ifFalse = 40)
````

### Crypto

Since the introduction of Random in kotlin MPP standard library, it is now possible to share some more cryptographically
things, and one of such is UUID version 4 (the random one)
which is implemented in UUID.

### Time

There is also a time unit in the project, which should allow a seamless conversion between time units, and be allocation
free (still waiting for inline classes to be promoted for this)

### Computer sizes

There are classes related to binary / computer sizes, from bits to terabytes.
