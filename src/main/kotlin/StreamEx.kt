import io.vertx.core.streams.Pump
import io.vertx.core.streams.ReadStream
import io.vertx.core.streams.WriteStream
import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.suspendCancellableCoroutine

suspend fun <T> ReadStream<T>.aread() : T {
    return suspendCancellableCoroutine { cont: CancellableContinuation<T> ->
        handler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}
suspend fun <T> ReadStream<T>.awriteTo(ws: WriteStream<T>) {
    return suspendCancellableCoroutine { cont: CancellableContinuation<Unit> ->
        endHandler { cont.resume(Unit) }
        exceptionHandler { cont.resumeWithException(it) }
        Pump.pump(this, ws).start()
    }
}