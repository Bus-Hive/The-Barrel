package app.bushive.theBarrel.config

import app.bushive.theBarrel.database.postgres.DatabaseFactory
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Application.configureDatabases() {
    val dbFactory by inject<DatabaseFactory>()
    dbFactory.connect()
}
