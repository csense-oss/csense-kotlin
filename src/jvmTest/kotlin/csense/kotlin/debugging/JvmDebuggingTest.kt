package csense.kotlin.debugging

import csense.kotlin.annotations.*
import csense.kotlin.tests.assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCsenseApi::class)
class JvmDebuggingTest {

    @Test
    fun getCurrentMethodName() {
        val name = this::getCurrentMethodName.name
        debugging.getCurrentMethodName().assert(name)
    }

    @Test
    fun getCurrentMethodLineNumber() {
        //This is EXTREMLY flaky, so just "verify" it roughly
        debugging.getCurrentMethodLineNumber().assertLargerThan(0)
    }

    @Test
    fun getCurrentMethodFileName() {
        //This is somewhat flaky, so just "verify" it roughly
        debugging.getCurrentMethodFileName().assert(guessCurrentFileName())
    }

    @Test
    fun getCurrentMethodFileNameAndLineNumber() {
        //This is EXTREMLY flaky, so just "verify" it roughly
        val filename: String = guessCurrentFileName()
        val filenameAndLineNumber: String = debugging.getCurrentMethodFileNameAndLineNumber()
        filenameAndLineNumber.assertStartsWith("${filename}:")
    }

    @Test
    fun getCurrentMethodCanonicalName() {
        val currentClassName: String = getCurrentClassNameCanonical()
        val currentMethodName: String = this::getCurrentMethodCanonicalName.name
        debugging.getCurrentMethodCanonicalName().assert("$currentClassName.$currentMethodName")
    }

    @Test
    fun getCurrentMethodInformation() {
        val currentClassName: String = getCurrentClassNameCanonical()
        val currentMethodName: String = this::getCurrentMethodInformation.name
        val currentFileName: String = guessCurrentFileName()
        val info: String = debugging.getCurrentMethodInformation()

        //info should have the format: $classname.$methodName($filename:$lineNumber)
        info.assertStartsWith("$currentClassName.$currentMethodName($currentFileName:")
        info.assertEndsWith(")")
    }

    private fun getCurrentClassNameSimple(): String = this::class.simpleName ?: ""
    private fun getCurrentClassNameCanonical(): String = this::class.qualifiedName ?: ""
    private fun guessCurrentFileName(): String = guessFileNameFromClassName(getCurrentClassNameSimple())

    private fun guessFileNameFromClassName(className: String): String {
        return "$className.kt"
    }

    @Test
    fun debugging() {
        val first: Debugging = debugging
        val second: Debugging = debugging
        (first != second).assertTrue("Should have different references")
        (second != first).assertTrue("Should have different references")
    }
}

class DebuggingTest {

    class Companion {
        @OptIn(ExperimentalCsenseApi::class)
        @Test
        fun fromCallingMethod() {
            val testMethod: Debugging = Debugging.fromCallingMethod()
            testMethod.getCurrentMethodName().assert(::fromCallingMethod.name)
            testMethod.getCurrentMethodInformation().assertStartsWith(DebuggingTest::class.qualifiedName!!)
        }
    }

}