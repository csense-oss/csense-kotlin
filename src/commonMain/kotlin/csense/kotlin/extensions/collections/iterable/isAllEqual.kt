package csense.kotlin.extensions.collections.iterable

import csense.kotlin.extensions.comparable.*
import csense.kotlin.extensions.equality.*
import csense.kotlin.extensions.general.*
import kotlin.jvm.*

//
///**
// *
// * @receiver Iterable<T>
// * @return Boolean
// */
//public fun <T> Iterable<T>.isAllEqualByEquals(): Boolean {
//    val hmm = AmISure<T>()
//    return all { value ->
//        hmm.withValueOrSet(value = value) { old ->
//            old == value
//        }
//    }
//    var lastSeenType: T? = null
//    return none {
//        lastSeenType = lastSeenType ?: it
//        lastSeenType == it
//    }
//}

//
///**
// *
// * @receiver Iterable<T>
// * @return Boolean
// */
//public fun <T : Comparable<T>> Iterable<T>.isAllEqual(): Boolean {
//    val hmm = AmISure<T>()
//    return all {
//        hmm.withValueOrSet(value = it, action = it::isEqualTo)
//    }
//    var lastSeenType: T? = null
//    return all {
//        val lastSeenTypeNotNull = lastSeenType ?: it
//        lastSeenType = lastSeenTypeNotNull
//        it.isEqualTo(lastSeenTypeNotNull)
//    }
//}
//
//@JvmInline
//public value class AmISure<T> private constructor(
//    private val mutableArray: Array<Any?>
//) {
//    public constructor() : this(arrayOf(null))
//
//    @Suppress("UNCHECKED_CAST")
//    public fun <U> withValueOrSet(value: T, action: (T) -> U): U {
//        val value = (mutableArray[0] as T) ?: value
//        mutableArray[0] = value
//        return action(value)
//    }
//}