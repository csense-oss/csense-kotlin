import csense.kotlin.logger.*
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {

    if (true) {
        CL.enableSensitiveLogging()
    }

//    CL.printLogsToConsole()
    CL.printLogsToConsoleAnsiColored()


//    async {
//        CL.allLoggers.filter { it.doesNotContainSensitiveInformation() }.collect {
//            delay(1000)
//
//        }
//    }
//    async {
//        CL.allLoggers.collect {
//            val sensitive = it.throwable?.toSensitiveStackTraceString()
//            println("got sensitive")
//            println("$sensitive")
//        }
//    }

//    async {
//        val sharedIn = CLogger.debugLoggers.shareIn(
//            this,
//            SharingStarted.Eagerly,
//            3
//        )
//        sharedIn.collect {
//            println("sharedInCache = ${sharedIn.replayCache.size}")
//        }
//
//
//    }
//    async {
//        while (true) {
//            CL.logDebug(
//                "MyTag",
//                "MyMessage using Person identifier {}",
//                "000000-0000",
//                //   throwable = RuntimeException()
//            )
//            delay(3000)
//        }
//    }

//    logClassDebug("Message {}", "someMessage")
    async {
        while (true) {
            logClassDebug("Message {} {}", "someMessage", throwable = RuntimeException("sensitive information potentially"))
//            CLogger.logDebug(
//                "FavoriteFood",
//                "NonPersonal info: meal type={}",
//                "meat",
//                sensitivity = LogSensitivity.Insensitive
//                //   throwable = RuntimeException()
//            )
            delay(3000)
        }
    }
}
