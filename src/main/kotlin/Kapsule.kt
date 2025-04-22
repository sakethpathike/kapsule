package sakethh

import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.HTML

suspend fun RoutingCall.respondWithKapsule(init: HTML.() -> Unit) {
    respondHtml {
        init()
    }
}
