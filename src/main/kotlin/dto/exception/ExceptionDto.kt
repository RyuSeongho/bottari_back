package dto.exception

import exception.ErrorDefine
import org.springframework.http.HttpStatus

open class ExceptionDto(
    val code: String,
    open val message: String
) {
    constructor(errorDefine: ErrorDefine) : this(
        code = errorDefine.errorCode,
        message = errorDefine.message
    )

    constructor(e: Exception) : this(
        code = HttpStatus.INTERNAL_SERVER_ERROR.value().toString(),
        message = e.message ?: "Unknown error"
    )
}