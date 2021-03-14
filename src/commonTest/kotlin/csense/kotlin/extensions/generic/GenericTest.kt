package csense.kotlin.extensions.generic

import csense.kotlin.extensions.primitives.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GenericTest {

    class GenericForEach {
        @Test
        fun empty() {
            Generic.forEach(0, { shouldNotBeCalled() }, 0, { shouldNotBeCalled() })
        }

        @Test
        fun badIndexes() {
            Generic.forEach(
                length = 1,
                retriever = { shouldNotBeCalled() },
                startIndex = 42,
                onEach = { shouldNotBeCalled() }
            )
        }

        @Test
        fun single() = assertCalled(times = 2) { shouldBeCalled ->
            Generic.forEach(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                onEach = {
                    it.assert("output")
                    shouldBeCalled()
                })
        }

        @Test
        fun singleWeirdIndex() = assertCalled(times = 2) { shouldBeCalled ->
            Generic.forEach(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output2"

                },
                startIndex = 0,
                onEach = {
                    it.assert("output2")
                    shouldBeCalled()
                })
        }

        @Test
        fun startIndexShouldBeRespected() = assertCalled(times = 2) { shouldBeCalled ->
            Generic.forEach(
                length = 3,
                retriever = {
                    it.assert(2, "start index should be respected")
                    shouldBeCalled()
                    "output"
                },
                startIndex = 2,
                onEach = {
                    shouldBeCalled()
                })
        }


        @Test
        fun multipleMixed() = assertCalled(times = 6) { shouldBeCalled ->
            var retrieverIndex = 0
            Generic.forEach(
                length = 3,
                retriever = {
                    it.assert(retrieverIndex)
                    retrieverIndex += 1
                    shouldBeCalled()
                    "$it"
                },
                startIndex = 0,
                onEach = {
                    val currentRealIndex = retrieverIndex - 1
                    it.assert("$currentRealIndex")
                    shouldBeCalled()
                })
        }
    }


    class GenericMap {
        @Test
        fun empty() {
            Generic.map(
                length = 0,
                retriever = { shouldNotBeCalled() },
                startIndex = 0,
                mapper = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun badIndexes() {
            Generic.map(
                length = 1,
                retriever = { shouldNotBeCalled() },
                startIndex = 42,
                mapper = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun single() = assertCalled(times = 2) { shouldBeCalled ->
            val lst = Generic.map(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                mapper = {
                    it.assert("output")
                    shouldBeCalled()
                    22
                })
            lst.assertSingle(22)
        }

        @Test
        fun singleWeirdIndex() = assertCalled(times = 2) { shouldBeCalled ->
            val lst = Generic.map(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output2"
                },
                startIndex = 0,
                mapper = {
                    it.assert("output2")
                    shouldBeCalled()
                    33
                })
            lst.assertSingle(33)
        }

        @Test
        fun startIndexShouldBeRespected() = assertCalled(times = 2) { shouldBeCalled ->
            Generic.map(
                length = 3,
                retriever = {
                    it.assert(2, "start index should be respected")
                    shouldBeCalled()
                    "output"
                },
                startIndex = 2,
                mapper = {
                    shouldBeCalled()
                    false
                })
        }

        @Test
        fun multipleMixed() = assertCalled(times = 6) { shouldBeCalled ->
            var retrieverIndex = 0
            val lst = Generic.map(
                length = 3,
                retriever = {
                    it.assert(retrieverIndex)
                    retrieverIndex += 1
                    shouldBeCalled()
                    "$it"
                },
                startIndex = 0,
                mapper = {
                    val currentRealIndex = retrieverIndex - 1
                    it.assert("$currentRealIndex")
                    shouldBeCalled()
                    it.toInt()
                })
            lst.assertSize(3)
            lst[0].assert(0)
            lst[1].assert(1)
            lst[2].assert(2)
        }
    }


    class GenericFilter {
        @Test
        fun empty() {
            Generic.filter(
                length = 0,
                retriever = { shouldNotBeCalled() },
                startIndex = 0,
                filterFunction = { shouldNotBeCalled() }
            )
        }

        @Test
        fun badIndexes() {
            Generic.filter(
                length = 1,
                retriever = { shouldNotBeCalled() },
                startIndex = 42,
                filterFunction = { shouldNotBeCalled() }
            ).assertEmpty()
        }

        @Test
        fun singleExclude() = assertCalled(times = 2) { shouldBeCalled ->
            val lst = Generic.filter(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                filterFunction = {
                    it.assert("output")
                    shouldBeCalled()
                    false
                })
            lst.assertEmpty()
        }

        @Test
        fun singleInclude() = assertCalled(times = 2) { shouldBeCalled ->
            val lst = Generic.filter(
                length = 1,
                retriever = {
                    it.assert(0)
                    shouldBeCalled()
                    "output"
                },
                startIndex = 0,
                filterFunction = {
                    it.assert("output")
                    shouldBeCalled()
                    true
                })
            lst.assertSingle("output")
        }

        @Test
        fun multipleMixed() = assertCalled(times = 6) { shouldBeCalled ->
            var retrieverIndex = 0
            val lst = Generic.filter(
                length = 3,
                retriever = {
                    it.assert(retrieverIndex)
                    retrieverIndex += 1
                    shouldBeCalled()
                    "$it"
                },
                startIndex = 0,
                filterFunction = {
                    val currentRealIndex = retrieverIndex - 1
                    it.assert("$currentRealIndex")
                    shouldBeCalled()
                    currentRealIndex.isEven
                })
            lst.assertSize(2)
            lst.first().assert("0")
            lst.last().assert("2")
        }

        @Test
        fun startIndexShouldBeRespected() = assertCalled(times = 2) { shouldBeCalled ->
            Generic.filter(
                length = 3,
                retriever = {
                    it.assert(2, "start index should be respected")
                    shouldBeCalled()
                    "output"
                },
                startIndex = 2,
                filterFunction = {
                    shouldBeCalled()
                    false
                })
        }

    }


}