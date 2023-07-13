@file:Suppress("MemberVisibilityCanBePrivate")

package csense.kotlin.extensions.collections.map

import csense.kotlin.classes.map.*
import csense.kotlin.extensions.mapping.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*


class MapKtTest {

    val emptyMap = mapOf<String, String>()
    val singleMap = mapOf("a" to "b")
    val nullMap = mapOf<String?, String>(null to "abc")

    @Test
    fun forEachIndexed() {
        emptyMap.forEachIndexed { _, _: Int ->
            failTest("should not be called on empty")
        }
        singleMap.forEachIndexed { entry: Map.Entry<String, String>, index: Int ->
            entry.key.assert("a")
            entry.value.assert("b")
            index.assert(0)
        }
    }

    class FilterMapKey {

        @Test
        fun empty() {
            mapOf<String, String>().filterMapKey { shouldNotBeCalled() }.assertEmpty("empty should be empty")
        }

        @Test
        fun singleReturnsFalse() {
            mapOf("a" to "1").filterMapKey {
                it.key.assert("a")
                it.value.assert("1")
                false
            }.assertEmpty("when filter returns false it should be discarded")
        }

        @Test
        fun singleReturnsTrue() = assertCalled { shouldBeCalled ->
            mapOf("a" to "1").filterMapKey {
                it.key.assert("a")
                it.value.assert("1")
                shouldBeCalled()
                true
            }.assertSingle("a") //should include items by true
        }

        @Test
        fun nullableTrue() = assertCalled { shouldBeCalled ->
            mapOf<String?, String>(null to "a").filterMapKey {
                it.key.assertNull()
                it.value.assert("a")
                shouldBeCalled()
                true
            }.assertSingle(null) // null should still work
        }

        @Test
        fun multipleAllFalse() = assertCalled(times = 2) { shouldBeCalled ->
            mapOf(
                "a" to "b",
                "b" to "c"
            ).filterMapKey {
                shouldBeCalled()
                false
            }.assertEmpty("should discard all")
        }

        @Test
        fun multipleAllTrue() = assertCalled(times = 2) { shouldBeCalled ->
            mapOf(
                "a" to "b",
                "b" to "c"
            ).filterMapKey {
                shouldBeCalled()
                true
            }.apply {
                assertSize(2, message = "should keep all")
                assertContainsAll("a", "b")
            }
        }

        @Test
        fun multipleAllBsAreTrue() = assertCalled(times = 2) { shouldBeCalled ->
            mapOf(
                "a" to "b",
                "b" to "c"
            ).filterMapKey {
                shouldBeCalled()
                it.key == "b"
            }.assertSingle("b")
        }

    }

    @Test
    fun useValueOr() {
        var notFoundCounter = 0
        emptyMap.useValueOr("notThere", { failTest("Should not get called") }, { notFoundCounter += 1 })
        notFoundCounter.assert(1)
        var foundCounter = 0
        singleMap.useValueOr("a", { foundCounter += 1 }, { failTest("should not get called") })
        foundCounter.assert(1)
        notFoundCounter = 0
        singleMap.useValueOr("b", { failTest() }, { notFoundCounter += 1 })
        notFoundCounter.assert(1)

        assertCalled { shouldBeCalled ->
            nullMap.useValueOr(null, {
                shouldBeCalled()
                it.assert("abc")
            }, { failTest("it contains null key so should use that") })
        }
    }

    class MapKVDoesNotContainKey {
        @Test
        fun empty() {
            val map = mapOf<String, String>()
            map.doesNotContainKey("1").assertTrue()
        }

        @Test
        fun single() {
            val map = mapOf("1234" to "")
            map.doesNotContainKey("1").assertTrue()
            map.doesNotContainKey("1234").assertFalse()
            map.doesNotContainKey("12345").assertTrue()
        }

        @Test
        fun multiple() {
            val map = mapOf(123 to "", 444 to "")
            map.doesNotContainKey(1).assertTrue()
            map.doesNotContainKey(2).assertTrue()
            map.doesNotContainKey(123).assertFalse()
            map.doesNotContainKey(444).assertFalse()
            map.doesNotContainKey(555).assertTrue()
        }

        @Test
        fun nullable() {
            val map = mapOf<String?, String>(
                null to "abc"
            )
            map.doesNotContainKey(null).assertFalse("contains null key")
        }

    }

