package csense.kotlin.classes.map

import csense.kotlin.extensions.collections.map.linkedHashMap.*
import kotlin.jvm.*

/**
 * [Map] with predictable iteration order
 */
public open class OrderedMap<Key, Value> private constructor(
    private val backingMap: LinkedHashMap<Key, Value>
) : Map<Key, Value> by backingMap {

    public constructor() : this(linkedMapOf())

    public constructor(items: Map<Key, Value>) : this(LinkedHashMap(items))

    public constructor(vararg items: Pair<Key, Value>) : this(linkedMapOf(*items))

    public constructor(items: List<MapEntry<Key, Value>>) : this(LinkedHashMap(items))

}

@JvmName("OrderedMapPair")
public fun <Key, Value> OrderedMap(items: List<Pair<Key, Value>>): OrderedMap<Key, Value> =
    OrderedMap(LinkedHashMap(items))
