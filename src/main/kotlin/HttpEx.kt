import io.vertx.core.buffer.Buffer
import io.vertx.core.http.*
import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import java.io.File

suspend fun HttpServerRequest.body():Buffer {
    return suspendCancellableCoroutine { cont: CancellableContinuation<Buffer> ->
        bodyHandler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}
suspend fun HttpServerRequest.upload(): HttpServerFileUpload {
    isExpectMultipart = true
    return suspendCancellableCoroutine { cont: CancellableContinuation<HttpServerFileUpload> ->
        uploadHandler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}
suspend fun HttpServerRequest.uploadToFile(path:File, name: String? = null) {
    val uploadStream = upload()
    uploadStream.streamToFileSystem(path.resolve(name?:uploadStream.filename()).path)
    return suspendCancellableCoroutine { cont: CancellableContinuation<Unit> ->
        endHandler { cont.resume(Unit) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}

suspend fun HttpClientRequest.response():HttpClientResponse {
    return suspendCancellableCoroutine { cont: CancellableContinuation<HttpClientResponse> ->
        handler { cont.resume(it) }
        exceptionHandler{ cont.resumeWithException(it) }
        end()
    }
}

suspend fun HttpClientResponse.body():Buffer {
    return suspendCancellableCoroutine { cont: CancellableContinuation<Buffer> ->
        bodyHandler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}

suspend fun HttpClientRequest.responseBody():Buffer = response().body()