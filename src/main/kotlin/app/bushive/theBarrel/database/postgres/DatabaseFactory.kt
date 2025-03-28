package app.bushive.theBarrel.database.postgres

import app.bushive.theBarrel.core.result.errors.DataError
import org.jetbrains.exposed.sql.Database
import app.bushive.theBarrel.core.result.Result

interface DatabaseFactory {
    fun connect()

    fun close()

    suspend fun <T> dbQuery(block: suspend () -> T?): Result<T, DataError.Local>

    var database: Database
}
