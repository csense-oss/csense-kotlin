package csense.kotlin.ds.cache

import csense.kotlin.extensions.*
import kotlin.also

/**
 * Simple LRU cache with good time complexity but a somewhat "higher" memory usage. (time over memory)
 * @param Key
 * @param Value
 * @property cacheSize Int
 * @property map HashMap<Key, Value>
 * @property order ArrayList<Key>
 * @constructor
 */
class SimpleLRUCache<Key, Value>(private var cacheSize: Int) {

    //TODO consider linked hashmap, potentially own edition where we can query the order since this is a "boring" replica of that.
    private val map = HashMap<Key, Value>(cacheSize)

    private val order = arrayListOf<Key>()

    /**
     *
     * @param key Key
     * @param value Value
     * @return Key?
     * @TimeComplexity O(1)
     */
    fun put(key: Key, value: Value): Key? {
        val evictedKey: Key? = shouldEvict().mapLazy(
                ifTrue = { evict() },
                ifFalse = { null })

        map[key] = value
        order.add(key)
        return evictedKey
    }

    fun containsKey(key: Key) = map.containsKey(key)

    fun notContainsKey(key: Key) = !containsKey(key)

    /**
     *
     * @return Boolean true if we are at max size thus we have to evict.
     */
    private fun shouldEvict(): Boolean = map.size >= cacheSize

    /**
     *
     * @return Key?
     * @TimeComplexity O(1)
     */
    private fun evict(): Key? = getKeyToEvict().also {
        map.remove(it)
    }

    /**
     *
     * @param key Key
     * @return Value?
     * @TimeComplexity O(1)
     */
    operator fun get(key: Key): Value? = map[key]

    /**
     * Gets a given value , and if there and the given condition is met the value is returned,
     * if the condition is not met, the item is evicted and null is returned.
     * @return value?
     * @TimeComplexity O(1) to O(n) if condition is false, n = size of data
     */
    fun getOrRemove(key: Key, condition: Function2<Key, Value, Boolean>): Value? {
        val value: Value = get(key) ?: return null
        return if (condition(key, value)) {
            value
        } else {
            remove(key)
            null
        }
    }

    /**
     *
     * @return Key?
     * @TimeComplexity O(1)
     */
    private fun getKeyToEvict(): Key? {
        return if (order.isNotEmpty()) {
            order.removeAt(0)
        } else {
            null
        }
    }

    /**
     *
     * @param key Key
     * @TimeComplexity O(n) where n = size of data
     */
    fun remove(key: Key) {
        map.remove(key)
        order.remove(key)
    }

    /**
     * Allows you to change the size of this LRU cache
     * @param newSize Int the new size to use.
     * @TimeComplexity O(1)
     */
    fun setCacheSize(newSize: Int) {
        cacheSize = newSize
    }

    /**
     *
     * @param key Key
     * @param value Function0<Value>
     * @return Value
     * @TimeComplexity O(1)
     */
    fun getOrPut(key: Key, value: () -> Value): Value {
        val haveKey = get(key)
        return if (haveKey != null) {
            haveKey
        } else {
            val computedValue = value()
            put(key, computedValue)
            computedValue
        }
    }

    /**
     *
     * @param key Key
     * @param value Value
     * @return Key?
     * @TimeComplexity O(1)
     */
    operator fun set(key: Key, value: Value): Key? = put(key, value)
}