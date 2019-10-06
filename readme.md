# Csense kotlin

This project contains very basic kotlin only related extensions and functionality.
It is the building block for most csense projects.

Be aware that the use of experimental features, to support a more correct implementation and usage is applied, 
since correctness is one of the highest concerns, along the "right" usage; eg the design principle is that the right way to use something should be emphasized
more than convenience; however with kotlin there are very few things that seems inconvenient that are also "wrong";


## Changelog
can be found in [changelog.md](changelog.md) 

## Roadmap
- more extensions
- general algorithms that would be needed across multiple platforms
- general functionality that is very common to share (eg multiple sub projects; for example preferences for an application ect)


## High level explanations  

In overall terms this library contains extensions building on top of the kotlin Stdlib and kotlin coroutines lib.
The intention is that while the stdlib's provide quite a lot of functionality, there are always something that seems to be missing
and as such this library contains a lot of very stdlib looking alike code.

However there are also more "general" purpose things, such as Logging, some algorithms implemented with more "flexibility"
and of cause some other concepts as well. 


### Logging
The logging is an all purpose general simple logging framework, that exposes a few levels (including a special channel ment for production)
along the ability to change, hook into, and generally do what you want with how it does it and whereto;

There are also controls to set whenever a channel should "produce" anything, akk you could have a logger printing to the console, but a channel, say debug, is set to not print anything.
For example disabling error logging:
```kotlin
L.isErrorLoggingAllowed = false
```
To control all loggings the following function is available
```kotlin
L.isLoggingAllowed(true)
```

The general form to log is:
````kotlin
//the fully fledged 
L.error("tag","message",someException)
//a more regular log
L.warning("tag","message")
````

To modify the loggers, (clearing them and or appending listeners), just access it like so
````kotlin
L.warningLoggers
````


When in the JVM, there are quite a lot of further help for logging; some examples hereof:
````kotlin

class X{
    fun doWork(){
        logClassError("some message") //will use the classname as the tag
        
        logCurrentStackTraceDebug() //will log the current stack to debug (the tag will be "stack") but can be changed
        
        
    }
}
L.debug(x::class,"message") // uses the name of X via the KClass 
````

 

To setup logging to use the console there are the following function
```kotlin
L.usePrintAsLoggers() //potentially taking a formatter function to format the logs.

```


### Extensions

Check out the type of extensions you are curious about; they are arranged in terms of their type; 

### Patterns

### Very general helpers

There are some very convenient methods / properties; a few examples / mentions:

typeK() is basically a function that gives you the required type(class) of an object.
The java equivalent  (which gives a java class ) is called "type", and is only in the JVM
before:
````kotlin
class AClassWithALongName
fun usesType(type:KClass<AClassWithALongName>){}
usesType(AClassWithALongName::class)
````
csense:
````kotlin
class AClassWithALongName
fun usesType(type:KClass<AClassWithALongName>){}
usesType(typeK()) //extension
//there is also type(), which is only for java (java Class)   
````
toUnit() is a function where you are to return Unit but writing a whole line just to make the compiler shut up is quite annoying and tedious
This function makes it quite easy to write more idiomatic kotlin code.
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



map() convert a boolean to a value; 
In regular terms you can convert a bool to a value in a very boilerplate way; 
before:
````kotlin
val myValue = if(someBool){
    42
}else{
    40
}

````
csense:
````kotlin
val myValue = someBool.map(42,40)
````
and of cause if that is "too condensend" you could always write it like so:
````kotlin
val myValue = someBool.map(ifTrue = 42, ifFalse = 40)
````



### Crypto
Since the introduction of Random in kotlin MPP std lib, its now possible to share some more cryptographically things, 
and one of such is UUID version 4 (the random one)
which is implemented in UUID.


### Time
there is also a time unit in the project, 
which should allow a seamless conversion between time units, and be allocation free (still waiting for inline classes to be promoted for this)
 

#### Expected
This pattern is quite useful, the one in Coroutines (Result) is not meant for General purpose usage; 
however the current state of this pattern is very poor and will be improved / changed drastically in the future, especially depending on the improvements to contracts.

The Expected pattern is a simply put, a dual way of handling exceptions and bad results.
The point is, instead of forcing to throw exceptions, on behalf of the caller, the caller gets to
decide how he/she will handle the result of a function; 
this means that if checking the result via "isValid", is more appropiat, then no exceptions will be thrown,
but if that is the intended way one can simply access the value, which in the case of errors will throw, 
just like a regular throwing method would. 
This means that its up to the caller rather then the calle, to decide how the program handles "expected" results
(bad / good results)

 