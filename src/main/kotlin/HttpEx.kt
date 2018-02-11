import io.vertx.core.buffer.Buffer
import io.vertx.core.http.*
import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import java.io.File

suspend fun HttpServerRequest.abody():Buffer {
    return suspendCancellableCoroutine { cont: CancellableContinuation<Buffer> ->
        bodyHandler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}
suspend fun HttpServerRequest.aupload(): HttpServerFileUpload {
    isExpectMultipart = true
    return suspendCancellableCoroutine { cont: CancellableContinuation<HttpServerFileUpload> ->
        uploadHandler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}
suspend fun HttpServerRequest.auploadToFile(path:File, name: String? = null) {
    val uploadStream = aupload()
    uploadStream.streamToFileSystem(path.resolve(name?:uploadStream.filename()).path)
    return suspendCancellableCoroutine { cont: CancellableContinuation<Unit> ->
        endHandler { cont.resume(Unit) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}

suspend fun HttpClientRequest.aresponse():HttpClientResponse {
    return suspendCancellableCoroutine { cont: CancellableContinuation<HttpClientResponse> ->
        handler { cont.resume(it) }
        exceptionHandler{ cont.resumeWithException(it) }
        end()
    }
}

suspend fun HttpClientResponse.abody():Buffer {
    return suspendCancellableCoroutine { cont: CancellableContinuation<Buffer> ->
        bodyHandler { cont.resume(it) }
        exceptionHandler { cont.resumeWithException(it) }
    }
}

suspend fun HttpClientRequest.aresponseBody():Buffer = aresponse().abody()