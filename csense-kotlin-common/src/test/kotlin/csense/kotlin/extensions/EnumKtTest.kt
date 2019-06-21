package csense.kotlin.extensions

import csense.kotlin.test.assertions.*
import kotlin.test.*


class EnumKtTest {


    @Test
    fun enumFromOrString() {
        enumFromOr("A", EnumKtTestEnum.C).assert(EnumKtTestEnum.A)
        enumFromOr("AAAA", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr("B", EnumKtTestEnum.C).assert(EnumKtTestEnum.B)
        enumFromOr("b", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr("C", EnumKtTestEnum.A).assert(EnumKtTestEnum.C)
        enumFromOr("Q", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
    }

    @Test
    fun enumFromOrInt() {
        enumFromOr(EnumKtTestEnum.A.ordinal, EnumKtTestEnum.C).assert(EnumKtTestEnum.A)
        enumFromOr(-999, EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr(EnumKtTestEnum.B.ordinal, EnumKtTestEnum.C).assert(EnumKtTestEnum.B)
        enumFromOr("b", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr(EnumKtTestEnum.C.ordinal, EnumKtTestEnum.A).assert(EnumKtTestEnum.C)
        enumFromOr("Q", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
    }
}

enum class EnumKtTestEnum {
    A, B, C
}