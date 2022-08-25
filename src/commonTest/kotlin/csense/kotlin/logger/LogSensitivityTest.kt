//package csense.kotlin.logger
//
//import kotlin.test.*
//
//class LogSensitivityTest {
//    class LogSensitivityToLogMessageFormatMessage {
//
//        @Test
//        fun LogSensitivityToLogMessageFormatMessage() {
//            val input = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(""), false)
//            val expected = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input
//            val input1 = LogSensitivity.Insensitive.toLogMessageFormat("", arrayOf(""), false)
//            val expected1 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input1
//        }
//
//
//        @Test
//        fun Message() {
//            val input = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(""), false)
//            val expected = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input
//            val input1 = LogSensitivity.Sensitive.toLogMessageFormat(" ", arrayOf(""), false)
//            val expected1 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input1
//            val input2 = LogSensitivity.Sensitive.toLogMessageFormat("a", arrayOf(""), false)
//            val expected2 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input2
//            val input3 = LogSensitivity.Sensitive.toLogMessageFormat("abc", arrayOf(""), false)
//            val expected3 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input3
//            val input4 = LogSensitivity.Sensitive.toLogMessageFormat("1234", arrayOf(""), false)
//            val expected4 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input4
//            val input5 = LogSensitivity.Sensitive.toLogMessageFormat("Other region 한", arrayOf(""), false)
//            val expected5 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input5
//            val input6 = LogSensitivity.Sensitive.toLogMessageFormat("Hi ☺", arrayOf(""), false)
//            val expected6 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input6
//            val input7 = LogSensitivity.Sensitive.toLogMessageFormat("�", arrayOf(""), false)
//            val expected7 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input7
//            val input8 = LogSensitivity.Sensitive.toLogMessageFormat("\n", arrayOf(""), false)
//            val expected8 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input8
//            val input9 = LogSensitivity.Sensitive.toLogMessageFormat("...()[]", arrayOf(""), false)
//            val expected9 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input9
//        }
//
////        class Placeholders {
////
////            @Test
////            fun empty() {
////                val input = arrayOf<kotlin.String>().toLogMessageFormat()
////                val expected = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input
////            }
////
////
////            @Test
////            fun single() {
////                val input = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(""), false)
////                val expected = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input
////            }
////
////
////            @Test
////            fun multiple() {
////                val input = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(""), false)
////                val expected = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input
////                val input1 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(" "), false)
////                val expected1 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input1
////                val input2 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("a"), false)
////                val expected2 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input2
////                val input3 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("abc"), false)
////                val expected3 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input3
////                val input4 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("1234"), false)
////                val expected4 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input4
////                val input5 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("Other region 한"), false)
////                val expected5 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input5
////                val input6 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("Hi ☺"), false)
////                val expected6 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input6
////                val input7 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("�"), false)
////                val expected7 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input7
////                val input8 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("\n"), false)
////                val expected8 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input8
////                val input9 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf("...()[]"), false)
////                val expected9 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
////                input9
////            }
////
////        }
//
//        @Test
//        fun MayLogSensitive() {
//            val input = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(""), false)
//            val expected = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input
//            val input1 = LogSensitivity.Sensitive.toLogMessageFormat("", arrayOf(""), true)
//            val expected1 = LogMessageFormat.InsensitiveValues("", arrayOf(""), LogSensitivity.Sensitive)
//            input1
//        }
//
//    }
//}