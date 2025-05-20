package org.csenseoss.kotlin.extensions.collections

import org.csenseoss.kotlin.annotations.numbers.limit.*

public typealias GenericGetterIndexMethod<T> = (index: @IntLimit(from = 0) Int) -> T

public typealias GenericSetterIndexMethod<T> = (index: @IntLimit(from = 0) Int, item: T) -> Unit

public typealias Predicate<Item> = (item: Item) -> Boolean

public typealias PredicateIndexed<Item> = (index: @IntLimit(from = 0) Int, item: Item) -> Boolean

public typealias PredicateCount<Item> = (count: @IntLimit(from = 0) Int, item: Item) -> Boolean