package org.csenseoss.kotlin.annotations

/**
 * Used to ignore a given method for kover(x)
 * Use ONLY for say compile time deprecation errors
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
internal annotation class KoverIgnore

