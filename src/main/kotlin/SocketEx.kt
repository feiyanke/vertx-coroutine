import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.datagram.DatagramSocket
import io.vertx.core.http.HttpClient
import io.vertx.core.http.HttpClientResponse
import io.vertx.core.net.NetClient
import io.vertx.core.net.NetSocket
import io.vertx.core.net.SocketAddress
import io.vertx.core.streams.Pump
import io.vertx.core.streams.ReadStream
import io.vertx.core.streams.WriteStream
import io.vertx.kotlin.coroutines.awaitEvent
import io.vertx.kotlin.coroutines.awaitResult
import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.suspendCancellableCoroutine

suspend fun NetClient.aconnect(port:Int, host:String, name:String? = null)
        = awaitResult<NetSocket> { connect(port, host, name, it) }
suspend fun NetClient.aconnect(address: SocketAddress, name: String? = null)
        = awaitResult<NetSocket> { connect(address, name, it) }
suspend fun DatagramSocket.asend(port: Int, host: String, buffer: Buffer):DatagramSocket
        = awaitResult { send(buffer, port, host, it) }
suspend fun DatagramSocket.asend(port: Int, host: String, str: String, encode:String = "UTF-8"):DatagramSocket
        = awaitResult { send(str, port, host, it) }
suspend fun DatagramSocket.aclose() = awaitResult<Void> { close(it) }
suspend fun DatagramSocket.alisten(port: Int, host: String):DatagramSocket = awaitResult { listen(port, host, it) }
suspend fun DatagramSocket.alistenMulticast(addr:String):DatagramSocket
        = awaitResult { listenMulticastGroup(addr, it) }
suspend fun DatagramSocket.aunlistenMulticast(addr:String):DatagramSocket
        = awaitResult { unlistenMulticastGroup(addr, it) }
suspend fun DatagramSocket.alistenMulticast(addr:String, networkInterface:String, source:String):DatagramSocket
        = awaitResult { listenMulticastGroup(addr, networkInterface, source, it) }
suspend fun DatagramSocket.aunlistenMulticast(addr:String, networkInterface:String, source:String):DatagramSocket
        = awaitResult { unlistenMulticastGroup(addr, networkInterface, source, it) }
suspend fun DatagramSocket.ablockMulticast(addr:String, source: String):DatagramSocket
        = awaitResult { blockMulticastGroup(addr, source, it) }
suspend fun DatagramSocket.ablockMulticast(addr:String, networkInterface: String, source: String):DatagramSocket
        = awaitResult { blockMulticastGroup(addr, networkInterface, source, it) }


