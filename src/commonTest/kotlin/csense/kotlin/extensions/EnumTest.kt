package csense.kotlin.extensions

import csense.kotlin.tests.assertions.assert
import csense.kotlin.tests.assertions.assertNotNullAndEquals
import csense.kotlin.tests.assertions.assertNull
import kotlin.test.Test


class EnumKtTest {


    //region String search for enum

    @Test
    fun enumFromOrNullName() {
        enumFromOrNull<EnumKtTestEnum>("A").assertNotNullAndEquals(EnumKtTestEnum.A)
        enumFromOrNull<EnumKtTestEnum>("AAAA").assertNull()
        enumFromOrNull<EnumKtTestEnum>("B").assertNotNullAndEquals(EnumKtTestEnum.B)
        enumFromOrNull<EnumKtTestEnum>("b").assertNull()
        enumFromOrNull<EnumKtTestEnum>("C").assertNotNullAndEquals(EnumKtTestEnum.C)
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
        enumFromOrNull<EnumKtTestEnum>(null, { false }).assertNull()
        enumFromOrNull(EnumKtTestEnum.B, { false }).assertNotNullAndEquals(EnumKtTestEnum.B)
        enumFromOrNull<EnumKtTestEnum>(null, { true }).assertNotNullAndEquals(EnumKtTestEnum.A)
        enumFromOrNull(EnumKtTestEnum.C, { true }).assertNotNullAndEquals(EnumKtTestEnum.A)
        enumFromOrNull<EnumKtTestEnum>(null, { it == EnumKtTestEnum.C }).assertNotNullAndEquals(EnumKtTestEnum.C)
    }

    @Test
    fun enumFromOrIfNotFound() {
        enumFromOr(EnumKtTestEnum.B, { false }).assert(EnumKtTestEnum.B)
        enumFromOr(EnumKtTestEnum.B, { true }).assertNotNullAndEquals(EnumKtTestEnum.A)
        enumFromOr(EnumKtTestEnum.C, { true }).assertNotNullAndEquals(EnumKtTestEnum.A)
    }

    //endregion


    //region Ordinal search for enum
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
        enumFromOrNull<EnumKtTestEnum>(EnumKtTestEnum.A.ordinal).assertNotNullAndEquals(EnumKtTestEnum.A)
        enumFromOrNull<EnumKtTestEnum>(-999).assertNull()
        enumFromOrNull<EnumKtTestEnum>(EnumKtTestEnum.B.ordinal).assertNotNullAndEquals(EnumKtTestEnum.B)
        enumFromOrNull<EnumKtTestEnum>("b").assertNull()
        enumFromOrNull<EnumKtTestEnum>(EnumKtTestEnum.C.ordinal).assertNotNullAndEquals(EnumKtTestEnum.C)
        enumFromOrNull<EnumKtTestEnum>("Q").assertNull()
    }
    //endregion
}

private enum class EnumKtTestEnum {
    A, B, C
}