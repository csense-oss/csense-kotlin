package csense.kotlin.extensions.collections.generic

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class SharedCollectionTest {


    class GenericCollectionExtensionsJoinEveryAction {
        @Test
        fun empty() = assertCalled { shouldBeCalled ->
            GenericCollectionExtensions.joinEveryAction<String, String>(
                itemsBetweenJoins = 0,
                toJoinAction = { shouldNotBeCalled() },
                size = 0,
                getter = { shouldNotBeCalled() },
                builderType = { size, _ ->
                    size.assert(0)
                    shouldBeCalled()
                    ""
                }
            )
        }

        @Test
        fun singleBadJoins() = assertCalled { shouldBeCalled ->
            GenericCollectionExtensions.joinEveryAction<String, String>(
                itemsBetweenJoins = 0,
                toJoinAction = { shouldNotBeCalled() },
                size = 1,
                getter = { shouldNotBeCalled() },
                builderType = { _, _ ->
                    shouldBeCalled()
                    ""
                }
            )
        }

        @Test
        fun single() = assertCalled(times = 2) { shouldBeCalled ->
            val result = GenericCollectionExtensions.joinEveryAction(
                itemsBetweenJoins = 1,
                toJoinAction = { shouldNotBeCalled() },
                size = 1,
                getter = {
                    it.assert(0)
                    shouldBeCalled()
                    "res"
                },
                builderType = { size, getter ->
                    size.assert(1)
                    getter(0).assert("res")
                    shouldBeCalled()
                    "output"
                }
            )
            result.assert("output")
        }

        @Test
        fun multipleWithJoin() = assertCalled(times = 4) { shouldBeCalled ->
            val result = GenericCollectionExtensions.joinEveryAction(
                itemsBetweenJoins = 1,
                toJoinAction = {
                    shouldBeCalled()
                    "join"
                },
                size = 2,
                getter = {
                    shouldBeCalled()
                    "res"
                },
                builderType = { size, getter ->
                    size.assert(3)
                    getter(0).assert("res")
                    getter(1).assert("join")
                    getter(2).assert("res")
                    shouldBeCalled()
                    "output"
                }
            )
            result.assert("output")
        }
    }
}