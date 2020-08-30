package csense.kotlin.extensions.collections

import csense.kotlin.annotations.numbers.*

public typealias GenericGetterIndexMethod<T> = (index: @IntLimit(from = 0) Int) -> T

public typealias GenericSetterIndexMethod<T> = (index: @IntLimit(from = 0) Int, item: T) -> Unit