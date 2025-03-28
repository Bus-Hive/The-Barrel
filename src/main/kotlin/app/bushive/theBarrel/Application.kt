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

package app.bushive.theBarrel

import app.bushive.theBarrel.config.configureCache
import app.bushive.theBarrel.config.configureDatabases
import app.bushive.theBarrel.config.configureHTTP
import app.bushive.theBarrel.config.configureMonitoring
import app.bushive.theBarrel.config.configureRouting
import app.bushive.theBarrel.config.configureSerialization
import app.bushive.theBarrel.config.setupConfig
import app.bushive.theBarrel.di.configureKoin
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureKoin()
    configureHTTP()
    configureMonitoring()
    setupConfig()
    configureDatabases()
    configureSerialization()
    configureCache()
    configureRouting()
}
