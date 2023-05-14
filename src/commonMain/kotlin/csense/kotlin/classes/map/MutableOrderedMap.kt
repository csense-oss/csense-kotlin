package csense.kotlin.classes.map

import csense.kotlin.extensions.collections.map.linkedHashMap.*
import kotlin.jvm.*

/**
 * [MutableMap] with predictable iteration order
 */
public open class MutableOrderedMap<Key, Value>
private constructor(
    private val backingMap: LinkedHashMap<Key, Value>
) : OrderedMap<Key, Value>(), MutableMap<Key, Value> by backingMap {

    override val keys: MutableSet<Key>
        get() = backingMap.keys

    override val size: Int
        get() = backingMap.size

    override val values: MutableCollection<Value>
        get() = backingMap.values

    override val entries: MutableSet<MutableMap.MutableEntry<Key, Value>>
        get() = backingMap.entries


    public constructor() : this(linkedMapOf())

    public constructor(items: Map<Key, Value>) : this(LinkedHashMap(items))

    public constructor(vararg items: Pair<Key, Value>) : this(linkedMapOf(*items))

    public constructor(items: List<MapEntry<Key, Value>>) : this(LinkedHashMap(items))


    override fun containsKey(key: Key): Boolean =
        backingMap.containsKey(key)

    override fun containsValue(value: Value): Boolean =
        backingMap.containsValue(value)

    override fun get(key: Key): Value? =
        backingMap.get(key)

    override fun isEmpty(): Boolean =
        backingMap.isEmpty()

}

@JvmName("MutableOrderedMapPair")
public fun <Key, Value> MutableOrderedMap(items: List<Pair<Key, Value>>): MutableOrderedMap<Key, Value> =
    MutableOrderedMap(LinkedHashMap(items))

