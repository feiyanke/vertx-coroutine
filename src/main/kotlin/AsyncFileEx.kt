import io.vertx.core.buffer.Buffer
import io.vertx.core.file.AsyncFile
import io.vertx.core.streams.Pump
import io.vertx.kotlin.coroutines.awaitResult

suspend fun AsyncFile.aclose() = awaitResult<Void> { close(it) }
suspend fun AsyncFile.awrite(buffer: Buffer, position: Long = 0) = awaitResult<Void> { write(buffer, position, it) }
suspend fun AsyncFile.aread(buffer: Buffer, offset:Int, position: Long, len:Int):Buffer
        = awaitResult<Buffer> { read(buffer, offset, position, len, it) }
suspend fun AsyncFile.aflush() = awaitResult<Void> { flush(it) }
suspend fun AsyncFile.aflush(buffer: Buffer, position: Long = 0) {
    awrite(buffer, position)
    aflush()
}
