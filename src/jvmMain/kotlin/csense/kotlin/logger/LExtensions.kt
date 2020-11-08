@file:Suppress("unused", "NOTHING_TO_INLINE")


package csense.kotlin.logger

import csense.kotlin.Function0R
import kotlin.reflect.KClass


//region Debug
/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.debug]
 */
public inline fun LLogger.debug(cls: Class<*>, message: String, exception: Throwable? = null) {
    debug(cls.simpleName, message, exception)
}

public inline fun LLogger.debugLazy(cls: Class<*>, noinline message: Function0R<String>, exception: Throwable? = null) {
    debugLazy(cls.simpleName, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.debug]
 */
public inline fun LLogger.debug(kClass: KClass<*>, message: String, exception: Throwable? = null) {
    debug(kClass.java, message, exception)
}

public inline fun LLogger.debugLazy(
    kClass: KClass<*>,
    noinline message: Function0R<String>,
    exception: Throwable? = null
) {
    debugLazy(kClass.java, message, exception)
}
//endregion

//region Warning
/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.warning]
 */
public inline fun LLogger.warning(cls: Class<*>, message: String, throwable: Throwable? = null) {
    warning(cls.simpleName, message, throwable)
}

public inline fun LLogger.warningLazy(
    cls: Class<*>,
    noinline message: Function0R<String>,
    throwable: Throwable? = null
) {
    warningLazy(cls.simpleName, message, throwable)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.warning]
 */
public inline fun LLogger.warning(kClass: KClass<*>, message: String, throwable: Throwable? = null) {
    warning(kClass.java, message, throwable)
}

public inline fun LLogger.warning(
    kClass: KClass<*>,
    noinline message: Function0R<String>,
    throwable: Throwable? = null
) {
    warningLazy(kClass.java, message, throwable)
}
//endregion

//region Error
/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.error]
 */
public inline fun LLogger.error(cls: Class<*>, message: String, exception: Throwable? = null) {
    error(cls.simpleName, message, exception)
}

public inline fun LLogger.errorLazy(cls: Class<*>, noinline message: Function0R<String>, exception: Throwable? = null) {
    errorLazy(cls.simpleName, message, exception)
}

/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.error]
 */
public inline fun LLogger.error(kClass: KClass<*>, message: String, exception: Throwable? = null) {
    error(kClass.java, message, exception)
}

public inline fun LLogger.errorLazy(
    kClass: KClass<*>,
    noinline message: Function0R<String>,
    exception: Throwable? = null
) {
    errorLazy(kClass.java, message, exception)
}
//endregion

//region Prod logging
/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.logProd]
 */
public inline fun LLogger.logProd(cls: Class<*>, message: String, throwable: Throwable? = null) {
    logProd(cls.simpleName, message, throwable)
}

public inline fun LLogger.logProdLazy(
    cls: Class<*>,
    noinline message: Function0R<String>,
    throwable: Throwable? = null
) {
    logProdLazy(cls.simpleName, message, throwable)
}


/**
 * Uses the simple class name for the tag, otherwise just as the regular debug method
 * see [L.logProd]
 */
public inline fun LLogger.logProd(kClass: KClass<*>, message: String, throwable: Throwable? = null) {
    logProd(kClass.java, message, throwable)
}

public inline fun LLogger.logProdLazy(
    kClass: KClass<*>,
    noinline message: Function0R<String>,
    throwable: Throwable? = null
) {
    logProdLazy(kClass.java, message, throwable)
}
//endregion