    class MapOrgKeyOrgValueToMapMapEntry {
        @Test
        fun empty() {
            mapOf<String, String>().toMapViaMapEntry<String, String, String, String>(mapEntry = {
                shouldNotBeCalled()
            })
        }

        @Test
        fun single() {
            val toAssert = MapEntry("1234", "abc")
            val mapped = mapOf("test" to "1234").toMapViaMapEntry {
                toAssert
            }
            mapped.assertSingle {
                it.key.assert(toAssert.key)
                it.value.assert(toAssert.value)
            }
        }

        @Test
        fun multiple() {
            val mapped = mapOf("a" to 1, "b" to 2).toMapViaMapEntry {
                MapEntry(it.key, it.value + 10)
            }
            mapped.assertSize(2)
            mapped.containsKey("a").assertTrue()
            mapped.containsKey("b").assertTrue()

            mapped["a"].assert(11)
            mapped["b"].assert(12)
        }
    }

    class MapOrgKeyOrgValueToMapMapEntryToPair {
        @Test
        fun empty() {
            mapOf<String, String>().toMapViaKeyValuePair<String, String, String, String>(mapEntryToPair = {
                shouldNotBeCalled()
            })
        }

        @Test
        fun single() {
            val toAssert = "12345" to "abc"
            val mapped = mapOf("test" to "1234").toMapViaKeyValuePair {
                toAssert
            }
            mapped.assertSingle(toAssert)
        }

        @Test
        fun multiple() {
            val mapped = mapOf("a" to 1, "b" to 2).toMapViaKeyValuePair {
                it.key to it.value + 20
            }
            mapped.assertSize(2)
            mapped.containsKey("a").assertTrue()
            mapped.containsKey("b").assertTrue()

            mapped["a"].assert(21)
            mapped["b"].assert(22)
        }
    }

    class MapOrgKeyOrgValueToMapViaMapEntry {
        @Test
        fun empty() {
            mapOf<Int, Int>().toMapViaMapEntry<Int, Int, Int, Int> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            mapOf(0 to 1).toMapViaMapEntry {
                MapEntry(it.key, it.value)
            }.assertSingle {
                it.key.assert(0)
                it.value.assert(1)
            }
            mapOf(0 to 1).toMapViaMapEntry {
                MapEntry(10, 20)
            }.assertSingle {
                it.key.assert(10)
                it.value.assert(20)
            }

        }

        @Test
        fun multipleDirect() {
            val result = mapOf(0 to 1, 10 to 20).toMapViaMapEntry {
                MapEntry(it.key, it.value)
            }

            result.assertSize(2)
            result.assertContainsKeyAnd(0) { value ->
                value.assert(1)
            }
            result.assertContainsKeyAnd(10) { value ->
                value.assert(20)
            }
        }

        @Test
        fun multipleSameKey() {
            val result = mapOf(0 to 1, 10 to 20).toMapViaMapEntry {
                MapEntry(0, 30)
            }

            result.assertSingle {
                it.key.assert(0)
                it.value.assert(30)
            }
        }

    }

    class MapOrgKeyOrgValueToMapViaKeyValuePair {
        @Test
        fun empty() {
            mapOf<Int, Int>().toMapViaKeyValuePair<Int, Int, Int, Int> { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            mapOf(0 to 1).toMapViaKeyValuePair {
                it.key to it.value
            }.assertSingle {
                it.key.assert(0)
                it.value.assert(1)
            }
            mapOf(0 to 1).toMapViaKeyValuePair {
                10 to 20
            }.assertSingle {
                it.key.assert(10)
                it.value.assert(20)
            }

        }

        @Test
        fun multipleSameKey() {
            val result = mapOf(0 to 1, 10 to 20).toMapViaKeyValuePair {
                0 to 10
            }

            result.assertSingle {
                it.key.assert(0)
                it.value.assert(10)
            }
        }
    }

    class MapKVForeachBackwards {

        @Test
        fun empty() {
            mapOf<String, String>().foreachBackwards { shouldNotBeCalled() }
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            mapOf("test" to "1234").foreachBackwards {
                it.key.assert("test")
                it.value.assert("1234")
                shouldBeCalled()
            }
        }

        @Test
        fun multiple() = assertCalled(times = 2) { shouldBeCalled ->
            var haveCalled = false
            mapOf("first" to 0, "last" to 1).foreachBackwards {
                it.key.assert(haveCalled.map("first", "last"))
                it.value.assert(haveCalled.map(0, 1))
                haveCalled = true
                shouldBeCalled()
            }
        }
    }

