# Changelog

## 0.1.0

This is the culmination of a lot of updates and breaking changes.(in particular namespace changes.)

In short the logging framework have been completely revamp.
Old deprecations are removed.
Many extensions have been split into sub namespaces, due to the sheer size / amount of them.
Some extensions are moved into a more "specific" namespace (eg the construct such as "log", see "logging" below)

### Logging

All regular "logClass<Level>" are now presented on a "log" namespace to avoid polluting global namespace with more
top-level functions.

```kotlin
class MyClass {
    fun doAction() {
        log.debug("hmm not sure")
    }
}

fun work() {
    val myClass = MyClass()
    myClass.log.debug("logging on behalf of myClass")
}
```

### Before 0.1.0

see [documentation/changelog-0.0.x.md](documentation/changelog-0.0.x.md)
