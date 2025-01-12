@file:Suppress("unused")

package org.csenseoss.kotlin.extensions.collections.collection

import org.csenseoss.kotlin.tests.assertions.collections.iterable.*
import org.csenseoss.kotlin.tests.assertions.collections.map.*
import org.csenseoss.kotlin.tests.assertions.collections.map.iterable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.csenseoss.kotlin.tests.assertions.primitives.charSequence.*
import kotlin.test.*

class ToMapTest {
    class CollectionItemToMapFlatKeyMapper {
        @Test
        fun empty() {
            listOf<String>().toMapFlat { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf("1234").toMapFlat {
                it.assert("1234")
                it.toInt()
            }.assert(1234 to "1234")
        }

        @Test
        fun multipleDifferentKey() {
            val data: List<String> = listOf("123", "abc")
            assertCallbackCalledWith(data) { assertIsExpected: (String) -> Unit ->
                data.toMapFlat { it: String ->
                    assertIsExpected(it)
                    "$it-"
                }.apply {
                    assertSize(2)
                    assertContains(
                        Pair("123-", "123")
                    )
                    assertContains(
                        Pair("abc-", "abc")
                    )
                }
            }
        }

        @Test
        fun multipleSameKey() {
            val data: List<String> = listOf("123", "abc")
            assertCallbackCalledWith(data) { assertIsExpected: (String) -> Unit ->
                data.toMapFlat { it: String ->
                    assertIsExpected(it)
                    "test"
                }.assert("test" to "abc", message = "123 gets overwritten by abc")
            }
        }
    }

    class CollectionItemToMutableMapFlatKeyMapper {
        @Test
        fun empty() {
            listOf<String>().toMutableMapFlat { shouldNotBeCalled() }
        }

        @Test
        fun single() {
            listOf("1234").toMutableMapFlat {
                it.assert("1234")
                it.toInt()
            }.assert(1234 to "1234")

        }

        @Test
        fun multipleSameKey() {
            val data: List<String> = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMapFlat {
                    expectedValue(it)
                    "test"
                }.assert("test" to "abc", message = "abc overwrites 1234")

            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMapFlat {
                    expectedValue(it)
                    it
                }
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", "1234"
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", "abc"
                    )
                )
            }
        }
    }

    class CollectionItemToMapKeyMapperFlatValueMapper {
        @Test
        fun empty() {
            listOf<String>().toMapFlat(
                keyMapper = { shouldNotBeCalled() },
                valueMapper = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() {
            listOf("555").toMapFlat(
                keyMapper = {
                    it.assert("555")
                    "key"
                },
                valueMapper = {
                    it.assert("555")
                    "value"
                }
            ).assert("key" to "value")
        }

        @Test
        fun multipleDifferentKey() {
            val data: List<String> = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
                val result: Map<String, Int> = data.toMapFlat(keyMapper = {
                    expectedValue(it)
                    it
                }, valueMapper = {
                    it.length
                })
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", 4
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", 3
                    )
                )
            }
        }

        @Test
        fun multipleSameKey() {
            val data: List<String> = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
                val result: Map<String, Int> = data.toMapFlat(keyMapper = {
                    expectedValue(it)
                    "qwerty"
                }, valueMapper = {
                    it.length
                })
                result.assert("qwerty" to 3, message = "length of abc")
            }
        }
    }

    class CollectionItemToMutableMapFlatKeyMapperValueMapper {
        @Test
        fun empty() {
            listOf<String>().toMutableMapFlat(
                keyMapper = { shouldNotBeCalled() },
                valueMapper = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() {
            listOf("555").toMutableMapFlat(
                keyMapper = {
                    it.assert("555")
                    "key"
                },
                valueMapper = {
                    it.assert("555")
                    "value"
                }
            ).assert("key" to "value")
        }
    }

    @Test
    fun multipleSameKey() {
        val data = listOf("123", "abc")
        assertCallbackCalledWith(data) { expectedValue ->
            val result = data.toMutableMapFlat(
                keyMapper = {
                    expectedValue(it)
                    "key"
                },
                valueMapper = {
                    it.length
                }
            )
            result.assert("key" to 3, message = "length of abc")
        }
    }

    @Test
    fun multipleDifferentKey() {
        val data = listOf("123", "abc")
        assertCallbackCalledWith(data) { expectedValue ->
            val result = data.toMutableMapFlat(
                keyMapper = {
                    expectedValue(it)
                    it
                },
                valueMapper = {
                    it
                }
            )
            result.assertSize(2)
            result.assertContains(
                Pair(
                    "123", "123"
                )
            )
            result.assertContains(
                Pair(
                    "abc", "abc"
                )
            )
        }

    }
}


class CollectionItemToMapKeyMapper {
    @Test
    fun empty() {
        listOf<String>().toMap {
            shouldNotBeCalled()
        }.assertEmpty()
    }

    @Test
    fun single() {
        val map: Map<String, List<String>> = listOf("abc").toMap {
            "key"
        }
        map.assert("key" to listOf("abc"))
    }

    @Test
    fun multipleDifferentKeys() {
        val data: List<String> = listOf("1234", "456")
        assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
            val size: Map<String, List<String>> = data.toMap { it: String ->
                expectedValue(it)
                it
            }
            size.assert(
                "1234" to listOf("1234"),
                "456" to listOf("456")
            )

        }
    }

    @Test
    fun multipleSameKey() {
        val data: List<String> = listOf("1234", "456")
        assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
            val size: Map<String, List<String>> = data.toMap { it: String ->
                expectedValue(it)
                "key"
            }
            size.assert("key" to listOf("1234", "456"))
        }
    }
}

