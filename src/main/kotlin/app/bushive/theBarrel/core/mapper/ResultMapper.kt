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

package app.bushive.theBarrel.core.mapper

import app.bushive.theBarrel.core.result.Result
import app.bushive.theBarrel.core.result.ResultHandler.fold
import app.bushive.theBarrel.core.result.RootError
import app.bushive.theBarrel.core.result.errors.DataError
import kotlinx.serialization.SerializationException

object ResultMapper {
    inline fun <D, reified E : RootError, R> Result<D, E>.mapResult(transform: (D) -> R?): Result<R, E> =
        fold(
            onSuccess = { value ->
                try {
                    val mappedValue = transform(value)
                    if (mappedValue == null) {
                        Result.Error<R, E>(DataError.Local.NullMappingResult as E)
                    } else {
                        Result.Success<R, E>(mappedValue)
                    }
                } catch (e: SerializationException) {
                    Result.Error<R, E>(DataError.Local.SerializationError as E)
                } catch (e: IllegalArgumentException) {
                    Result.Error<R, E>(DataError.Local.InvalidDataType as E)
                } catch (e: Exception) {
                    Result.Error<R, E>(DataError.Local.MappingFailed as E)
                }
            },
            onFailure = { error ->
                Result.Error<R, E>(error)
            },
        )
}
