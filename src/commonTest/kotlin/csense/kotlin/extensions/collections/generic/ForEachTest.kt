package csense.kotlin.extensions.collections.generic

import csense.kotlin.Function1
import csense.kotlin.extensions.primitives.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ForEachTest {
    @Test
    fun genericCollectionExtensionsCanNotForeach2() {
        GenericCollectionExtensions.canNotForeach2(0).assertTrue()
        GenericCollectionExtensions.canNotForeach2(1).assertTrue()
        GenericCollectionExtensions.canNotForeach2(2).assertFalse()
        GenericCollectionExtensions.canNotForeach2(3).assertTrue()
        GenericCollectionExtensions.canNotForeach2(4).assertFalse()
    }


    class GenericCollectionExtensionsMapEach2Indexed {
        @Test
        fun empty() {
            GenericCollectionExtensions.mapEach2Indexed(
                length = 0,
                getter = { shouldNotBeCalled() },
                mapper = { _, _, _ -> shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun single() {
            GenericCollectionExtensions.mapEach2Indexed(
                length = 1,
                getter = { shouldNotBeCalled() },
                mapper = { _, _, _ -> shouldNotBeCalled() }
            ).assertEmpty("cannot map 2 at at time given 1 element")
        }

        @Test
        fun factorOf2() {
            val indexes = listOf(0, 1)
            assertCallbackCalledWith(indexes) { expectedValue ->
                val getterData = arrayOf(42, 11)

                var getCallCount = 0
                var mapperCallCount = 0
                GenericCollectionExtensions.mapEach2Indexed(
                    length = 2,
                    getter = {
                        expectedValue(it)
                        getCallCount += 1
                        getterData[it]
                    },
                    mapper = { index, first, second ->
                        index.assert(0)
                        mapperCallCount += 1
                        first.assert(42)
                        second.assert(11)
                        "wee"
                    }
                ).assertSingle("wee")
                getCallCount.assert(2)
                mapperCallCount.assert(1)
            }
        }
    }

    @Test
    fun genericCollectionExtensionsMapEach2() {
        
        //TODO make me.

    }

    @Test
    fun genericCollectionExtensionsForEach2Indexed() {
        //TODO make me.

    }

    @Test
    fun genericCollectionExtensionsForEach2() {
        //TODO make me.

    }


    class GenericCollectionExtensionsForEachBackwardsIndexed() {
        @Test
        fun empty() {
            GenericCollectionExtensions.forEachBackwardsIndexed(
                length = 0,
                getter = {
                    shouldNotBeCalled()
                },
                action = { _, _ ->
                    shouldNotBeCalled()
                })
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            GenericCollectionExtensions.forEachBackwardsIndexed(
                length = 1,
                getter = {
                    42
                },
                action = { index, item ->
                    shouldBeCalled()
                    index.assert(0)
                    item.assert(42)
                })
        }

        @Test
        fun multiple() {
            val data = listOf(42, 22)
            val reverseIndexed = listOf(1, 0)
            assertCallbackCalledWith(data.reversed()) { expectedValue ->
                assertCallbackCalledWith(reverseIndexed) { expectedIndex ->
                    GenericCollectionExtensions.forEachBackwardsIndexed(
                        length = data.size,
                        getter = {
                            data[it]
                        },
                        action = { index, item ->
                            expectedValue(item)
                            expectedIndex(index)
                        })
                }
            }
        }
    }


    class GenericCollectionExtensionsForEachBackwards() {
        @Test
        fun empty() {
            GenericCollectionExtensions.forEachBackwards(
                length = 0,
                getter = {
                    shouldNotBeCalled()
                },
                action = {
                    shouldNotBeCalled()
                })
        }

        @Test
        fun single() = assertCalled { shouldBeCalled ->
            GenericCollectionExtensions.forEachBackwards(
                length = 1,
                getter = {
                    42
                },
                action = {
                    it.assert(42)
                    shouldBeCalled()
                })
        }

        @Test
        fun multiple() {
            val data = listOf(42, 22)
            assertCallbackCalledWith(data.reversed()) { expectedValue ->
                GenericCollectionExtensions.forEachBackwards(
                    length = data.size,
                    getter = {
                        data[it]
                    },
                    action = {
                        expectedValue(it)
                    })

            }
        }
    }
}