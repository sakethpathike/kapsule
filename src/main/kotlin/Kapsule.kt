package sakethh

import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.HTML

suspend inline fun RoutingCall.respondWithKapsule(crossinline init: HTML.() -> Unit) {
    respondHtml {
        init()
    }
}
