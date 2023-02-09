@file:Suppress("unused", "INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package csense.kotlin.extensions.general

import kotlin.contracts.*


/**
 * Another way of writing "!is" with is not "inverse" logic (not is), this "is not"
 * NB TYPE ERASURE STILL APPLIES SO LIST<STRING> IS == LIST<OBJECT> (because they become LIST<*>)
 * @receiver [Any]
 * @return [Boolean] true if this is not the given type, false if this is
 */

public inline fun <reified T> Any.isNot(): Boolean {
    contract {
        returns(false) implies (this@isNot is T)
        returns(true) implies (this@isNot !is T)
    }
    return (this !is T)
}