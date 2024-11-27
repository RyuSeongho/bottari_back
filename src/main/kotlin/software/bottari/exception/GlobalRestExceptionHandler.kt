package software.bottari.exception

import software.bottari.dto.response.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalRestExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<*> {
        return ResponseDto.toResponseEntity(e)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidArgumentException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        return ResponseDto.toResponseEntity(e)
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun handleJSONConversionException(e: HttpMessageConversionException): ResponseEntity<*> {
        return ResponseDto.toResponseEntity(e)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<*> {
        return ResponseDto.toResponseEntity(e)
    }
}