class CollectionItemToMapKeyMapperValueMapper {
    @Test
    fun empty() {
        listOf<String>().toMap(
            keyMapper = {

            }, valueMapper = {

            }
        ).assertEmpty()
    }

    @Test
    fun single() {
        listOf("1234").toMap(
            keyMapper = {
                it.assert("1234")
                "key"
            }, valueMapper = {
                it.assert("1234")
                "value"
            }
        ).assert("key" to listOf("value"))
    }

    @Test
    fun multipleDifferentKeys() {
        val data: List<String> = listOf("1234", "456")
        assertCallbackCalledWith(data) { expectedKey ->
            assertCallbackCalledWith(data) { expectedValue ->
                val size = data.toMap(
                    keyMapper = {
                        expectedKey(it)
                        it
                    }, valueMapper = {
                        expectedValue(it)
                        it
                    }
                )
                size.assert(
                    "1234" to listOf("1234"),
                    "456" to listOf("456")
                )
            }
        }
    }

    @Test
    fun multipleSameKey() {
        val data: List<String> = listOf("1234", "456")
        assertCallbackCalledWith(data) { expectedKey: (String) -> Unit ->
            assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
                val size: Map<String, List<String>> = data.toMap(
                    keyMapper = { it: String ->
                        expectedKey(it)
                        "key"
                    }, valueMapper = { it: String ->
                        expectedValue(it)
                        it
                    }
                )
                size.assert("key" to listOf("1234", "456"))
            }
        }
    }
}

class CollectionItemToMutableMapKeyMapper {

    @Test
    fun empty() {
        listOf<String>().toMutableMap {
            shouldNotBeCalled()
        }.assertEmpty()
    }


    @Test
    fun single() {
        listOf("1234").toMutableMap { it: String ->
            it.assert("1234")
            it
        }.assert("1234" to listOf("1234"))
    }


    @Test
    fun multipleSameKey() {
        val data: List<String> = listOf("1234", "abc")
        assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
            data.toMutableMap { it: String ->
                expectedValue(it)
                "key"
            }.assert("key" to listOf("1234", "abc"))
        }
    }

    @Test
    fun multipleDifferentKey() {
        val data = listOf("1234", "abc")
        assertCallbackCalledWith(data) { expectedValue ->
            val result = data.toMutableMap {
                expectedValue(it)
                it
            }
            result.assert(
                "1234" to listOf("1234"),
                "abc" to listOf("abc")
            )
        }
    }

}

