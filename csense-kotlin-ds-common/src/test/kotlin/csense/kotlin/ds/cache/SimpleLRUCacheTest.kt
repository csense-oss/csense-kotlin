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
    fun get() {
        val cache = SimpleLRUCache<String, String>(2)
        //empty case
        cache[""].assertNull()
        cache["test"].assertNull()
        // single case
        cache["abc"] = "abc"
        cache[""].assertNull()
        cache["test"].assertNull()
        cache["abc"].assertNotNullAndEquals("abc")
        // multiple case
        cache["123"] = "4"
        cache[""].assertNull()
        cache["test"].assertNull()
        cache["abc"].assertNotNullAndEquals("abc")
        cache["123"].assertNotNullAndEquals("4")
    }

    @Test
    fun getOrRemove() {
        val cache = SimpleLRUCache<String, String>(2)
        cache["a"].assertNull()
        cache.getOrRemove("a") { _: String, value: String -> value == "1" }.assertNull() //there is none
        cache["a"] = "1"
        cache.getOrRemove("a") { key: String, _: String -> key == "1" }.assertNull("condition not ok, so should remove entry")
        cache["a"].assertNull()
        cache["a"] = "2"
        cache.getOrRemove("a") { _: String, value: String -> value == "2" }.assertNotNullAndEquals("2")
        cache["a"].assertNotNullAndEquals("2")
    }

    @Test
    fun remove() {
        val cache = SimpleLRUCache<String, String>(2)
        cache["abc"] = "123"
        cache["abc"].assertNotNull()
        cache.remove("abc").assertNotNull()
        cache["abc"].assertNull()

        cache["1"] = "a"
        cache["2"] = "b"

        cache.remove("2")

        cache["1"].assertNotNull()
        cache["2"].assertNull()
        cache.remove("1")
        cache["1"].assertNull()
    }

    @Test
    fun setCacheSize() {
        //test broken values
        val cache = SimpleLRUCache<String, String>(1)
        cache.setCacheSize(0)
        cache.set("1234", "1234").assertNull()
        cache.set("2222", "2222").assertNotNull("Should not allow cache size of 0, thus there should always be at least 1 size")
        cache.clear()
        cache.setCacheSize(-20)
        cache.set("1234", "1234").assertNull()
        cache.set("2222", "2222").assertNotNull("Should not allow cache size of -20, thus there should always be at least 1 size")
        //well lets try valid ones

        cache.setCacheSize(2)
        cache.set("1111", "").assertNull("now we should have space for 2 elements")
        cache.set("1112", "").assertNotNull("should only hold 2 items")

        cache.setCacheSize(10)
        for (i in 2 until 10) {
            cache.set("011$i", "").assertNull()
        }
        cache.set("888", "").assertNotNull("should only hold 10 items..")

        cache.setCacheSize(2)

    }

    @Test
    fun getOrPut() {
        val cache = SimpleLRUCache<String, String>(2)
        cache["a"].assertNull()
        cache.getOrPut("a") { "1" }
        cache["a"].assertNotNullAndEquals("1")
        cache.getOrPut("a") { "2" }
        cache["a"].assertNotNullAndEquals("1", "should not update value if there.")
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

    @Test
    fun badSizeForInit() {
        val broken = SimpleLRUCache<String, String>(0)
        broken.set("1234", "1234").assertNull()
        broken.set("2222", "2222").assertNotNull("Should not allow cache size of 0, thus there should always be at least 1 size")

        val negative = SimpleLRUCache<String, String>(-100)
        negative.set("1234", "1234").assertNull()
        negative.set("2222", "2222").assertNotNull("Should not allow cache size of -100, thus there should always be at least 1 size")
    }

    @Test
    fun clear() {
        val cache = SimpleLRUCache<String, String>(2)
        //make sure nothing weird happens
        cache.clear()
        //then try set some data and clear that.
        cache.set("1", "").assertNull()
        cache.set("2", "").assertNull()
        cache.clear()
        cache.set("a", "").assertNull("should not have any data")
        cache.set("b", "").assertNull("should not have any data")

    }

    @Test
    fun removeOldest() {
        val cache = SimpleLRUCache<String, String>(2)
        cache["1"] = "1"
        cache["2"] = "2"

        //try weird / bad values
        cache.removeOldest(-10)
        cache.removeOldest(0)
        //and
        cache.removeOldest(1)
        cache["1"].assertNull("1 is the oldest")
        cache["2"].assertNotNull()
        cache.removeOldest(1)
        cache["1"].assertNull()
        cache["2"].assertNull()

        cache["a"] = "a"
        cache["b"] = "b"

        cache.removeOldest(2)
        cache["a"].assertNull()
        cache["b"].assertNull()
    }
}