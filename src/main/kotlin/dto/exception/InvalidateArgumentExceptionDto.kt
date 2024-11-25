package dto.exception

import exception.ErrorDefine
import org.springframework.web.bind.MethodArgumentNotValidException

data class InvalidateArgumentExceptionDto(
    val errorFields: Map<String, String>
) : ExceptionDto(ErrorDefine.INVALID_ARGUMENT) {

    constructor(invalidException: MethodArgumentNotValidException) : this(
        errorFields = invalidException.bindingResult.fieldErrors
            .associate { it.field to (it.defaultMessage ?: "No message available") }
    )
}