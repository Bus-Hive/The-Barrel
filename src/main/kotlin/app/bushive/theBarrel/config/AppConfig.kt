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

import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

data class ServerConfig(
    val isProd: Boolean,
)

data class PostgresConfig(
    val driverClass: String,
    val jdbcUrl: String,
    val host: String,
    val port: Int,
    val database: String,
    val user: String,
    val password: String,
    val maxPoolSize: Int,
    val autoCommit: Boolean,
)

data class RedisConfig(
    val host: String,
    val port: Int,
    val password: String,
    val database: Int,
)

data class GTFSRealtimeConfig(
    val url: String,
    val apiKey: String,
)

class AppConfig {
    lateinit var serverConfig: ServerConfig
    lateinit var postgresConfig: PostgresConfig
    lateinit var redisConfig: RedisConfig
    lateinit var gtfsRealtimeConfig: GTFSRealtimeConfig
}

fun Application.setupConfig() {
    val appConfig by inject<AppConfig>()

    // Server
    val serverObject = environment.config.config("ktor.server")
    val isProd = serverObject.property("isProd").getString().toBoolean()
    appConfig.serverConfig = ServerConfig(isProd)

    // Postgres
    val driverClass = environment.config.property("postgres.driverClass").getString()
    val host = environment.config.property("postgres.host").getString()
    val port =
        environment.config
            .property("postgres.port")
            .getString()
            .toInt()
    val database = environment.config.property("postgres.database").getString()
    val jdbcUrl = environment.config.property("postgres.jdbcURL").getString()
    val user = environment.config.property("postgres.user").getString()
    val password = environment.config.property("postgres.password").getString()
    val maxPoolSize =
        environment.config
            .property("postgres.maxPoolSize")
            .getString()
            .toInt()
    val autoCommit =
        environment.config
            .property("postgres.autoCommit")
            .getString()
            .toBoolean()

    appConfig.postgresConfig =
        PostgresConfig(
            driverClass = driverClass,
            jdbcUrl = jdbcUrl,
            host = host,
            port = port,
            database = database,
            user = user,
            password = password,
            maxPoolSize = maxPoolSize,
            autoCommit = autoCommit,
        )

    // Redis
    val redisHost = environment.config.property("redis.host").getString()
    val redisPort =
        environment.config
            .property("redis.port")
            .getString()
            .toInt()
    val redisPassword = environment.config.property("redis.password").getString()
    val redisDatabase =
        environment.config
            .property("redis.database")
            .getString()
            .toInt()

    appConfig.redisConfig =
        RedisConfig(
            host = redisHost,
            port = redisPort,
            password = redisPassword,
            database = redisDatabase,
        )

    // GTFS Realtime
    val gtfsRealtimeUrl = environment.config.property("gtfsRealtime.url").getString()
    val gtfsRealtimeApiKey = environment.config.property("gtfsRealtime.apiKey").getString()

    appConfig.gtfsRealtimeConfig = GTFSRealtimeConfig(
        url = gtfsRealtimeUrl,
        apiKey = gtfsRealtimeApiKey,
    )
}
