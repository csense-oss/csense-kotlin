package csense.kotlin.extensions.collections.generic.collection.operations

import csense.kotlin.annotations.numbers.*

public typealias Function2Unit<T, U> = (first: T, second: U) -> Unit
public typealias Function2IndexedUnit<T, U> = (indexOfFirst: @IntLimit(from = 0) Int, first: T, second: U) -> Unit
