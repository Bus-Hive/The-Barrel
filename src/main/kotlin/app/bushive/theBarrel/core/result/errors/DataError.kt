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

package app.bushive.theBarrel.core.result.errors

import io.ktor.http.HttpStatusCode

sealed interface DataError : Error {
    sealed interface Network : DataError {
        object RequestTimeout : Network {
            override val defaultCode = HttpStatusCode.Companion.RequestTimeout
        }

        object TooManyRequests : Network {
            override val defaultCode = HttpStatusCode.Companion.TooManyRequests
        }

        object NoInternet : Network {
            override val defaultCode = HttpStatusCode.Companion.ServiceUnavailable
        }

        object PayloadTooLarge : Network {
            override val defaultCode = HttpStatusCode.Companion.PayloadTooLarge
        }

        object ServerError : Network {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object Unauthorized : Network {
            override val defaultCode = HttpStatusCode.Companion.Unauthorized
        }

        object Serialization : Network {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object InternalServerError : Network {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object NotFound : Network {
            override val defaultCode = HttpStatusCode.Companion.NotFound
        }

        object BadRequest : Network {
            override val defaultCode = HttpStatusCode.Companion.BadRequest
        }

        object Unknown : Network {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object ConnectionFailed : Network {
            override val defaultCode = HttpStatusCode.Companion.ServiceUnavailable
        }

        object ConnectionAlreadyExists : Network {
            override val defaultCode = HttpStatusCode.Companion.Conflict
        }

        object ConnectionNotEstablished : Network {
            override val defaultCode = HttpStatusCode.Companion.ServiceUnavailable
        }

        object SendFailed : Network {
            override val defaultCode = HttpStatusCode.Companion.BadGateway
        }
    }

    sealed interface Local : DataError {
        object DatabaseConnectionFailed : Local {
            override val defaultCode = HttpStatusCode.Companion.ServiceUnavailable
        }

        object QueryFailed : Local {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object TransactionRollback : Local {
            override val defaultCode = HttpStatusCode.Companion.Conflict
        }

        object ConstraintViolation : Local {
            override val defaultCode = HttpStatusCode.Companion.BadRequest
        }

        object RecordNotFound : Local {
            override val defaultCode = HttpStatusCode.Companion.NotFound
        }

        object DeadlockDetected : Local {
            override val defaultCode = HttpStatusCode.Companion.Conflict
        }

        object Timeout : Local {
            override val defaultCode = HttpStatusCode.Companion.RequestTimeout
        }

        object PermissionDenied : Local {
            override val defaultCode = HttpStatusCode.Companion.Forbidden
        }

        object SerializationError : Local {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object Unknown : Local {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object MappingFailed : Local {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object NullMappingResult : Local {
            override val defaultCode = HttpStatusCode.Companion.InternalServerError
        }

        object InvalidDataType : Local {
            override val defaultCode = HttpStatusCode.Companion.BadRequest
        }

        object FieldMissing : Local {
            override val defaultCode = HttpStatusCode.Companion.BadRequest
        }
    }
}
