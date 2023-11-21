@file:Suppress("unused", "NOTHING_TO_INLINE", "INVISIBLE_MEMBER")

package csense.kotlin.extensions.general

import kotlin.contracts.*

public inline fun isAnyNull(
    first: Any?
): Boolean {
    contract {
        returns(true) implies (first == null)
        returns(false) implies (first != null)
    }
    return first == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null)
        returns(false) implies (first != null && second != null)
    }
    return first == null || second == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null)
        returns(false) implies (first != null && second != null && third != null)
    }
    return first == null || second == null || third == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null || fourth == null)
        returns(false) implies (first != null && second != null && third != null && fourth != null)
    }
    return first == null || second == null || third == null || fourth == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null || fourth == null || fifth == null)
        returns(false) implies (first != null && second != null && third != null && fourth != null && fifth != null)
    }
    return first == null || second == null || third == null || fourth == null || fifth == null
}


public inline fun isAnyNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?,
    sixth: Any?
): Boolean {
    contract {
        returns(true) implies (first == null || second == null || third == null || fourth == null || fifth == null || sixth == null)
        returns(false) implies (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null)
    }
    return first == null || second == null || third == null || fourth == null || fifth == null || sixth == null
}


public inline fun isAnyNotNull(
    first: Any?
): Boolean {
    contract {
        returns(false) implies (first == null)
        returns(true) implies (first != null)
    }
    return first != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null)
        returns(true) implies (first != null && second != null)
    }
    return first != null || second != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null)
        returns(true) implies (first != null || second != null || third != null)
    }
    return first != null || second != null || third != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null || fourth == null)
        returns(true) implies (first != null || second != null || third != null || fourth != null)
    }
    return first != null || second != null || third != null || fourth != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null || fourth == null || fifth == null)
        returns(true) implies (first != null || second != null || third != null || fourth != null || fifth != null)
    }
    return first != null || second != null || third != null || fourth != null || fifth != null
}


public inline fun isAnyNotNull(
    first: Any?,
    second: Any?,
    third: Any?,
    fourth: Any?,
    fifth: Any?,
    sixth: Any?
): Boolean {
    contract {
        returns(false) implies (first == null || second == null || third == null || fourth == null || fifth == null || sixth == null)
        returns(true) implies (first != null || second != null || third != null || fourth != null || fifth != null || sixth != null)
    }
    return first != null || second != null || third != null || fourth != null || fifth != null || sixth != null
}
