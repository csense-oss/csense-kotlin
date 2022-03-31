# Changelog

## 0.0.56

- added
    - StringComparison (namespacing some string extensions)
    - string.endsWith taking a "ignoreWhitespace" parameter
    - string.startsWith taking a "ignoreWhitespace" parameter
    - string.equals taking a "ignoreWhitespace" parameter
    - CharSequence.indexOfLastOrNull
    - CharSequence.indexOfLastIndexedOrNull
    - StringComparison.containsStringEndingAt
    - StringComparison.containsStringAt
    - StringComparison.compareTo
    - T?.isNull() (function that supports contracts)
    - T?.isNotNull() (function that supports contracts)
    - isAnyNull (up to 6 args) (works with contacts)
    - isAnyNotNull (up to 6 args)  (works with contacts)
    - expected pattern (see [ExpectedPattern.md](documentation/ExpectedPattern.md))

- moved
    - these string extensions to StringModifications; "modifications" in code
        - replaceCharAt
        - replaceCharAtOrNull
        - splitAtOrNull
        - StringSplitAt (class) into StringModifications
        - replaceIfOr
        - replaceIf
        - mapEachMatching
    - string.fromHexStringToByteArray to StringConversion(via "conversion" on string).fromHexStringToByteArray

- renamed
    - string.findAllOf to allIndicesOf

## 0.0.55

- added
    - duration.delay
    - collection.toUniqueMap
    - MutableMap.put to a Map.Entry
    - LazyArgument (see [LazyArgument.md](documentation/LazyArgument.md))

- deprecated
    - Time unit, use instead the Duration api from kotlin.Time instead

## 0.0.54

- added
    - Map
        - reverse (reverses the relation, so from key -> value it is value -> key)

    - MutableMap
        - reverse (reverses the relation, so from key -> value it is value -> key)
        - remove (key?) convince remove function

    - awaitAll (global functions) for awaiting multiple coroutines

    - @ExperimentalCsenseApi for marking things that are really experimental and might be removed or changed without
      notice

- Removed
    - deprecated things (most had a deprecation cycle of 4+ releases)
        - if using the foreach2 extensions the general function is still available at GenericCollectionExtensions.{name}

    - Workaround for coroutines tests (runTest)

## 0.0.53

- added
    - MutableCollection
        - addIf
        - addIfNotNull
        - removeIf
        - removeIfNotNull

    - MutableMap
        - putIfMissing
        - putIfMissingAnd

    - CharSequence
        - indexOfFirstOrNull
        - indexOfFirstIndexedOrNull

    - String
        - convince constructor (codePoints) for JVM
        - replaceCharAt
        - replaceCharAtOrNull
        - splitAtOrNull

    - Char
        - isNotWhitespace
        - isNotEqual

    - isIndex on collection & array & map types. this is meant to assist in bounds testing but in a more readable manner
        - it contains the following functions
            - inBoundsEndNotInBounds
            - inBoundsEndInBounds
            - outOfBoundsEndOutOfBounds
            - outOfBoundsEndInBounds
            - inBounds
            - outOfBounds

- Changed
    - MutableCollection.setAll now returns the result from "addAll"
    - MutableCollection.set now returns the result from add


- Improved String.titleCaseFirstWord to actually handle if there are starting whitespace chars
    - and added a JVM version taking a locale

## 0.0.52

- kotlin 1.6.0
- added
    - MutableSet
        - addIfMissingAnd
    - UnexpectedException + unexpected function(s)
        - unexpected, unexpectedWithLogging, logUnexpected

- updated annotations to 0.0.50

## 0.0.51

- kotlin 1.5.31
- added
    - InputStream
        - readToEnd
        - readOrNullOnEnd
    - String
        - nullOnEmpty
    - map
        - hasSameKeys
        - hasSameContent
        - hasSameContentBy


- Fixed some other tests that were mistakenly copied and pasted
- Added nullable keys for map & set tests

## 0.0.50

- kotlin 1.5.30
- annotated some functions with OnlyInputTypes (to avoid type misuse for extensions)
- gradle 7
- add Iterator extensions:
    - toList
    - toMutableList
    - map
- added collection extensions:
    - toMap
    - toMapFlat
    - toMutableMap
- added CharSequence.equals & CharSequence.notEquals
- added extensions to CharSequence
    - substringOrNull
    - split
    - splitBy
- added List<T>.mapToTypedArray
- added Iterator.atEnd

## 0.0.46