class CollectionItemToMutableMapKeyMapperValueMapper {

    @Test
    fun empty() {
        listOf<String>().toMutableMap(
            keyMapper = {
                shouldNotBeCalled()
            },
            valueMapper = {
                shouldNotBeCalled()
            }
        ).assertEmpty()
    }


    @Test
    fun single() {
        listOf("1234").toMutableMap(keyMapper = {
            it.assert("1234")
            "key"
        }, valueMapper = {
            it.assert("1234")
            "value"
        }).assert("key" to listOf("value"))
    }


    @Test
    fun multipleSameKey() {
        val data: List<String> = listOf("1234", "abc")
        assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
            data.toMutableMap(keyMapper = { it: String ->
                expectedValue(it)
                "key"
            }, valueMapper = { it: String ->
                "$it+"
            }).assert("key" to listOf("1234+", "abc+"))
        }
    }

    @Test
    fun multipleDifferentKey() {
        val data: List<String> = listOf("1234", "abc")
        assertCallbackCalledWith(data) { expectedValue: (String) -> Unit ->
            val result: MutableMap<String, MutableList<String>> = data.toMutableMap(keyMapper = { it: String ->
                expectedValue(it)
                it
            }, valueMapper = { it: String ->
                "$it+"
            })
            result.assert(
                "1234" to listOf("1234+"),
                "abc" to listOf("abc+")
            )
        }
    }

}

class CollectionItemToUniqueMutableMap {

    @Test
    fun empty() {
        listOf<String>().toUniqueMutableMap(
            keyMapper = { shouldNotBeCalled() },
            valueMapper = { shouldNotBeCalled() },
            onKeyCollision = { _, _ -> shouldNotBeCalled() }
        )
    }


    @Test
    fun single() {
        listOf("0").toUniqueMutableMap(
            keyMapper = { "key" },
            valueMapper = { "-$it-" },
            onKeyCollision = { _, _ -> shouldNotBeCalled() }
        ).assert("key" to "-0-")
    }


    @Test
    fun multipleCollision() {
        listOf("0", "0").toUniqueMutableMap(
            keyMapper = { "key" },
            valueMapper = { "-$it-" },
            onKeyCollision = { first, second ->
                first.assert("-0-")
                second.assert("-0-")
                "test"
            }
        ).assert("key" to "test")
    }
}

class CollectionItemToUniqueMap {
    @Test
    fun empty() {
        val map = listOf<String>().toUniqueMap(
            { shouldNotBeCalled() },
            { shouldNotBeCalled() },
            { _, _ -> shouldNotBeCalled() }
        )
        map.assertEmpty()
    }


    @Test
    fun single() {
        val map = listOf(Pair("a", "b")).toUniqueMap(
            { it.first },
            { it.second },
            { _, _ -> shouldNotBeCalled() }
        )
        map.assert("a" to "b")
    }


    @Test
    fun multipleNonColliding() {
        val map = listOf(Pair("a", "b")).toUniqueMap(
            { it.first },
            { it.second },
            { _, _ -> shouldNotBeCalled() }
        )
        map.assert("a" to "b")
    }

    @Test
    fun multipleWithColliding() {
        val map = listOf(Pair("a", "b")).toUniqueMap(
            { it.first },
            { it.second },
            { _, _ -> shouldNotBeCalled() }
        )
        map.assert("a" to "b")
    }

}

class CollectionItemMapToMutable {
    @Test
    fun empty() {
        val input = listOf<String>().mapToMutable { shouldNotBeCalled() }
        input.assertEmpty()
    }


    @Test
    fun single() = assertCalled { shouldBeCalled: () -> Unit ->
        val input = listOf(
            "test"
        ).mapToMutable {
            it.assert("test")
            shouldBeCalled()
            42
        }
        input.assert(42)
    }


    @Test
    fun multiple() = assertCalled(times = 2) { shouldBeCalled: () -> Unit ->
        val input = listOf(
            "1",
            "_2"
        ).mapToMutable {
            shouldBeCalled()
            it.length
        }
        input.assert(1, 2)
    }
}