    class MapKeyValueHasSameKeys {

        @Test
        fun empty() {
            mapOf<String, String>().hasSameKeys(mapOf()).assertTrue("nothing is the same as nothing")
        }

        @Test
        fun nullableKeys() {
            mapOf<String?, String>().hasSameKeys(mapOf()).assertTrue()
            mapOf<String?, String>(
                null to "a"
            ).hasSameKeys(
                mapOf(
                    null to "b"
                )
            ).assertTrue()
            mapOf<String?, String>(
                null to "a"
            ).hasSameKeys(
                mapOf(
                    "null" to "b"
                )
            ).assertFalse()
        }

        @Test
        fun singleNotSameSize() {
            mapOf("a" to "b").hasSameKeys(mapOf()).assertFalse()
            mapOf<String, String>().hasSameKeys(mapOf("a" to "b")).assertFalse()
        }

        @Test
        fun singleSameSizeNotSameKeys() {
            mapOf("d" to "e").hasSameKeys(mapOf("a" to "b")).assertFalse()
        }

        @Test
        fun singleSameKeyShouldIgnoreContent() {
            mapOf("a" to "c").hasSameKeys(mapOf("a" to "b")).assertTrue()
            mapOf("a" to "b").hasSameKeys(mapOf("a" to "b")).assertTrue()
        }


        @Test
        fun multipleNotSameSize() {
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameKeys(mapOf()).assertFalse("different size")
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameKeys(
                mapOf(
                    "a" to "b"
                )
            ).assertFalse("different size")
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameKeys(
                mapOf(
                    "a" to "b",
                    "1" to "2",
                    "q" to "w"
                )
            ).assertFalse("different size")
        }

        @Test
        fun multipleDifferentKeys() {
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameKeys(
                mapOf(
                    "0" to "1",
                    "q" to "w"
                )
            ).assertFalse()

            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameKeys(
                mapOf(
                    "a" to "1",
                    "q" to "w"
                )
            ).assertFalse()
        }

        @Test
        fun multipleSameKeys() {
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameKeys(
                mapOf(
                    "a" to "b",
                    "1" to "2"
                )
            ).assertTrue()
            mapOf(
                "a" to "q",
                "1" to "w"
            ).hasSameKeys(
                mapOf(
                    "a" to "e",
                    "1" to "r"
                )
            ).assertTrue()
        }
    }

    class HasSameContent {
        @Test
        fun empty() {
            mapOf<String, String>().hasSameContent(mapOf()).assertTrue("nothing is the same as nothing")
        }

        @Test
        fun singleNotSameSize() {
            mapOf("a" to "b").hasSameContent(mapOf()).assertFalse()
            mapOf<String, String>().hasSameContent(mapOf("a" to "b")).assertFalse()
        }

        @Test
        fun singleSameSizeNotSameKeys() {
            mapOf("d" to "e").hasSameContent(mapOf("a" to "b")).assertFalse()
        }

        @Test
        fun singleSameKeyDifferentContent() {
            mapOf("a" to "c").hasSameContent(mapOf("a" to "b")).assertFalse()
        }


        @Test
        fun multipleNotSameSize() {
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameContent(mapOf()).assertFalse("different size")
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameContent(
                mapOf(
                    "a" to "b"
                )
            ).assertFalse("different size")
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameContent(
                mapOf(
                    "a" to "b",
                    "1" to "2",
                    "q" to "w"
                )
            ).assertFalse("different size")
        }

        @Test
        fun multipleDifferentKeys() {
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameContent(
                mapOf(
                    "0" to "1",
                    "q" to "w"
                )
            ).assertFalse()

            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameContent(
                mapOf(
                    "a" to "1",
                    "q" to "w"
                )
            ).assertFalse()
        }

        @Test
        fun multipleSameKeys() {
            mapOf(
                "a" to "b",
                "1" to "2"
            ).hasSameContent(
                mapOf(
                    "a" to "b",
                    "1" to "2"
                )
            ).assertTrue("same values should yield true")
            mapOf(
                "a" to "q",
                "1" to "w"
            ).hasSameContent(
                mapOf(
                    "a" to "e",
                    "1" to "r"
                )
            ).assertFalse("different values should yield false")
        }
    }

    class MapKeyValueHasSameContentBy {

