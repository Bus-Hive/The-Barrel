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

package app.bushive.theBarrel.core.api

import app.bushive.theBarrel.core.api.ApiResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respondText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend inline fun <reified T> ApplicationCall.respondSuccess(
    data: T,
    code: HttpStatusCode = HttpStatusCode.OK,
    message: String? = null,
) {
    respondText(
        contentType = ContentType.Application.Json,
        status = code,
    ) {
        Json.encodeToString(
            ApiResponse.Success(data = data, message = message, code = code.value),
        )
    }
}

suspend fun ApplicationCall.respondError(
    statusCode: HttpStatusCode,
    errorCode: Int,
    message: String,
    details: String? = null,
) {
    respondText(
        text = Json.encodeToString(ApiResponse.Error(errorCode, message, details)),
        contentType = ContentType.Application.Json,
        status = statusCode,
    )
}
