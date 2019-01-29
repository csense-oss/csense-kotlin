package csense.kotlin.extensions

import kotlin.reflect.*

/**
 * the name shown iff the kClass.SimpleName is null.
 */
const val missingName = "<no name>"

/**
 * A simpleName or the missing name for any KClass, such that handling the simpleName's nullability is kinda reduced.
 */
@Suppress("NO_REFLECTION_IN_CLASS_PATH")
val KClass<*>.safeSimpleName: String
    get () = simpleName ?: missingName

/**
 * In companion with the safeSimpleName ,there is a question on the KClass to tell iff theres is even a simpleName on it.
 * true if there are , false otherwise (and thus the safeSimpleName will  be the "missingName")
 */
@Suppress("NO_REFLECTION_IN_CLASS_PATH")
val KClass<*>.haveSimpleName: Boolean
    get() = simpleName != null