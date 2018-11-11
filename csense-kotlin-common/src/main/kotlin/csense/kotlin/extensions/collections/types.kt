package csense.kotlin.extensions.collections


typealias GenericGetterIndexMethod<T> = Function1<Int, T>
typealias GenericSetterIndexMethod<T> = Function2<Int, T, Unit>