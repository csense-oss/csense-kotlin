package csense.kotlin.extensions

import csense.kotlin.tests.assertions.*
import kotlin.test.*


class EnumKtTest {


    //region String search for enum

    @Test
    fun enumFromOrNullName() {
        enumFromOrNull<EnumKtTestEnum>("A").assert(EnumKtTestEnum.A)
        enumFromOrNull<EnumKtTestEnum>("AAAA").assertNull()
        enumFromOrNull<EnumKtTestEnum>("B").assert(EnumKtTestEnum.B)
        enumFromOrNull<EnumKtTestEnum>("b").assertNull()
        enumFromOrNull<EnumKtTestEnum>("C").assert(EnumKtTestEnum.C)
        enumFromOrNull<EnumKtTestEnum>("Q").assertNull()
    }

    @Test
    fun enumFromOrName() {
        enumFromOr("A", EnumKtTestEnum.C).assert(EnumKtTestEnum.A)
        enumFromOr("AAAA", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr("B", EnumKtTestEnum.C).assert(EnumKtTestEnum.B)
        enumFromOr("b", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr("C", EnumKtTestEnum.A).assert(EnumKtTestEnum.C)
        enumFromOr("Q", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
    }


    //endregion

    //region Generalized search for enum

    @Test
    fun enumFromOrNullIfNotFound() {
        enumFromOrNull<EnumKtTestEnum>(null) { false }.assertNull()
        enumFromOrNull(EnumKtTestEnum.B) { false }.assert(EnumKtTestEnum.B)
        enumFromOrNull<EnumKtTestEnum>(null) { true }.assert(EnumKtTestEnum.A)
        enumFromOrNull(EnumKtTestEnum.C) { true }.assert(EnumKtTestEnum.A)
        enumFromOrNull<EnumKtTestEnum>(null) { it == EnumKtTestEnum.C }.assert(EnumKtTestEnum.C)
    }

    @Test
    fun enumFromOrIfNotFound() {
        enumFromOr(EnumKtTestEnum.B) { false }.assert(EnumKtTestEnum.B)
        enumFromOr(EnumKtTestEnum.B) { true }.assert(EnumKtTestEnum.A)
        enumFromOr(EnumKtTestEnum.C) { true }.assert(EnumKtTestEnum.A)
    }

    //endregion


    //region Ordinal searches for enum
    @Test
    fun enumFromOrValue() {
        enumFromOr(EnumKtTestEnum.A.ordinal, EnumKtTestEnum.C).assert(EnumKtTestEnum.A)
        enumFromOr(-999, EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr(EnumKtTestEnum.B.ordinal, EnumKtTestEnum.C).assert(EnumKtTestEnum.B)
        enumFromOr("b", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
        enumFromOr(EnumKtTestEnum.C.ordinal, EnumKtTestEnum.A).assert(EnumKtTestEnum.C)
        enumFromOr("Q", EnumKtTestEnum.C).assert(EnumKtTestEnum.C)
    }


    @Test
    fun enumFromOrNullValue() {
        enumFromOrNull<EnumKtTestEnum>(EnumKtTestEnum.A.ordinal).assert(EnumKtTestEnum.A)
        enumFromOrNull<EnumKtTestEnum>(-999).assertNull()
        enumFromOrNull<EnumKtTestEnum>(EnumKtTestEnum.B.ordinal).assert(EnumKtTestEnum.B)
        enumFromOrNull<EnumKtTestEnum>("b").assertNull()
        enumFromOrNull<EnumKtTestEnum>(EnumKtTestEnum.C.ordinal).assert(EnumKtTestEnum.C)
        enumFromOrNull<EnumKtTestEnum>("Q").assertNull()
    }
    //endregion
}

private enum class EnumKtTestEnum {
    A, B, C
}