package csense.kotlin.extensions.collections


typealias GenericGetterIndexMethod<T> = (index: Int) -> T
typealias GenericSetterIndexMethod<T> = (index: Int, item: T) -> Unit