package software.bottari.dto.response

import software.bottari.dto.exception.ExceptionDto
import software.bottari.dto.exception.InvalidateArgumentExceptionDto
import software.bottari.dto.exception.JSONConvertExceptionDto
import software.bottari.exception.ApiException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.MethodArgumentNotValidException

data class ResponseDto<T>(
    val isSuccess: Boolean,
    val responseDto: T?,
    val error: ExceptionDto?
) {

    constructor(responseDto: T?) : this(
        isSuccess = true,
        responseDto = responseDto,
        error = null
    )

    companion object {

        fun toResponseEntity(e: ApiException): ResponseEntity<*> {
            return ResponseEntity.status(e.error.httpStatus)
                .body(
                    ResponseDto(
                        isSuccess = false,
                        responseDto = null,
                        error = ExceptionDto(e.error)
                    )
                )
        }

        fun toResponseEntity(e: MethodArgumentNotValidException): ResponseEntity<*> {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    ResponseDto(
                        isSuccess = false,
                        responseDto = null,
                        error = InvalidateArgumentExceptionDto(e)
                    )
                )
        }

        fun toResponseEntity(e: HttpMessageConversionException): ResponseEntity<*> {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    ResponseDto(
                        isSuccess = false,
                        responseDto = null,
                        error = JSONConvertExceptionDto(e)
                    )
                )
        }

        fun toResponseEntity(e: Exception): ResponseEntity<*> {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    ResponseDto(
                        isSuccess = false,
                        responseDto = null,
                        error = ExceptionDto(e)
                    )
                )
        }
    }
}