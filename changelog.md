# Changelog

## 0.0.23
- kotlin 1.3.60


## 0.0.22
- kotlin 1.3.50
- coroutines 1.3.2
- lazy logging
- split logging functions to method referencing works (since default arguments is not allowed for method refs).
- massive amounts of tests added.
    - and some bugs fixed eg. with ranges
    

## 0.0.21
- moved LLogger extension to separated file
- started on data structure package
    - SimpleLRUCache
- more tests

## 0.0.20
- potential fixed broken doc for jvm target
- kotlin 1.4.41
- junit 5.5

## 0.0.19
- breaking change: enumFromOr (for function arguments) now called "enumFromOrNull" since it returns null, and added one that does not allow null.
    - documented the enum functions as well as performance time.
    - more test hereof.
    - @see changed as they appear to cause issues with dokka.

- added isNotNullOrBlank & isNotNullOrEmpty to charsequence
- collection & array now have a "isNotNullOrEmpty" and a "isNullOrEmpty" extensions. 


## 0.0.18
- kotlin 1.3.40
- kotlin coroutines 1.2.2
- Any?.isNull & Any?.isNotNull contracts removed as they turned into compiler error.
- enumFromOr via integer (using the ordinal value).

## 0.0.17
- logMeasureTimeInMs function (JVM)
- more tests
- kotlin 1.3.31

## 0.0.16
- reversedIf extension on collection
- spited the categorizeInto function into multiple functions to simplify it as well as potentially allowing other kinds of usage.
- gradle 5.4
- kotlin 1.3.0
- kotlin coroutines 1.2.0

## 0.0.15
- Started on various unit types
    - bytes (in the binary notation) 
- strings default ignore case argument (false) to be like stdlib
- moved TimeUnit to units package (breaking change) (and renamed file)
- reflection extension for jvm
- throwable to string extension (jvm)
- removed unnecessary ignore annotations
- a lot of minor things from commonsense android kotlin 0.0.18 / 0.0.20 ( generally extensions)
- cleaned up unused enum in Logger, as the level is now more concise with how its implemented 
- Comparing is now "ItemComparing", since its between "items"
- uniformed extensions on numbers (float, double, int, long, short, byte, now have +- the same extensions regarding positive, negative ect)
- extracted the L logging part into a class, such that it can be used in various ways, while still having a L object (also improves tests).
    - also updated extensions.
- gradle 5.3-rc-1

## 0.0.14
- fixed logging issue 
- added async mapping extensions
- added mapToSet
- added setIfNotEmpty to map
- added a bunch of string extensions
- improved some of the string functions to use more of the std lib.
- moved generic code from global namespace to generic array.
- fixed bug in StringBuilder.clear (by removing it as the extension is available from kotlin stdlib)
- fixed functions depending on old StringBuilder.clear
- gradle 5.2.1
- kotlin 1.3.21
- very generic foreach, map and filter (in generic).
- more work on coverage
- more gradle / groovy cleanup 

## 0.0.13
- removed dependency on kotlin reflect, as that was quite bad (caused issues with logging functions)


## 0.0.12
- JVM timing function naming (lowercase method name and millis in name). and made it usable in suspension
- removed a lot of crossinline where they made no real sense (if the function would make sense with a return inside of it, and or allow it to be used in suspend functions )
- removed supporting functions that were due to crossinlining.


## 0.0.11
- gradle 5.2-rc-1
- JVM 
    - log stack trace 
- coroutines 1.1.1
- more parallel kotlin compilation (kotlin.parallel.tasks.in.project=true)
- KClassExtensions.kt moved to jvm module; common does not have reflection. (and extensions and tests)
- more coroutines extensions (io for jvm)
- more extensions (map, string, Throwable stdLib extensions)
- printlogger for Logging
- started on Timing for JVM (measureXX which can handle computation).

## 0.0.10
- gradle 5.1.1
- kotlin 1.3.20
- suspend function arity. (0 to 6)
- removed awaitAll since this is in the std lib
- added helper functions for launch/async on various dispatchers 

## 0.0.9
- added more documentation
- updated tests to reflect actual and expected correctly.
- more extensions
- more test extensions
- gradle 5.1

## 0.0.8
- Foreach 2 (traversal of 2 elements at a time on iterable ,arrays, sets and maps)
- Foreach backwards (on iterable ,iterable ,arrays, sets and maps)
- char to digits and back (hex converting)
- inserting strings into a string (since this is not in kotlin STL)
-  UUID (4) as random is in kotlin STL we can now create more shared things :) 
- 
## 0.0.7
- file ignore headers librarys stuff
- more testing and indentation. 

## 0.0.6
- kotlin 1.3.10
- coroutines 1.0.1
- more tests
- more generic generalization
- more general functions / extensions
- started use contracts
- gradle 5.0-rc2

## 0.0.5
- migrated a lot from commonsenseAndroid version 0.0.15
- more tests and documentation
- kotlin 1.3 and coroutines in prod.



## 0.0.4
added
 - tryAndLog
 - logging
 
## 0.0.3
- migrated a lot from commonsenseAndroid

## 0.0.2
- migrated a lot from commonsenseAndroid

## 0.0.1

- moved all general commonsense code from various projects into this.
- primary setup
- this especially mean the commonsenseandroidkotlin project.
