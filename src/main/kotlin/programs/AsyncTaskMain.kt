package programs

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// scenario of concurrent calls
suspend fun main() {

    // execution time
    val timeA = measureTimeMillis {
        getDataWithoutAsyncPattern()
    }
    println("time = $timeA")

    // execution time in async manner
    val timeB = measureTimeMillis {
        asyncPattern()
    }
    println("async manner time = $timeB")
}

suspend fun getDataWithoutAsyncPattern() {
    coroutineScope {
        println("about to send requests")

        val responseA = getRemoteData("A") // waits to finish and resume when get response A
        val responseB = getRemoteData("B") // waits to finish and resume when get response B

        println("fetched all responses")

        println("response $responseA")
        println("response $responseB")
    }
}

suspend fun asyncPattern() {
    coroutineScope {

        println("about to send async requests")

        // async request to fetch data in concurrent manner
        val requests = listOf(
            async { getRemoteData("A") },
            async { getRemoteData("B") }
        )

        val responses = requests.awaitAll() // resume execution when get all responses

        println("fetched all responses")

        responses.forEach {
            println("response $it")
        }
    }
}

// behaves like network call
suspend fun getRemoteData(value: String) = withContext(Dispatchers.IO) {
    println("get request of $value")
    delay(3000)
    value
}