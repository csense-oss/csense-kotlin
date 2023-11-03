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
        val filename = guessCurrentFileName()
        val filenameAndLineNumber = debugging.getCurrentMethodFileNameAndLineNumber()
        filenameAndLineNumber.assertStartsWith("${filename}:")
    }

    @Test
    fun getCurrentMethodCanonicalName() {
        val currentClassName = getCurrentClassNameCanonical()
        val currentMethodName = this::getCurrentMethodCanonicalName.name
        debugging.getCurrentMethodCanonicalName().assert("$currentClassName.$currentMethodName")
    }

    @Test
    fun getCurrentMethodInformation() {
        val currentClassName = getCurrentClassNameCanonical()
        val currentMethodName = this::getCurrentMethodInformation.name
        val currentFileName = guessCurrentFileName()
        val info = debugging.getCurrentMethodInformation()

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
        val first = debugging
        val second = debugging
        (first != second).assertTrue("Should have different references")
        (second != first).assertTrue("Should have different references")
    }
}

class DebuggingTest {

    class Companion {
        @OptIn(ExperimentalCsenseApi::class)
        @Test
        fun fromCallingMethod() {
            val testMethod = Debugging.fromCallingMethod()
            testMethod.getCurrentMethodName().assert(::fromCallingMethod.name)
            testMethod.getCurrentMethodInformation().assertStartsWith(DebuggingTest::class.qualifiedName!!)
        }
    }

}