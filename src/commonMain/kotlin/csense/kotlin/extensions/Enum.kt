@file:Suppress("unused", "NOTHING_TO_INLINE")

package csense.kotlin.extensions

//region String search for enum
/**
 * using the given value as the name from the enum to try and resolve it ,via looking at each value's name.
 * @TimeComplexity O(n * m) where n is the size of the enum, and m is the "length" of names in "average" (since we are looking at names,
 *  which in the regular simple case is char by char comparison and not a anything fancier).
 *  so if you ask for an enum extremely many times consider making a map or some other form available to optimize it.
 * @param name String the enum's name to find, or null will be returned
 * @return T either the found or null if not found.
 */
inline fun <reified T : Enum<T>> enumFromOrNull(name: String?): T? {
    return enumFromOrNull<T> { it.name == name }
}

/**
 * using the given value as the name from the enum to try and resolve it ,via looking at each value's name.
 * @TimeComplexity O(n * m) where n is the size of the enum, and m is the "length" of names in "average" (since we are looking at names,
 *  which in the regular simple case is char by char comparison and not a anything fancier).
 *  so if you ask for an enum extremely many times consider making a map or some other form available to optimize it.
 * @param name String the enum's name to find, or null will be returned
 * @param orElse T
 * @return T either the found or orElse if the name was not found.
 */
inline fun <reified T : Enum<T>> enumFromOr(name: String?, orElse: T): T {
    return enumFromOrNull<T>(name) ?: orElse
}
//endregion

//region Generalized search for enum
/**
 * A Generalized search for an enum using a find criteria. it will basically go though each enum value and if the find action
 * returns true then that value is returned. This allows null.
 * @TimeComplexity O(n * x) where n is the size of the enum, x is the "timecomplexity" of the findAction.
 * @param findAction Function1<T, Boolean> the search function
 * @param ifNotFound T? if the find action never returned true this will be returned
 * @return T? the value if findAction returns true, or ifNotFound. allowing for null
 */
inline fun <reified T : Enum<T>> enumFromOrNull(
        ifNotFound: T? = null,
        findAction: Function1<T, Boolean>
): T? {
    return enumValues<T>().find(findAction) ?: ifNotFound
}


/**
 * A Generalized search for an enum using a find criteria. it will basically go though each enum value and if the find action
 * returns true then that value is returned. this does not allow null
 * @TimeComplexity O(n * x) where n is the size of the enum, x is the "timecomplexity" of the findAction.
 * @param findAction Function1<T, Boolean> the search function
 * @param ifNotFound T if the find action never returned true this will be returned
 * @return T the value if findAction returns true, or ifNotFound.
 */
inline fun <reified T : Enum<T>> enumFromOr(
        ifNotFound: T,
        findAction: Function1<T, Boolean>
): T {
    @Suppress("RemoveExplicitTypeArguments")
    //see https://youtrack.jetbrains.net/issue/KT-32165 for the "<T>" here..
    return enumFromOrNull<T>(null, findAction) ?: ifNotFound
}
//endregion

//region Ordinal search for enum
/**
 * using the given value as the ordinal from the enum to try and resolve it ,via looking at each value.
 * @TimeComplexity O(n) where n is the size of the enum
 * @param value Int the enum ordinal to find, or null will be returned
 * @return T either the found or null if not found.
 */
inline fun <reified T : Enum<T>> enumFromOrNull(value: Int): T? {
    return enumFromOrNull<T> { it.ordinal == value }
}


/**
 * using the given value as the ordinal from the enum to try and resolve it ,via looking at each value.
 * @TimeComplexity O(n) where n is the size of the enum
 * @param value Int the enum ordinal to find, orElse will be returned
 * @param orElse T the value to return if the value is not found.
 * @return T either the found or the orElse value.
 */
inline fun <reified T : Enum<T>> enumFromOr(value: Int, orElse: T): T {
    return enumFromOrNull<T>(value) ?: orElse
}
//endregion
