/*
 *  Copyright (c) 2025 BusHive <https://bushive.app>
 *
 *  This work is licensed under the Creative Commons Attribution-NonCommercial 4.0
 *  International License. To view a copy of this license, visit
 *  http://creativecommons.org/licenses/by-nc/4.0/ or send a letter to
 *  Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 *
 * The software is provided "as is", without warranty of any kind, express or
 * implied, including but not limited to the warranties of merchantability,
 * fitness for a particular purpose and noninfringement. In no event shall the
 * authors or copyright holders be liable for any claim, damages or other
 * liability, whether in an action of contract, tort or otherwise, arising from,
 * out of or in connection with the software or the use or other dealings in the
 * software.
 */

package app.bushive.theBarrel.config

import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

@Resource("/the-barrel")
class TheBarrel {
    @Resource("/v1")
    class V1(
        val parent: TheBarrel,
    )
}

fun Application.configureRouting() {
    install(Resources)

    docsRoutes()

    routing {
        get("/health") {
            call.respondText("Hello, World!")
        }
    }
}

fun Application.docsRoutes() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        openAPI(path = "the-barrel/docs")
        swaggerUI(path = "the-barrel/docs")
    }
}
