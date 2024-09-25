package org.csenseoss.kotlin.annotations

/**
 * Marks the given (class) constructor as "not the intended" use case, and instead tells the user that they should use any "instance" there might be (the singleton pattern).
 */
@Retention(AnnotationRetention.BINARY)
@RequiresOptIn("Use the instance (singleton pattern) on this class", level = RequiresOptIn.Level.ERROR)
@Target(AnnotationTarget.CONSTRUCTOR)
public annotation class UseSingletonInstance