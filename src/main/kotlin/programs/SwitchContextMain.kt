package programs

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 Coroutine switching context withContext -> launch and async create new coroutines. withContext does not create a new coroutine, it only shifts the context of the existing coroutine, which is why it's a suspend function (unlike launch and async).
it runs on main thread and switch context of IO while getting remote data

- Following Dispatchers you can put in withContext according to you following operation

Dispatchers.Main - Use this dispatcher to run a coroutine on the main Android thread. This should be used only for interacting with the UI and performing quick work. Examples include calling suspend functions, running Android UI framework operations, and updating LiveData objects.
Dispatchers.IO - This dispatcher is optimized to perform disk or network I/O outside of the main thread. Examples include using the Room component, reading from or writing to files, and running any network operations.
Dispatchers.Default - This dispatcher is optimized to perform CPU-intensive work outside of the main thread. Example use cases include sorting a list and parsing JSON. **/

suspend fun main() {

    val time = measureTimeMillis {
        // coroutineScope works same as runBlocking here it will not terminate the program before finishing the execution of the coroutineScope
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


suspend fun getRemoteData() = withContext(Dispatchers.IO) {
    // Blocking network request code
    delay(2000)
    println("coroutine get remote data")
    true
}
