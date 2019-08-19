package csense.kotlin.ds.cache

import csense.kotlin.test.assertions.*
import kotlin.test.*

class SimpleLRUCacheTest {

    @Test
    fun put() {
        val smallCache = SimpleLRUCache<String, String>(1)
        smallCache.put("1234", "1234").assertNull("should not evict any key")
        smallCache.put("abc", "abc").assertNotNullAndEquals("1234", "should evict the first key")

        val largeCache = SimpleLRUCache<String, String>(5)
        for (i in 0 until 5) {
            largeCache.put(i.toString(), i.toString())
        }
        //now lets see that we also get the order and the count still matches.
        largeCache.put("a1", "a1").assertNotNullAndEquals("0")
        largeCache.put("a2", "a2").assertNotNullAndEquals("1")
        largeCache.put("a3", "a3").assertNotNullAndEquals("2")
        largeCache.put("a4", "a4").assertNotNullAndEquals("3")
        largeCache.put("a5", "a5").assertNotNullAndEquals("4")
    }

    @Test
    fun containsKey() {
        val smallCache = SimpleLRUCache<String, String>(2)
        smallCache.put("1234", "1234")
        smallCache.containsKey("1234").assertTrue()
        smallCache.containsKey("123").assertFalse()
        smallCache.containsKey("abc").assertFalse()
        smallCache.containsKey("1").assertFalse()
        smallCache.containsKey("_").assertFalse()
        smallCache.put("random", "omg")
        smallCache.containsKey("1234").assertTrue()
        smallCache.containsKey("random").assertTrue()
        smallCache.containsKey("123").assertFalse()
        smallCache.containsKey("rando").assertFalse()
        smallCache.containsKey("random2").assertFalse()
        smallCache.put("12345", "2")
        smallCache.containsKey("1234").assertFalse()
        smallCache.containsKey("random").assertTrue()
        smallCache.containsKey("12345").assertTrue()
    }

    @Test
    fun notContainsKey() {
        val smallCache = SimpleLRUCache<String, String>(2)
        smallCache.put("1234", "1234")
        smallCache.notContainsKey("1234").assertFalse()

        smallCache.notContainsKey("123").assertTrue()
        smallCache.notContainsKey("abc").assertTrue()
        smallCache.notContainsKey("1").assertTrue()
        smallCache.notContainsKey("_").assertTrue()
        smallCache.put("random", "omg")
        smallCache.notContainsKey("1234").assertFalse()
        smallCache.notContainsKey("random").assertFalse()

        smallCache.notContainsKey("123").assertTrue()
        smallCache.notContainsKey("rando").assertTrue()
        smallCache.notContainsKey("random2").assertTrue()
        smallCache.put("12345", "2")
        smallCache.notContainsKey("1234").assertTrue()

        smallCache.notContainsKey("random").assertFalse()
        smallCache.notContainsKey("12345").assertFalse()
    }

    @Test
    @Ignore
    fun get() {
    }

    @Test
    @Ignore
    fun getOrRemove() {
    }

    @Test
    @Ignore
    fun remove() {
    }

    @Test
    @Ignore
    fun setCacheSize() {
    }

    @Test
    @Ignore
    fun getOrPut() {
    }

    @Test
    fun set() {
        val smallCache = SimpleLRUCache<String, String>(1)
        smallCache.set("1234", "1234").assertNull("should not evict any key")
        smallCache.set("abc", "abc").assertNotNullAndEquals("1234", "should evict the first key")

        val largeCache = SimpleLRUCache<String, String>(5)
        for (i in 0 until 5) {
            largeCache[i.toString()] = i.toString()
        }
        //now lets see that we also get the order and the count still matches.
        largeCache.set("a1", "a1").assertNotNullAndEquals("0")
        largeCache.set("a2", "a2").assertNotNullAndEquals("1")
        largeCache.set("a3", "a3").assertNotNullAndEquals("2")
        largeCache.set("a4", "a4").assertNotNullAndEquals("3")
        largeCache.containsKey("4").assertTrue()
        largeCache["a5"] = "a5"
        largeCache.containsKey("4").assertFalse()
    }
}