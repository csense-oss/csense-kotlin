#Csense kotlin

This project contains very basic kotlin only related extensions and functionality.
It is the building block for most csense projects.

## Changelog
### 0.0.1
- moved all general commonsense code from various projects into this.
- primary setup
- this especially mean the commonsenseandroidkotlin project.

## Roadmap
The next part of the roadmap is 100% coverage (+-)
along with unifying extensions on specifics (such as arrays  / BooleanArray .. ect)

 

## Content

### Extensions

### Types

### Patterns

#### Expected
The Expected pattern is a simply put, a dual way of handling exceptions and bad results.
The point is, instead of forcing to throw exceptions, on behalf of the caller, the caller gets to
decide how he/she will handle the result of a function; 
this means that if checking the result via "isValid", is more appropiat, then no exceptions will be thrown,
but if that is the intended way one can simply access the value, which in the case of errors will throw, 
just like a regular throwing method would. 
This means that its up to the caller rather then the calle, to decide how the program handles "expected" results
(bad / good results)

 