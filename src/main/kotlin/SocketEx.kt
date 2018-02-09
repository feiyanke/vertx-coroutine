import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpClient
import io.vertx.core.http.HttpClientResponse
import io.vertx.core.net.NetClient
import io.vertx.core.net.NetSocket
import io.vertx.core.net.SocketAddress
import io.vertx.core.streams.ReadStream
import io.vertx.kotlin.coroutines.awaitEvent
import io.vertx.kotlin.coroutines.awaitResult
import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.suspendCancellableCoroutine

suspend fun NetClient.connect(port:Int, host:String, name:String? = null)
        = awaitResult<NetSocket> { connect(port, host, name, it) }
suspend fun NetClient.connect(address: SocketAddress, name: String? = null)
        = awaitResult<NetSocket> { connect(address, name, it) }
suspend fun <T> ReadStream<T>.read() : T {
    return suspendCancellableCoroutine { cont: CancellableContinuation<T> ->
        handler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}