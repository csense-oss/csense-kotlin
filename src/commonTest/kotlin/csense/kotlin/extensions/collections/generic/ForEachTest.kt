package csense.kotlin.extensions.collections.generic

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


    class genericCollectionExtensionsMapEach2Indexed() {
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
            val indexDataGetter = arrayOf(0, 1).iterator()//TODO csense test thing?
            val indexDataMapper = arrayOf(0, 1).iterator()//TODO csense test thing?
            val getterData = arrayOf(42, 11)

            var getCallCount = 0
            var mapperCallCount = 0
            GenericCollectionExtensions.mapEach2Indexed(
                length = 2,
                getter = {
                    indexDataGetter.next().assert(it)//TODO csense test thing?
                    getCallCount += 1
                    getterData[it]
                },
                mapper = { index, first, second ->
                    indexDataMapper.next().assert(index)
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

    @Test
    fun genericCollectionExtensionsForEachBackwardsIndexed() {
        //TODO make me.

    }

    @Test
    fun genericCollectionExtensionsForEachBackwards() {
        //TODO make me.

    }
}