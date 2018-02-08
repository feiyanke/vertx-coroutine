import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.Message
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.kotlin.coroutines.awaitEvent
import io.vertx.kotlin.coroutines.awaitResult

suspend fun <T> EventBus.await(event:String):Message<T> = awaitEvent { consumer(event, it) }
suspend fun <T> MessageConsumer<T>.await() = awaitEvent<Message<T>> { handler(it) }
suspend fun <T> EventBus.register(event: String)
        = consumer<T>(event).apply { awaitResult<Void> { completionHandler(it) } }
suspend fun <T> MessageConsumer<T>.unregister() = awaitResult<Void> { unregister(it) }
suspend fun <T> EventBus.request(event: String, message:Any, option:DeliveryOptions = DeliveryOptions()):Message<T>
        = awaitResult<Message<T>> { send(event, message, it) }
suspend fun <T> EventBus.request(event: String, message:Any, timeout:Long):Message<T>
        = request(event, message, DeliveryOptions().apply { sendTimeout = timeout })