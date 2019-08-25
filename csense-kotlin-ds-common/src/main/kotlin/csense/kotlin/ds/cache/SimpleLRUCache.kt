@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package csense.kotlin.ds.cache

import csense.kotlin.extensions.*
import csense.kotlin.extensions.primitives.*
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

    init {
        //make sure no one is evil enough to try and set no valid cache size.
        cacheSize = getLeastValidCacheSize(cacheSize)
    }

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

    /**
     *
     * @param key Key
     * @return Boolean
     * @TimeComplexity O(1)
     */
    fun containsKey(key: Key) = map.containsKey(key)

    /**
     *
     * @param key Key
     * @return Boolean
     * @TimeComplexity O(1)
     */
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
     * @TimeComplexity O(n)
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
     * if the size is lower, the "oldest" entry(/ies) will be purged.
     * @param newSize Int the new size to use.
     * @TimeComplexity O(n)
     */
    fun setCacheSize(newSize: Int) {
        if (newSize < cacheSize) {
            //reduce size => remove oldest
            removeOldest(cacheSize - newSize)
        }
        cacheSize = getLeastValidCacheSize(newSize)
    }

    /**
     * Removes the first count elements
     * @param count Int
     * @TimeComplexity O(n)
     */
    fun removeOldest(count: Int) = count.forEach {
        getKeyToEvict()?.let(this::remove)
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


    /**
     * Clears all data.
     * @TimeComplexity O(1)
     */
    fun clear() {
        map.clear()
        order.clear()
    }

    /**
     * Get the least valid cache size, so take unsafe input and turns it safe (sane)
     * @param size Int
     * @return Int a safe value for this cache size least valued.
     * @TimeComplexity O(1)
     */
    private fun getLeastValidCacheSize(size: Int): Int {
        return maxOf(size, 1)
    }
}