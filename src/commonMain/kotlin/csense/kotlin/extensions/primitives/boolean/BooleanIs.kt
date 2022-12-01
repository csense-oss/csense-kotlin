@file:Suppress("NOTHING_TO_INLINE")

package csense.kotlin.extensions.primitives.boolean

import csense.kotlin.*
import csense.kotlin.extensions.*
import csense.kotlin.extensions.nullabillity.*
import csense.kotlin.general.*
import kotlin.contracts.*


public inline fun Boolean.isTrue(): Boolean = this

public inline fun Boolean.isFalse(): Boolean = !this


public inline fun Boolean?.isNullOrFalse(): Boolean {
    contract {
        returns(false) implies (this@isNullOrFalse != null)
    }
    return isNull() || isFalse()
}

@Deprecated(
    message = "receiver known at compile time to not be null, thus isNull is always false. Use == false instead",
    level = DeprecationLevel.ERROR
)
public inline fun Boolean.isNullOrFalse(): Nothing = unexpected()


public inline fun Boolean?.isNullOrTrue(): Boolean {
    contract {
        returns(false) implies (this@isNullOrTrue != null)
    }
    return isNull() || isTrue()
}

@Deprecated(
    message = "receiver known at compile time to not be null, thus isNull is always false. Use == true instead",
    level = DeprecationLevel.ERROR
)
public inline fun Boolean.isNullOrTrue(): Nothing = unexpected()




public inline fun Boolean?.isNotNullOrTrue(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrTrue != null)
    }
    return isNotNull() && isFalse()
}

@Deprecated(
    message = "receiver known at compile time to not be null, thus isNotNull is always true. Use == true instead",
    level = DeprecationLevel.ERROR
)
public inline fun Boolean.isNotNullOrTrue(): Nothing = unexpected()




public inline fun Boolean?.isNotNullOrFalse(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrFalse != null)
    }
    return isNotNull() && isTrue()
}

@Deprecated(
    message = "receiver known at compile time to not be null, thus isNotNull is always true. Use == false instead",
    level = DeprecationLevel.ERROR
)
public inline fun Boolean.isNotNullOrFalse(): Nothing = unexpected()