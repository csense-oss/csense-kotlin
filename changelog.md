# Changelog

## 0.0.14
- fixed logging issue 
- added async mapping extensions
- added mapToSet
- added setIfNotEmpty to map
 

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
