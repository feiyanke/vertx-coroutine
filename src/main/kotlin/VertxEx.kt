import io.vertx.core.*
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.coroutines.awaitEvent
import io.vertx.kotlin.coroutines.awaitResult
import java.util.function.Supplier

suspend fun Vertx.adelay(ms:Long) = awaitEvent<Long> { setTimer(ms, it) }
suspend fun Vertx.adeploy(verticle: Verticle, option:DeploymentOptions = DeploymentOptions()) = awaitResult<String> {
    deployVerticle(verticle, option, it)
}
suspend fun Vertx.adeploy(verticle:String, option: DeploymentOptions = DeploymentOptions()) = awaitResult<String> {
    deployVerticle(verticle, option, it)
}
suspend fun Vertx.adeploy(verticleSupplier:Supplier<Verticle>, option: DeploymentOptions = DeploymentOptions()) = awaitResult<String> {
    deployVerticle(verticleSupplier, option, it)
}
suspend fun <T:Verticle> Vertx.adeploy(verticleClass: Class<T>, option: DeploymentOptions = DeploymentOptions()) = awaitResult<String> {
    deployVerticle(verticleClass, option, it)
}
suspend fun Vertx.aundeploy(deployId:String) = awaitResult<Void> {
    undeploy(deployId, it)
}
suspend fun <T> Vertx.arunBlocking(order:Boolean = true, block:()->T) = awaitResult<T> {
    executeBlocking(Handler<Future<T>> { f ->
        try {
            f.complete(block())
        } catch (e:Throwable) {
            f.fail(e)
        }
    }, order, it)
}