- added ability for LLoggers to use ansi colored output
- added sublist with single parameter ( length)
- added mapToTypedArray
- added more missing extensions to iterable and added more contracts
- added double & float extensions for decimal parts
- stringFile extensions
    - added withFileExtension
    - deprecated "removeFileExtension" for the name "removeFileExtension"
    - Spelling improved

- added more extensions for numbers

## 0.0.45

- added Double.isNotZero
- added replaceFirst to list with a predicate

## 0.0.44

- re-enabled benchmark after investigation
- kotlin 1.4.10

## 0.0.43

- added string.isNewline
- added collection.joinEvery
- added Array.joinEvery
- removed the benchmark(jmh) test as it got though to regular code somehow.

## 0.0.42

- re-introduce NoStackTraceException but only for JVM.
- jacoco disabled for ci

## 0.0.41

- fixed "Channel.forEach" missing inline modifier

## 0.0.40

- kotlin 1.4
- removed deprecated things
- removed stuff that was not very generic or does not fit into this "module"
- improved documentation & spelling
- more annotations
- many more extensions (forEachBackwards on strings, "or", and more)
- many more contracts added
- moved certain extensions to "Named" / scoped parts to avoid clutter say String with a lot of extensions.
- moved algorithms out.

## 0.0.36

- added more tests
- improved contracts
- cleanup more docs
- add extensions
    - set containAny
    - set doesNotContainAny
    - collection largest
    - char.isNotDigit()
- changed is{Uppercase,Lowercase} impl to never count numbers, whitespace ect as either (not lower, nor upper)- it does
  not make sense
- removed MutableCollection<E>.set
- isUpperCase and isLowerCase on char is now a function.
    - javascript now has a "isLetter" that tries to test for the full unicode range of "real" letters. (WIP)

## 0.0.35

- deprecation for more "algorithm" / "datastructure" code.
    - might also be updated to include logger.
- char.isDigit method added
- improved impl for isAllUppercase & isAllLowercase (whitespace & digit handling)
- azure pipelines working
- doesNotContain for maps, sets & lists
- char is{Upper, lower} case extensions and
- string is{Upper, lower}CaseOnly extensions

## 0.0.34

- added extensions to collections
    - indexOfFirstOrNull
    - indexOfLastOrNull

## 0.0.33

- isNot extension added to Any
- indexOfOrNull for CharSequence (as it is not a collection)

## 0.0.32

- forEachIsInstance<T> added as an "allocation free" alternative to filterIsInstance + foreach
- indexOfOrNull to collections (why is this even missing from the standard library ?)
    - https://youtrack.jetbrains.com/issue/KT-37585)
- minor collection extension(s)

## 0.0.31

- more extensions for mutableCollection

## 0.0.30

- more string extensions (does not start with)
- removeLast & removeFirst from lists.

## 0.0.29

- iterable extension for contains (for strings)
- more tests

## 0.0.28

- weak reference extensions improved.
- more float extensions
- equalsWithin (float) renamed from equalWithin

## 0.0.27

- more extensions.

## 0.0.26

- more annotations
- more logging extensions
- LockableValue & SetOnceBool added.

## 0.0.25

- use of csense kotlin annotations. (mostly ranges)

## 0.0.24

- changed project to new MPP format
- updated location in bintray
- fixed group name (from . to -)

## 0.0.23

- kotlin 1.3.60

## 0.0.22

- kotlin 1.3.50
- coroutines 1.3.2
- lazy logging
- split logging functions to method referencing works (since default arguments is not allowed for method refs).
- massive amounts of tests added.
    - Some bugs fixed e.g. with ranges

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

- breaking change: enumFromOr (for function arguments) now called "enumFromOrNull" since it returns null, and added one
  that does not allow null.
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
- spited the categorizeInto function into multiple functions to simplify it as well as potentially allowing other kinds
  of usage.
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
- uniformed extensions on numbers (float, double, int, long, short, byte, now have +- the same extensions regarding
  positive, negative ect)
- extracted the L logging part into a class, such that it can be used in various ways, while still having a L object (
  also improves tests).
    - also updated extensions.
- gradle 5.3-rc-1

## 0.0.14

- fixed logging issue
- added async mapping extensions
- added mapToSet
- added setIfNotEmpty to map
- added a bunch of string extensions
- improved some string functions to use more of the standard library.
- moved generic code from the global namespace to generic array.
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

- JVM timing function naming (lowercase method name and millis in name). Made it usable in suspension
- removed a lot of crossinlines where they made no real sense (if the function would make sense with a return inside of
  it, and or allow it to be used in suspend functions )
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
- removed awaitAll since this is in the standard library
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
- UUID (4) as random is in kotlin STL we can now create more shared things :)
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
- kotlin 1.3 and coroutines in production.

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
