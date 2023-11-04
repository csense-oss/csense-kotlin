@file:Suppress("unused")

package csense.kotlin.extensions.collections.collection

import csense.kotlin.tests.assertions.*
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
            }.assertSingle {
                it.key.assert(1234)
                it.value.assert("1234")
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("123", "abc")
            assertCallbackCalledWith(data) { assertIsExpected ->
                data.toMapFlat {
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
            val data = listOf("123", "abc")
            assertCallbackCalledWith(data) { assertIsExpected ->
                data.toMapFlat {
                    assertIsExpected(it)
                    "test"
                }.assertSingle {
                    it.key.assert("test")
                    it.value.assert("abc", message = "123 gets overwritten by abc")
                }
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
            }.assertSingle {
                it.key.assert(1234)
                it.value.assert("1234")
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMapFlat {
                    expectedValue(it)
                    "test"
                }.assertSingle {
                    it.value.assert("abc", message = "abc overwrites 1234")
                }
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
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("value")
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMapFlat(keyMapper = {
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
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMapFlat(keyMapper = {
                    expectedValue(it)
                    "qwerty"
                }, valueMapper = {
                    it.length
                })
                result.assertSingle {
                    it.key.assert("qwerty")
                    it.value.assert(3, message = "length of abc")
                }
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
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("value")
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
                result.assertSingle {
                    it.key.assert("key")
                    it.value.assert(3, "length of abc")
                }
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
            listOf("abc").toMap {
                "key"
            }.assertSingle {
                it.key.assert("key")
                it.value.assertSingle("abc")
            }
        }

        @Test
        fun multipleDifferentKeys() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedValue ->
                val size = data.toMap {
                    expectedValue(it)
                    it
                }
                size.assertSize(2)
                size.assertContains(
                    Pair(
                        "1234",
                        listOf("1234")
                    )
                )
                size.assertContains(
                    Pair(
                        "456",
                        listOf("456")
                    )
                )
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedValue ->
                val size = data.toMap {
                    expectedValue(it)
                    "key"
                }
                size.assertSingle {
                    it.key.assert("key")
                    it.value.assertSize(2)
                    it.value.assertContainsInOrder("1234", "456")
                }
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
            ).assertSingle {
                it.key.assert("key")
                it.value.assertSingle("value")
            }
        }

        @Test
        fun multipleDifferentKeys() {
            val data = listOf("1234", "456")
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
                    size.assertSize(2)
                    size.assertContains(
                        Pair(
                            "1234",
                            listOf("1234")
                        )
                    )
                    size.assertContains(
                        Pair(
                            "456",
                            listOf("456")
                        )
                    )
                }
            }
        }

        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "456")
            assertCallbackCalledWith(data) { expectedKey ->
                assertCallbackCalledWith(data) { expectedValue ->
                    val size = data.toMap(
                        keyMapper = {
                            expectedKey(it)
                            "key"
                        }, valueMapper = {
                            expectedValue(it)
                            it
                        }
                    )
                    size.assertSingle {
                        it.key.assert("key")
                        it.value.assertSize(2)
                        it.value.assertContainsInOrder("1234", "456")
                    }
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
            listOf("1234").toMutableMap {
                it.assert("1234")
                it
            }.assertSingle {
                it.key.assert("1234")
                it.value.assertSingle("1234")
            }
        }


        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMap {
                    expectedValue(it)
                    "key"
                }.assertSingle {
                    it.key.assert("key")
                    it.value.assertSize(2)
                    it.value.assertContainsInOrder("1234", "abc")
                }
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
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", listOf("1234")
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", listOf("abc")
                    )
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
            }).assertSingle {
                it.key.assert("key")
                it.value.assertSingle("value")
            }
        }


        @Test
        fun multipleSameKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                data.toMutableMap(keyMapper = {
                    expectedValue(it)
                    "key"
                }, valueMapper = {
                    "$it+"
                }).assertSingle {
                    it.key.assert("key")
                    it.value.assertSize(2)
                    it.value.assertContainsInOrder("1234+", "abc+")
                }
            }
        }

        @Test
        fun multipleDifferentKey() {
            val data = listOf("1234", "abc")
            assertCallbackCalledWith(data) { expectedValue ->
                val result = data.toMutableMap(keyMapper = {
                    expectedValue(it)
                    it
                }, valueMapper = {
                    "$it+"
                })
                result.assertSize(2)
                result.assertContains(
                    Pair(
                        "1234", listOf("1234+")
                    )
                )
                result.assertContains(
                    Pair(
                        "abc", listOf("abc+")
                    )
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
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("-0-")
            }
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
            ).assertSingle {
                it.key.assert("key")
                it.value.assert("test")
            }
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
            map.assertSingle("a" to "b")
        }


        @Test
        fun multipleNonColliding() {
            val map = listOf(Pair("a", "b")).toUniqueMap(
                { it.first },
                { it.second },
                { _, _ -> shouldNotBeCalled() }
            )
            map.assertSingle("a" to "b")
        }

        @Test
        fun multipleWithColliding() {
            val map = listOf(Pair("a", "b")).toUniqueMap(
                { it.first },
                { it.second },
                { _, _ -> shouldNotBeCalled() }
            )
            map.assertSingle("a" to "b")
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
            input.assertSingle(42)
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
            input.assertSize(2)
            input.assertContainsInOrder(1, 2)
        }

    }
}