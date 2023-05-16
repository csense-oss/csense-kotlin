package csense.kotlin.extensions.collections.generic.collectionBounds.operations

import csense.kotlin.extensions.collections.generic.collectionBounds.*


public inline val Collection<*>.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val CharSequence.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = length)

public inline val Map<*, *>.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

//region array types
public inline val Array<*>.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val BooleanArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val ByteArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val CharArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val DoubleArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val FloatArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val IntArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val LongArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)

public inline val ShortArray.isIndex: CollectionBounds
    get() = CollectionBounds(collectionLength = size)
//endregion
