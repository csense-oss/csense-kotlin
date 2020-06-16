package csense.kotlin.extensions.collections

import csense.kotlin.annotations.numbers.*

//TODO when https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.annotation/-annotation-target/-t-y-p-e_-p-a-r-a-m-e-t-e-r.html is supported, change this
//@IntLimit(from = 0)
typealias GenericGetterIndexMethod<T> = (index: @IntLimit(from = 0) Int) -> T

typealias GenericSetterIndexMethod<T> = (index: Int, item: T) -> Unit