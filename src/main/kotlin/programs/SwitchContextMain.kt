package programs

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/* Coroutine switching context with context
* it runs on main thread and switch context of IO while getting remote data  */

suspend fun main() {
    val time = measureTimeMillis {
        // coroutineScope works same as runBlocking here
        coroutineScope {
            // get data
            println("calling get remote data")
            val data = getRemoteData()

            if (data) {
                println("coroutine get response")
            }

            println("finished")
        }
    }
    // print total execution time here
    println("time = $time")
}

/*
* withContext works with suspend to await of the execution code written within this block */
suspend fun getRemoteData() =
    withContext(Dispatchers.IO) {
        // Blocking network request code
        delay(2000)
        println("coroutine get remote data")
        true
    }