        @Test
        fun empty() {
            mapOf<String, String>().hasSameContentBy(
                mapOf()
            ) { _, _ ->
                shouldNotBeCalled()
            }.assertTrue("nothing is the same as nothing")
        }

        @Test
        fun differentSizes() {
            mapOf(
                "a" to "b"
            ).hasSameContentBy(
                mapOf()
            ) { _, _ -> shouldNotBeCalled() }.assertFalse()
            mapOf(
                "a" to "b",
                "2" to "1"
            ).hasSameContentBy(
                mapOf()
            ) { _, _ -> shouldNotBeCalled() }.assertFalse()
            mapOf<String, String>().hasSameContentBy(
                mapOf(
                    "a" to "b",
                    "2" to "1"
                )
            ) { _, _ -> shouldNotBeCalled() }.assertFalse()
        }

        @Test
        fun sameSizeReturnsFalseSingle() = assertCalled(times = 1) {
            mapOf(
                "a" to "b"
            ).hasSameContentBy(
                mapOf(
                    "a" to "b"
                ),
                compareValue = { first, second ->
                    first.assert("b")
                    second.assert("b")
                    it()
                    false
                }
            ).assertFalse("should be false when compareValue returns false")
        }

        @Test
        fun sameSizeReturnsFalseMultiple() = assertCalled(times = 1, message = "should only be called once") {
            mapOf(
                "a" to "b",
                "b" to "a"
            ).hasSameContentBy(
                mapOf(
                    "a" to "b",
                    "b" to "a"
                ),
                compareValue = { _, _ ->
                    it()
                    false
                }
            ).assertFalse("should be false when compareValue returns false")
        }

        @Test
        fun sameSizeReturnsTrueSingle() = assertCalled(times = 1) {
            mapOf(
                "a" to "b"
            ).hasSameContentBy(
                mapOf(
                    "a" to "b"
                ),
                compareValue = { first, second ->
                    first.assert("b")
                    second.assert("b")
                    it()
                    true
                }
            ).assertTrue("when compareValue returns true the result should be true")
        }

        @Test
        fun sameSizeReturnsTrueMultiple() =
            assertCalled(
                times = 2,
                message = "should call compareValue for each item until either one is false or all is true"
            ) {
                mapOf(
                    "a" to "b",
                    "b" to "a"
                ).hasSameContentBy(
                    mapOf(
                        "a" to "b",
                        "b" to "a"
                    ),
                    compareValue = { _, _ ->
                        it()
                        true
                    }
                ).assertTrue("when compareValue returns true the result should be true")
            }


    }

    class ReverseKeyValue {

        @Test
        fun empty() {
            val empty = mapOf<String, Int>().reverseKeyValue()
            empty.assertEmpty()
        }


        @Test
        fun single() {
            val single = mapOf("abc" to 42).reverseKeyValue()
            single.assertIs<Map<Int, String>>()
            single.assertSingle {
                it.key.assert(42)
                it.value.assert("abc")
            }
        }


        @Test
        fun multipleNoCollisions() {
            val multiple = mapOf("abc" to 42, "1234" to 500).reverseKeyValue()
            multiple.assertIs<Map<Int, String>>()
            multiple.assertSize(2)
            multiple.assertContainsKeyAnd(42) {
                it.assert("abc")
            }
            multiple.assertContainsKeyAnd(500) {
                it.assert("1234")
            }
        }

        @Test
        fun collisions() {
            val multiple = mapOf("abc" to 500, "1234" to 500).reverseKeyValue()
            multiple.assertIs<Map<Int, String>>()
            multiple.assertSize(1)
            multiple.assertContainsKeyAnd(500) {
                it.assert("1234", message = "last entry wins")
            }
        }

    }

    class IsNotNullOrEmpty {

        @Test
        fun nullable() {
            val nullable: Map<String, String>? = null
            nullable.isNotNullOrEmpty().assertFalse("is null")
        }

        @Test
        fun empty() {
            val map: Map<String, String>? = mapOf()
            map.isNotNullOrEmpty().assertFalse("is empty")
        }


        @Test
        fun single() {
            val map: Map<String, String>? = mapOf("a" to "1")
            map.isNotNullOrEmpty().assertTrue("has content")
        }


        @Test
        fun multiple() {
            val map: Map<String, String>? = mapOf(
                "a" to "1",
                "b" to "2"
            )
            map.isNotNullOrEmpty().assertTrue("has content")
        }

    }
}

