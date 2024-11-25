package dto.exception

import exception.ErrorDefine
import org.springframework.http.converter.HttpMessageConversionException

data class JSONConvertExceptionDto(
    override val message: String,
    val cause: String
) : ExceptionDto(ErrorDefine.INVALID_ARGUMENT) {

    constructor(jsonException: HttpMessageConversionException) : this(
        message = jsonException.message ?: "No message available",
        cause = jsonException.cause?.toString() ?: "Non-Throwable Cause"
    )
}